package br.com.biot_admin.biot_admin.modules.log.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LogMqResponse implements Serializable {

    private Integer usuarioId;
    private String usuarioNome;
    private String usuarioEmail;
    private String aplicacao;
    private String urlAcessada;
    private String metodo;
    private String tipoOperacao;
    private LocalDateTime dataAcesso;

}
