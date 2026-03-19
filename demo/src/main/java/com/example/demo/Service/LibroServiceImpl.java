package com.example.demo.Service;

import com.example.demo.Model.Categoria;
import com.example.demo.Model.Libro;
import com.example.demo.Repository.CategoriaRepository;
import com.example.demo.Repository.LibroRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;
    private final CategoriaRepository categoriaRepository;

    public LibroServiceImpl(LibroRepository libroRepository, CategoriaRepository categoriaRepository) {
        this.libroRepository = libroRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // Retorna todos los libros con su categoría cargada
    @Override
    public List<Libro> listar() {
        return libroRepository.findAllConCategoria();
    }

    // Guarda un libro y le asigna la categoría correspondiente
    @Override
    public Libro guardar(Libro libro, Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + categoriaId));
        libro.setCategoria(categoria);
        return libroRepository.save(libro);
    }

    // Actualiza los datos del libro; si se proporciona categoriaId, también la actualiza
    @Override
    public Libro actualizar(Long id, Libro libroActualizado, Long categoriaId) {
        return libroRepository.findById(id).map(existente -> {
            existente.setTitulo(libroActualizado.getTitulo());
            existente.setAutor(libroActualizado.getAutor());
            existente.setAniopublicacion(libroActualizado.getAniopublicacion());
            if (categoriaId != null) {
                Categoria cat = categoriaRepository.findById(categoriaId)
                        .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + categoriaId));
                existente.setCategoria(cat);
            }
            return libroRepository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id));
    }

    // Elimina el libro si existe
    @Override
    public void eliminar(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
        } else {
            throw new RuntimeException("No es posible eliminar. Libro no encontrado con id: " + id);
        }
    }

    // Filtra libros por categoría usando JPQL
    @Override
    public List<Libro> listarPorCategoria(Long catId) {
        return libroRepository.findByCategoriaConDetalle(catId);
    }

    // Filtra libros por categoría usando consulta nativa
    @Override
    public List<Object[]> listarNativoPorCategoria(Long catId) {
        return libroRepository.findLibrosNativoPorCategoria(catId);
    }
}
