package br.com.biot_admin.biot_admin.modules.log.repository;

import br.com.biot_admin.biot_admin.modules.dashboard.dto.RelatorioUsuariosSeteDiasResponse;

import java.util.List;

public interface LogRepositoryCustom {

    List<RelatorioUsuariosSeteDiasResponse> getUsuariosUltimosSeteDias(String aplicacao);

    Long getTotalUsuariosUltimosSeteDias(String aplicacao);
}
