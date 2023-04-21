package br.sc.senai.provaspring.service;

import br.sc.senai.provaspring.model.entities.EnderecoEntrega;
import br.sc.senai.provaspring.repository.EnderecoEntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnderecoEntregaService {

    private EnderecoEntregaRepository enderecoEntregaRepository;

    public List<EnderecoEntrega> findAll() {
        return enderecoEntregaRepository.findAll();
    }

    public Optional<EnderecoEntrega> findById(Long id) {
        return enderecoEntregaRepository.findById(id);
    }

    public Boolean existsById(Long id) {
        return enderecoEntregaRepository.existsById(id);
    }

    public EnderecoEntrega save(EnderecoEntrega enderecoEntrega) {
        return enderecoEntregaRepository.save(enderecoEntrega);
    }

    public void deleteById(Long id) {
        enderecoEntregaRepository.deleteById(id);
    }
}
