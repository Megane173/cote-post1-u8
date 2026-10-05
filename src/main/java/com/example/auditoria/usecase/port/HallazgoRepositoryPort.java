package com.example.auditoria.usecase.port;

// usecase/port/HallazgoRepositoryPort.java

import java.util.List;
import java.util.Optional;

import com.example.auditoria.domain.HallazgoId;
import com.example.auditoria.domain.entity.HallazgoAuditoria;

public interface HallazgoRepositoryPort {
    void guardar(HallazgoAuditoria hallazgo);
    Optional<HallazgoAuditoria> buscarPorId(HallazgoId id);
    List<HallazgoAuditoria> buscarTodos();
}
