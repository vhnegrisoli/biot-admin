package br.com.biot_admin.biot_admin.modules.dashboard.dto.mes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RelatorioSemestreResponse {

    private Integer mes;
    private String mesNome;
    private Long qtdUsuarios;
}
