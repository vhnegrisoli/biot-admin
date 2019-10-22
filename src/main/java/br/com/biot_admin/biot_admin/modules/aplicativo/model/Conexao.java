package br.com.biot_admin.biot_admin.modules.aplicativo.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CONEXAO")
public class Conexao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "HOST")
    @NotNull
    private String host;

    @Column(name = "PORT")
    @NotNull
    private String port;

    @Column(name = "URL")
    @NotNull
    private String url;

    @Column(name = "USERNAME")
    @NotNull
    private String username;

    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    @Column(name = "DATABASE")
    @NotNull
    private String database;

    @ManyToOne
    @JoinColumn(name = "FK_APLICATIVO")
    @NotNull
    private Aplicativo aplicativo;
}
