package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.model.Orders;
import com.pragma.powerup.plazamicroservice.domain.spi.IOrderPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


class OrderUseCaseTest {

    @Mock
    private IOrderPersistencePort orderPersistencePort;

    private OrderUseCase orderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderUseCase = new OrderUseCase(orderPersistencePort);
    }

    @Test
    void saveOrder() {
        Orders orders = new Orders(
                1L,
                1L,
                Date.from(java.time.Instant.now()),
                "Terminado",
                1L,
                1L
        );
        orderUseCase.saveOrder(orders);
        verify(orderPersistencePort).saveOrder(orders);
    }

    @Test
    void updateOrder() {
        Long idOrder = 1L;
        String status = "Terminado";
        orderUseCase.updateOrder(idOrder, status);
        verify(orderPersistencePort).updateOrder(idOrder, status);
    }

    @Test
    void setOrderToEmployee() {
        Long idOrder = 1L;
        Long idEmployee = 1L;
        orderUseCase.setOrderToEmployee(idOrder, idEmployee);
        verify(orderPersistencePort).setOrderToEmployee(idOrder, idEmployee);
    }
}