package br.com.biot_admin.biot_admin.modules.log.repository;

import br.com.biot_admin.biot_admin.modules.dashboard.dto.dia.RelatorioUsuarios7DiasResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.hora.RelatorioUsuariosHoraDiaHojeResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.hora.RelatorioUsuariosHoraDiaOntemResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.mes.RelatorioSemestreResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.minuto.RelatorioUsuariosUltimos15MinutosResponse;

import java.util.List;

public interface LogRepositoryCustom {

    List<RelatorioUsuarios7DiasResponse> getUsuariosUltimosSeteDias(String aplicacao);

    Long getTotalUsuariosUltimosSeteDias(String aplicacao);

    List<RelatorioUsuariosHoraDiaHojeResponse> getUsuariosPorHoraDeHoje(String aplicacao);

    Long getTotalUsuariosPorHoraDeHoje(String aplicacao);

    List<RelatorioUsuariosHoraDiaOntemResponse> getUsuariosPorHoraDeOntem(String aplicacao);

    Long getTotalUsuariosPorHoraDeOntem(String aplicacao);

    List<RelatorioUsuariosUltimos15MinutosResponse> getUsuariosAtivosNosUltimos15Minutos(String aplicacao);

    Long getTotalUsuariosAtivosNosUltimos15Minutos(String aplicacao);

    Long getTotalUsuariosNoMesAtual(String aplicacao);

    List<RelatorioSemestreResponse> getUsuariosSemestreAnterior(String aplicacao);

    List<RelatorioSemestreResponse> getUsuariosSemestreAtual(String aplicacao);
}
