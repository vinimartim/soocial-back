package com.example.demo.resources;

import com.example.demo.entity.Envio;
import com.example.demo.entity.Mensagem;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.RegradeNegocioException;
import com.example.demo.services.EnvioService;
import com.example.demo.services.MensagemService;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/envio")
public class EnvioController {

    @Autowired
    private EnvioService service;

    @Autowired
    private MensagemService mensagemService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("mensagens/destinatario/{idUsuario}")
    @ResponseStatus(OK)
    public ResponseEntity<List<Envio>> getAllByDestinatario(@PathVariable(value = "idUsuario") Long idUsuario) {
        Usuario destinatario = usuarioService
                .findById(idUsuario)
                .orElseThrow(() -> new RegradeNegocioException("Usuário não encontrado"));

        List<Envio> listaEnvio = service.findAllByDestinatario(destinatario);
        return ResponseEntity.ok(listaEnvio);
    }

    @GetMapping("mensagem/{idMensagem}")
    @ResponseStatus(OK)
    public ResponseEntity<Envio> getByMensagem(@PathVariable(value = "idMensagem") Long idMensagem) {
        Mensagem mensagem = mensagemService
                .findById(idMensagem)
                .orElseThrow(() -> new RegradeNegocioException("Mensagem não encontrada"));

        Envio envio = service
                .findByMensagem(mensagem)
                .orElseThrow(() -> new RegradeNegocioException("Dados da mensagem não encontrados"));

        return ResponseEntity.ok(envio);
    }

    @GetMapping("mensagens/remetente/{idUsuario}")
    @ResponseStatus(OK)
    public ResponseEntity<List<Envio>> getAllByRemetente(@PathVariable(value = "idUsuario") Long idUsuario) {

        Usuario remetente = usuarioService
                .findById(idUsuario)
                .orElseThrow(() -> new RegradeNegocioException("Usuário não encontrado"));

        List<Envio> listaEnvio = service.findAllByRemetente(remetente);
        return ResponseEntity.ok(listaEnvio);
    }
}
