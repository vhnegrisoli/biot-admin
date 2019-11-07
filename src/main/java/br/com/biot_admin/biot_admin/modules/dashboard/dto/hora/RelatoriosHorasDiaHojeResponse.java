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
public class RelatoriosHorasDiaHojeResponse {

    private Long totalRelatorioUsuariosHoraDiaHoje;
    private List<RelatorioUsuariosHoraDiaHojeResponse> relatorioUsuariosHoraDiaHoje;

}
