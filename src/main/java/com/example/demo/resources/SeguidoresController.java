package com.example.demo.resources;

import com.example.demo.entity.Seguidores;
import com.example.demo.services.impl.SeguidoresServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/seguidores")
public class SeguidoresController {

    @Autowired
    private SeguidoresServiceImpl seguidoresServiceImpl;

    @GetMapping("{idQuemSegue}")
    public ResponseEntity<List<Seguidores>> getAllById(@PathVariable(value = "idQuemSegue") Long idQuemSegue) {
        return ResponseEntity.ok(seguidoresServiceImpl.findAllByQuemSegue(idQuemSegue));
    }

    @PostMapping
    public ResponseEntity<Seguidores> add(@RequestBody Seguidores seguidores) {
        return new ResponseEntity<>(seguidoresServiceImpl.save(seguidores), CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Seguidores> add(@RequestBody Seguidores seguidores, @PathVariable(value = "id") Long id) {
        Seguidores seguidoresExistentes = seguidoresServiceImpl.findById(id);

        if(!seguidoresServiceImpl.existsById(id)) return ResponseEntity.notFound().build();
        seguidores.setId(id);

        return new ResponseEntity<>(seguidoresServiceImpl.save(seguidores), CREATED);
    }
}
