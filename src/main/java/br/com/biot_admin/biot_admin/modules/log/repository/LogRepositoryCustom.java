package br.com.biot_admin.biot_admin.modules.log.repository;

import br.com.biot_admin.biot_admin.modules.log.model.Log;

import java.util.List;

public interface LogRepositoryCustom {

    List<Log> findByUsuarioIdAndAplicacao(Integer usuarioId, String aplicacao);
}
