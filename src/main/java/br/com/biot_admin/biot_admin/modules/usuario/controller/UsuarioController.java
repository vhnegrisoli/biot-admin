package br.com.biot_admin.biot_admin.modules.usuario.controller;

import br.com.biot_admin.biot_admin.modules.log.model.Log;
import br.com.biot_admin.biot_admin.modules.log.service.LogService;
import br.com.biot_admin.biot_admin.modules.usuario.dto.UsuarioAutenticado;
import br.com.biot_admin.biot_admin.modules.usuario.dto.UsuarioRequest;
import br.com.biot_admin.biot_admin.modules.usuario.model.Usuario;
import br.com.biot_admin.biot_admin.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LogService logService;

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @GetMapping("/check-session")
    public ResponseEntity checkSession() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/novo")
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Usuário inserido com sucesso!")
    public void novoUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        usuarioService.save(usuarioRequest);
    }

    @PutMapping("/alterar-acesso")
    @ResponseStatus(code = HttpStatus.OK, reason = "Usuário alterado com sucesso!")
    public void alterarDadosUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        usuarioService.save(usuarioRequest);
    }

    @GetMapping("/usuario-autenticado")
    public UsuarioAutenticado getUsuarioAutenticado() {
        return usuarioService.getUsuarioAutenticadoAtualizaUltimaData();
    }

    @GetMapping("/log")
    public List<Log> findLogPaginado(@RequestParam("page") Integer page,
                                     @RequestParam("size") Integer size) {
        return logService.findAllPageable(page, size);
    }
}
