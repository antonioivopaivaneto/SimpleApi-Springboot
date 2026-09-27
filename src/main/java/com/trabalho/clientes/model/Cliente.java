package com.trabalho.clientes.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "clientes")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    private String email;

    private String telefone;

    @OneToMany (
        mappedBy = "cliente",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private java.util.List<Endereco> enderecos;
    
}
