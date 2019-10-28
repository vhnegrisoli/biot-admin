package br.com.biot_admin.biot_admin.modules.aplicativo.service;

import br.com.biot_admin.biot_admin.modules.aplicativo.model.Aplicativo;
import br.com.biot_admin.biot_admin.modules.aplicativo.repository.AplicativoRepository;
import br.com.biot_admin.biot_admin.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.biot_admin.biot_admin.modules.aplicativo.exception.AplicativoException.APLICATIVO_NAO_ENCONTRADO;
import static br.com.biot_admin.biot_admin.modules.aplicativo.exception.AplicativoException.USUARIO_SEM_APLICATIVO;

@Service
public class AplicativoService {

    @Autowired
    private AplicativoRepository aplicativoRepository;
    @Autowired
    private UsuarioService usuarioService;

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
}
