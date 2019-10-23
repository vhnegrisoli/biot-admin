package br.com.biot_admin.biot_admin.modules.usuario.client;

import br.com.biot_admin.biot_admin.modules.dashboard.dto.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
    contextId = "usuarioClient",
    name = "usuarioAutenticadoClient",
    url = "${app-config.services.guia-de-projeto-api.url}")
public interface UsuarioClient {

    @GetMapping("check-session")
    void checkSession();

    @GetMapping("/api/usuarios/all")
    List<UsuarioResponse> getAllUsuarios();
}
