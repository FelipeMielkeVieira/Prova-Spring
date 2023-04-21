package br.sc.senai.provaspring.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cartao_credito")
@Data
public class CartaoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String numero;
}
