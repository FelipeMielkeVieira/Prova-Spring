package br.sc.senai.provaspring.repository;

import br.sc.senai.provaspring.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
