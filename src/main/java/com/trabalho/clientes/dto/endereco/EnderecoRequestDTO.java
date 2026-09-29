package com.trabalho.clientes.dto.endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record EnderecoRequestDTO(

        
        @Schema(
                description = "CEP do endereço",
                example = "01310-100"
        )
        @NotBlank(message = "O CEP é obrigatório")
        String cep,

        @Schema(
                description = "Logradouro do endereço",
                example = "Rua Exemplo"
        )
        @NotBlank(message = "A rua é obrigatória")
        String rua,

        @Schema(
                description = "Número do endereço",
                example = "123"
        )
        @NotBlank(message = "O número é obrigatório")
        String numero,

        @Schema(
                description = "Bairro do endereço",
                example = "Centro"
        )
        @NotBlank(message = "O bairro é obrigatório")
        String bairro,

        @Schema(
                description = "Cidade do endereço",
                example = "São Paulo"
        )
        @NotBlank(message = "A cidade é obrigatória")
        String cidade,

        @Schema(
                description = "Estado do endereço",
                example = "SP"
        )
        @NotBlank(message = "O estado é obrigatório")
        String estado

) {
}