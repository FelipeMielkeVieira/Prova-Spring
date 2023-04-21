package br.sc.senai.provaspring.service;

import br.sc.senai.provaspring.model.entities.ProdutoPedido;
import br.sc.senai.provaspring.repository.ProdutoPedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoPedidoService {

    private ProdutoPedidoRepository produtoPedidoRepository;

    public List<ProdutoPedido> findAll() {
        return produtoPedidoRepository.findAll();
    }

    public Optional<ProdutoPedido> findById(Long id) {
        return produtoPedidoRepository.findById(id);
    }

    public Boolean existsById(Long id) {
        return produtoPedidoRepository.existsById(id);
    }

    public ProdutoPedido save(ProdutoPedido produtoPedido) {
        return produtoPedidoRepository.save(produtoPedido);
    }

    public void deleteById(Long id) {
        produtoPedidoRepository.deleteById(id);
    }
}
