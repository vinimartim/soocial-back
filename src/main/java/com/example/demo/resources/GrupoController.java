package com.example.demo.resources;

import com.example.demo.entity.Grupo;
import com.example.demo.services.GrupoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/grupo")
public class GrupoController {

    @Autowired
    private GrupoService service;

    @GetMapping("{id}")
    @ResponseStatus(OK)
    public Grupo get(@PathVariable(value = "id") Long id) {
        return service
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Grupo não encpntrado"));
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<Grupo> getAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Grupo add(@RequestBody Grupo grupo) {
        return service.save(grupo);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public Grupo update(@PathVariable(value = "id") Long id, @RequestBody Grupo grupo) {
        return service
            .findById(id)
            .map(grupoExistente -> {
                grupo.setId(grupoExistente.getId());
                service.save(grupo);
                return grupoExistente;
            })
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Grupo não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        service
            .findById(id)
            .map(grupo -> {
                service.delete(grupo);
                return grupo;
            })
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Grupo não encontrado"));
    }
}