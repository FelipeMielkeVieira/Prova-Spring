package br.sc.senai.provaspring.controller;

import br.sc.senai.provaspring.model.dto.EnderecoDTO;
import br.sc.senai.provaspring.model.entities.Endereco;
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
@RequestMapping("/prova/endereco")
public class EnderecoController {

    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<Endereco> enderecoOptional = enderecoService.findById(id);
        if (enderecoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(enderecoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Endereco> save(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO, endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(endereco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody @Valid EnderecoDTO enderecoDTO) {
        if (!enderecoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado!");
        }

        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO, endereco);
        endereco.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.save(endereco));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        if (!enderecoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado!");
        }
        enderecoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Endereço deletado com sucesso!");
    }
}
