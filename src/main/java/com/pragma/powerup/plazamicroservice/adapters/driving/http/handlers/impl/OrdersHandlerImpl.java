package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.IOrdersHandler;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IOrderRequestMapper;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IOrderResponseMapper;
import com.pragma.powerup.plazamicroservice.domain.api.IOrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersHandlerImpl implements IOrdersHandler {
        private final IOrderServicePort orderServicePort;
        private final IOrderRequestMapper orderRequestMapper;
        private final IOrderResponseMapper orderResponseMapper;

        @Override
        public void saveOrder(OrderRequestDto orderRequestDto) {
            orderServicePort.saveOrder(orderRequestMapper.toOrder(orderRequestDto));
        }
        @Override
        public void updateOrder(Long idOrder, String status){
            orderServicePort.updateOrder(idOrder, status);
        }
        @Override
        public void setOrderToEmployee(Long idOrder, Long idEmployee) {
            orderServicePort.setOrderToEmployee(idOrder, idEmployee);
        }
        @Override
        public List<OrderResponseDto> getAllOrdersByStatus(Long restaurantId, String status, Integer page, Integer size) {
            return orderResponseMapper.toResponseList(orderServicePort.getAllOrdersByStatus(restaurantId, status, page, size));
        }
}
