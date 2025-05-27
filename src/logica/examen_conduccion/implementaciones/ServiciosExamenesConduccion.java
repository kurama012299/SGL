/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen_conduccion.implementaciones;

import javafx.collections.ObservableList;
import logica.examen_conduccion.consultas.ConsultaExamen;
import logica.examen_conduccion.modelos.ExamenConduccion;

/**
 *
 * @author Kris
 */
public class ServiciosExamenesConduccion {
    
    public static ObservableList<ExamenConduccion> ObtenerExamenesTeoricos() throws Exception {
        return ConsultaExamen.ObtenerExamenesTeoricosConsultas();
    }
    
    public static ObservableList<ExamenConduccion> ObtenerExamenesPracticos() throws Exception {
        return ConsultaExamen.ObtenerExamenesPracticosConsultas();
    }
    
    public static ExamenConduccion ObtenerExamenesTeoricosPorID(Long Id) throws Exception {
        return ConsultaExamen.ObtenerExamenesTeoricosPorIdConsultas(Id);
    }
    
    public static ExamenConduccion ObtenerExamenesPracticosPorID(Long Id) throws Exception {
        return ConsultaExamen.ObtenerExamenesPracticosPorIdConsultas(Id);
    }
    
    public static ObservableList<ExamenConduccion> ObtenerExamenesTeoricosPorIDRol(Long Id) throws Exception {
        return ConsultaExamen.ObtenerExamenesTeoricosPorIdRolConsultas(Id);
    }
    
    public static ObservableList<ExamenConduccion> ObtenerExamenesPracticosPorIDRol(Long Id) throws Exception {
        return ConsultaExamen.ObtenerExamenesPracticosPorIdRolConsultas(Id);
    }
    
    public static void crearExamenTeorico(ExamenConduccion examen) throws Exception
    {
        ConsultaExamen.CrearExamenTeorico(examen);
    }
    
}
