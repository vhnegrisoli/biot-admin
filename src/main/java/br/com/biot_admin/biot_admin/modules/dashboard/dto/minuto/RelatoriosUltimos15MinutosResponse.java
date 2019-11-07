package br.com.biot_admin.biot_admin.modules.dashboard.dto.minuto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RelatoriosUltimos15MinutosResponse {

    private Long totalUsuariosAtivosUltimos15Minutos;
    private List<RelatorioUsuariosUltimos15MinutosResponse> relatorioUsuariosAtivosUltimos15Minutos;
}
