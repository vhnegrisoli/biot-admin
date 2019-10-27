package br.com.biot_admin.biot_admin.modules.log.repository;

import br.com.biot_admin.biot_admin.modules.log.model.Log;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static br.com.biot_admin.biot_admin.modules.log.model.QLog.log;
import static br.com.biot_admin.biot_admin.modules.log.enums.ETipoOperacao.BUSCANDO;
import static br.com.biot_admin.biot_admin.modules.log.enums.ETipoOperacao.ALTERANDO;
import static br.com.biot_admin.biot_admin.modules.log.enums.ETipoOperacao.SALVANDO;
import static br.com.biot_admin.biot_admin.modules.log.enums.ETipoOperacao.REMOVENDO;

@Repository
@SuppressWarnings("PMD.TooManyStaticImports")
public class LogRepositoryImpl implements LogRepositoryCustom {

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

    public Long countBuscasByIdAndAplicacao(Integer usuarioId, String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(log.tipoOperacao.count())
            .from(log)
            .where(log.usuarioId.eq(usuarioId)
                .and(log.aplicacao.eq(aplicacao)
                    .and(log.tipoOperacao.eq(BUSCANDO.name()))))
            .fetchOne();
    }

    public Long countInsercoesByIdAndAplicacao(Integer usuarioId, String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(log.tipoOperacao.count())
            .from(log)
            .where(log.usuarioId.eq(usuarioId)
                .and(log.aplicacao.eq(aplicacao)
                    .and(log.tipoOperacao.eq(SALVANDO.name()))))
            .fetchOne();
    }

    public Long countAlteracoesByIdAndAplicacao(Integer usuarioId, String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(log.tipoOperacao.count())
            .from(log)
            .where(log.usuarioId.eq(usuarioId)
                .and(log.aplicacao.eq(aplicacao)
                    .and(log.tipoOperacao.eq(ALTERANDO.name()))))
            .fetchOne();
    }

    public Long countRemocoesByIdAndAplicacao(Integer usuarioId, String aplicacao) {
        return new JPAQuery<Void>(entityManager)
            .select(log.tipoOperacao.count())
            .from(log)
            .where(log.usuarioId.eq(usuarioId)
                .and(log.aplicacao.eq(aplicacao)
                    .and(log.tipoOperacao.eq(REMOVENDO.name()))))
            .fetchOne();
    }
}
