package com.example.demo.resources;

import com.example.demo.entity.Envio;
import com.example.demo.entity.Mensagem;
import com.example.demo.entity.Usuario;
import com.example.demo.services.impl.EnvioServiceImpl;
import com.example.demo.services.impl.MensagemServiceImpl;
import com.example.demo.services.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/envio")
public class EnvioController {

    @Autowired
    private EnvioServiceImpl envioServiceImpl;

    @Autowired
    private MensagemServiceImpl mensagemServiceImpl;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping("mensagens/destinatario/{idUsuario}")
    public ResponseEntity<List<Envio>> getAllByDestinatario(@PathVariable(value = "idUsuario") Long idUsuario) {

        Usuario destinatario = usuarioServiceImpl.findById(idUsuario);
        List<Envio> listaEnvio = envioServiceImpl.findAllByDestinatario(destinatario);
        return ResponseEntity.ok(listaEnvio);
    }

    @GetMapping("mensagem/{idMensagem}")
    public ResponseEntity<Envio> getByMensagem(@PathVariable(value = "idMensagem") Long idMensagem) {

        Mensagem mensagem = mensagemServiceImpl.findById(idMensagem);
        Envio envio = envioServiceImpl.findByMensagem(mensagem);

        return ResponseEntity.ok(envio);
    }

    @GetMapping("mensagens/remetente/{idUsuario}")
    public ResponseEntity<List<Envio>> getAllByRemetente(@PathVariable(value = "idUsuario") Long idUsuario) {

        Usuario remetente = usuarioServiceImpl.findById(idUsuario);
        List<Envio> listaEnvio = envioServiceImpl.findAllByRemetente(remetente);

        return ResponseEntity.ok(listaEnvio);
    }
}
