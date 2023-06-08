package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapters;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.DishesEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.DishesAlreadyExistException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.InvalidDishesNameException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IDishesEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IDishesRepository;
import com.pragma.powerup.plazamicroservice.domain.model.Dishes;
import com.pragma.powerup.plazamicroservice.domain.model.UpdateDish;
import com.pragma.powerup.plazamicroservice.domain.spi.IDishesPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DishesMysqlAdapter implements IDishesPersistencePort {
    private final IDishesRepository dishesRepository;
    private final IDishesEntityMapper dishesEntityMapper;

    @Override
    public void createDish(Dishes dishes) {
        if (dishes.getName().isEmpty()) {
            throw new InvalidDishesNameException();
        }
        if (dishesRepository.findByName(dishes.getName()).isPresent()) {
            throw new DishesAlreadyExistException();
        }
        dishes.setActive(true);
        dishesRepository.save(dishesEntityMapper.toEntity(dishes));
    }

    @Override
    public void updateDish(Long id, UpdateDish updateDish) {
        if (id == null) {
            throw new NullPointerException();
        }
        if (updateDish.getDescription().isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (updateDish.getPrice() < 0) {
            throw new IllegalArgumentException();
        }
        if (dishesRepository.findById(id).isEmpty()) {
            throw new NullPointerException();
        }
        Optional<DishesEntity> dishesOptional = dishesRepository.findById(id);
        if (dishesOptional.isPresent()) {
            DishesEntity dishes = dishesOptional.get();
            dishes.setDescription(updateDish.getDescription());
            dishes.setPrice(updateDish.getPrice());
            dishesRepository.save(dishes);
        }
    }

    @Override
    public void updateDishStatus(Long id, boolean status) {
        //TODO: Validar que el owner del restaurante sea el que este haciendo la peticion para cambiar el estado del plato de ese restaurante
        if (id == null) {
            throw new NullPointerException();
        }
        if (dishesRepository.findById(id).isEmpty()) {
            throw new NullPointerException();
        }
        Optional<DishesEntity> dishesOptional = dishesRepository.findById(id);
        if (dishesOptional.isPresent()) {
            DishesEntity dishes = dishesOptional.get();
            dishes.setActive(status);
            dishesRepository.save(dishes);
        }
    }

    @Override
    public List<Dishes> getAllDishes(Long restaurantId, Integer page, Integer size, String category) {
        if (restaurantId == null || page == null || size == null) {
            throw new IllegalArgumentException("Missing required parameters");
        }
        if (page < 0 || size < 0) {
            throw new IllegalArgumentException("Invalid page or size");
        }

        Specification<DishesEntity> restaurantSpecification = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("restaurantEntity").get("id"), restaurantId);

        Specification<DishesEntity> categorySpecification = Specification.where(null);
        if (!StringUtils.hasText(category)) {
            categorySpecification = (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("categoryEntity").get("name"), category);
        }

        Specification<DishesEntity> combinedSpecification = Specification.where(restaurantSpecification)
                .and(categorySpecification);

        Pageable pagination = PageRequest.of(page, size);
        Page<DishesEntity> dishesPage = dishesRepository.findAll(combinedSpecification, pagination);

        return dishesPage.getContent().stream()
                .map(dishesEntityMapper::toDishes)
                .toList();
    }
}
