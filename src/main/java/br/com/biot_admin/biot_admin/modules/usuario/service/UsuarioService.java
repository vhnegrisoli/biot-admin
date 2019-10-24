package br.com.biot_admin.biot_admin.modules.usuario.service;

import br.com.biot_admin.biot_admin.modules.aplicativo.model.Aplicativo;
import br.com.biot_admin.biot_admin.modules.usuario.dto.UsuarioResponse;
import br.com.biot_admin.biot_admin.modules.usuario.dto.UsuarioRequest;
import br.com.biot_admin.biot_admin.modules.usuario.model.Usuario;
import br.com.biot_admin.biot_admin.modules.usuario.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.biot_admin.biot_admin.modules.usuario.dto.UsuarioResponse.of;
import static br.com.biot_admin.biot_admin.modules.usuario.model.Usuario.of;
import static br.com.biot_admin.biot_admin.modules.usuario.exception.UsuarioException.*;

@Service
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponse getLogin(String email) {
        var usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(USUARIO_NAO_ENCONTRADO::getException);
        atualizarUltimoAcesso(usuario);
        return of(usuario);
    }

    @Transactional
    private void atualizarUltimoAcesso(Usuario usuario) {
        usuario.setUltimoAcesso(LocalDateTime.now());
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void save(UsuarioRequest usuarioRequest) {
        var usuario = of(usuarioRequest);
        validarEmailExistente(usuario);
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setUltimoAcesso(LocalDateTime.now());
        vincularAplicacoes(usuario, usuarioRequest.getAplicativosIds());
        usuarioRepository.save(usuario);
    }

    private void vincularAplicacoes(Usuario usuario, List<Integer> aplicativos) {
        usuario.setAplicativos(aplicativos.stream().map(Aplicativo::new).collect(Collectors.toList()));
    }

    private void validarEmailExistente(Usuario usuario) {
        usuarioRepository.findByEmail(usuario.getEmail())
            .ifPresent(usuarioExistente -> {
                if (usuario.isNovoCadastro() || !usuario.getId().equals(usuarioExistente.getId())) {
                    throw USUARIO_EMAIL_JA_CADASTRADO.getException();
                }
            });
    }
}
