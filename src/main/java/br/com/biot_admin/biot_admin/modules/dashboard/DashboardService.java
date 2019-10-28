package br.com.biot_admin.biot_admin.modules.dashboard;

import br.com.biot_admin.biot_admin.modules.aplicativo.repository.AplicativoRepository;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.DashboardResponse;
import br.com.biot_admin.biot_admin.modules.log.repository.LogRepository;
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
        var usuarioId = usuarioLogado.getId();
        var aplicativo = aplicativoRepository.findById(aplicacaoId)
            .orElseThrow(APLICATIVO_NAO_ENCONTRADO::getException);
        return DashboardResponse
            .builder()
            .qtdConsultas(logRepository.countBuscasByIdAndAplicacao(usuarioId, aplicativo.getCodigo().name()))
            .qtdInsercoes(logRepository.countInsercoesByIdAndAplicacao(usuarioId, aplicativo.getCodigo().name()))
            .qtdAtualizacoes(logRepository.countAlteracoesByIdAndAplicacao(usuarioId, aplicativo.getCodigo().name()))
            .qtdRemocoes(logRepository.countRemocoesByIdAndAplicacao(usuarioId, aplicativo.getCodigo().name()))
            .qtdUrlsAcessadas(logRepository.getQtdUrlsAcessadas(usuarioId, aplicativo.getCodigo().name()))
            .build();
    }
}
