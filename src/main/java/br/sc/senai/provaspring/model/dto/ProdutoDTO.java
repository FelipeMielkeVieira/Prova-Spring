package br.sc.senai.provaspring.model.dto;

import br.sc.senai.provaspring.model.entities.Fornecedor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.util.List;

@Getter
public class ProdutoDTO {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @Positive
    private Double preco;

    @NotNull
    @Positive
    private Integer quantidade;

    private List<Fornecedor> fornecedores;
}
