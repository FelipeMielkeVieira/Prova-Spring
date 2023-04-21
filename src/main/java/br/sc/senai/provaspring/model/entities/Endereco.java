package br.sc.senai.provaspring.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "endereco")
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String rua;

    @Column(length = 4)
    private String numero;

    @Column(length = 50)
    private String bairro;

    @Column(length = 50, nullable = false)
    private String cidade;

    @Column(length = 50)
    private String estado;

    @Column(length = 10)
    private String cep;
}
