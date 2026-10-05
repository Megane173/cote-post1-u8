package com.example.auditoria.usecase.port;

// usecase/port/HallazgoRepositoryPort.java

import java.util.List;
import java.util.Optional;

import com.example.auditoria.domain.HallazgoId;
import com.example.auditoria.domain.entity.HallazgoAuditoria;

// usecase/port/HallazgoRepositoryPort.java (extendido en la Parte 2)
public interface HallazgoRepositoryPort {
    void guardar(HallazgoAuditoria hallazgo);
    Optional<HallazgoAuditoria> buscarPorId(HallazgoId id);
    List<HallazgoAuditoria> buscarTodos();

    // Métodos añadidos en la Parte 2 — mismo puerto, sin stack de lectura separado
    List<ConteoCategoria> contarPorSeveridad();
    List<ConteoCategoria> contarPorEstado();
    List<PromedioCategoria> promedioDiasCierrePorArea();
}
