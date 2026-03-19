package com.example.demo.Service;

import com.example.demo.Model.Curso;
import com.example.demo.Model.Estudiante;
import com.example.demo.Repository.CursoRepository;
import com.example.demo.Repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository, CursoRepository cursoRepository) {
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    // Retorna todos los estudiantes con sus cursos cargados
    @Override
    public List<Estudiante> Listar() {
        return estudianteRepository.findAllConCursos();
    }

    // Registra un nuevo estudiante
    @Override
    public Estudiante guardar(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    // Actualiza nombre y correo del estudiante
    @Override
    public Estudiante actualizar(Long id, Estudiante estudianteActualizado) {
        return estudianteRepository.findById(id).map(existente -> {
            existente.setNombre(estudianteActualizado.getNombre());
            existente.setCorreo(estudianteActualizado.getCorreo());
            return estudianteRepository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + id));
    }

    // Elimina el estudiante si existe en la base de datos
    @Override
    public void eliminar(Long id) {
        if (estudianteRepository.existsById(id)) {
            estudianteRepository.deleteById(id);
        } else {
            throw new RuntimeException("No es posible eliminar. Estudiante no encontrado con id: " + id);
        }
    }

    // Inscribe al estudiante en un curso agregándolo al Set de cursos
    @Override
    public Estudiante inscribir(Long estudianteId, Long cursoId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + estudianteId));
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + cursoId));
        estudiante.getCursos().add(curso);
        return estudianteRepository.save(estudiante);
    }

    // Elimina la inscripción del estudiante en el curso indicado
    @Override
    public Estudiante desinscribir(Long estudianteId, Long cursoId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + estudianteId));
        estudiante.getCursos().removeIf(c -> c.getId().equals(cursoId));
        return estudianteRepository.save(estudiante);
    }

    // Consulta nativa: lista estudiantes de un curso
    @Override
    public List<Object[]> listarNativoPorCurso(Long cursoId) {
        return estudianteRepository.findEstudiantesPorCursoNativo(cursoId);
    }
}
