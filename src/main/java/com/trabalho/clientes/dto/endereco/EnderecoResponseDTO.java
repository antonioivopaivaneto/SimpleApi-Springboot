package com.trabalho.clientes.dto.endereco;

public record EnderecoResponseDTO(

        Long id,
        String cep,
        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado

) {
}