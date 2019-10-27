package br.com.biot_admin.biot_admin.modules.log.rabbitmq;

import br.com.biot_admin.biot_admin.modules.log.dto.LogMqResponse;
import br.com.biot_admin.biot_admin.modules.log.service.LogService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogListener {

    @Autowired
    private LogService logService;

    @RabbitListener(queues = "${app-config.queue.usuario-log}")
    public void receive(LogMqResponse response) {
        logService.processarLogDeUsuario(response);
        System.out.println("Mensagem recebida: " + response);
    }
}
