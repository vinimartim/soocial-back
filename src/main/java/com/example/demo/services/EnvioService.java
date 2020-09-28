package com.example.demo.services;

import com.example.demo.dto.MensagemDTO;
import com.example.demo.entity.Envio;
import com.example.demo.entity.Mensagem;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.RegradeNegocioException;
import com.example.demo.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EnvioService {

    @Autowired
    private EnvioRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public Envio save(Envio entity) {
        return repository.save(entity);
    }

    public Optional<Envio> findById(Long id) { return repository.findById(id); }

    public Optional<Envio> findByMensagem(Mensagem mensagem) { return repository.findByMensagem(mensagem); }

    public boolean existsById(Long id) { return repository.existsById(id); }

    public boolean existsByMensagem(Mensagem mensagem) { return repository.existsByMensagem(mensagem); }

    public List<Envio> findAllByDestinatario(Usuario usuario) {
        return repository.findAllByDestinatario(usuario);
    }

    public List<Envio> findAllByRemetente(Usuario usuario) { return repository.findAllByRemetente(usuario); }

    public Envio setarEnvio(MensagemDTO mensagemDTO) {
        Envio envio = new Envio();
        Usuario remetente = mensagemDTO.getRemetente();
        Usuario destinatario = usuarioService
                .findByUsername(mensagemDTO.getDestinatario().getUsername())
                .orElseThrow(() -> new RegradeNegocioException("Destinatário inválido"));
        envio.setRemetente(remetente);
        envio.setDestinatario(destinatario);

        return envio;
    }

    public void delete(Envio entity) {
        repository.delete(entity);
    }
}
