package com.example.auditoria.usecase;


import java.time.LocalDate;

import com.example.auditoria.domain.HallazgoId;

public interface IniciarRemediacionUseCase {

    void ejecutar(
        HallazgoId id,
        String responsable,
        LocalDate fechaLimite,
        String notas
    );
}
