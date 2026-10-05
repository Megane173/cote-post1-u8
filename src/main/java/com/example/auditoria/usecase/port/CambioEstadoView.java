package com.example.auditoria.usecase.port;
// usecase/port/CambioEstadoView.java

import java.time.LocalDateTime;

public record CambioEstadoView(String estadoAnterior, String estadoNuevo, String motivo, LocalDateTime fecha) {}
