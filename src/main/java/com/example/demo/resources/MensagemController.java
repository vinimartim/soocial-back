package com.example.demo.controllers;

import com.example.demo.dto.MensagemDTO;
import com.example.demo.entity.Envio;
import com.example.demo.entity.Mensagem;
import com.example.demo.entity.Usuario;
import com.example.demo.services.EnvioService;
import com.example.demo.services.MensagemService;

import com.example.demo.services.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/mensagem")
public class MensagemController {

    @Autowired
    private MensagemService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EnvioService envioService;

    @GetMapping("{id}")
    @ResponseStatus(OK)
    public Mensagem get(Long id) {
        return service
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Mensagem não encontrada"));
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<Mensagem> getAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Mensagem> add(@RequestBody MensagemDTO mensagemDTO) {
        Usuario remetente = mensagemDTO.getRemetente();
        Usuario destinatario = mensagemDTO.getDestinatario();
        Envio envio = new Envio();

        Mensagem mensagem = new Mensagem();
        mensagem.setAnexo(mensagemDTO.getAnexo());
        mensagem.setAssunto(mensagemDTO.getAssunto());
        mensagem.setConteudo(mensagemDTO.getConteudo());
        mensagem.setEdicao(mensagemDTO.isEdicao());
        mensagem.setSpam(mensagemDTO.isSpam());
        mensagem.setVisualizada(mensagemDTO.isVisualizada());

        envio.setRemetente(remetente);
        envio.setDestinatario(destinatario);
        envio.setMensagem(mensagem);

        if(envioService.save(envio) != null && service.save(mensagem) != null) {
            return new ResponseEntity<>(mensagem, CREATED);
        }

        return ResponseEntity.badRequest().build();
    }


    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public Mensagem update(@PathVariable(value = "id") Long id, @RequestBody Mensagem mensagem) {
        return service
            .findById(id)
            .map(mensagemExistente -> {
                mensagem.setId(mensagemExistente.getId());
                service.save(mensagem);
                return mensagemExistente;
            })
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Mensagem não encontrada"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(Long id) {
        service
            .findById(id)
            .map(mensagem -> {
                service.delete(mensagem);
                return mensagem;
            })
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Mensagem não encontrada"));
    }


}