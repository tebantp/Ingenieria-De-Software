package com.example.demo.Repository;

import com.example.demo.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Métodos derivados: filtran libros por autor o por id de categoría
    List<Libro> findByAutor(String autor);
    List<Libro> findByCategoriaId(Long categoriaId);

    // JPQL: recupera todos los libros con su categoría cargada en la misma consulta
    @Query("SELECT l FROM Libro l JOIN FETCH l.categoria")
    List<Libro> findAllConCategoria();

    // JPQL: filtra libros de una categoría específica e incluye el detalle de la categoría
    @Query("SELECT l FROM Libro l JOIN FETCH l.categoria WHERE l.categoria.id = :catId")
    List<Libro> findByCategoriaConDetalle(@Param("catId") Long catId);

    // Consulta nativa: retorna libros junto con el nombre de su categoría usando JOIN
    @Query(value = "SELECT l.*, c.nombre AS categoria_nombre " +
            "FROM libros l INNER JOIN categorias c ON l.categoria_id = c.id " +
            "WHERE c.id = :catId", nativeQuery = true)
    List<Object[]> findLibrosNativoPorCategoria(@Param("catId") Long catId);
}
