package com.example.auditoria.usecase.impl;

import com.example.auditoria.domain.HallazgoId;
import com.example.auditoria.domain.entity.HallazgoAuditoria;
import com.example.auditoria.usecase.CerrarHallazgoUseCase;
import com.example.auditoria.usecase.exception.HallazgoNotFoundException;
import com.example.auditoria.usecase.port.HallazgoRepositoryPort;

public class CerrarHallazgoService implements CerrarHallazgoUseCase {

    private final HallazgoRepositoryPort repo;

    public CerrarHallazgoService(HallazgoRepositoryPort repo) {
        this.repo = repo;
    }

    @Override
    public void ejecutar(HallazgoId id) {
        HallazgoAuditoria hallazgo = repo.buscarPorId(id)
            .orElseThrow(() -> new HallazgoNotFoundException(id));

        hallazgo.cerrar();

        repo.guardar(hallazgo);
    }
}