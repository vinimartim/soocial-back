package com.example.demo.resources;

import com.example.demo.entity.Seguidores;
import com.example.demo.repository.SeguidoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/seguidores")
public class SeguidoresController {

    @Autowired
    private SeguidoresRepository repository;

    @GetMapping
    public String hello() {
        return "Hello";
    }

    @GetMapping("{id}")
    public List<Seguidores> getSeguidores(@PathVariable(value = "id") Long idQuemSegue) {
        return repository.findAllByQuemSegue(idQuemSegue);
    }
}
