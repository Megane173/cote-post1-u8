package com.example.auditoria.usecase.exception;

import com.example.auditoria.domain.HallazgoId;

public class HallazgoNotFoundException extends RuntimeException {

    public HallazgoNotFoundException(HallazgoId id) {
        super("No se encontró el hallazgo con ID: " + id);
    }
}
