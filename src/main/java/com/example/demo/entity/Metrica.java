package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Metrica {

    @Id
    private Long id;
//    private List<Usuario> curtidas;
//    private List<Usuario> comentarios;
//    private List<Usuario> compartilhamentos;
//    private List<Usuario> denuncias;
    private boolean destaque;
}