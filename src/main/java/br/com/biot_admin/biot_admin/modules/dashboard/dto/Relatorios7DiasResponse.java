package br.com.biot_admin.biot_admin.modules.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Relatorios7DiasResponse {

    private Long totalUsuariosUltimosSeteDias;
    private List<RelatorioUsuarios7DiasResponse> relatorioUsuariosUltimosSeteDias;

}
