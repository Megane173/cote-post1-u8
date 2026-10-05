package com.example.auditoria.domain;

// domain/valueobject/TransicionInvalidaException.java
public class TransicionInvalidaException extends RuntimeException {
    public TransicionInvalidaException(EstadoHallazgo actual, EstadoHallazgo destino) {
        super("No se puede transicionar de " + actual + " a " + destino);
    }
}