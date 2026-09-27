package com.trabalho.clientes.dto.endereco;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequestDTO(

        @NotBlank(message = "O CEP é obrigatório")
        String cep,

        @NotBlank(message = "A rua é obrigatória")
        String rua,

        @NotBlank(message = "O número é obrigatório")
        String numero,

        @NotBlank(message = "O bairro é obrigatório")
        String bairro,

        @NotBlank(message = "A cidade é obrigatória")
        String cidade,

        @NotBlank(message = "O estado é obrigatório")
        String estado

) {
}