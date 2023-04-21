package br.sc.senai.provaspring.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EnderecoDTO {

    @NotNull
    private String rua;

    private String numero;

    private String bairro;

    @NotNull
    private String cidade;

    private String estado;

    @NotNull
    private String cep;
}
