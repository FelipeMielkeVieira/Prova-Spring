package br.sc.senai.provaspring.controller;

import br.sc.senai.provaspring.model.dto.CartaoCreditoDTO;
import br.sc.senai.provaspring.model.entities.CartaoCredito;
import br.sc.senai.provaspring.service.CartaoCreditoService;
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
@RequestMapping("/prova/cartaocredito")
public class CartaoCreditoController {

    private CartaoCreditoService cartaoCreditoService;

    @GetMapping
    public ResponseEntity<List<CartaoCredito>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(cartaoCreditoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<CartaoCredito> cartaoCreditoOptional = cartaoCreditoService.findById(id);
        if (cartaoCreditoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cartaoCreditoOptional.get());
    }

    @PostMapping
    public ResponseEntity<CartaoCredito> save(@RequestBody @Valid CartaoCreditoDTO cartaoCreditoDTO) {
        CartaoCredito cartaoCredito = new CartaoCredito();
        BeanUtils.copyProperties(cartaoCreditoDTO, cartaoCredito);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaoCreditoService.save(cartaoCredito));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody @Valid CartaoCreditoDTO cartaoCreditoDTO) {
        if (!cartaoCreditoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado!");
        }

        CartaoCredito cartaoCredito = new CartaoCredito();
        BeanUtils.copyProperties(cartaoCreditoDTO, cartaoCredito);
        cartaoCredito.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cartaoCreditoService.save(cartaoCredito));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        if (!cartaoCreditoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado!");
        }
        cartaoCreditoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cartão deletado com sucesso!");
    }
}
