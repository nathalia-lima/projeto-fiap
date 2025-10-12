package com.fiap.java.restaurante.insfrastucture.repository;

import com.fiap.java.restaurante.insfrastucture.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmailIgnoreCase(String email);
}
