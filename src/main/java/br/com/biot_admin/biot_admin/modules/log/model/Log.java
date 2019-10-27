package br.com.biot_admin.biot_admin.modules.log.model;

import br.com.biot_admin.biot_admin.modules.log.dto.LogMqResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "LOG")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "usuario_id")
    @NotNull
    private Integer usuarioId;

    @Column(name = "usuario_nome")
    @NotNull
    private String usuarioNome;

    @Column(name = "usuario_email")
    @NotNull
    private String usuarioEmail;

    @Column(name = "aplicacao")
    @NotNull
    private String aplicacao;

    @Column(name = "url_acessada")
    @NotNull
    private String urlAcessada;

    @Column(name = "metodo")
    @NotNull
    private String metodo;

    @Column(name = "tipo_operacao")
    @NotNull
    private String tipoOperacao;

    @Column(name = "data_acesso")
    @NotNull
    private LocalDateTime dataAcesso;

    public static Log of(LogMqResponse response) {
        var log = new Log();
        BeanUtils.copyProperties(response, log);
        return log;
    }
}
