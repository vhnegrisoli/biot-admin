package br.com.biot_admin.biot_admin.modules.log.service;

import br.com.biot_admin.biot_admin.modules.log.dto.LogMqResponse;
import br.com.biot_admin.biot_admin.modules.log.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

import static br.com.biot_admin.biot_admin.modules.log.model.Log.of;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void processarLogDeUsuario(LogMqResponse response) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:MM:ss");
        System.out.println("Salvando o log do usuário " + response.getUsuarioNome() + " ("
            + response.getUsuarioEmail() + ") às " + df.format(response.getDataAcesso()));
        logRepository.save(of(response));
    }
}