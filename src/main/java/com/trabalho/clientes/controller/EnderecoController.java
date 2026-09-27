package com.trabalho.clientes.controller;

import com.trabalho.clientes.dto.endereco.EnderecoRequestDTO;
import com.trabalho.clientes.dto.endereco.EnderecoResponseDTO;
import com.trabalho.clientes.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("/clientes/{clienteId}/enderecos")
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
    public ResponseEntity<EnderecoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody EnderecoRequestDTO dto) {

        return ResponseEntity.ok(
                enderecoService.atualizar(id, dto)
        );
    }

    @DeleteMapping("/enderecos/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        enderecoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}