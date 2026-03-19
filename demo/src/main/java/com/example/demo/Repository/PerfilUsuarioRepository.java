package com.example.demo.Repository;

import com.example.demo.Model.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Long> {

    // Métodos derivados: buscan perfil por documento o por id del usuario asociado
    Optional<PerfilUsuario> findByDocumento(String documento);
    Optional<PerfilUsuario> findByUsuarioId(Long usuarioId);

    // JPQL: obtiene el perfil junto con el usuario en una sola consulta
    @Query("SELECT p FROM PerfilUsuario p JOIN FETCH p.usuario WHERE p.usuario.id = :uid")
    Optional<PerfilUsuario> findByUsuarioConDetalle(@Param("uid") Long uid);

    // Consulta nativa: busca el perfil directamente por id de usuario en la tabla
    @Query(value = "SELECT * FROM perfiles_usuario WHERE usuario_id = :uid", nativeQuery = true)
    Optional<PerfilUsuario> findByUsuarioNativo(@Param("uid") Long uid);
}
