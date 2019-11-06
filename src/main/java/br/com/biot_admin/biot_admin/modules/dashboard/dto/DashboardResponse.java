package br.com.biot_admin.biot_admin.modules.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DashboardResponse {

    private Relatorios7DiasResponse relatorioUltimos7Dias;
    private RelatoriosHorasDiaResponse relatorioHorasDia;
    private RelatoriosUltimos15MinutosResponse relatorioUltimos15Minutos;
}
