/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.auditoria.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


// adapter/out/persistence/HallazgoJpaRepository.java (extendido en la Parte 2)
public interface HallazgoJpaRepository extends JpaRepository<HallazgoJpaEntity, String> {

    @Query("SELECT h.severidad AS categoria, COUNT(h) AS total FROM HallazgoJpaEntity h GROUP BY h.severidad")
    List<ConteoProjection> contarPorSeveridad();

    @Query("SELECT h.estado AS categoria, COUNT(h) AS total FROM HallazgoJpaEntity h GROUP BY h.estado")
    List<ConteoProjection> contarPorEstado();

    @Query("SELECT h.areaResponsable AS categoria, " +
       "AVG(TIMESTAMPDIFF(DAY, h.fechaDeteccion, h.fechaCierre)) AS promedio " +
       "FROM HallazgoJpaEntity h " +
       "WHERE h.estado = 'CERRADO' " +
       "GROUP BY h.areaResponsable")
    List<PromedioProjection> promedioDiasCierrePorArea();

    interface ConteoProjection {
        String getCategoria();
        Long getTotal();
    }

    interface PromedioProjection {
        String getCategoria();
        Double getPromedio();
    }
}
