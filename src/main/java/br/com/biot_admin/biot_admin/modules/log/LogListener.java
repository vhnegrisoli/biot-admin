package br.com.biot_admin.biot_admin.modules.log;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class LogListener {

    @Autowired
    private LogService logService;

    @RabbitListener(queues = "${app-config.queue.usuario-log}")
    public void receive(@Payload String fileBody) {
        logService.processarLogDeUsuario();
        System.out.println("Mensagem recebida: " + fileBody);
    }
}
