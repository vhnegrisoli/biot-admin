package br.com.biot_admin.biot_admin.modules.aplicativo.repository;

import br.com.biot_admin.biot_admin.modules.aplicativo.model.Aplicativo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AplicativoRepository extends JpaRepository<Aplicativo, Integer> {
}
