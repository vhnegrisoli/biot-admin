package br.com.biot_admin.biot_admin.modules.log.repository;

import br.com.biot_admin.biot_admin.modules.aplicativo.enums.EAplicativo;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.RelatorioUsuariosSeteDiasResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogRepositoryJdbcImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String getUsuariosUltimosSeteDiasQuery(EAplicativo aplicativo) {
        return "SELECT TO_CHAR(DATA_ACESSO, 'TMDAY') as diaSemana, "
            + "TO_CHAR(DATA_ACESSO, 'DD') as diaMes, "
            + "COUNT(DISTINCT USUARIO_ID) as qtdUsuarios "
            + "FROM LOG "
            + "WHERE "
            + "APLICACAO = '" + aplicativo.name() + "' "
            + "AND DATA_ACESSO BETWEEN "
            + "CURRENT_DATE - 7 AND CURRENT_DATE "
            + "GROUP BY TO_CHAR(DATA_ACESSO, 'TMDAY'), TO_CHAR(DATA_ACESSO, 'DD') "
            + "ORDER BY TO_CHAR(DATA_ACESSO, 'DD');";
    }

    public List<RelatorioUsuariosSeteDiasResponse> getUsuariosUltimosSeteDias(EAplicativo aplicativo) {
        return jdbcTemplate.query(getUsuariosUltimosSeteDiasQuery(aplicativo),
            (rs, rowNum) -> new RelatorioUsuariosSeteDiasResponse(
                rs.getString("diaSemana"),
                rs.getInt("diaMes"),
                rs.getLong("qtdUsuarios")
            ));
    }

    public void atualizaCamposDeDataEHora() {
        jdbcTemplate.update("SET lc_time TO 'pt_BR.UTF-8';");
        jdbcTemplate.update("call UPDATE_DATE_COLUMNS();");
    }
}
