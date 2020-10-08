package com.example.demo.services.impl;

import com.example.demo.entity.Mensagem;
import com.example.demo.exception.RegradeNegocioException;
import com.example.demo.repository.MensagemRepository;
import com.example.demo.services.MensagemService;
import com.example.demo.services.UsuarioService;
import com.example.demo.utils.classificador.Classificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MensagemServiceImpl implements MensagemService {
    
    @Autowired
    private MensagemRepository repository;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private Classificador classificador;

    public MensagemServiceImpl() {}

    public Mensagem save(Mensagem entity) throws Exception {
        entity.setSpam(classificador.classificar(entity.getConteudo()));

        // Checa se remetente ou destinatário existem
        if(usuarioServiceImpl.findById(entity.getEnvio().getRemetente().getId()) == null &&
                usuarioServiceImpl.findById(entity.getEnvio().getDestinatario().getId()) == null) {
            throw new RegradeNegocioException("Remetente ou destinatário inválido");
        }

        return repository.save(entity);
    }

    public Mensagem update(Mensagem entity) { return repository.save(entity); }

    public void delete(Mensagem entity) {
        repository.delete(entity);
    }

    public Mensagem findById(Long id) {

        return repository
                .findById(id)
                .orElseThrow(() -> new RegradeNegocioException("Mensagem não encontrada"));
    }

    public List<Mensagem> findAll() {
        return repository.findAll();
    }

    public boolean existsById(Long id) { return repository.existsById(id); }

    public List<Mensagem> findAllByDestinatario(Long id) {
        return repository.findAllByDestinatario(id);
    }

    public List<Mensagem> findAllByRemetente(Long id) {
        return repository.findAllByRemetente(id);
    }
}