package br.com.biot_admin.biot_admin.modules.dashboard.controller;

import br.com.biot_admin.biot_admin.modules.dashboard.dto.UsuarioResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/dashboard")
public class DashboardController {

    @GetMapping("usuarios")
    public List<UsuarioResponse> findAllUsuarios() {
        return null;
    }
}
