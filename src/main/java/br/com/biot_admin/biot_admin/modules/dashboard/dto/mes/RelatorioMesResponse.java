package br.com.biot_admin.biot_admin.modules.dashboard.dto.mes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RelatorioMesResponse {

    private String mesAtual;
    private Long totalUsuariosNoMes;
    private List<RelatorioSemestreResponse> relatorioSemestreAnterior;
    private List<RelatorioSemestreResponse> relatorioSemestreAtual;
}
