package com.example.demo.Controller;

import com.example.demo.Model.Libro;
import com.example.demo.Service.LibroServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroServiceImpl libroService;

    public LibroController(LibroServiceImpl libroService) {
        this.libroService = libroService;
    }

    // GET /api/libros/listar → retorna todos los libros con categoría
    @GetMapping("/listar")
    public ResponseEntity<List<Libro>> listar() {
        return ResponseEntity.ok(libroService.listar());
    }

    // GET /api/libros/listar/categoria/{catId} → filtra libros por categoría
    @GetMapping("/listar/categoria/{catId}")
    public ResponseEntity<List<Libro>> listarPorCategoria(@PathVariable Long catId) {
        return ResponseEntity.ok(libroService.listarPorCategoria(catId));
    }

    // GET /api/libros/listar/nativo/categoria/{catId} → consulta nativa por categoría
    @GetMapping("/listar/nativo/categoria/{catId}")
    public ResponseEntity<List<Object[]>> listarNativo(@PathVariable Long catId) {
        return ResponseEntity.ok(libroService.listarNativoPorCategoria(catId));
    }

    // POST /api/libros/guardar?categoriaId=x → crea un libro asignando categoría
    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Libro libro, @RequestParam Long categoriaId) {
        try {
            return ResponseEntity.status(201).body(libroService.guardar(libro, categoriaId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PUT /api/libros/actualizar/{id}?categoriaId=x → actualiza libro (categoría opcional)
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Libro libro,
                                        @RequestParam(required = false) Long categoriaId) {
        try {
            return ResponseEntity.ok(libroService.actualizar(id, libro, categoriaId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /api/libros/eliminar/{id} → elimina el libro indicado
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            libroService.eliminar(id);
            return ResponseEntity.ok("Libro eliminado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
