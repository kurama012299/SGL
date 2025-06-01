/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen.implementaciones;

import java.sql.SQLException;
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
import logica.usuario.implementaciones.ServicioUsuario;

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
        ObservableList<ExamenConduccion> examenesPracticos = ServiciosExamenesConduccion.obtenerExamenesPracticos();
        ObservableList<ExamenConduccion> examenesTeoricos = ServiciosExamenesConduccion.obtenerExamenesTeoricos();
        ObservableList<ExamenMedico> examenesMedicos = ServiciosExamenesMedicos.obtenerExamenesMedico();
        
        examenes.addAll(examenesMedicos);
        examenes.addAll(examenesPracticos);
        examenes.addAll(examenesTeoricos);
        
        return examenes;
    }
    
    public static ObservableList<Examen> obtenerTodosLosExamenesMedicos() throws Exception{
        
        ObservableList<Examen> examenes = obtenerTodosLosExamenes();
        for(Examen examen:examenes)
            if(!(examen.getTipo().equals("Médico")))
                examenes.remove(examen);
        return examenes;
    }
    
   public static ObservableList<Examen> obtenerTodosLosExamenesConduccion() throws Exception{
        
        ObservableList<Examen> examenes = FXCollections.observableArrayList();
        ObservableList<ExamenConduccion> examenesPracticos = ServiciosExamenesConduccion.obtenerExamenesPracticos();
        ObservableList<ExamenConduccion> examenesTeoricos = ServiciosExamenesConduccion.obtenerExamenesTeoricos();
        
        examenes.addAll(examenesPracticos);
        examenes.addAll(examenesTeoricos);
        
        return examenes;
    }
   
   public static ObservableList<Examen> obtenerTodosLosExamenesPorEntidad(Long id) throws Exception{
       ObservableList<Examen> examenes = obtenerTodosLosExamenes();
       ObservableList<Examen> examenesEntidad = FXCollections.observableArrayList();
        for(Examen examen:examenes)
            if(examen.getEntidad().getId().equals(id))
                examenesEntidad.add(examen);
        return examenesEntidad;
   }
   
   public static int TotalMedicos() throws Exception{
        return ServicioUsuario.obtenerUsuariosExamenesMedicos().size();
    } 
     
     public static int TotalExamenesAprobados(Long id) throws SQLException, Exception {

        ObservableList<Examen> examenes = ServicioExamenes.obtenerTodosLosExamenesPorEntidad(id);
        int cont = 0;
        for(Examen examen:examenes)
            if(examen.isAprobado())
                cont++;
        return cont;
    }
     
     public static int TotalExamenesSuspensos(Long id) throws SQLException, Exception {

        ObservableList<Examen> examenes = ServicioExamenes.obtenerTodosLosExamenesPorEntidad(id);
        int cont = 0;
        for(Examen examen:examenes)
            if(!(examen.isAprobado()))
                cont++;
        return cont;
    }
     
    public static int TotalExaminadores() throws Exception{
        return ServicioUsuario.obtenerUsuariosExamenesConduccion().size();
    } 
    
}
