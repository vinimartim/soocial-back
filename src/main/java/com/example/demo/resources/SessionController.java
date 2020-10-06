package com.example.demo.resources;

import com.example.demo.entity.Session;
import com.example.demo.entity.Usuario;
import com.example.demo.services.impl.SessionServiceImpl;
import com.example.demo.services.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/load-session")
public class SessionController {

    @Autowired
    private SessionServiceImpl sessionServiceImpl;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    // Retorna a sessão de um usuário
    @GetMapping
    public ResponseEntity<Session> getSession(@RequestHeader("Authorization") String token) {
        String[] jwt = token.split(" ");

        Session session = sessionServiceImpl.findByToken(jwt[1]);
        Usuario usuario = session.getUsuario();

        return ResponseEntity.ok(new Session(jwt[1], usuario));
    }
}
