package com.example.demo.services.impl;

import com.example.demo.entity.Seguidores;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.RegradeNegocioException;
import com.example.demo.repository.SeguidoresRepository;
import com.example.demo.services.SeguidoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeguidoresServiceImpl implements SeguidoresService {

    @Autowired
    private SeguidoresRepository repository;

    public List<Seguidores> findAllByQuemSegue(Long id) {
        return repository.findAllByQuemSegue(id);
    }

    public Seguidores save(Seguidores entity) {
        return repository.save(entity);
    }

    public Seguidores findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new RegradeNegocioException("Erro ao seguir, usuário não encontrado"));
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
