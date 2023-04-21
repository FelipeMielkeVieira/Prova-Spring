package br.sc.senai.provaspring.controller;

import br.sc.senai.provaspring.model.dto.EnderecoEntregaDTO;
import br.sc.senai.provaspring.model.entities.Endereco;
import br.sc.senai.provaspring.model.entities.EnderecoEntrega;
import br.sc.senai.provaspring.service.EnderecoEntregaService;
import br.sc.senai.provaspring.service.EnderecoService;
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
@RequestMapping("/prova/enderecoentrega")
public class EnderecoEntregaController {

    private EnderecoEntregaService enderecoEntregaService;
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<EnderecoEntrega>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoEntregaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<EnderecoEntrega> enderecoEntregaOptional = enderecoEntregaService.findById(id);
        if (enderecoEntregaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço Entrega não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(enderecoEntregaOptional.get());
    }

    @PostMapping
    public ResponseEntity<EnderecoEntrega> save(@RequestBody @Valid EnderecoEntregaDTO enderecoEntregaDTO) {
        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
        BeanUtils.copyProperties(enderecoEntregaDTO, enderecoEntrega);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoEntregaService.save(enderecoEntrega));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody @Valid EnderecoEntregaDTO enderecoEntregaDTO) {
        if (!enderecoEntregaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço Entrega não encontrado!");
        }

        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
        enderecoEntrega.setId(id);

        if(enderecoEntregaDTO.getEndereco() == null) {
            enderecoEntrega.setEndereco(null);
        } else {
            if(enderecoEntregaDTO.getEndereco().getId() != null) {
                enderecoEntrega.setEndereco(enderecoService.findById(enderecoEntregaDTO.getEndereco().getId()).get());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(enderecoEntregaService.save(enderecoEntrega));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        if (!enderecoEntregaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço Entrega não encontrado!");
        }
        enderecoEntregaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Endereço Entrega deletado com sucesso!");
    }
}
