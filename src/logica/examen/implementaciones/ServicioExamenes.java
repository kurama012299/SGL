/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen.implementaciones;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.examen.modelos.Examen;
import logica.examen_conduccion.implementaciones.ServiciosExamenesConduccion;
import logica.examen_conduccion.modelos.ExamenConduccion;
import logica.examen_medico.implementaciones.ServiciosExamenesMedicos;
import logica.examen_medico.modelos.ExamenMedico;

/**
 *
 * @author Adrian
 */
public class ServicioExamenes {
    
     public static ObservableList<Examen> obtenerExamenesPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
        // Validar las fechas de entrada
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Ambas fechas deben ser proporcionadas");
        }
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

        // Obtener todas los examenes
        ObservableList<Examen> examenes = obtenerTodosLosExamenes();

        // Convertir LocalDate a Date para comparación
        Date fechaInicioDate = java.sql.Date.valueOf(fechaInicio);
        Date fechaFinDate = java.sql.Date.valueOf(fechaFin.plusDays(1)); // +1 día para incluir la fecha fin

        // Filtrar por el rango de fechas
        List<Examen> examenesFiltrados = examenes.stream()
                .filter(examen -> examen.getFecha() != null)
                .filter(examen
                        -> !examen.getFecha().before(fechaInicioDate)
                && examen.getFecha().before(fechaFinDate))
                .sorted((inf1, inf2) -> inf2.getFecha().compareTo(inf1.getFecha())) // Orden descendente
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(examenesFiltrados);
    }

    public static ObservableList<Examen> obtenerTodosLosExamenes() throws Exception{
        
        ObservableList<Examen> examenes = FXCollections.observableArrayList();
        ObservableList<ExamenConduccion> examenesPracticos = ServiciosExamenesConduccion.ObtenerExamenesPracticos();
        ObservableList<ExamenConduccion> examenesTeoricos = ServiciosExamenesConduccion.ObtenerExamenesTeoricos();
        ObservableList<ExamenMedico> examenesMedicos = ServiciosExamenesMedicos.ObtenerExamenesMedico();
        
        examenes.addAll(examenesMedicos);
        examenes.addAll(examenesPracticos);
        examenes.addAll(examenesTeoricos);
        
        return examenes;
    }
}
