package br.com.biot_admin.biot_admin.modules.usuario.dto;

import br.com.biot_admin.biot_admin.modules.aplicativo.model.Aplicativo;
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
    private String nomeUsuario;
    private String email;
    Aplicativo aplicativo;

    public static UsuarioResponse of(Usuario usuario) {
        var response = new UsuarioResponse();
        BeanUtils.copyProperties(usuario, response);
        return response;
    }
}
