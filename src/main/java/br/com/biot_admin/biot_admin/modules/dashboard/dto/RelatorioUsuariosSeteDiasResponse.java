package br.com.biot_admin.biot_admin.modules.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelatorioUsuariosSeteDiasResponse {

    private static final String ZERO = "0";
    private static final Integer DEZ = 10;

    private String diaSemana;
    private Long qtdUsuarios;

    public RelatorioUsuariosSeteDiasResponse(Integer diaMes, Integer mes, Long qtdUsuarios) {
        this.qtdUsuarios = qtdUsuarios;
        this.diaSemana = (diaMes < DEZ ? ZERO + diaMes : diaMes) + "/" + (mes < DEZ ? ZERO + mes : mes);
    }
}
