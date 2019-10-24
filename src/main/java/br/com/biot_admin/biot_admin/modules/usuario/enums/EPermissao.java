package br.com.biot_admin.biot_admin.modules.usuario.enums;

import lombok.Getter;

public enum EPermissao {

    USER("Usuário"),
    ADMIN("Administrador");

    @Getter
    private String descricao;

    EPermissao(String descricao) {
        this.descricao = descricao;
    }
}
