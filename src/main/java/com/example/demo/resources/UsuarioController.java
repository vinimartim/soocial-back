package com.example.demo.resources;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.assember.UsuarioAssember;
import com.example.demo.entity.Usuario;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("{id}")
    public ResponseEntity<Usuario> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("username/{username}")
    public ResponseEntity<Usuario> getByUsername(@PathVariable(value = "username") String username) {
        return ResponseEntity.ok(service.findByUsername(username));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Usuario> add(@Valid @RequestBody UsuarioDTO usuarioDTO) {

        Usuario usuario = UsuarioAssember.dtoToEntityModel(usuarioDTO);
        return new ResponseEntity<>(service.save(usuario), CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {

        Usuario usuario = UsuarioAssember.dtoToEntityModel(usuarioDTO);
        Usuario existente = service.findById(id);
        usuario.setId(existente.getId());
        return new ResponseEntity<>(service.update(usuario), NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(value = "id") Long id) {

        Usuario usuario = service.findById(id);
        service.delete(usuario);
    }

//    @GetMapping("seguidores/{id}")
//    public ResponseEntity<List<Usuario>> getPosts(@PathVariable(value = "id") Long id) {
//        List<Usuario> seguindo = service.findSeguidoresById(id);
//
//        if(seguindo != null) {
//            return ResponseEntity.ok(seguindo);
//        }
//
//        return ResponseEntity.notFound().build();
//    }
}