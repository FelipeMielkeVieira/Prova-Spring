package br.sc.senai.provaspring.model.dto;

import br.sc.senai.provaspring.model.entities.Endereco;
import br.sc.senai.provaspring.model.entities.Pedido;
import lombok.Getter;

@Getter
public class EnderecoEntregaDTO {

    private Endereco endereco;

    private Pedido pedido;
}
