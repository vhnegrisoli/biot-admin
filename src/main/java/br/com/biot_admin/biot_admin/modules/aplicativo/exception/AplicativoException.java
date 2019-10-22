package br.com.biot_admin.biot_admin.modules.aplicativo.exception;

import br.com.biot_admin.biot_admin.exceptions.ValidacaoException;
import lombok.Getter;

public enum AplicativoException {

    APLICATIVO_NAO_ENCONTRADO(new ValidacaoException("Aplicativo não encontrado.")),
    CONEXAO_NAO_ENCONTRADA(new ValidacaoException("Não foi encontrada uma conexão para esse aplicativo.")),
    USUARIO_SEM_APLICATIVO(new ValidacaoException("Você não tem um aplicativo."));

    @Getter
    private ValidacaoException exception;

    AplicativoException(ValidacaoException exception) {
        this.exception = exception;
    }
}
