package br.com.biot_admin.biot_admin.modules.dashboard.dto.minuto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RelatorioUsuariosUltimos15MinutosResponse {

    private static final String ZERO = "0";
    private static final Integer DEZ = 10;

    private String horaMinuto;
    private Long qtdUsuarios;

    public RelatorioUsuariosUltimos15MinutosResponse(Integer hora, Integer minuto, Long qtdUsuarios) {
        this.horaMinuto = (hora < DEZ ? ZERO + hora : hora) + ":" + (minuto < DEZ ? ZERO + minuto : minuto);
        this.qtdUsuarios = qtdUsuarios;
    }
}
