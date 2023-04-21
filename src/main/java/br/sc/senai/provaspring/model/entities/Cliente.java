package br.sc.senai.provaspring.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "cliente")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 150, nullable = false)
    private String email;

    @Column(length = 20)
    private String telefone;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<Endereco> enderecos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartao_credito_id")
    private CartaoCredito cartao;
}
