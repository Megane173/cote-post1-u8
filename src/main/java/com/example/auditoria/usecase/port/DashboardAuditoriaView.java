package com.example.auditoria.usecase.port;

// usecase/port/DashboardAuditoriaView.java

import java.util.List;

public record DashboardAuditoriaView(
    List<ConteoCategoria> porSeveridad,
    List<ConteoCategoria> porEstado,
    List<PromedioCategoria> promedioDiasCierrePorArea
) {}