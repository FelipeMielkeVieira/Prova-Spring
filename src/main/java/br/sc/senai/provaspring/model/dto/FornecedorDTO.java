package br.sc.senai.provaspring.model.dto;

import br.sc.senai.provaspring.model.entities.Cliente;
import br.sc.senai.provaspring.model.entities.Produto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class FornecedorDTO {

    @NotNull
    private String nome;

    @NotNull
    private String cnpj;

    @Valid
    private List<ProdutoDTO> produtos;

    private List<Cliente> clientes;
}
