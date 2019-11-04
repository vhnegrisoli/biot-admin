package br.com.biot_admin.biot_admin.modules.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DashboardResponse {

    private Long qtdConsultas;
    private Long qtdInsercoes;
    private Long qtdAtualizacoes;
    private Long qtdRemocoes;
    private List<QtdUrlsAcessadasResponse> qtdUrlsAcessadas;
    private List<RelatorioUsuariosSeteDiasResponse> relatorioUsuariosUltimosSeteDias;
}
