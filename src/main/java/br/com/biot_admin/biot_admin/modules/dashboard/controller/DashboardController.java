package br.com.biot_admin.biot_admin.modules.dashboard.controller;

import br.com.biot_admin.biot_admin.modules.dashboard.repository.DashboardRepository;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.UsuarioResponse;
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

    @GetMapping("usuarios")
    public List<UsuarioResponse> findAllUsuarios() {
        return dashboardRepository.findAllUsuarios();
    }
}
