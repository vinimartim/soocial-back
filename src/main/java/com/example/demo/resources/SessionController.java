package com.example.demo.resources;

import com.example.demo.entity.Session;
import com.example.demo.entity.Usuario;
import com.example.demo.services.SessionService;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/load-session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> getSession(@RequestHeader("Authorization") String token) {
        String[] jwt = token.split(" ");

        Session session = sessionService.findByToken(jwt[1]);

        if(session == null) {
            return ResponseEntity.notFound().build();
        } else {
            Usuario usuario = session.getUsuario();

            return ResponseEntity.ok(new Session(jwt[1], usuario));
        }

    }
}
