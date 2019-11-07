package br.com.biot_admin.biot_admin.modules.dashboard.controller;

import br.com.biot_admin.biot_admin.modules.dashboard.service.DashboardService;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.DashboardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("relatorios/aplicacao/{aplicacaoId}")
    public DashboardResponse findRelatorios(@PathVariable Integer aplicacaoId) {
        return dashboardService.getRelatorios(aplicacaoId);
    }
}
