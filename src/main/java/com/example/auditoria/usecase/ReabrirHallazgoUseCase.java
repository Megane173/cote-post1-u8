package com.example.auditoria.usecase;

import com.example.auditoria.domain.HallazgoId;

public interface ReabrirHallazgoUseCase {
    void ejecutar(HallazgoId id, String motivo);
}
