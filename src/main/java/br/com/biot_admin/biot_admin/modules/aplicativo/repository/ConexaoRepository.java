package br.com.biot_admin.biot_admin.modules.aplicativo.repository;

import br.com.biot_admin.biot_admin.modules.aplicativo.model.Conexao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConexaoRepository extends JpaRepository<Conexao, Integer> {

    Optional<Conexao> findByAplicativoId(Integer aplicativoId);
}
