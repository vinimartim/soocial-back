package com.example.demo.dto;

import com.example.demo.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class GrupoDTO {

    @NotBlank
    @Size(min = 3, max = 5)
    private String nome;

    @NotBlank
    private String descricao;

    @NotBlank
    private String privacidade;

    @NotNull
    private Usuario dono;

    private List<Usuario> administradores;
    private List<Usuario> membros;

    public GrupoDTO() {
        this.administradores = new ArrayList<>();
        this.membros = new ArrayList<>();
    }
}
