package br.com.biot_admin.biot_admin.modules.dashboard.dto.hora;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RelatorioUsuariosHoraDiaHojeResponse {

    private static final String ZERO = "0";
    private static final String MINUTO = ":00";
    private static final Integer DEZ = 10;

    private String hora;
    private Long qtdUsuarios;

    public RelatorioUsuariosHoraDiaHojeResponse(Integer hora, Long qtdUsuarios) {
        this.hora = (hora < DEZ ? ZERO + hora : hora) + MINUTO;
        this.qtdUsuarios = qtdUsuarios;
    }
}
