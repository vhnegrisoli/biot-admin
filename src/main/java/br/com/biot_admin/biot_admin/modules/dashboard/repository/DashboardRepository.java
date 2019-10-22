package br.com.biot_admin.biot_admin.modules.dashboard.repository;

import br.com.biot_admin.biot_admin.modules.dashboard.dto.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DashboardRepository {

    @Autowired
    @Qualifier("app")
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<UsuarioResponse> findAllUsuarios() {
        return jdbcTemplate
            .query("SELECT ID, NOME, EMAIL FROM USUARIO",
                (rs, rowNum) -> new UsuarioResponse(
                    rs.getInt("ID"),
                    rs.getString("NOME"),
                    rs.getString("EMAIL")));
    }
}
