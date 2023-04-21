package br.sc.senai.provaspring.repository;

import br.sc.senai.provaspring.model.entities.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {
}
