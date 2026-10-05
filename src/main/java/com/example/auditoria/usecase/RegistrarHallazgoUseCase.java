package com.example.auditoria.usecase;

// usecase/RegistrarHallazgoUseCase.java

import java.time.LocalDate;

import com.example.auditoria.domain.HallazgoId;
import com.example.auditoria.domain.Severidad;

public interface RegistrarHallazgoUseCase {
    HallazgoId ejecutar(String titulo, String descripcion, String areaResponsable,
                         Severidad severidad, LocalDate fechaDeteccion);
}