package com.example.demo.resources;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.assember.PostAssember;
import com.example.demo.entity.Anexo;
import com.example.demo.entity.Grupo;
import com.example.demo.entity.Post;
import com.example.demo.entity.Usuario;
import com.example.demo.services.impl.AnexoServiceImpl;
import com.example.demo.services.impl.PostServiceImpl;
import com.example.demo.services.impl.UsuarioServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostServiceImpl postServiceImpl;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private AnexoServiceImpl anexoServiceImpl;

    @GetMapping("{id}")
    public ResponseEntity<Post> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(postServiceImpl.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(postServiceImpl.findAll());
    }

    @RequestMapping(headers=("content-type=multipart/*"), method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Post> add(@RequestPart("postDTO") String postDTOString,
                                    @RequestPart(value = "anexo", required = false) MultipartFile anexo) throws Exception {

        PostDTO postDTO = new ObjectMapper().readValue(postDTOString, PostDTO.class);
        Post post = PostAssember.dtoToEntityModel(postDTO);

        if(anexo != null) {
            Anexo anexoValidadoSalvo = anexoServiceImpl.validaAnexo(anexo);

            if(anexoValidadoSalvo != null) {
                post.setAnexo(anexoValidadoSalvo);
            }
        } else {
            post.setAnexo(null);
        }

        return new ResponseEntity<>(postServiceImpl.save(post), CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PostDTO postDTO) throws Exception {
        Post post = PostAssember.dtoToEntityModel(postDTO);

        if(!postServiceImpl.existsById(id)) return ResponseEntity.notFound().build();
        post.setId(id);

        return new ResponseEntity<>(postServiceImpl.save(post), NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(value = "id") Long id) {
       Post post = postServiceImpl.findById(id);
       postServiceImpl.delete(post);
    }

    @GetMapping("usuario/{id}")
    public List<Post> getAllByUsuario(@PathVariable(value = "id") Long id) {
        Usuario usuario = usuarioServiceImpl.findById(id);
        List<Post> postList = postServiceImpl.findByUsuario(usuario, Sort.by("dataPostagem").ascending());
        List<Post> timelinePosts = new ArrayList<>();

        if(postList != null) {
            for(Post p : postList) {
                if (p.getGrupo() == null) {
                    timelinePosts.add(p);
                }
            }
            return timelinePosts;
        }

        return null;
    }

    @GetMapping("grupo/{grupo}")
    public ResponseEntity<List<Post>> getAllByGrupo(@PathVariable(value = "grupo") Grupo grupo) {
        return ResponseEntity.ok(postServiceImpl.findAllByGrupo(grupo));
    }

    @GetMapping("perfil/{usuario}")
    public ResponseEntity<List<Post>> getAllByPerfilUsuario(@PathVariable(value = "usuario") Usuario usuario) {
        List<Post> postList = postServiceImpl.findByUsuario(usuario, Sort.by("dataPostagem").descending());
        List<Post> timelinePosts = new ArrayList<>();

        if(postList != null) {
            for (Post p : postList) {
                if (p.getGrupo() == null) {
                    timelinePosts.add(p);
                }
            }

            return ResponseEntity.ok(timelinePosts);
        }

        return null;
    }
}