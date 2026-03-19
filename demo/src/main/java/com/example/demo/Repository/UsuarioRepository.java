package com.example.demo.Repository;

import com.example.demo.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método derivado: busca un usuario por su correo electrónico
    Optional<Usuario> findByCorreo(String correo);

    // JPQL: obtiene todos los usuarios con su perfil cargado (relación OneToOne)
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.perfil")
    List<Usuario> findAllConPerfil();

    // Consulta nativa: une usuarios con perfiles para retornar datos combinados
    @Query(value = "SELECT u.id, u.mombre, u.correo, p.documento, p.telefono " +
            "FROM usuarios u LEFT JOIN perfiles_usuario p ON p.usuario_id = u.id",
            nativeQuery = true)
    List<Object[]> findUsuariosConPerfilNativo();
}
