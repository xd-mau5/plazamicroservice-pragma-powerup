package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.model.DishesOrdered;
import com.pragma.powerup.plazamicroservice.domain.spi.IDishesOrderedPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


class DishesOrderedUseCaseTest {

    @Mock
    private IDishesOrderedPersistencePort dishesOrderedPersistencePort;

    private DishesOrderedUseCase dishesOrderedUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dishesOrderedUseCase = new DishesOrderedUseCase(dishesOrderedPersistencePort);
    }

    @Test
    void saveDishesOrdered() {
        DishesOrdered dishesOrdered = new DishesOrdered(
                1L, 1L, 1L, 10
        );

        dishesOrderedUseCase.saveDishesOrdered(dishesOrdered);

        verify(dishesOrderedPersistencePort).saveDishesOrdered(dishesOrdered);
    }
}