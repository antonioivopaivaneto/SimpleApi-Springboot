package com.trabalho.clientes.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteUpdateDTO(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O CPF é obrigatório")
        String cpf,

        @Email(message = "Informe um e-mail válido")
        String email,

        String telefone

) {
}