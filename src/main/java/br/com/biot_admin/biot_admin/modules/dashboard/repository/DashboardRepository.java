package br.com.biot_admin.biot_admin.modules.dashboard.repository;

import br.com.biot_admin.biot_admin.modules.dashboard.dto.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConfigurationProperties("spring.datasource-app")
public class DashboardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UsuarioResponse> findAllUsuarios() {
        return jdbcTemplate.query("SELECT ID, NOME, EMAIL FROM USUARIO",
            (rs, rowNum) -> new UsuarioResponse(
                rs.getInt("ID"),
                rs.getString("NOME"),
                rs.getString("EMAIL")));
    }
}
