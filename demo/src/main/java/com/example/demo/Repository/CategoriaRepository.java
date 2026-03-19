package com.example.demo.Repository;

import com.example.demo.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Método derivado: busca una categoría por su nombre exacto
    Optional<Categoria> findByNombre(String nombre);

    // JPQL: obtiene todas las categorías junto con sus libros (evita el problema N+1)
    @Query("SELECT DISTINCT c FROM Categoria c LEFT JOIN FETCH c.libros")
    List<Categoria> findAllConLibros();

    // JPQL: obtiene una categoría específica con sus libros incluidos
    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.libros WHERE c.id = :id")
    Optional<Categoria> findByIdConLibros(@Param("id") Long id);

    // Consulta nativa: busca categorías cuyo nombre contenga el texto indicado (case-insensitive)
    @Query(value = "SELECT * FROM categorias WHERE nombre ILIKE %:nombre%", nativeQuery = true)
    List<Categoria> buscarNativo(@Param("nombre") String nombre);
}
