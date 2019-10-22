package br.com.biot_admin.biot_admin.modules.usuario.dto;

import br.com.biot_admin.biot_admin.modules.aplicativo.model.Aplicativo;
import br.com.biot_admin.biot_admin.modules.usuario.enums.EPermissao;
import br.com.biot_admin.biot_admin.modules.usuario.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.biot_admin.biot_admin.modules.usuario.enums.EPermissao.APP_OWNER;
import static br.com.biot_admin.biot_admin.modules.usuario.enums.EPermissao.BIOT_ADMIN;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioAutenticado {

    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private EPermissao permissao;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime ultimoAcesso;
    private List<Aplicativo> aplicativos;

    public boolean isAdmin() {
        return permissao.equals(BIOT_ADMIN);
    }

    public boolean isUser() {
        return permissao.equals(APP_OWNER);
    }

    public static UsuarioAutenticado of(Usuario usuario) {
        var usuarioAutenticado = new UsuarioAutenticado();
        BeanUtils.copyProperties(usuario, usuarioAutenticado);
        usuarioAutenticado.setPermissao(usuario.getPermissao().getCodigo());
        usuarioAutenticado.setDescricao(usuario.getPermissao().getDescricao());
        usuarioAutenticado.setAplicativos(usuario.getAplicativos());
        return usuarioAutenticado;
    }
}
