package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    @Override
    @NotNull
    Optional<UserEntity> findById(@NotNull Long id);
    Optional<UserEntity> findByPersonEntityIdAndRoleEntityId(Long idPerson, Long idRole);
}
