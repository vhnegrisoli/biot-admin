package br.com.biot_admin.biot_admin.modules.usuario.model;

import br.com.biot_admin.biot_admin.modules.aplicativo.model.Aplicativo;
import br.com.biot_admin.biot_admin.modules.usuario.dto.UsuarioRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static br.com.biot_admin.biot_admin.modules.usuario.enums.EPermissao.APP_OWNER;
import static org.springframework.util.ObjectUtils.isEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "NOME")
    @NotNull
    private String nome;

    @Column(name = "EMAIL")
    @NotNull
    private String email;

    @Column(name = "SENHA")
    @NotNull
    private String senha;

    @Column(name = "CPF")
    @NotNull
    @CPF
    private String cpf;

    @Column(name = "DATA_CADASTRO")
    @NotNull
    private LocalDateTime dataCadastro;

    @Column(name = "ULTIMO_ACESSO")
    @NotNull
    private LocalDateTime ultimoAcesso;

    @JoinColumn(name = "FK_PERMISSAO")
    @ManyToOne
    @NotNull
    private Permissao permissao;

    @ManyToMany
    @JoinTable(name = "USUARIO_APLICATIVO",
        joinColumns = @JoinColumn(name = "FK_USUARIO"),
        inverseJoinColumns = @JoinColumn(name = "FK_APLICATIVO"))
    private List<Aplicativo> aplicativos;

    @JsonIgnore
    public boolean isNovoCadastro() {
        return isEmpty(id);
    }

    public static Usuario of(UsuarioRequest usuarioRequest) {
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioRequest, usuario);
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setUltimoAcesso(LocalDateTime.now());
        usuario.setPermissao(new Permissao(2, APP_OWNER, "Application Owner"));
        return usuario;
    }
}
