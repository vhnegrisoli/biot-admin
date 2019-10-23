package br.com.biot_admin.biot_admin.modules.dashboard.controller;

import br.com.biot_admin.biot_admin.modules.dashboard.repository.DashboardRepository;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.UsuarioResponse;
import br.com.biot_admin.biot_admin.modules.usuario.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private UsuarioClient usuarioClient;

    @GetMapping("usuarios")
    public List<UsuarioResponse> findAllUsuarios() {
        return dashboardRepository.findAllUsuarios();
    }

    @GetMapping("usuarios-client")
    public List<UsuarioResponse> findAllUsuariosClient() {
        return usuarioClient.getAllUsuarios();
    }
}
