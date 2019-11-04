package br.com.biot_admin.biot_admin.modules.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelatorioUsuariosSeteDiasResponse {

    private String diaSemana;
    private Integer diaMes;
    private Long qtdUsuarios;

    public RelatorioUsuariosSeteDiasResponse(DayOfWeek dataAcesso, Long qtdUsuarios) {
        this.qtdUsuarios = qtdUsuarios;
        this.diaSemana = dataAcesso.getDisplayName(TextStyle.FULL, new Locale("pt"));
        this.diaMes = dataAcesso.getValue();
    }
}
