package com.example.auditoria.usecase.impl;

import java.util.List;

import com.example.auditoria.domain.HallazgoId;
import com.example.auditoria.usecase.ConsultarHistorialUseCase;
import com.example.auditoria.usecase.port.CambioEstadoView;
import com.example.auditoria.usecase.port.HistorialAuditoriaPort;

public class ConsultarHistorialService implements ConsultarHistorialUseCase {

    private final HistorialAuditoriaPort historialPort;

    public ConsultarHistorialService(HistorialAuditoriaPort historialPort) {
        this.historialPort = historialPort;
    }

    @Override
    public List<CambioEstadoView> ejecutar(HallazgoId id) {
        return historialPort.listarPorHallazgo(id);
    }
}