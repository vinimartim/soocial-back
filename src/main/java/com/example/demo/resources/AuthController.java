package com.example.demo.resources;

import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.AuthResponse;
import com.example.demo.entity.Session;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.RegradeNegocioException;
import com.example.demo.services.impl.SessionServiceImpl;
import com.example.demo.services.impl.UsuarioServiceImpl;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private SessionServiceImpl sessionServiceImpl;

    @PostMapping
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws RegradeNegocioException {
        
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getSenha())
            );
        } catch (BadCredentialsException e) {
            throw new RegradeNegocioException("Usuário ou senha inválidos");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        final String mensagem = "Autorização efetuada com sucesso";
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        final Usuario usuario = usuarioServiceImpl.findByUsername(userDetails.getUsername());

        Session session = new Session(jwt, usuario);
        sessionServiceImpl.save(session);

        // Atualiza o último login
        usuario.setLoginAt(LocalDateTime.now());
        usuarioServiceImpl.update(usuario);

        return ResponseEntity.ok(new AuthResponse(mensagem, jwt, usuario));
    }
}
