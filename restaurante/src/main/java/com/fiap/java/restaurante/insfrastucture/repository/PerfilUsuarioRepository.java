package com.fiap.java.restaurante.insfrastucture.repository;

import com.fiap.java.restaurante.insfrastucture.entity.PerfilUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuarioEntity, Long> {
}
