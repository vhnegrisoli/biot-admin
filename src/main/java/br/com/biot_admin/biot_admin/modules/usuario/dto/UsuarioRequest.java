package br.com.biot_admin.biot_admin.modules.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {

    private Integer id;
    private String nome;
    private String email;
    @CPF
    private String cpf;
    private String senha;
    private List<Integer> aplicativosIds;

}
