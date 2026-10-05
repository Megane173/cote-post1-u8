package com.example.auditoria.usecase.impl;

import com.example.auditoria.domain.EstadoHallazgo;
import com.example.auditoria.domain.HallazgoId;
import com.example.auditoria.domain.entity.HallazgoAuditoria;
import com.example.auditoria.usecase.CerrarHallazgoUseCase;
import com.example.auditoria.usecase.exception.HallazgoNotFoundException;
import com.example.auditoria.usecase.port.HallazgoRepositoryPort;
import com.example.auditoria.usecase.port.HistorialAuditoriaPort;

// usecase/impl/CerrarHallazgoService.java (extendido en la Parte 2)
public class CerrarHallazgoService implements CerrarHallazgoUseCase {

    private final HallazgoRepositoryPort repo;
    private final HistorialAuditoriaPort historial;

    

    public CerrarHallazgoService(HallazgoRepositoryPort repo, HistorialAuditoriaPort historial) {
        this.repo = repo;
        this.historial = historial;
    }

    @Override
    public void ejecutar(HallazgoId id) {
        HallazgoAuditoria hallazgo = repo.buscarPorId(id)
            .orElseThrow(() -> new HallazgoNotFoundException(id));
        EstadoHallazgo anterior = hallazgo.cerrar();
        repo.guardar(hallazgo);
        historial.registrar(id, anterior, EstadoHallazgo.CERRADO, "Cierre de remediacion");
    }
}