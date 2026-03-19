package com.example.demo.Controller;

import com.example.demo.Model.Estudiante;
import com.example.demo.Service.EstudianteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteServiceImpl estudianteService;

    public EstudianteController(EstudianteServiceImpl estudianteService) {
        this.estudianteService = estudianteService;
    }

    // GET /api/estudiantes/listar → retorna todos los estudiantes con cursos
    @GetMapping("/listar")
    public ResponseEntity<List<Estudiante>> listar() {
        return ResponseEntity.ok(estudianteService.Listar());
    }

    // GET /api/estudiantes/listar/nativo/curso/{cursoId} → consulta nativa por curso
    @GetMapping("/listar/nativo/curso/{cursoId}")
    public ResponseEntity<List<Object[]>> listarNativo(@PathVariable Long cursoId) {
        return ResponseEntity.ok(estudianteService.listarNativoPorCurso(cursoId));
    }

    // POST /api/estudiantes/guardar → registra un nuevo estudiante
    @PostMapping("/guardar")
    public ResponseEntity<Estudiante> guardar(@RequestBody Estudiante estudiante) {
        return ResponseEntity.status(201).body(estudianteService.guardar(estudiante));
    }

    // PUT /api/estudiantes/actualizar/{id} → actualiza los datos del estudiante
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        try {
            return ResponseEntity.ok(estudianteService.actualizar(id, estudiante));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /api/estudiantes/eliminar/{id} → elimina el estudiante indicado
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            estudianteService.eliminar(id);
            return ResponseEntity.ok("Estudiante eliminado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // POST /api/estudiantes/inscribir/{estudianteId}/curso/{cursoId} → inscribe al estudiante
    @PostMapping("/inscribir/{estudianteId}/curso/{cursoId}")
    public ResponseEntity<?> inscribir(@PathVariable Long estudianteId, @PathVariable Long cursoId) {
        try {
            return ResponseEntity.ok(estudianteService.inscribir(estudianteId, cursoId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /api/estudiantes/desinscribir/{estudianteId}/curso/{cursoId} → quita inscripción
    @DeleteMapping("/desinscribir/{estudianteId}/curso/{cursoId}")
    public ResponseEntity<?> desinscribir(@PathVariable Long estudianteId, @PathVariable Long cursoId) {
        try {
            return ResponseEntity.ok(estudianteService.desinscribir(estudianteId, cursoId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
