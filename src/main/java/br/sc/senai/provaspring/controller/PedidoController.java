package br.sc.senai.provaspring.controller;

import br.sc.senai.provaspring.model.dto.PedidoDTO;
import br.sc.senai.provaspring.model.dto.ProdutoPedidoDTO;
import br.sc.senai.provaspring.model.entities.EnderecoEntrega;
import br.sc.senai.provaspring.model.entities.Pedido;
import br.sc.senai.provaspring.model.entities.ProdutoPedido;
import br.sc.senai.provaspring.service.ClienteService;
import br.sc.senai.provaspring.service.EnderecoEntregaService;
import br.sc.senai.provaspring.service.PedidoService;
import br.sc.senai.provaspring.service.ProdutoPedidoService;
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
@RequestMapping("/prova/pedido")
public class PedidoController {

    private PedidoService pedidoService;
    private ProdutoPedidoService produtoPedidoService;
    private EnderecoEntregaService enderecoEntregaService;
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<Pedido> pedidoOptional = pedidoService.findById(id);
        if (pedidoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedidoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Pedido> save(@RequestBody @Valid PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        BeanUtils.copyProperties(pedidoDTO, pedido);

        Pedido pedidoSalvo = pedidoService.save(pedido);
        List<ProdutoPedido> listaProdutosPedido = new ArrayList<>();

        // Setar pedido nos produtos
        for (ProdutoPedidoDTO produtoPedidoDTO : pedidoDTO.getProdutos()) {
            ProdutoPedido produtoPedido = new ProdutoPedido();
            BeanUtils.copyProperties(produtoPedidoDTO, produtoPedido);
            produtoPedido.setPedido(pedidoSalvo);
            listaProdutosPedido.add(produtoPedidoService.save(produtoPedido));
        }
        pedidoSalvo.setProdutos(listaProdutosPedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedidoSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody @Valid Pedido pedido) {
        if (!pedidoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado!");
        }

        pedido.setId(id);
        List<ProdutoPedido> listaProdutosPedido = new ArrayList<>();

        // Buscar e adicionar produtos no pedido
        if(pedido.getProdutos() != null) {
            for (ProdutoPedido produtoPedido : pedido.getProdutos()) {
                listaProdutosPedido.add(produtoPedidoService.findById(produtoPedido.getId()).get());
            }
        }
        pedido.setProdutos(listaProdutosPedido);

        // Buscar e setar cliente no pedido
        if(pedido.getCliente() != null) {
            pedido.setCliente(clienteService.findById(pedido.getCliente().getId()).get());
        }

        // Buscar e setar endereço no pedido
        if(pedido.getEndereco() != null) {
            pedido.setEndereco(enderecoEntregaService.findById(pedido.getEndereco().getId()).get());
        }

        Pedido pedidoSalvo = pedidoService.save(pedido);


        // Atualizar o pedido no endereço
        if(pedidoSalvo.getEndereco() != null) {
            EnderecoEntrega enderecoEntrega = pedidoSalvo.getEndereco();
            enderecoEntrega.setPedido(pedidoSalvo);
            enderecoEntregaService.save(enderecoEntrega);
        }

        // Atualizar o pedido nos produtos
        if(pedido.getProdutos() != null) {
            for (ProdutoPedido produtoPedido : pedido.getProdutos()) {
                produtoPedido.setPedido(pedidoSalvo);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.save(pedidoSalvo));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        if (!pedidoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado!");
        }
        pedidoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido deletado com sucesso!");
    }
}
