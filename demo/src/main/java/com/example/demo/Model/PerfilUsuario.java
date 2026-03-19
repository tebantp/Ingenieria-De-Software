package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "perfiles_usuario")
public class PerfilUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // documento de identidad único del usuario
    @Column(nullable = false, unique = true, length = 126)
    private String documento;

    // número de teléfono de contacto
    @Column(nullable = false, length = 20)
    private String telefono;

    /*
     * Relación OneToOne con Usuario.
     * optional = false: todo perfil debe estar vinculado a un usuario existente.
     * unique = true en JoinColumn: cada usuario tiene un solo perfil.
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    @JsonIgnoreProperties("perfil")
    private Usuario usuario;

    public PerfilUsuario() {
    }

    public PerfilUsuario(Long id, String documento, String telefono, Usuario usuario) {
        this.id = id;
        this.documento = documento;
        this.telefono = telefono;
        this.usuario = usuario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    @Override
    public String toString() {
        return "PerfilUsuario{id=" + id + ", documento='" + documento + "', telefono='" + telefono + "', usuario=" + usuario + "}";
    }
}
