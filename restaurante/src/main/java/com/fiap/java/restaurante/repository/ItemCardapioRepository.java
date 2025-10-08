package com.fiap.java.restaurante.repository;

import com.fiap.java.restaurante.models.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
}
