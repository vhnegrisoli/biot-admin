package br.com.biot_admin.biot_admin.modules.usuario.exception;

import br.com.biot_admin.biot_admin.exceptions.ValidacaoException;
import lombok.Getter;

public enum UsuarioException {

    USUARIO_ACESSO_INVALIDO(new ValidacaoException("Usuário ou senha inválido."));

    @Getter
    private ValidacaoException exception;

    UsuarioException(ValidacaoException exception) {
        this.exception = exception;
    }
}
