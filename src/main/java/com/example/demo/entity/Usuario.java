package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

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

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column
    private String senha;

    @Column(name = "email")
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
    private List<Grupo> grupo;

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