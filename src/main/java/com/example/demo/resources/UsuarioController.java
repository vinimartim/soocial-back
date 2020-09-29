package com.example.demo.resources;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.assember.UsuarioAssember;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.RegradeNegocioException;
import com.example.demo.services.UsuarioService;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("{id}")
    @ResponseStatus(OK)
    public Usuario get(@PathVariable(value = "id") Long id) {
        return service
            .findById(id)
            .orElseThrow(() -> new RegradeNegocioException("Usuário não encontrado"));
    }

    @GetMapping("username/{username}")
    @ResponseStatus(OK)
    public ResponseEntity<Usuario> getByUsername(@PathVariable(value = "username") String username) {
        Usuario usuario = service.findByUsername(username);

        if(usuario != null) {
            return ResponseEntity.ok(usuario);
        }

        throw new RegradeNegocioException("Usuário não encontrado");
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<Usuario> getAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Usuario> add(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioAssember.dtoToEntityModel(usuarioDTO);

        if(service.save(usuario) != null) {
            return new ResponseEntity<>(usuario, CREATED);
        }

        throw new RegradeNegocioException("Não foi possível salvar o usuário");
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Usuario> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioAssember.dtoToEntityModel(usuarioDTO);
        Usuario existente = service
                .findById(id)
                .orElseThrow(() -> new RegradeNegocioException("Usuário não encontrado"));

        usuario.setId(existente.getId());

        if(service.update(usuario) != null) {
            return new ResponseEntity<>(usuario, NO_CONTENT);
        }

        throw new RegradeNegocioException("Não foi possível salvar o usuário");
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT )
    public void delete(@PathVariable(value = "id") Long id) {
        Usuario usuario = service
                .findById(id)
                .orElseThrow(() -> new RegradeNegocioException("Usuário não encontrado"));

        service.delete(usuario);
    }

    @GetMapping("seguidores/{id}")
    public ResponseEntity<List<Usuario>> getPosts(@PathVariable(value = "id") Long id) {
        List<Usuario> seguindo = service.findSeguidoresById(id);

        if(seguindo != null) {
            return ResponseEntity.ok(seguindo);
        }

        return ResponseEntity.notFound().build();
    }
}