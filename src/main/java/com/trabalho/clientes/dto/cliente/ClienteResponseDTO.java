package com.trabalho.clientes.dto.cliente;

import com.trabalho.clientes.dto.endereco.EnderecoResponseDTO;

import java.util.List;

public record ClienteResponseDTO(

        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,
        List<EnderecoResponseDTO> enderecos

) {
}