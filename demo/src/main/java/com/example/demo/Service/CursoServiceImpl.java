package com.example.demo.Service;

import com.example.demo.Model.Curso;
import com.example.demo.Repository.CursoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // Retorna todos los cursos con la lista de estudiantes cargada
    @Override
    public List<Curso> listar() {
        return cursoRepository.findAllConEstudiantes();
    }

    // Registra un nuevo curso
    @Override
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Actualiza nombre y créditos de un curso existente
    @Override
    public Curso actualizar(Long id, Curso cursoActualizado) {
        return cursoRepository.findById(id).map(existente -> {
            existente.setNombres(cursoActualizado.getNombres());
            existente.setCreditos(cursoActualizado.getCreditos());
            return cursoRepository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + id));
    }

    // Elimina el curso si existe
    @Override
    public void eliminar(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
        } else {
            throw new RuntimeException("No es posible eliminar. Curso no encontrado con id: " + id);
        }
    }

    // Consulta nativa: retorna cursos con el total de estudiantes inscritos
    @Override
    public List<Object[]> listarEstadisticas() {
        return cursoRepository.findCursosConTotalEstudiantes();
    }
}
