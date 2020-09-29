package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "anexo")
public class Anexo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private String path;

    @Column
    private String extensao;

    @Column
    private long tamanho;

    @JsonIgnore
    @OneToOne(mappedBy = "anexo")
    private Mensagem mensagem;

    @JsonIgnore
    @OneToOne(mappedBy = "anexo")
    private Post post;
}