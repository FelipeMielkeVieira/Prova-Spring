package br.sc.senai.provaspring.model.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "pedido")
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Positive
    private Double valorTotal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<ProdutoPedido> produtos;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoEntrega endereco;
}
