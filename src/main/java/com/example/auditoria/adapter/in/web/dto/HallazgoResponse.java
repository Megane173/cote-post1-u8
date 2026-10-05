package com.example.auditoria.adapter.in.web.dto;

import com.example.auditoria.domain.EstadoHallazgo;
import com.example.auditoria.domain.HallazgoId;
import com.example.auditoria.domain.Severidad;

import java.time.LocalDate;

public record HallazgoResponse(
    HallazgoId id,
    String titulo,
    String descripcion,
    String areaResponsable,
    Severidad severidad,
    EstadoHallazgo estado,
    LocalDate fechaDeteccion,
    String responsableRemediacion,
    LocalDate fechaLimiteRemediacion,
    String notasRemediacion
) {
}