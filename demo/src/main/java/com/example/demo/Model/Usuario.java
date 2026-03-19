package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nombre del usuario (hasta 80 caracteres)
    @Column(nullable = false, length = 80)
    private String mombre;

    // correo electrónico del usuario
    @Column(nullable = false, length = 120)
    private String correo;

    /*
     * Relación OneToOne con PerfilUsuario.
     * mappedBy = "usuario": PerfilUsuario es el dueño de la FK.
     * cascade = ALL: el perfil se crea/elimina junto con el usuario.
     */
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("usuario")
    private PerfilUsuario perfil;

    public Usuario() {
    }

    public Usuario(Long id, String mombre, String correo, PerfilUsuario perfil) {
        this.id = id;
        this.mombre = mombre;
        this.correo = correo;
        this.perfil = perfil;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMombre() { return mombre; }
    public void setMombre(String mombre) { this.mombre = mombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public PerfilUsuario getPerfil() { return perfil; }
    public void setPerfil(PerfilUsuario perfil) { this.perfil = perfil; }

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", mombre='" + mombre + "', correo='" + correo + "', perfil=" + perfil + "}";
    }
}
