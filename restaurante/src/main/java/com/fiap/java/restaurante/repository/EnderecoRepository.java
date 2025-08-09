package com.fiap.java.restaurante.repository;

import com.fiap.java.restaurante.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
