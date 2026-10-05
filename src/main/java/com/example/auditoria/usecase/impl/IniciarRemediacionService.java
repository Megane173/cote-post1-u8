package com.example.auditoria.usecase.impl;

import java.time.LocalDate;

import com.example.auditoria.domain.EstadoHallazgo;
import com.example.auditoria.domain.HallazgoId;
import com.example.auditoria.domain.PlanRemediacion;
import com.example.auditoria.domain.entity.HallazgoAuditoria;
import com.example.auditoria.usecase.IniciarRemediacionUseCase;
import com.example.auditoria.usecase.exception.HallazgoNotFoundException;
import com.example.auditoria.usecase.port.HallazgoRepositoryPort;
import com.example.auditoria.usecase.port.HistorialAuditoriaPort;

public class IniciarRemediacionService implements IniciarRemediacionUseCase {

    private final HallazgoRepositoryPort repo;
    private final HistorialAuditoriaPort historial;

    public IniciarRemediacionService(
            HallazgoRepositoryPort repo,
            HistorialAuditoriaPort historial) {
        this.repo = repo;
        this.historial = historial;
    }

    @Override
    public void ejecutar(
            HallazgoId id,
            String responsable,
            LocalDate fechaLimite,
            String notas) {

        HallazgoAuditoria hallazgo = repo.buscarPorId(id)
            .orElseThrow(() -> new HallazgoNotFoundException(id));

        EstadoHallazgo anterior = hallazgo.iniciarRemediacion(
            new PlanRemediacion(responsable, fechaLimite, notas)
        );

        repo.guardar(hallazgo);

        historial.registrar(
            id,
            anterior,
            EstadoHallazgo.EN_REMEDIACION,
            "Inicio de remediacion"
        );
    }
}