package br.com.biot_admin.biot_admin.modules.usuario.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "UsuarioClient", url = "http://localhost:8090/api/usuarios")
public interface UsuarioClient {

    @GetMapping("check-session")
    void checkSession();
}
