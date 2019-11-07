package br.com.biot_admin.biot_admin.modules.dashboard.service;

import br.com.biot_admin.biot_admin.exceptions.ValidacaoException;
import br.com.biot_admin.biot_admin.modules.aplicativo.model.Aplicativo;
import br.com.biot_admin.biot_admin.modules.aplicativo.repository.AplicativoRepository;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.DashboardResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.dia.Relatorios7DiasResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.hora.RelatoriosHorasDiaHojeResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.hora.RelatoriosHorasDiaOntemResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.mes.RelatorioMesResponse;
import br.com.biot_admin.biot_admin.modules.dashboard.dto.minuto.RelatoriosUltimos15MinutosResponse;
import br.com.biot_admin.biot_admin.modules.log.repository.LogRepository;
import br.com.biot_admin.biot_admin.modules.usuario.dto.UsuarioAutenticado;
import br.com.biot_admin.biot_admin.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.TimeZone;

import static br.com.biot_admin.biot_admin.modules.aplicativo.exception.AplicativoException.APLICATIVO_NAO_ENCONTRADO;

@Service
public class DashboardService {

    private static final String TIMEZONE_BUENOS_AIRES = "America/Argentina/Buenos_Aires";

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private AplicativoRepository aplicativoRepository;

    public DashboardResponse getRelatorios(Integer aplicacaoId) {
        TimeZone.setDefault(TimeZone.getTimeZone(TIMEZONE_BUENOS_AIRES));
        var usuarioLogado = usuarioService.getUsuarioAutenticado();
        var aplicativo = aplicativoRepository.findById(aplicacaoId)
            .orElseThrow(APLICATIVO_NAO_ENCONTRADO::getException);
        validarPermissaoDoUsuarioSobRelatorioDoAplciativo(usuarioLogado, aplicativo);
        var codigoAplicacao = aplicativo.getCodigo().name();
        return DashboardResponse
            .builder()
            .relatorioUltimos7Dias(prepararResponseRelatoriosSeteDias(codigoAplicacao))
            .relatorioHorasDia(prepararResponseRelatoriosHorasDoDia(codigoAplicacao))
            .relatoriosHorasDiaOntem(prepararResponseRelatoriosHorasDoDiaDeOntem(codigoAplicacao))
            .relatorioUltimos15Minutos(prepararResponseRelatoriosUltimos15Minutos(codigoAplicacao))
            .relatoriosMensais(prepararResponseRelatoriosMes(codigoAplicacao))
            .build();
    }

    private Relatorios7DiasResponse prepararResponseRelatoriosSeteDias(String codigoAplicacao) {
        return Relatorios7DiasResponse
            .builder()
            .totalUsuariosUltimosSeteDias(logRepository.getTotalUsuariosUltimosSeteDias(codigoAplicacao))
            .relatorioUsuariosUltimosSeteDias(logRepository.getUsuariosUltimosSeteDias(codigoAplicacao))
            .build();
    }

    private RelatoriosHorasDiaHojeResponse prepararResponseRelatoriosHorasDoDia(String codigoAplicacao) {
        return RelatoriosHorasDiaHojeResponse
            .builder()
            .totalRelatorioUsuariosHoraDiaHoje(logRepository.getTotalUsuariosPorHoraDeHoje(codigoAplicacao))
            .relatorioUsuariosHoraDiaHoje(logRepository.getUsuariosPorHoraDeHoje(codigoAplicacao))
            .build();
    }

    private RelatoriosHorasDiaOntemResponse prepararResponseRelatoriosHorasDoDiaDeOntem(String codigoAplicacao) {
        return RelatoriosHorasDiaOntemResponse
            .builder()
            .totalRelatorioUsuariosHoraDiaOntem(logRepository.getTotalUsuariosPorHoraDeOntem(codigoAplicacao))
            .relatorioUsuariosHoraDiaOntem(logRepository.getUsuariosPorHoraDeOntem(codigoAplicacao))
            .build();
    }

    private RelatoriosUltimos15MinutosResponse prepararResponseRelatoriosUltimos15Minutos(String codigoAplicacao) {
        return RelatoriosUltimos15MinutosResponse
            .builder()
            .totalUsuariosAtivosUltimos15Minutos(logRepository
                .getTotalUsuariosAtivosNosUltimos15Minutos(codigoAplicacao))
            .relatorioUsuariosAtivosUltimos15Minutos(logRepository
                .getUsuariosAtivosNosUltimos15Minutos(codigoAplicacao))

            .build();
    }

    private RelatorioMesResponse prepararResponseRelatoriosMes(String codigoAplicacao) {
        return RelatorioMesResponse
            .builder()
            .mesAtual(tratarNomeMes())
            .relatorioSemestreAnterior(logRepository.getUsuariosSemestreAnterior(codigoAplicacao))
            .relatorioSemestreAtual(logRepository.getUsuariosSemestreAtual(codigoAplicacao))
            .totalUsuariosNoMes(logRepository.getTotalUsuariosNoMesAtual(codigoAplicacao))
            .build();
    }

    private void validarPermissaoDoUsuarioSobRelatorioDoAplciativo(UsuarioAutenticado usuarioAutenticado,
                                                                   Aplicativo aplicativo) {
        if (usuarioAutenticado.isUser() && !usuarioAutenticado.getAplicativos().contains(aplicativo)) {
            throw new ValidacaoException("Você não tem permissões para visualizar os dados.");
        }
    }

    private String tratarNomeMes() {
        var mes = LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("pt"));
        return mes.substring(0, 1).toUpperCase() + mes.substring(1);
    }
}
