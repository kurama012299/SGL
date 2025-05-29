/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen_conduccion.implementaciones;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import logica.examen_conduccion.consultas.ConsultaExamen;
import logica.examen_conduccion.modelos.ExamenConduccion;
import static logica.examen_medico.implementaciones.ServiciosExamenesMedicos.ObtenerExamenesMedico;
import logica.examen_medico.modelos.ExamenMedico;

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
        ConsultaExamen.crearExamenTeorico(examen);
    }
    
     public static void crearExamenPractico(ExamenConduccion examen) throws Exception
    {
        ConsultaExamen.crearExamenPractico(examen);
    }
     
     
    public static ArrayList<ExamenConduccion> ObtenerExamenesTeoricosPorCI(String ci) throws Exception {
        ArrayList<ExamenConduccion> examenes = new ArrayList<>();
        for(ExamenConduccion e : ObtenerExamenesTeoricos())
        {
            if(e.getPersona().getCI().equals(ci))
            {
                examenes.add(e);
            }
        }
            return examenes;
    }
}
