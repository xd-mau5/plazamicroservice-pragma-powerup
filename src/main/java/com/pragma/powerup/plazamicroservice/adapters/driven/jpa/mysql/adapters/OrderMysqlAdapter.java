package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapters;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.MessageNotSendException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.OrderDoesntExistException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IOrdersEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IOrdersRepository;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.plazamicroservice.adapters.driven.mongo.mapper.ITractabilityEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.mongo.repository.ITractabilityRepository;
import com.pragma.powerup.plazamicroservice.domain.model.Orders;
import com.pragma.powerup.plazamicroservice.domain.model.Tractability;
import com.pragma.powerup.plazamicroservice.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static com.pragma.powerup.plazamicroservice.configuration.Constants.*;

@RequiredArgsConstructor
public class OrderMysqlAdapter implements IOrderPersistencePort {
    private final IOrdersRepository ordersRepository;
    private final IOrdersEntityMapper ordersEntityMapper;
    private final IUserRepository userRepository;
    private final IRestaurantRepository restaurantRepository;
    private final ITractabilityRepository tractabilityRepository;
    private final ITractabilityEntityMapper tractabilityEntityMapper;
    private final RestTemplate restTemplate;

    @Override
    public void saveOrder(Orders orders) {
        if (orders == null) {
            throw new IllegalArgumentException("Orders can't be null");
        }
        if (orders.getId() != null) {
            throw new IllegalArgumentException("Orders id can't be null");
        }
        if (ordersRepository.findByUserEntityIdAndStatus(orders.getUserId(), INITIAL_STATUS).isPresent()) {
            throw new IllegalArgumentException("User already has an order pending");
        }
        if (orders.getRestaurantId() == null) {
            throw new IllegalArgumentException("Restaurant id can't be null");
        }
        RestaurantEntity restaurantEntity = restaurantRepository.findById(orders.getRestaurantId())
                .orElseThrow(() -> new NullPointerException("Restaurant doesn't exist"));
        ordersEntityMapper.toEntity(orders).setRestaurantEntity(restaurantEntity);
        orders.setRestaurantId(restaurantEntity.getId());
        orders.setStatus(INITIAL_STATUS);
        orders.setChefId(restaurantEntity.getUserEntity().getId());
        ordersRepository.save(ordersEntityMapper.toEntity(orders));
    }

    @Override
    public void updateOrder(Long idOrder, String status) {
        if (idOrder == null) {
            throw new IllegalArgumentException(ORDER_ID_CANNOT_BE_NULL_ERROR);
        }
        if (status == null) {
            throw new IllegalArgumentException("Status can't be null");
        }
        if (ordersRepository.findById(idOrder).isEmpty()) {
            throw new IllegalArgumentException("Orders doesn't exist");
        }
        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(OrderDoesntExistException::new);
        sendTractabilityToMongo(idOrder, status);
        orderEntity.setStatus(status);
        ordersRepository.save(orderEntity);
    }

    @Override
    //TODO: Arreglar la salida de las entidades.
    public List<Orders> getAllOrdersByStatus(Long restaurantId, String status, Integer page, Integer size) {
        if (restaurantId == null || status == null || page == null || size == null) {
            throw new IllegalArgumentException(NULL_PARAMETER_ERROR);
        }
        if (page < 0 || size < 0) {
            throw new IllegalArgumentException("Invalid page or size");
        }
        if (restaurantRepository.findById(restaurantId).isEmpty()) {
            throw new IllegalArgumentException("Restaurant doesn't exist");
        }
        Specification<OrderEntity> statusSpecification = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);

