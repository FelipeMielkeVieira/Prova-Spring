package br.sc.senai.provaspring.service;

import br.sc.senai.provaspring.model.entities.CartaoCredito;
import br.sc.senai.provaspring.repository.CartaoCreditoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartaoCreditoService {

    private CartaoCreditoRepository cartaoCreditoRepository;

    public List<CartaoCredito> findAll() {
        return cartaoCreditoRepository.findAll();
    }

    public Optional<CartaoCredito> findById(Long id) {
        return cartaoCreditoRepository.findById(id);
    }

    public Boolean existsById(Long id) {
        return cartaoCreditoRepository.existsById(id);
    }

    public CartaoCredito save(CartaoCredito cartaoCredito) {
        return cartaoCreditoRepository.save(cartaoCredito);
    }

    public void delete(Long id) {
        cartaoCreditoRepository.deleteById(id);
    }
}
