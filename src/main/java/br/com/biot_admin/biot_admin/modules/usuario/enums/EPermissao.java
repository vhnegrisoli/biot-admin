package br.com.biot_admin.biot_admin.modules.usuario.enums;

import lombok.Getter;

public enum EPermissao {

    BIOT_ADMIN("BIoT Admin"),
    APP_OWNER("Application Owner"),
    APPLICATION("APPLICATION");

    @Getter
    private String descricao;

    EPermissao(String descricao) {
        this.descricao = descricao;
    }
}
