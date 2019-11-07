package br.com.biot_admin.biot_admin.modules.dashboard.dto;

import br.com.biot_admin.biot_admin.modules.dashboard.dto.dia.Relatorios7DiasResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.hora.RelatoriosHorasDiaHojeResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.hora.RelatoriosHorasDiaOntemResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.mes.RelatorioMesResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.minuto.RelatoriosUltimos15MinutosResponse;
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
    private RelatoriosHorasDiaHojeResponse relatorioHorasDia;
    private RelatoriosHorasDiaOntemResponse relatoriosHorasDiaOntem;
    private RelatoriosUltimos15MinutosResponse relatorioUltimos15Minutos;
    private RelatorioMesResponse relatoriosMensais;
}
