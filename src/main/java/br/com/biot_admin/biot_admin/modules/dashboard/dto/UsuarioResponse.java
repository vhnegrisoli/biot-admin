package br.com.biot_admin.biot_admin.modules.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UsuarioResponse {

    private Integer id;
    private String nome;
    private String email;
}
