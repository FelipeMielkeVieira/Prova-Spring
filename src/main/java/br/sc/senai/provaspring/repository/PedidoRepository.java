package br.sc.senai.provaspring.repository;

import br.sc.senai.provaspring.model.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
