package br.com.biot_admin.biot_admin.modules.dashboard;

import br.com.biot_admin.biot_admin.exceptions.ValidacaoException;
import br.com.biot_admin.biot_admin.modules.aplicativo.model.Aplicativo;
import br.com.biot_admin.biot_admin.modules.aplicativo.repository.AplicativoRepository;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.DashboardResponse;
import br.com.biot_admin.biot_admin.modules.log.repository.LogRepository;
import br.com.biot_admin.biot_admin.modules.usuario.dto.UsuarioAutenticado;
import br.com.biot_admin.biot_admin.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.biot_admin.biot_admin.modules.aplicativo.exception.AplicativoException.APLICATIVO_NAO_ENCONTRADO;

@Service
public class DashboardService {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private AplicativoRepository aplicativoRepository;

    public DashboardResponse getRelatorios(Integer aplicacaoId) {
        var usuarioLogado = usuarioService.getUsuarioAutenticado();
        var aplicativo = aplicativoRepository.findById(aplicacaoId)
            .orElseThrow(APLICATIVO_NAO_ENCONTRADO::getException);
        validarPermissaoDoUsuarioSobRelatorioDoAplciativo(usuarioLogado, aplicativo);
        var codigoAplicacao = aplicativo.getCodigo().name();
        return DashboardResponse
            .builder()
            .totalUsuariosUltimosSeteDias(logRepository.getTotalUsuariosUltimosSeteDias(codigoAplicacao))
            .relatorioUsuariosUltimosSeteDias(logRepository.getUsuariosUltimosSeteDias(codigoAplicacao))
            .totalRelatorioUsuariosHoraDiaHoje(logRepository.getTotalUsuariosPorHoraDeHoje(codigoAplicacao))
            .relatorioUsuariosHoraDiaHoje(logRepository.getUsuariosPorHoraDeHoje(codigoAplicacao))
            .build();
    }

    private void validarPermissaoDoUsuarioSobRelatorioDoAplciativo(UsuarioAutenticado usuarioAutenticado,
                                                                   Aplicativo aplicativo) {
        if (usuarioAutenticado.isUser() && !usuarioAutenticado.getAplicativos().contains(aplicativo)) {
            throw new ValidacaoException("Você não tem permissões para visualizar os dados.");
        }
    }
}
