package br.com.biot_admin.biot_admin.modules.usuario.model;

import br.com.biot_admin.biot_admin.modules.aplicativo.model.Aplicativo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @JoinColumn(name = "FK_PERMISSAO")
    @ManyToOne
    @NotNull
    private Permissao permissao;

    @ManyToMany
    @JoinTable(name = "USUARIO_APLICATIVO",
        joinColumns = @JoinColumn(name = "FK_USUARIO"),
        inverseJoinColumns = @JoinColumn(name = "FK_APLICATIVO"))
    private List<Aplicativo> aplicativos;
}
