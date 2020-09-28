package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "usuario",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_username_email",
                        columnNames = {"username", "email"}
                )
        }
)
public class Usuario implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private String sobrenome;

    @Column(name = "username", unique = true)
    private String username;

    @JsonIgnore
    @Column
    private String senha;

    @Column(name = "email", unique = true)
    private String email;

    @Column
    private String dataNascimento;

    @Column
    private LocalDateTime loginAt;

    @Column
    private LocalDateTime logoutAt;

    @Column
    private Boolean statusCadastro;

    @Column
    private String genero;

    @Column
    private String telefone;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "usuario"
    )
    private List<Colecao> colecao;

    @JsonIgnore
    @OneToOne(
            cascade = CascadeType.ALL,
            mappedBy = "usuario"
    )
    private Comentario comentario;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Post> post;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(columnDefinition = "VARCHAR(10) default ROLE_USER", insertable = false, updatable = true)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "nome")
    )
    private List<Role> roles;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario")
    private Session session;

    @OneToMany(mappedBy = "dono")
    private Grupo grupo;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return false;
    }
}