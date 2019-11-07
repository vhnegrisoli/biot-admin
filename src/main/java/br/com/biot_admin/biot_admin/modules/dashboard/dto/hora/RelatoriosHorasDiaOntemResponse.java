package br.com.biot_admin.biot_admin.modules.dashboard.dto.hora;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RelatoriosHorasDiaOntemResponse {

    private Long totalRelatorioUsuariosHoraDiaOntem;
    private List<RelatorioUsuariosHoraDiaOntemResponse> relatorioUsuariosHoraDiaOntem;
}
