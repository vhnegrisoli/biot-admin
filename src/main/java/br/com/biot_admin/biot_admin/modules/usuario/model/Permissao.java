package br.com.biot_admin.biot_admin.modules.usuario.model;

import br.com.biot_admin.biot_admin.modules.usuario.enums.EPermissao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "PERMISSAO")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "CODIGO")
    @NotNull
    @Enumerated(EnumType.STRING)
    private EPermissao codigo;

    @Column(name = "DESCRICAO")
    @NotNull
    private String descricao;
}
