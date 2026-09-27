package com.trabalho.clientes.service;

import com.trabalho.clientes.dto.cliente.ClienteRequestDTO;
import com.trabalho.clientes.dto.cliente.ClienteResponseDTO;
import com.trabalho.clientes.dto.cliente.ClienteUpdateDTO;
import com.trabalho.clientes.dto.endereco.EnderecoResponseDTO;
import com.trabalho.clientes.exception.RecursoNaoEncontradoException;
import com.trabalho.clientes.exception.RegraNegocioException;
import com.trabalho.clientes.model.Cliente;
import com.trabalho.clientes.model.Endereco;
import com.trabalho.clientes.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponseDTO cadastrar(ClienteRequestDTO dto) {

        if (clienteRepository.existsByCpf(dto.cpf())) {
            throw new RegraNegocioException("Já existe um cliente com esse CPF.");
        }

        Cliente cliente = new Cliente();

        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());

        List<Endereco> enderecos = dto.enderecos()
                .stream()
                .map(enderecoDTO -> {

                    Endereco endereco = new Endereco();

                    endereco.setCep(enderecoDTO.cep());
                    endereco.setRua(enderecoDTO.rua());
                    endereco.setNumero(enderecoDTO.numero());
                    endereco.setBairro(enderecoDTO.bairro());
                    endereco.setCidade(enderecoDTO.cidade());
                    endereco.setEstado(enderecoDTO.estado());

                    endereco.setCliente(cliente);

                    return endereco;
                })
                .toList();

        cliente.setEnderecos(enderecos);

        Cliente clienteSalvo = clienteRepository.save(cliente);

        return converterParaResponseDTO(clienteSalvo);
    }

    public List<ClienteResponseDTO> listarTodos() {

        return clienteRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Long id) {

        Cliente cliente = buscarEntidadePorId(id);

        return converterParaResponseDTO(cliente);
    }

    private Cliente buscarEntidadePorId(Long id) {

        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Cliente não encontrado."
                        )
                );
    }

    private ClienteResponseDTO converterParaResponseDTO(Cliente cliente) {

    List<EnderecoResponseDTO> enderecos = cliente.getEnderecos()
            .stream()
            .map(endereco ->
                    new EnderecoResponseDTO(
                            endereco.getId(),
                            endereco.getCep(),
                            endereco.getRua(),
                            endereco.getNumero(),
                            endereco.getBairro(),
                            endereco.getCidade(),
                            endereco.getEstado()
                    )
            )
            .toList();

    return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getEmail(),
            cliente.getTelefone(),
            enderecos
    );
}

public ClienteResponseDTO atualizar(Long id, ClienteUpdateDTO dto) {

    Cliente cliente = buscarEntidadePorId(id);

    clienteRepository.findByCpf(dto.cpf())
            .ifPresent(clienteComMesmoCpf -> {

                if (!clienteComMesmoCpf.getId().equals(id)) {

                    throw new RegraNegocioException(
                            "Já existe outro cliente com esse CPF."
                    );
                }
            });

    cliente.setNome(dto.nome());
    cliente.setCpf(dto.cpf());
    cliente.setEmail(dto.email());
    cliente.setTelefone(dto.telefone());

    Cliente clienteAtualizado =
            clienteRepository.save(cliente);

    return converterParaResponseDTO(clienteAtualizado);
}

public void excluir(Long id) {

    Cliente cliente = buscarEntidadePorId(id);

    clienteRepository.delete(cliente);      

}

}