package br.sc.senai.provaspring.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "endereco_entrega")
@Data
public class EnderecoEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToOne(mappedBy = "endereco")
    @JsonIgnore
    private Pedido pedido;
}
