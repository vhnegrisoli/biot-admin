package br.com.biot_admin.biot_admin.modules.log.repository;

import br.com.biot_admin.biot_admin.modules.dashboard.dto.RelatorioUsuariosHoraDiaHojeResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.RelatorioUsuariosSeteDiasResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static br.com.biot_admin.biot_admin.modules.log.model.QLog.log;

@Repository
@SuppressWarnings("PMD.TooManyStaticImports")
public class LogRepositoryImpl implements LogRepositoryCustom {

    private static final Integer UMA_SEMANA = 7;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<RelatorioUsuariosSeteDiasResponse> getUsuariosUltimosSeteDias(String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(
                Projections.constructor(RelatorioUsuariosSeteDiasResponse.class,
                    log.diaMes,
                    log.mes,
                    log.usuarioId.countDistinct()))
            .from(log)
            .where(log.aplicacao.eq(aplicacao)
                .and(log.dataAcesso.between(LocalDateTime.now().minusDays(UMA_SEMANA), LocalDateTime.now())))
            .groupBy(log.diaMes, log.mes)
            .orderBy(log.mes.asc(), log.diaMes.asc())
            .fetch();
    }

    @Override
    public Long getTotalUsuariosUltimosSeteDias(String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(log.usuarioId.countDistinct())
            .from(log)
            .where(log.aplicacao.eq(aplicacao)
                .and(log.dataAcesso.between(LocalDateTime.now().minusDays(UMA_SEMANA), LocalDateTime.now())))
            .fetchOne();
    }

    @Override
    public List<RelatorioUsuariosHoraDiaHojeResponse> getUsuariosPorHoraDeHoje(String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(
                Projections.constructor(RelatorioUsuariosHoraDiaHojeResponse.class,
                    log.hora,
                    log.usuarioId.countDistinct()))
            .from(log)
            .where(log.aplicacao.eq(aplicacao)
                .and(log.dataAcesso.dayOfMonth().eq(LocalDate.now().getDayOfMonth()))
                .and(log.dataAcesso.month().eq(LocalDate.now().getMonthValue())))
            .groupBy(log.hora)
            .orderBy(log.hora.asc())
            .fetch();
    }

    @Override
    public Long getTotalUsuariosPorHoraDeHoje(String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(log.usuarioId.countDistinct())
            .from(log)
            .where(log.aplicacao.eq(aplicacao)
                .and(log.dataAcesso.dayOfMonth().eq(LocalDate.now().getDayOfMonth()))
                .and(log.dataAcesso.month().eq(LocalDate.now().getMonthValue())))
            .fetchOne();
    }
}
