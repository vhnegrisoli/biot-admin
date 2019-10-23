package br.com.biot_admin.biot_admin.modules.usuario.dto;

import br.com.biot_admin.biot_admin.modules.aplicativo.enums.EAplicativo;
import br.com.biot_admin.biot_admin.modules.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UsuarioResponse {

    private Integer id;
    private String nome;
    private String email;
    private EAplicativo appCodigo;
    private String appNome;
    private String backEndUrl;
    private String appClient;
    private String appSecret;
    private String appHeader;

    public static UsuarioResponse of(Usuario usuario) {
        var response = new UsuarioResponse();
        BeanUtils.copyProperties(usuario, response);
        response.setAppCodigo(usuario.getAplicativo().getCodigo());
        response.setAppNome(usuario.getAplicativo().getNome());
        response.setBackEndUrl(usuario.getAplicativo().getBackEndUrl());
        response.setAppClient(usuario.getAplicativo().getAppClient());
        response.setAppSecret(usuario.getAplicativo().getAppSecret());
        response.setAppHeader(usuario.getAplicativo().getAppHeader());
        return response;
    }
}
