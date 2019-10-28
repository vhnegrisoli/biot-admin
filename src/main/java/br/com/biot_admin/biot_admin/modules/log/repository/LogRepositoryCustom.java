package br.com.biot_admin.biot_admin.modules.log.repository;

import br.com.biot_admin.biot_admin.modules.dashboard.dto.QtdUrlsAcessadasResponse;
import br.com.biot_admin.biot_admin.modules.log.model.Log;

import java.util.List;

public interface LogRepositoryCustom {

    List<Log> findByUsuarioIdAndAplicacao(Integer usuarioId, String aplicacao);

    Long countBuscasByIdAndAplicacao(Integer usuarioId, String aplicacao);

    Long countInsercoesByIdAndAplicacao(Integer usuarioId, String aplicacao);

    Long countAlteracoesByIdAndAplicacao(Integer usuarioId, String aplicacao);

    Long countRemocoesByIdAndAplicacao(Integer usuarioId, String aplicacao);

    List<QtdUrlsAcessadasResponse> getQtdUrlsAcessadas(Integer usuarioId, String aplicacao);
}
