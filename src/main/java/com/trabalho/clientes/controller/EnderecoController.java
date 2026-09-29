package com.trabalho.clientes.controller;

import com.trabalho.clientes.dto.endereco.EnderecoRequestDTO;
import com.trabalho.clientes.dto.endereco.EnderecoResponseDTO;
import com.trabalho.clientes.service.EnderecoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(
        name = "Endereços",
        description = "Operações relacionadas aos endereços dos clientes"
)
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("/clientes/{clienteId}/enderecos")
    @Operation(
        summary = "Adicionar endereço",
        description = "Adiciona um novo endereço a um cliente existente."
)
    public ResponseEntity<EnderecoResponseDTO> cadastrar(
            @PathVariable Long clienteId,
            @Valid @RequestBody EnderecoRequestDTO dto) {

        EnderecoResponseDTO endereco =
                enderecoService.cadastrar(clienteId, dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(endereco);
    }

    @PutMapping("/enderecos/{id}")
    @Operation(
        summary = "Atualizar endereço",
        description = "Atualiza os dados de um endereço existente."
)
    public ResponseEntity<EnderecoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody EnderecoRequestDTO dto) {

        return ResponseEntity.ok(
                enderecoService.atualizar(id, dto)
        );
    }

    @DeleteMapping("/enderecos/{id}")
    @Operation(
        summary = "Excluir endereço",
        description = "Exclui um endereço existente."
)
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        enderecoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}