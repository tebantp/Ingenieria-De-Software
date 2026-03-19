package com.example.demo.Controller;

import com.example.demo.Model.PerfilUsuario;
import com.example.demo.Service.PerfilUsuarioServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilUsuarioController {

    private final PerfilUsuarioServiceImpl perfilService;

    public PerfilUsuarioController(PerfilUsuarioServiceImpl perfilService) {
        this.perfilService = perfilService;
    }

    // GET /api/perfiles/listar → lista todos los perfiles
    @GetMapping("/listar")
    public ResponseEntity<List<PerfilUsuario>> listar() {
        return ResponseEntity.ok(perfilService.listar());
    }

    // GET /api/perfiles/buscar/usuario/{usuarioId} → busca el perfil de un usuario
    @GetMapping("/buscar/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioId) {
        try {
            return ResponseEntity.ok(perfilService.buscarPorUsuario(usuarioId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // POST /api/perfiles/guardar?usuarioId=x → crea perfil para el usuario indicado
    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody PerfilUsuario perfil, @RequestParam Long usuarioId) {
        try {
            return ResponseEntity.status(201).body(perfilService.guardar(perfil, usuarioId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PUT /api/perfiles/actualizar/{id} → actualiza documento y teléfono del perfil
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody PerfilUsuario perfil) {
        try {
            return ResponseEntity.ok(perfilService.actualizar(id, perfil));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /api/perfiles/eliminar/{id} → elimina el perfil indicado
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            perfilService.eliminar(id);
            return ResponseEntity.ok("Perfil eliminado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
