package br.com.biot_admin.biot_admin.modules.aplicativo.service;

import br.com.biot_admin.biot_admin.modules.aplicativo.model.Aplicativo;
import br.com.biot_admin.biot_admin.modules.aplicativo.model.Conexao;
import br.com.biot_admin.biot_admin.modules.aplicativo.repository.AplicativoRepository;
import br.com.biot_admin.biot_admin.modules.aplicativo.repository.ConexaoRepository;
import br.com.biot_admin.biot_admin.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.biot_admin.biot_admin.modules.aplicativo.exception.AplicativoException.*;

@Service
public class AplicativoService {

    private static final String DB_JDBC_URL = "datasource-app.jdbc-url";
    private static final String DB_USERNAME = "datasource-app.username";
    private static final String DB_PASSWORD = "datasource-app.password";

    @Autowired
    private AplicativoRepository aplicativoRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ConexaoRepository conexaoRepository;

    public List<Aplicativo> buscarTodos() {
        var usuarioLogado = usuarioService.getUsuarioAutenticado();
        if (usuarioLogado.isAdmin()) {
            return aplicativoRepository.findAll();
        }
        return aplicativoRepository
            .findByIdIn(usuarioService.getAplicativosIdsDoUsuario(usuarioLogado));
    }

    public Aplicativo buscarUm(Integer id) {
        var usuarioLogado = usuarioService.getUsuarioAutenticado();
        if (usuarioLogado.isAdmin()) {
            return aplicativoRepository.findById(id)
                .orElseThrow(APLICATIVO_NAO_ENCONTRADO::getException);
        }
        if (usuarioService.getAplicativosIdsDoUsuario(usuarioLogado).contains(id)) {
            return aplicativoRepository.findById(id)
                .orElseThrow(APLICATIVO_NAO_ENCONTRADO::getException);
        }
        throw USUARIO_SEM_APLICATIVO.getException();
    }

    public Conexao configurarBancoDeDados(Integer id) {
        var conexao = conexaoRepository.findByAplicativoId(buscarUm(id).getId())
            .orElseThrow(CONEXAO_NAO_ENCONTRADA::getException);
        alterarConfiguracao(conexao);
        return conexao;
    }

    private void alterarConfiguracao(Conexao conexao) {
        System.setProperty(DB_JDBC_URL, conexao.getUrl());
        System.setProperty(DB_USERNAME, conexao.getUsername());
        System.setProperty(DB_PASSWORD, conexao.getPassword());
    }
}
