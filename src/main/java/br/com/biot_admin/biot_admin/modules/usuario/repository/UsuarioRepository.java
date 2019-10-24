package br.com.biot_admin.biot_admin.modules.usuario.repository;

import br.com.biot_admin.biot_admin.modules.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNomeUsuario(String email);

}
