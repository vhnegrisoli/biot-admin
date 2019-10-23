package br.com.biot_admin.biot_admin.modules.aplicativo.model;

import br.com.biot_admin.biot_admin.modules.aplicativo.enums.EAplicativo;
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
@Table(name = "APLICATIVO")
public class Aplicativo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "CODIGO")
    @NotNull
    @Enumerated(EnumType.STRING)
    private EAplicativo codigo;

    @Column(name = "NOME")
    @NotNull
    private String nome;

    @Column(name = "BACK_END_URL")
    @NotNull
    private String backEndUrl;

    @Column(name = "APP_CLIENT")
    @NotNull
    private String appClient;

    @Column(name = "APP_SECRET")
    @NotNull
    private String appSecret;

    @Column(name = "APP_HEADER")
    @NotNull
    private String appHeader;

    public Aplicativo(Integer id) {
        this.id = id;
    }
}
