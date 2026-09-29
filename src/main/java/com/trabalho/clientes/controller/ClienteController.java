package com.trabalho.clientes.controller;

import com.trabalho.clientes.dto.cliente.ClienteRequestDTO;
import com.trabalho.clientes.dto.cliente.ClienteResponseDTO;
import com.trabalho.clientes.dto.cliente.ClienteUpdateDTO;
import com.trabalho.clientes.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(
        name = "Clientes",
        description = "Operações relacionadas aos clientes"
)
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @Operation(
        summary = "Cadastrar cliente",
        description = "Cadastra um novo cliente com pelo menos um endereço."
)
    public ResponseEntity<ClienteResponseDTO> cadastrar(
            @Valid @RequestBody ClienteRequestDTO dto) {

        ClienteResponseDTO cliente = clienteService.cadastrar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cliente);
    }

    @GetMapping
    @Operation(
        summary = "Listar clientes",
        description = "Retorna todos os clientes e seus respectivos endereços."
)
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {

        return ResponseEntity.ok(
                clienteService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar cliente",
        description = "Busca um cliente pelo seu ID."
)
    public ResponseEntity<ClienteResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                clienteService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar cliente",
        description = "Atualiza os dados de um cliente existente."
)
public ResponseEntity<ClienteResponseDTO> atualizar(
        @PathVariable Long id,
        @Valid @RequestBody ClienteUpdateDTO dto) {

    return ResponseEntity.ok(
            clienteService.atualizar(id, dto)
    );
}


@DeleteMapping("/{id}")
@Operation(
        summary = "Excluir cliente",
        description = "Exclui um cliente e seus endereços."
)
public ResponseEntity<Void> excluir(
        @PathVariable Long id) {

    clienteService.excluir(id);

    return ResponseEntity.noContent().build();
}

}