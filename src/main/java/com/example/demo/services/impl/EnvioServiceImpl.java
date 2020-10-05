package com.example.demo.services.impl;

import com.example.demo.dto.MensagemDTO;
import com.example.demo.entity.Envio;
import com.example.demo.entity.Mensagem;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.RegradeNegocioException;
import com.example.demo.repository.EnvioRepository;
import com.example.demo.services.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnvioServiceImpl implements EnvioService {

    @Autowired
    private EnvioRepository repository;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    public Envio save(Envio entity) {
        if (usuarioServiceImpl.findByUsername(entity.getDestinatario().getUsername()) == null &&
        usuarioServiceImpl.findById(entity.getRemetente().getId()) == null) {
            throw new RegradeNegocioException("Usuário não encontrado");
        }

        return repository.save(entity);
    }

    public Envio findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new RegradeNegocioException("Dados de envio não encontrados"));
    }

    public Envio findByMensagem(Mensagem mensagem) {
        return repository
                .findByMensagem(mensagem)
                .orElseThrow(() -> new RegradeNegocioException("Dados de envio não encontrados"));
    }

    public List<Envio> findAllByDestinatario(Usuario usuario) {
        return repository.findAllByDestinatario(usuario);
    }

    public List<Envio> findAllByRemetente(Usuario usuario) { return repository.findAllByRemetente(usuario); }

    public void delete(Envio entity) {
        repository.delete(entity);
    }

    public Envio setarEnvio(MensagemDTO mensagemDTO) {
//        Envio envio = new Envio();
//        Usuario remetente = mensagemDTO.getRemetente();
//        Usuario destinatario = usuarioService.findByUsername(mensagemDTO.getDestinatario().getUsername());
//
//        if(destinatario == null) {
//            throw new RegradeNegocioException("Destinatário não encontrado");
//        }
//
//        envio.setRemetente(remetente);
//        envio.setDestinatario(destinatario);

        return null;
    }
}
