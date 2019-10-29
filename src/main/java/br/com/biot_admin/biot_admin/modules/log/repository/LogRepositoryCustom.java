package br.com.biot_admin.biot_admin.modules.log.repository;

import br.com.biot_admin.biot_admin.modules.dashboard.dto.QtdUrlsAcessadasResponse;

import java.util.List;

public interface LogRepositoryCustom {

    Long countBuscasByIdAndAplicacao(String aplicacao);

    Long countInsercoesByIdAndAplicacao(String aplicacao);

    Long countAlteracoesByIdAndAplicacao(String aplicacao);

    Long countRemocoesByIdAndAplicacao(String aplicacao);

    List<QtdUrlsAcessadasResponse> getQtdUrlsAcessadas(String aplicacao);
}
