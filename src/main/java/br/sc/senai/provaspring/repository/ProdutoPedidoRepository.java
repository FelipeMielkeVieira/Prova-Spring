package br.sc.senai.provaspring.repository;

import br.sc.senai.provaspring.model.entities.ProdutoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Long> {
}
