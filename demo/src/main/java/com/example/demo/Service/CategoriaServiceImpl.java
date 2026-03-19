package com.example.demo.Service;

import com.example.demo.Model.Categoria;
import com.example.demo.Repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // Retorna todas las categorías registradas
    @Override
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    // Retorna categorías junto con sus libros asociados
    @Override
    public List<Categoria> listarConLibros() {
        return categoriaRepository.findAllConLibros();
    }

    // Persiste una nueva categoría en la base de datos
    @Override
    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Actualiza nombre y descripción de una categoría existente
    @Override
    public Categoria actualizar(Long id, Categoria categoriaActualizada) {
        return categoriaRepository.findById(id).map(existente -> {
            existente.setNombre(categoriaActualizada.getNombre());
            existente.setDescripcion(categoriaActualizada.getDescripcion());
            return categoriaRepository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + id));
    }

    // Elimina la categoría si existe; lanza excepción en caso contrario
    @Override
    public void eliminar(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
        } else {
            throw new RuntimeException("No es posible eliminar. Categoria no encontrada con id: " + id);
        }
    }

    // Búsqueda nativa por nombre (parcial, sin distinguir mayúsculas)
    @Override
    public List<Categoria> buscarNativo(String nombre) {
        return categoriaRepository.buscarNativo(nombre);
    }
}
