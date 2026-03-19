package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String titulo;

    @Column(nullable = false, length = 100)
    private String autor;

    // año en que fue publicado el libro
    @Column(name = "anio_publicacion", nullable = false)
    private int Aniopublicacion;

    /*
     * Relación ManyToOne con Categoria.
     * optional = false: un libro siempre debe pertenecer a una categoría.
     * FetchType.EAGER: la categoría se carga junto con el libro.
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", nullable = false)
    @JsonBackReference
    private Categoria categoria;

    public Libro() {
    }

    public Libro(Long id, String titulo, String autor, int aniopublicacion, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.Aniopublicacion = aniopublicacion;
        this.categoria = categoria;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public int getAniopublicacion() { return Aniopublicacion; }
    public void setAniopublicacion(int aniopublicacion) { this.Aniopublicacion = aniopublicacion; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return "Libro{id=" + id + ", titulo='" + titulo + "', autor='" + autor + "', anio=" + Aniopublicacion + ", categoria=" + categoria + "}";
    }
}
