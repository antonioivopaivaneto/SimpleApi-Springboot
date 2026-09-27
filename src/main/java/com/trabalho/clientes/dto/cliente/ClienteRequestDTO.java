package com.trabalho.clientes.dto.cliente;

import com.trabalho.clientes.dto.endereco.EnderecoRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ClienteRequestDTO(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O CPF é obrigatório")
        String cpf,

        @Email(message = "Informe um e-mail válido")
        String email,

        String telefone,

        @NotEmpty(message = "O cliente deve possuir pelo menos um endereço")
        List<@Valid EnderecoRequestDTO> enderecos

) {
}