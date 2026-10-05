package com.example.auditoria.usecase.impl;

import java.util.List;

import com.example.auditoria.adapter.in.web.dto.HallazgoResponse;
import com.example.auditoria.domain.HallazgoId;
import com.example.auditoria.domain.entity.HallazgoAuditoria;
import com.example.auditoria.usecase.ConsultarHallazgoUseCase;
import com.example.auditoria.usecase.exception.HallazgoNotFoundException;
import com.example.auditoria.usecase.port.HallazgoRepositoryPort;

public class ConsultarHallazgoService implements ConsultarHallazgoUseCase {

    private final HallazgoRepositoryPort repo;

    public ConsultarHallazgoService(HallazgoRepositoryPort repo) {
        this.repo = repo;
    }

    @Override
    public HallazgoResponse buscarPorId(HallazgoId id) {
        HallazgoAuditoria hallazgo = repo.buscarPorId(id)
            .orElseThrow(() -> new HallazgoNotFoundException(id));

        return convertir(hallazgo);
    }

    @Override
    public List<HallazgoResponse> listarTodos() {
        return repo.buscarTodos()
            .stream()
            .map(this::convertir)
            .toList();
    }

    private HallazgoResponse convertir(HallazgoAuditoria hallazgo) {
        return new HallazgoResponse(
            hallazgo.getId(),
            hallazgo.getTitulo(),
            hallazgo.getDescripcion(),
            hallazgo.getAreaResponsable(),
            hallazgo.getSeveridad(),
            hallazgo.getEstado(),
            hallazgo.getFechaDeteccion(),
            hallazgo.getPlanRemediacion() != null
                ? hallazgo.getPlanRemediacion().responsable()
                : null,
            hallazgo.getPlanRemediacion() != null
                ? hallazgo.getPlanRemediacion().fechaLimite()
                : null,
            hallazgo.getPlanRemediacion() != null
                ? hallazgo.getPlanRemediacion().notas()
                : null
        );
    }
}