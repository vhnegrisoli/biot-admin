package br.com.biot_admin.biot_admin.modules.usuario.repository;

import br.com.biot_admin.biot_admin.modules.usuario.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Integer> {
}
