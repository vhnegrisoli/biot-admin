package br.com.biot_admin.biot_admin.modules.aplicativo.controller;

import br.com.biot_admin.biot_admin.modules.aplicativo.model.Aplicativo;
import br.com.biot_admin.biot_admin.modules.aplicativo.model.Conexao;
import br.com.biot_admin.biot_admin.modules.aplicativo.service.AplicativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/aplicativos")
public class AplicativoController {

    @Autowired
    private AplicativoService aplicativoService;

    @GetMapping
    public List<Aplicativo> buscarTodos() {
        return aplicativoService.buscarTodos();
    }

    @GetMapping("{id}")
    public Aplicativo buscarUm(@PathVariable Integer id) {
        return aplicativoService.buscarUm(id);
    }

    @GetMapping("{id}/configurar-conexao")
    public Conexao configurarBancoDeDados(@PathVariable Integer id) {
        return aplicativoService.configurarBancoDeDados(id);
    }
}
