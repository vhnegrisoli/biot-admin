package br.com.biot_admin.biot_admin.modules.usuario.exception;

import br.com.biot_admin.biot_admin.exceptions.ValidacaoException;
import lombok.Getter;

public enum UsuarioException {

    USUARIO_NAO_ENCONTRADO(new ValidacaoException("Usuário não encontrado.")),
    USUARIO_NOME_JA_CADASTRADO(new ValidacaoException("Nome de usuário já cadastrado para um usuário ativo."));

    @Getter
    private ValidacaoException exception;

    UsuarioException(ValidacaoException exception) {
        this.exception = exception;
    }
}
