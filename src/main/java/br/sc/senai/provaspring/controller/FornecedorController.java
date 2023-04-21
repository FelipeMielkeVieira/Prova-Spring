package br.sc.senai.provaspring.controller;

import br.sc.senai.provaspring.model.dto.FornecedorDTO;
import br.sc.senai.provaspring.model.dto.ProdutoDTO;
import br.sc.senai.provaspring.model.entities.Cliente;
import br.sc.senai.provaspring.model.entities.Fornecedor;
import br.sc.senai.provaspring.model.entities.Produto;
import br.sc.senai.provaspring.service.ClienteService;
import br.sc.senai.provaspring.service.FornecedorService;
import br.sc.senai.provaspring.service.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/prova/fornecedor")
public class FornecedorController {

    private FornecedorService fornecedorService;
    private ProdutoService produtoService;
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Fornecedor>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<Fornecedor> fornecedorOptional = fornecedorService.findById(id);
        if (fornecedorOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorOptional.get());
    }

    @PostMapping
    public ResponseEntity<Fornecedor> save(@RequestBody @Valid FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = new Fornecedor();
        BeanUtils.copyProperties(fornecedorDTO, fornecedor);

        Fornecedor fornecedorSalvo = fornecedorService.save(fornecedor);
        List<Fornecedor> listaFornecedores = new ArrayList<>();
        listaFornecedores.add(fornecedorSalvo);

        // Setar o fornecedor nos produtos
        List<Produto> listaProdutos = new ArrayList<>();
        for (ProdutoDTO produtoDTO : fornecedorDTO.getProdutos()) {
            Produto produto = new Produto();
            BeanUtils.copyProperties(produtoDTO, produto);
            produto.setFornecedores(listaFornecedores);
            listaProdutos.add(produtoService.save(produto));
        }

        fornecedorSalvo.setProdutos(listaProdutos);
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorService.save(fornecedorSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody @Valid Fornecedor fornecedor) {
        if (!fornecedorService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado!");
        }

        fornecedor.setId(id);

        // Buscar e setar clientes do fornecedor
        if (fornecedor.getClientes() != null) {
            List<Cliente> listaClientes = new ArrayList<>();
            for (Cliente cliente : fornecedor.getClientes()) {
                listaClientes.add(clienteService.findById(cliente.getId()).get());
            }
            fornecedor.setClientes(listaClientes);
        }

        // Buscar e setar produtos do fornecedor
        List<Produto> listaProdutos = new ArrayList<>();
        if (fornecedor.getProdutos() != null) {
            for (Produto produto : fornecedor.getProdutos()) {
                listaProdutos.add(produtoService.findById(produto.getId()).get());
            }
        }
        fornecedor.setProdutos(listaProdutos);

        Fornecedor fornecedorSalvo = fornecedorService.save(fornecedor);
        List<Fornecedor> listaFornecedores = new ArrayList<>();
        listaFornecedores.add(fornecedorSalvo);

        // Setar o fornecedor nos produtos
        if(fornecedor.getProdutos() != null) {
            for (Produto produto : fornecedorSalvo.getProdutos()) {
                produto.setFornecedores(listaFornecedores);
                produtoService.save(produto);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.save(fornecedorSalvo));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        if (!fornecedorService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado!");
        }

        fornecedorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Fornecedor deletado com sucesso!");
    }
}
