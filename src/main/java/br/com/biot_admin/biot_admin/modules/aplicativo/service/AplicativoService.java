package br.com.biot_admin.biot_admin.modules.aplicativo.service;

import br.com.biot_admin.biot_admin.modules.aplicativo.model.Aplicativo;
import br.com.biot_admin.biot_admin.modules.aplicativo.repository.AplicativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AplicativoService {

    @Autowired
    private AplicativoRepository aplicativoRepository;

    public List<Aplicativo> buscarTodos() {
        return aplicativoRepository.findAll();
    }

    public Aplicativo buscarUm(Integer id) {
        return null;
    }

    public void configurarBancoDeDados(Integer id) {
    }
}
