package com.trabalho.clientes.repository;

import com.trabalho.clientes.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}