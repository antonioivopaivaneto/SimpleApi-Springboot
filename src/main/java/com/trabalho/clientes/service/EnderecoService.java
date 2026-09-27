package com.trabalho.clientes.service;

import com.trabalho.clientes.dto.endereco.EnderecoRequestDTO;
import com.trabalho.clientes.dto.endereco.EnderecoResponseDTO;
import com.trabalho.clientes.exception.RecursoNaoEncontradoException;
import com.trabalho.clientes.exception.RegraNegocioException;
import com.trabalho.clientes.model.Cliente;
import com.trabalho.clientes.model.Endereco;
import com.trabalho.clientes.repository.ClienteRepository;
import com.trabalho.clientes.repository.EnderecoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;

    public EnderecoService(
            EnderecoRepository enderecoRepository,
            ClienteRepository clienteRepository) {

        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
    }

    public EnderecoResponseDTO cadastrar(
            Long clienteId,
            EnderecoRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Cliente não encontrado."
                        )
                );

        Endereco endereco = new Endereco();

        endereco.setCep(dto.cep());
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());

        endereco.setCliente(cliente);

        Endereco enderecoSalvo =
                enderecoRepository.save(endereco);

        return converterParaResponseDTO(enderecoSalvo);
    }

    public EnderecoResponseDTO atualizar(
            Long id,
            EnderecoRequestDTO dto) {

        Endereco endereco = buscarEntidadePorId(id);

        endereco.setCep(dto.cep());
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());

        Endereco enderecoAtualizado =
                enderecoRepository.save(endereco);

        return converterParaResponseDTO(enderecoAtualizado);
    }

    @Transactional
    public void excluir(Long id) {

        Endereco endereco = buscarEntidadePorId(id);

        Cliente cliente = endereco.getCliente();

        if (cliente.getEnderecos().size() <= 1) {
            throw new RegraNegocioException(
                    "O cliente não pode ficar sem endereço."
            );
        }

        cliente.getEnderecos().remove(endereco);
    }

    private Endereco buscarEntidadePorId(Long id) {

        return enderecoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Endereço não encontrado."
                        )
                );
    }

    private EnderecoResponseDTO converterParaResponseDTO(
            Endereco endereco) {

        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getCep(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado()
        );
    }
}
