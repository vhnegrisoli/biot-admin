package br.com.biot_admin.biot_admin.modules.usuario.controller;

import br.com.biot_admin.biot_admin.modules.usuario.dto.UsuarioResponse;
import br.com.biot_admin.biot_admin.modules.usuario.dto.UsuarioRequest;
import br.com.biot_admin.biot_admin.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login/{email}")
    public UsuarioResponse login(@PathVariable String email) {
        return usuarioService.getLogin(email);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Usuário criado com sucesso!")
    public void save(@RequestBody UsuarioRequest request) {
        usuarioService.save(request);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK, reason = "Usuário alterado com sucesso!")
    public void update(@RequestBody UsuarioRequest request) {
        usuarioService.save(request);
    }
}
