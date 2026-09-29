package com.trabalho.clientes.dto.cliente;

import com.trabalho.clientes.dto.endereco.EnderecoRequestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ClienteRequestDTO(

        @Schema(
                description = "Nome completo do cliente",
                example = "João da Silva"
        )
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @Schema(
                description = "CPF do cliente",
                example = "123.456.789-00"
        )
        @NotBlank(message = "O CPF é obrigatório")
        String cpf,

        
        @Email(message = "Informe um e-mail válido")
        @Schema(
                description = "E-mail do cliente",
                example = "joao.silva@example.com"
        )
        String email,

        @Schema(
                description = "Telefone do cliente",
                example = "(11) 99999-9999"
        )
        String telefone,

        @Schema(
                description = "Endereços do cliente",
                example = "[{\"logradouro\": \"Rua Exemplo\", \"numero\": \"123\", \"bairro\": \"Centro\", \"cidade\": \"São Paulo\", \"estado\": \"SP\", \"cep\": \"12345-678\"}]"
        )
        @NotEmpty(message = "O cliente deve possuir pelo menos um endereço")
        List<@Valid EnderecoRequestDTO> enderecos

) {
}