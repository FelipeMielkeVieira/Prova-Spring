package br.sc.senai.provaspring.controller;

import br.sc.senai.provaspring.model.dto.ProdutoDTO;
import br.sc.senai.provaspring.model.entities.Produto;
import br.sc.senai.provaspring.service.ProdutoService;
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
@RequestMapping("/prova/produto")
public class ProdutoController {

    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<Produto> produtoOptional = produtoService.findById(id);
        if (produtoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody @Valid ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoDTO, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody @Valid ProdutoDTO produtoDTO) {
        if (!produtoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }

        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoDTO, produto);
        produto.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produto));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        if (!produtoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        produtoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
    }
}
