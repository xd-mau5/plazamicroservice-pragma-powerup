package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazamicroservice.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    @Test
    void testGetAllRestaurants() {
        List<Restaurant> expectedRestaurants = Arrays.asList(
                new Restaurant(1L, "Restaurant 1", "Description 1", 1L, "3107079023", "image1.jpg", "1234"),
                new Restaurant(2L, "Restaurant 2", "Description 2", 2L, "3107079024", "image2.jpg", "12345")
        );
        when(restaurantPersistencePort.getAllRestaurants(1,1)).thenReturn(expectedRestaurants);

        List<Restaurant> actualRestaurants = restaurantUseCase.getAllRestaurants(1,1);
        verify(restaurantPersistencePort).getAllRestaurants(1,1);
        assertEquals(expectedRestaurants, actualRestaurants);
    }
}
