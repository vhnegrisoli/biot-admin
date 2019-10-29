package br.com.biot_admin.biot_admin.modules.aplicativo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app")
public class AppController {

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK, reason = "Aplicação rodando!")
    public void checkApp() {}

}
