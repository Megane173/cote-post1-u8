package com.example.auditoria.usecase.port;

import java.util.List;

// usecase/port/HistorialAuditoriaPort.java — un solo puerto, escritura y lectura

import com.example.auditoria.domain.EstadoHallazgo;
import com.example.auditoria.domain.HallazgoId;

public interface HistorialAuditoriaPort {
    void registrar(HallazgoId hallazgoId, EstadoHallazgo anterior, EstadoHallazgo nuevo, String motivo);
    List<CambioEstadoView> listarPorHallazgo(HallazgoId hallazgoId);
}
