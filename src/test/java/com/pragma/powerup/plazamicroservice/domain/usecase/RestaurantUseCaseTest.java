package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazamicroservice.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class RestaurantUseCaseTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    private RestaurantUseCase restaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort);
    }

    @Test
    void testCreateRestaurant() {
        Restaurant restaurant = new Restaurant(1L, "Restaurant Name", "Address", 2L,
                "3107079023", "image.jpg", "1234");
        restaurantUseCase.createRestaurant(restaurant);
        verify(restaurantPersistencePort).createRestaurant(restaurant);
    }
}
