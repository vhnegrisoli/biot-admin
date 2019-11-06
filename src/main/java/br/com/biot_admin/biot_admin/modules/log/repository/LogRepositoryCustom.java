package br.com.biot_admin.biot_admin.modules.log.repository;

import br.com.biot_admin.biot_admin.modules.dashboard.dto.RelatorioUsuariosHoraDiaHojeResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.RelatorioUsuarios7DiasResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.RelatorioUsuariosUltimos15MinutosResponse;

import java.util.List;

public interface LogRepositoryCustom {

    List<RelatorioUsuarios7DiasResponse> getUsuariosUltimosSeteDias(String aplicacao);

    Long getTotalUsuariosUltimosSeteDias(String aplicacao);

    List<RelatorioUsuariosHoraDiaHojeResponse> getUsuariosPorHoraDeHoje(String aplicacao);

    Long getTotalUsuariosPorHoraDeHoje(String aplicacao);

    List<RelatorioUsuariosUltimos15MinutosResponse> getUsuariosAtivosNosUltimos15Minutos(String aplicacao);

    Long getTotalUsuariosAtivosNosUltimos15Minutos(String aplicacao);
}
