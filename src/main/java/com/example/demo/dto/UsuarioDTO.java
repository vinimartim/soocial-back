package com.example.demo.dto;

import com.example.demo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioDTO {

    private String nome;
    private String sobrenome;
    private String username;
    private String senha;
    private String email;
    private String dataNascimento;
    private String genero;
    private String telefone;
    private List<Role> roles;

    public UsuarioDTO() {
        this.roles = new ArrayList<>();
    }

}