        Specification<OrderEntity> restaurantSpecification = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("restaurantEntity").get("id"), restaurantId);

        Specification<OrderEntity> combinedSpecification = Specification.where(restaurantSpecification)
                .and(statusSpecification);

        Pageable pagination = PageRequest.of(page, size);

        Page<OrderEntity> orderPage = ordersRepository.findAll(combinedSpecification, pagination);
        return orderPage.map(ordersEntityMapper::toOrders).toList();
    }

    @Override
    public List<Orders> setOrderToEmployee(Long idOrder, Long idEmployee, String status, Integer page, Integer size) {
        /*
        TODO: Arreglar la salida de las entidades.
         */
        if (idOrder == null || idEmployee == null || status == null) {
            throw new IllegalArgumentException(NULL_PARAMETER_ERROR);
        }
        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(OrderDoesntExistException::new);

        UserEntity chefEntity = userRepository.findById(idEmployee)
                .orElseThrow(() -> new IllegalArgumentException("Employee doesn't exist"));
        sendTractabilityToMongo(idOrder,status);
        orderEntity.setStatus(status);
        orderEntity.setChefEntity(chefEntity);
        ordersRepository.save(orderEntity);
        return getAllOrdersByStatus(orderEntity.getRestaurantEntity().getId(), status, page, size);
    }
    public void checkIfOrderIsEmpty(Long idOrder){
        if (ordersRepository.findById(idOrder).isEmpty()) {
            throw new OrderDoesntExistException();
        }
    }

    @Override
    public String generateSecurityCode(Long idOrder, String status) {
        if (idOrder == null) {
            throw new IllegalArgumentException(ORDER_ID_CANNOT_BE_NULL_ERROR);
        }
        if (status == null) {
            throw new IllegalArgumentException("Status can't be null");
        }
        checkIfOrderIsEmpty(idOrder);
        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(OrderDoesntExistException::new);
        long orderDateUnixTime = orderEntity.getOrderDate().toInstant().getEpochSecond();
        long idSum = orderEntity.getId() + orderEntity.getRestaurantEntity().getId();
        long securityCode = orderDateUnixTime + idSum;
        return Long.toString(securityCode);
    }

    public String createMessageOrderReady(Long idOrder) {
        if (idOrder == null) {
            throw new IllegalArgumentException(PARAMETER_CANNOT_BE_NULL_ERROR);
        }
        checkIfOrderIsEmpty(idOrder);
        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(OrderDoesntExistException::new);
        if (!orderEntity.getStatus().equals("Listo")) {
            throw new IllegalArgumentException("Order isn't ready");
        }
        return "Su pedido está listo para ser retirado.\n" +
                "El N° de pedido es: " + orderEntity.getId() + "\n" +
                "El código de seguridad es: " + generateSecurityCode(orderEntity.getId(), orderEntity.getStatus());
    }

    @Override
    public boolean checkSecurityCode(Long idOrder, String securityCode) {
        if (idOrder == null || securityCode == null) {
            throw new IllegalArgumentException(PARAMETER_CANNOT_BE_NULL_ERROR);
        }
        if (ordersRepository.findById(idOrder).isEmpty()) {
            throw new OrderDoesntExistException();
        }
        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(OrderDoesntExistException::new);

        long orderDateUnixTime = orderEntity.getOrderDate().toInstant().getEpochSecond();
        long idSum = orderEntity.getId() + orderEntity.getRestaurantEntity().getId();
        long securityCodeGenerated = orderDateUnixTime + idSum;
        return securityCode.equals(Long.toString(securityCodeGenerated));
    }

    @Override
    public void sendMessageToUser(Long idOrder) {
        String messageApiUrl = "http://xd-mau5.xyz:8093";
        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(OrderDoesntExistException::new);
        String message = createMessageOrderReady(idOrder);
        String receiver = orderEntity.getUserEntity().getPersonEntity().getPhone();
        String escapedMessage = StringEscapeUtils.escapeJson(message);
        String escapedReceiver = StringEscapeUtils.escapeJson(receiver);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\"message\":\"" + escapedMessage
                + "\",\"receiver\":\"" + escapedReceiver + "\"}";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate
                .postForEntity(messageApiUrl + "/message/send", request, String.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new MessageNotSendException();
        }
    }

    @Override
    public void deliverOrder(Long idOrder, String securityCode) {
        if (idOrder == null) {
            throw new IllegalArgumentException(ORDER_ID_CANNOT_BE_NULL_ERROR);
        }
        checkIfOrderIsEmpty(idOrder);
        if (!checkSecurityCode(idOrder, securityCode)) {
            throw new IllegalArgumentException("Invalid security code");
        }
        if (!ordersRepository.findById(idOrder).get().getStatus().equals("Listo")) {
            throw new IllegalArgumentException("Order isn't ready");
        }
        if (ordersRepository.findById(idOrder).get().getStatus().equals(DELIVERED_STATUS)) {
            throw new IllegalArgumentException("Order is already delivered");
        }
        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(OrderDoesntExistException::new);
        sendTractabilityToMongo(idOrder,DELIVERED_STATUS);
        orderEntity.setStatus(DELIVERED_STATUS);
        ordersRepository.save(orderEntity);
    }

    @Override
    public String cancelOrder(Long idOrder) {
        if (idOrder == null) {
            throw new IllegalArgumentException(ORDER_ID_CANNOT_BE_NULL_ERROR);
        }
        checkIfOrderIsEmpty(idOrder);
        if (ordersRepository.findById(idOrder).get().getStatus().equals(CANCELLED_STATUS)) {
            throw new IllegalArgumentException("Order is already canceled");
        }
        if (!ordersRepository.findById(idOrder).get().getStatus().equals("Pendiente")) {
            return "Order is already being prepared, it can't be canceled";
        } else {
            OrderEntity orderEntity = ordersRepository.findById(idOrder)
                    .orElseThrow(OrderDoesntExistException::new);
            orderEntity.setStatus(CANCELLED_STATUS);
            sendTractabilityToMongo(idOrder,CANCELLED_STATUS);
            ordersRepository.save(orderEntity);
            return "Order with ID: " + idOrder + " was canceled successfully";
        }
    }

    public void sendTractabilityToMongo(Long idOrder, String newStatus) {
        if (idOrder == null || newStatus == null) {
            throw new IllegalArgumentException("Parameters can't be null");
        }
        if (ordersRepository.findById(idOrder).isEmpty()) {
            throw new OrderDoesntExistException();
        }
        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(OrderDoesntExistException::new);
        Tractability tractability = new Tractability(
                ObjectId.get(),
                orderEntity.getId(),
                orderEntity.getUserEntity().getId(),
                orderEntity.getUserEntity().getPersonEntity().getMail(),
                LocalDateTime.now(),
                orderEntity.getStatus(),
                newStatus,
                orderEntity.getChefEntity().getId(),
                orderEntity.getChefEntity().getPersonEntity().getMail()
        );
        tractabilityRepository.save(tractabilityEntityMapper.toEntity(tractability));
    }
}
