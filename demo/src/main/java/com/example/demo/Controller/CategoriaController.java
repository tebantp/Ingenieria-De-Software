package com.example.demo.Controller;

import com.example.demo.Model.Categoria;
import com.example.demo.Service.CategoriaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaServiceImpl categoriaService;

    public CategoriaController(CategoriaServiceImpl categoriaService) {
        this.categoriaService = categoriaService;
    }

    // GET /api/categorias/listar → lista todas las categorías
    @GetMapping("/listar")
    public ResponseEntity<List<Categoria>> listar() {
        return ResponseEntity.ok(categoriaService.listar());
    }

    // GET /api/categorias/listar-con-libros → lista categorías con sus libros
    @GetMapping("/listar-con-libros")
    public ResponseEntity<List<Categoria>> listarConLibros() {
        return ResponseEntity.ok(categoriaService.listarConLibros());
    }

    // GET /api/categorias/buscar?nombre=x → búsqueda por nombre (nativa)
    @GetMapping("/buscar")
    public ResponseEntity<List<Categoria>> buscar(@RequestParam String nombre) {
        return ResponseEntity.ok(categoriaService.buscarNativo(nombre));
    }

    // POST /api/categorias/guardar → crea una nueva categoría
    @PostMapping("/guardar")
    public ResponseEntity<Categoria> guardar(@RequestBody Categoria categoria) {
        return ResponseEntity.status(201).body(categoriaService.guardar(categoria));
    }

    // PUT /api/categorias/actualizar/{id} → actualiza una categoría existente
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            return ResponseEntity.ok(categoriaService.actualizar(id, categoria));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /api/categorias/eliminar/{id} → elimina una categoría por id
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            categoriaService.eliminar(id);
            return ResponseEntity.ok("Categoria eliminada correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
