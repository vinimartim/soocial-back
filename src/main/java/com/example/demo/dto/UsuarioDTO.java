package com.example.demo.dto;

import com.example.demo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @Size(max = 16)
    private String username;

    @NotBlank
    private String senha;

    @Email
    private String email;

    @NotBlank
    private String dataNascimento;

    @NotBlank
    private String genero;

    @NotBlank
    @Size(max = 11)
    private String telefone;

    private boolean statusCadastro;

    private List<Role> roles;

    public UsuarioDTO() {
        this.roles = new ArrayList<>();
    }

}
