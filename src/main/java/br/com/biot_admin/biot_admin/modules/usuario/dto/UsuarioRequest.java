package br.com.biot_admin.biot_admin.modules.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UsuarioRequest {

    private Integer id;
    private String nome;
    private String email;
    private Integer permissaoId;
    private Integer aplicativoId;

}
