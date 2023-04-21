package br.sc.senai.provaspring.repository;

import br.sc.senai.provaspring.model.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
