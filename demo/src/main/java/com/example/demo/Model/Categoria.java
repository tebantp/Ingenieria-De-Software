package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nombre único de la categoría
    @Column(nullable = false, unique = true, length = 80)
    private String nombre;

    // descripción breve de la categoría
    @Column(nullable = false, length = 150)
    private String descripcion;

    /*
     * Relación OneToMany con Libro.
     * cascade = ALL: las operaciones se propagan a los libros.
     * orphanRemoval = false: si se elimina la categoría, los libros NO se eliminan.
     * mappedBy indica el campo dueño de la relación en Libro.
     */
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference
    private List<Libro> libros = new ArrayList<>();

    public Categoria() {
    }

    public Categoria(Long id, String nombre, String descripcion, List<Libro> libros) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.libros = libros;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<Libro> getLibros() { return libros; }
    public void setLibros(List<Libro> libros) { this.libros = libros; }

    @Override
    public String toString() {
        return "Categoria{id=" + id + ", nombre='" + nombre + "', descripcion='" + descripcion + "', libros=" + libros + "}";
    }
}
