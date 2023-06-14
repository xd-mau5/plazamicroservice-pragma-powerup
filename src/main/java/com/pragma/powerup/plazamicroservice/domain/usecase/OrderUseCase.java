package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.plazamicroservice.domain.model.Orders;
import com.pragma.powerup.plazamicroservice.domain.spi.IOrderPersistencePort;

import java.util.List;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }
    @Override
    public void saveOrder(Orders orders) {
        orderPersistencePort.saveOrder(orders);
    }
    @Override
    public void updateOrder(Long idOrder, String status){
        orderPersistencePort.updateOrder(idOrder, status);
    }
    @Override
    public List<Orders> setOrderToEmployee(Long idOrder, Long idEmployee, String status, Integer page, Integer size) {
        return orderPersistencePort.setOrderToEmployee(idOrder, idEmployee, status, page, size);
    }
    @Override
    public List<Orders> getAllOrdersByStatus(Long idRestaurant, String status, Integer page, Integer size) {
        return orderPersistencePort.getAllOrdersByStatus(idRestaurant, status, page, size);
    }
    @Override
    public void sendMessageToUser(Long idOrder) {
        orderPersistencePort.sendMessageToUser(idOrder);
    }
    @Override
    public boolean checkSecurityCode(Long idOrder, String securityCode) {
        return orderPersistencePort.checkSecurityCode(idOrder, securityCode);
    }
    @Override
    public void deliverOrder(Long idOrder, String securityCode) {
        orderPersistencePort.deliverOrder(idOrder, securityCode);
    }
}
