package br.com.biot_admin.biot_admin.modules.log.repository;

import br.com.biot_admin.biot_admin.modules.dashboard.dto.QtdUrlsAcessadasResponse;
import br.com.biot_admin.biot_admin.modules.log.model.Log;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static br.com.biot_admin.biot_admin.modules.log.enums.ETipoOperacao.CONSULTANDO;
import static br.com.biot_admin.biot_admin.modules.log.enums.ETipoOperacao.REMOVENDO;
import static br.com.biot_admin.biot_admin.modules.log.enums.ETipoOperacao.SALVANDO;
import static br.com.biot_admin.biot_admin.modules.log.enums.ETipoOperacao.ALTERANDO;
import static br.com.biot_admin.biot_admin.modules.log.model.QLog.log;

@Repository
@SuppressWarnings("PMD.TooManyStaticImports")
public class LogRepositoryImpl implements LogRepositoryCustom {

    private static final Integer LIMITE_DADOS_CONSULTA = 10;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Log> findByUsuarioIdAndAplicacao(Integer usuarioId, String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(log)
            .from(log)
            .where(log.usuarioId.eq(usuarioId)
                .and(log.aplicacao.eq(aplicacao)))
            .fetch();
    }

    @Override
    public Long countBuscasByIdAndAplicacao(Integer usuarioId, String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(log.tipoOperacao.count())
            .from(log)
            .where(log.usuarioId.eq(usuarioId)
                .and(log.aplicacao.eq(aplicacao)
                    .and(log.tipoOperacao.eq(CONSULTANDO.name()))))
            .fetchOne();
    }

    @Override
    public Long countInsercoesByIdAndAplicacao(Integer usuarioId, String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(log.tipoOperacao.count())
            .from(log)
            .where(log.usuarioId.eq(usuarioId)
                .and(log.aplicacao.eq(aplicacao)
                    .and(log.tipoOperacao.eq(SALVANDO.name()))))
            .fetchOne();
    }

    @Override
    public Long countAlteracoesByIdAndAplicacao(Integer usuarioId, String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(log.tipoOperacao.count())
            .from(log)
            .where(log.usuarioId.eq(usuarioId)
                .and(log.aplicacao.eq(aplicacao)
                    .and(log.tipoOperacao.eq(ALTERANDO.name()))))
            .fetchOne();
    }

    @Override
    public Long countRemocoesByIdAndAplicacao(Integer usuarioId, String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(log.tipoOperacao.count())
            .from(log)
            .where(log.usuarioId.eq(usuarioId)
                .and(log.aplicacao.eq(aplicacao)
                    .and(log.tipoOperacao.eq(REMOVENDO.name()))))
            .fetchOne();
    }

    @Override
    public List<QtdUrlsAcessadasResponse> getQtdUrlsAcessadas(Integer usuarioId, String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(
                Projections.constructor(QtdUrlsAcessadasResponse.class,
                    log.urlAcessada,
                    log.usuarioId.count()))
            .from(log)
            .where(log.usuarioId.eq(usuarioId)
                .and(log.aplicacao.eq(aplicacao)))
            .orderBy(log.usuarioId.count().desc())
            .groupBy(log.urlAcessada)
            .limit(LIMITE_DADOS_CONSULTA)
            .fetch();
    }
}
