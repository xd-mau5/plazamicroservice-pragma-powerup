package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.DishesOrderedEntity;
import com.pragma.powerup.plazamicroservice.domain.model.DishesOrdered;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishesOrderedEntityMapper {

    @Mapping(target = "orderEntity.id", source = "dishesOrdered.idOrder", qualifiedByName = "idToOrderEntity")
    @Mapping(target = "dishesEntity.id", source = "dishesOrdered.idDish", qualifiedByName = "idToDishesEntity")
    @Mapping(target = "quantity", source = "dishesOrdered.quantity")
    DishesOrderedEntity toEntity(DishesOrdered dishesOrdered);
    @Named("idToOrderEntity")
    default Long idToOrderEntity(Long idOrder) {
        return idOrder;
    }
    @Named("idToDishesEntity")
    default Long idToDishesEntity(Long idDish) {
        return idDish;
    }
}