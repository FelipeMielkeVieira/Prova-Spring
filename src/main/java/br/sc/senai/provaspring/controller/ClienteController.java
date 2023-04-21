package br.sc.senai.provaspring.controller;

import br.sc.senai.provaspring.model.dto.ClienteDTO;
import br.sc.senai.provaspring.model.entities.Cliente;
import br.sc.senai.provaspring.model.entities.Endereco;
import br.sc.senai.provaspring.service.CartaoCreditoService;
import br.sc.senai.provaspring.service.ClienteService;
import br.sc.senai.provaspring.service.EnderecoService;
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
@RequestMapping("/prova/cliente")
public class ClienteController {

    private ClienteService clienteService;
    private CartaoCreditoService cartaoCreditoService;
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<Cliente> clienteOptional = clienteService.findById(id);
        if (clienteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody @Valid ClienteDTO clienteDTO) {
        if (!clienteService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }

        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);
        cliente.setId(id);

        // Setar cartão do cliente
        if(cliente.getCartao() != null) {
            cliente.setCartao(cartaoCreditoService.findById(cliente.getCartao().getId()).get());
        }

        // Setar endereços do cliente
        List<Endereco> listaEnderecos = new ArrayList<>();
        if(cliente.getEnderecos() != null) {
            for (Endereco endereco : cliente.getEnderecos()) {
                listaEnderecos.add(enderecoService.findById(endereco.getId()).get());
            }
            cliente.setEnderecos(listaEnderecos);
        }

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(cliente));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        if (!clienteService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        clienteService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
    }
}
