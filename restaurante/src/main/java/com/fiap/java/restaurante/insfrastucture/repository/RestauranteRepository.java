package com.fiap.java.restaurante.insfrastucture.repository;

import com.fiap.java.restaurante.insfrastucture.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
}