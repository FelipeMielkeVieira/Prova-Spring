package br.sc.senai.provaspring.model.dto;

import br.sc.senai.provaspring.model.entities.Pedido;
import br.sc.senai.provaspring.model.entities.Produto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ProdutoPedidoDTO {

    private Long id;

    @NotNull
    @Positive
    private Integer quantidade;

    private Produto produto;

    private Pedido pedido;
}
