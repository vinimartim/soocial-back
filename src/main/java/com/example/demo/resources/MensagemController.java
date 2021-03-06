package com.example.demo.resources;

import com.example.demo.dto.MensagemDTO;
import com.example.demo.dto.assember.MensagemAssember;
import com.example.demo.entity.Anexo;
import com.example.demo.entity.Mensagem;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.RegradeNegocioException;
import com.example.demo.services.impl.AnexoServiceImpl;
import com.example.demo.services.impl.MensagemServiceImpl;
import com.example.demo.services.impl.UsuarioServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/mensagem")
public class MensagemController {

    @Autowired
    private MensagemServiceImpl mensagemServiceImpl;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private AnexoServiceImpl anexoServiceImpl;

    @GetMapping("{id}")
    public ResponseEntity<Mensagem> getById(@PathVariable(value = "id") Long id) {
         return ResponseEntity.ok(mensagemServiceImpl.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Mensagem>> getAll() {
        return ResponseEntity.ok(mensagemServiceImpl.findAll());
    }

    @RequestMapping(headers=("content-type=multipart/*"), method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Mensagem> add(@RequestPart("mensagemDTO") String mensagemDTOString,
                                    @Nullable @RequestPart(value = "anexo", required = false) MultipartFile anexo) throws Exception {
        @Valid MensagemDTO mensagemDTO = new ObjectMapper().readValue(mensagemDTOString, MensagemDTO.class);
        Mensagem mensagem = MensagemAssember.dtoToEntityModel(mensagemDTO);

        if(anexo != null) {
            Anexo anexoValidado = anexoServiceImpl.validaAnexo(anexo);
            mensagem.setAnexo(anexoValidado);
        } else {
            mensagem.setAnexo(null);
        }

        return new ResponseEntity<>(mensagemServiceImpl.save(mensagem), CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Mensagem> update(@PathVariable(value = "id") Long id, @Valid @RequestBody MensagemDTO mensagemDTO) {

        Mensagem mensagem = MensagemAssember.dtoToEntityModel(mensagemDTO);

        if(!mensagemServiceImpl.existsById(id)) return ResponseEntity.notFound().build();
        mensagem.setId(id);

        return new ResponseEntity<>(mensagemServiceImpl.update(mensagem), NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        Mensagem mensagem = mensagemServiceImpl.findById(id);
        mensagemServiceImpl.delete(mensagem);
    }

    // Retorna todas as mensagens de um determinado destinatario
    @GetMapping("destinatario/{id}")
    public ResponseEntity<List<Mensagem>> getAllByDestinatario(@PathVariable(value = "id") Long id) {
        Usuario usuario = usuarioServiceImpl.findById(id);
        return ResponseEntity.ok(mensagemServiceImpl.findAllByDestinatario(id));
    }

    // Retorna todas as mensagens de um determinado remetente
    @GetMapping("remetente/{id}")
    public ResponseEntity<List<Mensagem>> getAllByRemetente(@PathVariable(value = "id") Long id) {
        // Busca um usuário e se não encontra lança uma exceção
        Usuario usuario = usuarioServiceImpl.findById(id);
        return ResponseEntity.ok(mensagemServiceImpl.findAllByRemetente(id));
    }
}