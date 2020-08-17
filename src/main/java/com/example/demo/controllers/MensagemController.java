package com.example.demo.controllers;

import com.example.demo.entity.Mensagem;
import com.example.demo.services.MensagemService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensagem")
public class MensagemController {
    
    private MensagemService service;

    @GetMapping("{id}")
    @ResponseStatus(OK)
    public Mensagem get(Long id) {
        return service
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Mensagem não encontrada"));
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<Mensagem> getAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Mensagem add(@RequestBody Mensagem mensagem) {
        return service.save(mensagem);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public Mensagem update(@PathVariable(value = "id") Long id, @RequestBody Mensagem mensagem) {
        return service
            .findById(id)
            .map(mensagemExistente -> {
                mensagem.setId(mensagemExistente.getId());
                service.save(mensagem);
                return mensagemExistente;
            })
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Mensagem não encontrada"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(Long id) {
        service
            .findById(id)
            .map(mensagem -> {
                service.delete(mensagem);
                return mensagem;
            })
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Mensagem não encontrada"));
    }


}