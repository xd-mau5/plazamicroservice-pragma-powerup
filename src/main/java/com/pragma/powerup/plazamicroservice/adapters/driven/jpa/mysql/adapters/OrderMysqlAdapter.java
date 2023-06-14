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
import com.pragma.powerup.plazamicroservice.domain.model.Orders;
import com.pragma.powerup.plazamicroservice.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.pragma.powerup.plazamicroservice.configuration.Constants.*;

@RequiredArgsConstructor
public class OrderMysqlAdapter implements IOrderPersistencePort {
    private final IOrdersRepository ordersRepository;
    private final IOrdersEntityMapper ordersEntityMapper;
    private final IUserRepository userRepository;
    private final IRestaurantRepository restaurantRepository;
    private final RestTemplate restTemplate;
    @Override
    public void saveOrder(Orders orders) {
        if (orders == null) {
            throw new IllegalArgumentException("Orders can't be null");
        }
        if (orders.getId() != null) {
            throw new IllegalArgumentException("Orders id can't be null");
        }
        if (ordersRepository.findByUserEntityIdAndStatus(orders.getUserId(), "Pendiente").isPresent()) {
            throw new IllegalArgumentException("User already has an order pending");
        }
        if (orders.getRestaurantId() == null) {
            throw new IllegalArgumentException("Restaurant id can't be null");
        }
        RestaurantEntity restaurantEntity = restaurantRepository.findById(orders.getRestaurantId())
                .orElseThrow(() -> new NullPointerException("Restaurant doesn't exist"));
        ordersEntityMapper.toEntity(orders).setRestaurantEntity(restaurantEntity);
        orders.setRestaurantId(restaurantEntity.getId());
        orders.setStatus("Pendiente");
        orders.setChefId(1L);
        ordersRepository.save(ordersEntityMapper.toEntity(orders));
    }
    @Override
    public void updateOrder(Long idOrder, String status){
        if (idOrder == null) {
            throw new IllegalArgumentException("IdOrder can't be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status can't be null");
        }
        if (ordersRepository.findById(idOrder).isEmpty()) {
            throw new IllegalArgumentException("Orders doesn't exist");
        }
        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(OrderDoesntExistException::new);
        orderEntity.setStatus(status);
        ordersRepository.save(orderEntity);
    }
    @Override
    //TODO: Solo se pueden listar los pedidos del restaurante al que pertenece el empleado.
    //TODO: Arreglar la salida de las entidades.
    public List<Orders> getAllOrdersByStatus(Long restaurantId, String status, Integer page, Integer size){
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
    public List<Orders> setOrderToEmployee(Long idOrder, Long idEmployee, String status, Integer page, Integer size){
        /*
        TODO: Solo se pueden listar los pedidos del restaurante al que pertenece el empleado.
        TODO: Arreglar la salida de las entidades.
         */
        if (idOrder == null || idEmployee == null || status == null) {
            throw new IllegalArgumentException(NULL_PARAMETER_ERROR);
        }
        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(OrderDoesntExistException::new);

        UserEntity chefEntity = userRepository.findById(idEmployee)
                .orElseThrow(() -> new IllegalArgumentException("Employee doesn't exist"));

        orderEntity.setStatus(status);
        orderEntity.setChefEntity(chefEntity);
        ordersRepository.save(orderEntity);
        return getAllOrdersByStatus(orderEntity.getRestaurantEntity().getId(), status, page, size);
    }
    @Override
    public String generateSecurityCode(Long idOrder, String status){
        if (idOrder == null) {
            throw new IllegalArgumentException("IdOrder can't be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status can't be null");
        }
        if (ordersRepository.findById(idOrder).isEmpty()) {
            throw new OrderDoesntExistException();
        }
        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(OrderDoesntExistException::new);
        long orderDateUnixTime = orderEntity.getOrderDate().toInstant().getEpochSecond();
        long idSum = orderEntity.getId() + orderEntity.getRestaurantEntity().getId();
        long securityCode = orderDateUnixTime + idSum;
        return Long.toString(securityCode);
    }
    public String createMessageOrderReady(Long idOrder){
        if (idOrder == null) {
            throw new IllegalArgumentException("Parameters can't be null");
        }
        if (ordersRepository.findById(idOrder).isEmpty()) {
            throw new OrderDoesntExistException();
        }
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
    public boolean checkSecurityCode(Long idOrder, String securityCode){
        if (idOrder == null || securityCode == null) {
            throw new IllegalArgumentException("Parameters can't be null");
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
    public void deliverOrder(Long idOrder, String securityCode){
        if (idOrder == null) {
            throw new IllegalArgumentException("IdOrder can't be null");
        }
        if (ordersRepository.findById(idOrder).isEmpty()) {
            throw new OrderDoesntExistException();
        }
        if (!checkSecurityCode(idOrder, securityCode)) {
            throw new IllegalArgumentException("Invalid security code");
        }
        if (!ordersRepository.findById(idOrder).get().getStatus().equals("Listo")) {
            throw new IllegalArgumentException("Order isn't ready");
        }
        if (ordersRepository.findById(idOrder).get().getStatus().equals("Entregado")){
            throw new IllegalArgumentException("Order is already delivered");
        }
        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(OrderDoesntExistException::new);
        orderEntity.setStatus("Entregado");
        ordersRepository.save(orderEntity);
    }
}
