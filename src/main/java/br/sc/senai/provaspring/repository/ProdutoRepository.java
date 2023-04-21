package br.sc.senai.provaspring.repository;

import br.sc.senai.provaspring.model.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
