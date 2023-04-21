package br.sc.senai.provaspring.model.dto;

import br.sc.senai.provaspring.model.entities.Cliente;
import br.sc.senai.provaspring.model.entities.EnderecoEntrega;
import br.sc.senai.provaspring.model.entities.Produto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.util.List;

@Getter
public class PedidoDTO {

    @NotNull
    @Positive
    private Double valorTotal;

    @Valid
    private List<ProdutoPedidoDTO> produtos;

    @NotNull
    private Cliente cliente;

    @NotNull
    private EnderecoEntrega endereco;
}
