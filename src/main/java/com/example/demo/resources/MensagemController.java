package com.example.demo.resources;

import com.example.demo.dto.MensagemDTO;
import com.example.demo.dto.assember.MensagemAssember;
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
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
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
    public Mensagem get(@PathVariable(value = "id") Long id) {
         return service
            .findById(id)
            .orElseThrow(() -> new RegradeNegocioException("Mensagem não encontrada"));
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<Mensagem> getAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Mensagem> add(@Valid @RequestBody MensagemDTO mensagemDTO) {
        Mensagem mensagem = MensagemAssember.dtoToEntityModel(mensagemDTO);

        Envio envio = envioService.setarEnvio(mensagemDTO);
        envio.setMensagem(mensagem);

        if(service.save(mensagem) != null && envioService.save(envio) != null) {
            return new ResponseEntity<>(mensagem, CREATED);
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Mensagem> update(@PathVariable(value = "id") Long id, @RequestBody MensagemDTO mensagemDTO) {
        Mensagem mensagem = MensagemAssember.dtoToEntityModel(mensagemDTO);

        if(!service.existsById(id)) return ResponseEntity.notFound().build();

        mensagem.setId(id);
        service.save(mensagem);

        return new ResponseEntity<>(mensagem, NO_CONTENT);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        Mensagem mensagem = service
                .findById(id)
                .orElseThrow(() -> new RegradeNegocioException("Mensagem não encontrada"));

        Envio envio = envioService
                .findByMensagem(mensagem)
                .orElseThrow(() -> new RegradeNegocioException("Dados da mensagem não encontrados"));

        envioService.delete(envio);
        service.delete(mensagem);
    }

    @GetMapping("/usuario/{id}")
    @ResponseStatus(OK)
    public ResponseEntity<List<Mensagem>> getAllByUsuarioId(@PathVariable(value = "id") Long id) {
        Usuario usuario = usuarioService
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado"));

        List<Envio> listaEnvio = envioService.findAllByDestinatario(usuario);
        ArrayList<Mensagem> listaMensagem = new ArrayList<>();

        for(Envio e : listaEnvio) {
            listaMensagem.add(e.getMensagem());
        }

        return ResponseEntity.ok(listaMensagem);
    }
}