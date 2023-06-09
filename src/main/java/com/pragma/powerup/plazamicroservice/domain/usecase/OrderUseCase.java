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
    public void setOrderToEmployee(Long idOrder, Long idEmployee) {
        orderPersistencePort.setOrderToEmployee(idOrder, idEmployee);
    }
    @Override
    public List<Orders> getAllOrdersByStatus(Long idRestaurant, String status, Integer page, Integer size) {
        return orderPersistencePort.getAllOrdersByStatus(idRestaurant, status, page, size);
    }
}
