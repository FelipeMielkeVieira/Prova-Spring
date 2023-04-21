package br.sc.senai.provaspring.service;

import br.sc.senai.provaspring.model.entities.Fornecedor;
import br.sc.senai.provaspring.repository.FornecedorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FornecedorService {

    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> findById(Long id) {
        return fornecedorRepository.findById(id);
    }

    public Boolean existsById(Long id) {
        return fornecedorRepository.existsById(id);
    }

    public Fornecedor save(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public void deleteById(Long id) {
        fornecedorRepository.deleteById(id);
    }
}
