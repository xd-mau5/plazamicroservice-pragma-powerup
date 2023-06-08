package com.pragma.powerup.plazamicroservice.configuration;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapters.*;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.*;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.*;
import com.pragma.powerup.plazamicroservice.domain.api.*;
import com.pragma.powerup.plazamicroservice.domain.spi.*;
import com.pragma.powerup.plazamicroservice.domain.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Configuration
@RestController
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IDishesRepository dishesRepository;
    private final IDishesEntityMapper dishesEntityMapper;
    private final IOrdersEntityMapper ordersEntityMapper;
    private final IOrdersRepository ordersRepository;
    private final IDishesOrderedRepository dishesOrderedRepository;
    private final IDishesOrderedEntityMapper dishesOrderedEntityMapper;
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort());
    }
    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantMysqlAdapter(restaurantRepository, restaurantEntityMapper);
    }
    @Bean
    public IDishesServicePort dishesServicePort() {
        return new DishesUseCase(dishesPersistencePort());
    }
    public IDishesPersistencePort dishesPersistencePort() {
        return new DishesMysqlAdapter(dishesRepository, dishesEntityMapper);
    }
    @Bean
    public IDishesOrderedServicePort dishesOrderedServicePort() {
        return new DishesOrderedUseCase(dishesOrderedPersistencePort());
    }

    @Bean
    public IDishesOrderedPersistencePort dishesOrderedPersistencePort() {
        return new DishesOrderedMysqlAdapter(dishesOrderedRepository, dishesOrderedEntityMapper, dishesRepository, ordersRepository, ordersEntityMapper);
    }
    @Bean
    public IOrderPersistencePort ordersPersistencePort() {
        return new OrderMysqlAdapter(ordersRepository, ordersEntityMapper);
    }
    @Bean
    public IOrderServicePort ordersServicePort() {
        return new OrderUseCase(ordersPersistencePort());
    }
    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }
    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryMysqlAdapter(categoryRepository, categoryEntityMapper);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
