package com.example.auditoria.usecase;

import com.example.auditoria.domain.HallazgoId;

public interface CerrarHallazgoUseCase {
    void ejecutar(HallazgoId id);
}
