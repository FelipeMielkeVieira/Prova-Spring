package br.sc.senai.provaspring.repository;

import br.sc.senai.provaspring.model.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
