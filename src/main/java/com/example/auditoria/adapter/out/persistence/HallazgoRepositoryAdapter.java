package com.example.auditoria.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.auditoria.domain.EstadoHallazgo;
import com.example.auditoria.domain.HallazgoId;
import com.example.auditoria.domain.PlanRemediacion;
import com.example.auditoria.domain.entity.HallazgoAuditoria;
import com.example.auditoria.usecase.port.ConteoCategoria;
import com.example.auditoria.usecase.port.HallazgoRepositoryPort;
import com.example.auditoria.usecase.port.PromedioCategoria;

// adapter/out/persistence/HallazgoRepositoryAdapter.java
@Component
public class HallazgoRepositoryAdapter implements HallazgoRepositoryPort {

    private final HallazgoJpaRepository jpa;

    public HallazgoRepositoryAdapter(HallazgoJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public void guardar(HallazgoAuditoria hallazgo) {
        jpa.save(toEntity(hallazgo));
    }

    @Override
    public Optional<HallazgoAuditoria> buscarPorId(HallazgoId id) {
        return jpa.findById(id.toString()).map(this::toDomain);
    }

    private HallazgoAuditoria toDomain(HallazgoJpaEntity e) {
        HallazgoAuditoria h = new HallazgoAuditoria(new HallazgoId(UUID.fromString(e.getId())),
                e.getTitulo(), e.getDescripcion(), e.getAreaResponsable(), e.getSeveridad(), e.getFechaDeteccion());
        if (e.getPlanResponsable() != null) {
            h.iniciarRemediacion(new PlanRemediacion(
                    e.getPlanResponsable(), e.getPlanFechaLimite(), e.getPlanNotas()));
        }
        if (e.getEstado() == EstadoHallazgo.CERRADO) {
            h.cerrar();
        }
        if (e.getEstado() == EstadoHallazgo.REABIERTO) {
            h.reabrir();
        }
        return h;
    }

    private HallazgoJpaEntity toEntity(HallazgoAuditoria h) {
        HallazgoJpaEntity e = new HallazgoJpaEntity();
        e.setId(h.getId().toString());
        e.setTitulo(h.getTitulo());
        e.setDescripcion(h.getDescripcion());
        e.setAreaResponsable(h.getAreaResponsable());
        e.setSeveridad(h.getSeveridad());
        e.setEstado(h.getEstado());
        e.setFechaDeteccion(h.getFechaDeteccion());
        e.setFechaCierre(h.getFechaCierre());
        if (h.getPlanRemediacion() != null) {
            e.setPlanResponsable(h.getPlanRemediacion().responsable());
            e.setPlanFechaLimite(h.getPlanRemediacion().fechaLimite());
            e.setPlanNotas(h.getPlanRemediacion().notas());
        }
        return e;
    }

    @Override
    public List<HallazgoAuditoria> buscarTodos() {
        return jpa.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<ConteoCategoria> contarPorSeveridad() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ConteoCategoria> contarPorEstado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PromedioCategoria> promedioDiasCierrePorArea() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
