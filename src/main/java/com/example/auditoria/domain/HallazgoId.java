package com.example.auditoria.domain;

// domain/valueobject/HallazgoId.java — identidad tipada

import java.util.Objects;
import java.util.UUID;

public record HallazgoId(UUID valor) {
    public HallazgoId {
        Objects.requireNonNull(valor, "HallazgoId no puede ser nulo");
    }
    public static HallazgoId nuevo() { return new HallazgoId(UUID.randomUUID()); }
    @Override public String toString() { return valor.toString(); }
}