package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.model.Dishes;
import com.pragma.powerup.plazamicroservice.domain.model.UpdateDish;
import com.pragma.powerup.plazamicroservice.domain.spi.IDishesPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class DishesUseCaseTest {

    @Mock
    private IDishesPersistencePort dishesPersistencePort;

    private DishesUseCase dishesUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dishesUseCase = new DishesUseCase(dishesPersistencePort);
    }

    @Test
    void testCreateDish() {
        Dishes dish = new Dishes(1L, "Dish Name", 1L, "Dish Description", 10.99f, 1L, "image.jpg", true);
        dishesUseCase.createDish(dish);
        verify(dishesPersistencePort).createDish(dish);
    }

    @Test
    void testUpdateDish() {
        Long id = 1L;
        UpdateDish updateDish = new UpdateDish("Updated Name", 15.99f);
        dishesUseCase.updateDish(id, updateDish);
        verify(dishesPersistencePort).updateDish(id, updateDish);
    }
    @Test
    void testUpdateDishStatus() {
        Long id = 1L;
        boolean status = false;
        dishesUseCase.updateDishStatus(id, status);
        verify(dishesPersistencePort).updateDishStatus(id, status);
    }
}
