package br.sc.senai.provaspring.controller;

import br.sc.senai.provaspring.model.dto.ProdutoPedidoDTO;
import br.sc.senai.provaspring.model.entities.ProdutoPedido;
import br.sc.senai.provaspring.service.ProdutoPedidoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/prova/produtopedido")
public class ProdutoPedidoController {

    private ProdutoPedidoService produtoPedidoService;

    @GetMapping
    public ResponseEntity<List<ProdutoPedido>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoPedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<ProdutoPedido> produtoPedidoOptional = produtoPedidoService.findById(id);
        if (produtoPedidoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto Pedido não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoPedidoOptional.get());
    }

    @PostMapping
    public ResponseEntity<ProdutoPedido> save(@RequestBody @Valid ProdutoPedidoDTO produtoPedidoDTO) {
        ProdutoPedido produtoPedido = new ProdutoPedido();
        BeanUtils.copyProperties(produtoPedidoDTO, produtoPedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoPedidoService.save(produtoPedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody @Valid ProdutoPedidoDTO produtoPedidoDTO) {
        if (!produtoPedidoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto Pedido não encontrado!");
        }

        ProdutoPedido produtoPedido = new ProdutoPedido();
        BeanUtils.copyProperties(produtoPedidoDTO, produtoPedido);
        produtoPedido.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(produtoPedidoService.save(produtoPedido));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        if (!produtoPedidoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto Pedido não encontrado!");
        }
        produtoPedidoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto Pedido deletado com sucesso!");
    }
}
