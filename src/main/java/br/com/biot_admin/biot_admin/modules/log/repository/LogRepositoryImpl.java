package br.com.biot_admin.biot_admin.modules.log.repository;

import br.com.biot_admin.biot_admin.modules.dashboard.dto.hora.RelatorioUsuariosHoraDiaOntemResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.hora.RelatorioUsuariosHoraDiaHojeResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.dia.RelatorioUsuarios7DiasResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.mes.RelatorioSemestreResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.minuto.RelatorioUsuariosUltimos15MinutosResponse;
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
    private static final Integer QUINZE_MINUTOS = 15;
    private static final Integer ONTEM = 1;
    private static final Integer JANEIRO = 1;
    private static final Integer JUNHO = 6;
    private static final Integer JULHO = 7;
    private static final Integer DEZEMBRO = 12;


    @Autowired
    private EntityManager entityManager;

    @Override
    public List<RelatorioUsuarios7DiasResponse> getUsuariosUltimosSeteDias(String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(
                Projections.constructor(RelatorioUsuarios7DiasResponse.class,
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

    @Override
    public List<RelatorioUsuariosHoraDiaOntemResponse> getUsuariosPorHoraDeOntem(String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(
                Projections.constructor(RelatorioUsuariosHoraDiaOntemResponse.class,
                    log.hora,
                    log.usuarioId.countDistinct()))
            .from(log)
            .where(log.aplicacao.eq(aplicacao)
                .and(log.dataAcesso.dayOfMonth().eq(LocalDate.now().minusDays(ONTEM).getDayOfMonth()))
                .and(log.dataAcesso.month().eq(LocalDate.now().minusDays(ONTEM).getMonthValue())))
            .groupBy(log.hora)
            .orderBy(log.hora.asc())
            .fetch();
    }

    @Override
    public Long getTotalUsuariosPorHoraDeOntem(String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(log.usuarioId.countDistinct())
            .from(log)
            .where(log.aplicacao.eq(aplicacao)
                .and(log.dataAcesso.dayOfMonth().eq(LocalDate.now().minusDays(ONTEM).getDayOfMonth()))
                .and(log.dataAcesso.month().eq(LocalDate.now().minusDays(ONTEM).getMonthValue())))
            .fetchOne();
    }

    @Override
    public List<RelatorioUsuariosUltimos15MinutosResponse> getUsuariosAtivosNosUltimos15Minutos(String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(
                Projections.constructor(RelatorioUsuariosUltimos15MinutosResponse.class,
                    log.hora,
                    log.minuto,
                    log.usuarioId.countDistinct()))
            .from(log)
            .where(log.aplicacao.eq(aplicacao)
                .and(log.dataAcesso.between(LocalDateTime.now().minusMinutes(QUINZE_MINUTOS), LocalDateTime.now())))
            .groupBy(log.hora, log.minuto)
            .orderBy(log.hora.asc())
            .fetch();
    }

    @Override
    public Long getTotalUsuariosAtivosNosUltimos15Minutos(String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(
                log.usuarioId.countDistinct())
            .from(log)
            .where(log.aplicacao.eq(aplicacao)
                .and(log.dataAcesso.between(LocalDateTime.now().minusMinutes(QUINZE_MINUTOS), LocalDateTime.now())))
            .fetchOne();
    }

    @Override
    public List<RelatorioSemestreResponse> getUsuariosSemestreAnterior(String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(
                Projections.constructor(RelatorioSemestreResponse.class,
                    log.mes,
                    log.mesNome,
                    log.usuarioId.countDistinct()))
            .from(log)
            .where(log.aplicacao.eq(aplicacao)
                .and(log.mes.goe(JANEIRO))
                .and(log.mes.loe(JUNHO))
                .and(log.ano.eq(LocalDateTime.now().getYear())))
            .groupBy(log.mesNome, log.mes)
            .orderBy(log.mes.asc())
            .fetch();
    }

    @Override
    public List<RelatorioSemestreResponse> getUsuariosSemestreAtual(String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(
                Projections.constructor(RelatorioSemestreResponse.class,
                    log.mes,
                    log.mesNome,
                    log.usuarioId.countDistinct()))
            .from(log)
            .where(log.aplicacao.eq(aplicacao)
                .and(log.mes.goe(JULHO))
                .and(log.mes.loe(DEZEMBRO))
                .and(log.ano.eq(LocalDateTime.now().getYear())))
            .groupBy(log.mesNome, log.mes)
            .orderBy(log.mes.asc())
            .fetch();
    }

    @Override
    public Long getTotalUsuariosNoMesAtual(String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(
                log.usuarioId.countDistinct())
            .from(log)
            .where(log.aplicacao.eq(aplicacao).and(log.mes.eq(LocalDateTime.now().getMonthValue())))
            .fetchOne();
    }
}
