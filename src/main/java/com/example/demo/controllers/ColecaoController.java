package com.example.demo.controllers;

import com.example.demo.entity.Colecao;
import com.example.demo.services.ColecaoService;

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
@RequestMapping("/api/colecao")
public class ColecaoController {
    
    private ColecaoService service;

    @GetMapping("{id}")
    @ResponseStatus(OK)
    public Colecao get(@PathVariable(value = "id") Long id) {
        return service
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Coleção não encontrada"));
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<Colecao> getAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Colecao add(@RequestBody Colecao colecao) {
        return service.save(colecao);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public Colecao update(@PathVariable(value = "id") Long id, @RequestBody Colecao colecao) {
        return service
            .findById(id)
            .map(colecaoExistente -> {
                colecao.setId(colecaoExistente.getId());
                service.save(colecao);
                return colecaoExistente;
            })
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Coleção não encontrada"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(Long id) {
        service
            .findById(id)
            .map(colecao -> {
                service.delete(colecao);
                return colecao;
            })
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Coleção não encontarda"));
    }
}