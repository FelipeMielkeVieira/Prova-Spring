package br.sc.senai.provaspring.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "fornecedor")
@Data
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false)
    private String cnpj;

    @ManyToMany(mappedBy = "fornecedores", cascade = CascadeType.ALL)
    private List<Produto> produtos;

    @ManyToMany
    private List<Cliente> clientes;
}
