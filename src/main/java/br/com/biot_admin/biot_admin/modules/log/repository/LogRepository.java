package br.com.biot_admin.biot_admin.modules.log.repository;

import br.com.biot_admin.biot_admin.modules.log.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Integer>, LogRepositoryCustom {

    List<Log> findByDataAcessoLessThanEqual(LocalDateTime dataAcessoDoisMesesAtras);
}
