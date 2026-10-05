package com.example.auditoria.usecase.impl;

// usecase/impl/IniciarRemediacionService.java

import java.time.LocalDate;

import com.example.auditoria.domain.HallazgoId;
import com.example.auditoria.domain.PlanRemediacion;
import com.example.auditoria.domain.entity.HallazgoAuditoria;
import com.example.auditoria.usecase.IniciarRemediacionUseCase;
import com.example.auditoria.usecase.exception.HallazgoNotFoundException;
import com.example.auditoria.usecase.port.HallazgoRepositoryPort;

public class IniciarRemediacionService implements IniciarRemediacionUseCase {

    private final HallazgoRepositoryPort repo;

    public IniciarRemediacionService(HallazgoRepositoryPort repo) {
        this.repo = repo;
    }

    @Override
    public void ejecutar(HallazgoId id, String responsable, LocalDate fechaLimite, String notas) {
        HallazgoAuditoria hallazgo = repo.buscarPorId(id)
            .orElseThrow(() -> new HallazgoNotFoundException(id));
        hallazgo.iniciarRemediacion(new PlanRemediacion(responsable, fechaLimite, notas));
        repo.guardar(hallazgo);
    }
}