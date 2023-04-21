package br.sc.senai.provaspring.model.dto;

import br.sc.senai.provaspring.model.entities.CartaoCredito;
import br.sc.senai.provaspring.model.entities.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class ClienteDTO {

    @NotNull
    private String nome;

    @NotNull
    @Email
    private String email;

    private String telefone;

    private List<Endereco> enderecos;

    private CartaoCredito cartao;
}
