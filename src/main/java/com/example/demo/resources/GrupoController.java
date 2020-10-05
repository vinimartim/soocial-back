package com.example.demo.resources;

import com.example.demo.dto.GrupoDTO;
import com.example.demo.dto.assember.GrupoAssember;
import com.example.demo.entity.Grupo;
import com.example.demo.entity.Usuario;
import com.example.demo.services.impl.GrupoServiceImpl;
import com.example.demo.services.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/grupo")
public class GrupoController {

    @Autowired
    private GrupoServiceImpl grupoServiceImpl;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping("{id}")
    public ResponseEntity<Grupo> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(grupoServiceImpl.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Grupo>> getAll() {
        return ResponseEntity.ok(grupoServiceImpl.findAll());
    }

    @PostMapping
    public ResponseEntity<Grupo> add(@RequestBody GrupoDTO grupoDTO) {
        Grupo grupo = GrupoAssember.dtoToEntityModel(grupoDTO);
        return new ResponseEntity<>(grupoServiceImpl.save(grupo), CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Grupo> update(@PathVariable(value = "id") Long id, @Valid @RequestBody GrupoDTO grupoDTO) {
        Grupo grupo = GrupoAssember.dtoToEntityModel(grupoDTO);

        if(!grupoServiceImpl.existsById(id)) return ResponseEntity.notFound().build();
        grupo.setId(id);

        return new ResponseEntity<>(grupoServiceImpl.update(grupo), NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        Grupo grupo = grupoServiceImpl.findById(id);
        grupoServiceImpl.delete(grupo);
    }

    @GetMapping("dono/{donoId}")
    public ResponseEntity<List<Grupo>> getByDono(@PathVariable(value = "donoId") Long donoId) {
        Usuario dono = usuarioServiceImpl.findById(donoId);
        return ResponseEntity.ok(grupoServiceImpl.findByDono(dono));
    }

    @GetMapping("membro/{membro}")
    public ResponseEntity<List<Grupo>> getByMembro(@PathVariable(value = "membro") Usuario membro) {
        return ResponseEntity.ok(grupoServiceImpl.findByMembros(membro));
    }

    @GetMapping("membros/{grupo}")
    public ResponseEntity<List<Usuario>> getMembroById(@PathVariable(value = "grupo") Grupo grupo) {
        return ResponseEntity.ok(grupoServiceImpl.findMembrosByGrupo(grupo));
    }

    @GetMapping("admins/{grupo}")
    public ResponseEntity<List<Usuario>> getAdmsById(@PathVariable(value = "grupo") Grupo grupo) {
        return ResponseEntity.ok(grupoServiceImpl.findAdmsByGrupo(grupo));
    }
}