package br.com.biot_admin.biot_admin.modules.log.service;

import br.com.biot_admin.biot_admin.modules.log.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class LogAgendador {

    @Autowired
    private LogRepository logRepository;

    @Scheduled(cron = "0 12 * * * *")
    public void removerDadosAposDoisMeses() {
        System.out.println("Inicializando o agendador de remoção de logs de 2 meses: " + LocalDateTime.now());
        var registrosDoisMeses = logRepository
            .findByDataAcessoLessThanEqual(LocalDateTime.now().minusMonths(2));
        if (!isEmpty(registrosDoisMeses)) {
            logRepository.deleteAll(registrosDoisMeses);
        } else {
            System.out.println("Não há registros há mais de 2 meses para serem removidos.");
        }
    }
}
