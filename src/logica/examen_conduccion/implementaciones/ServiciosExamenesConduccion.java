/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen_conduccion.implementaciones;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.examen_conduccion.consultas.ConsultaExamen;
import logica.examen_conduccion.modelos.ExamenConduccion;

/**
 *
 * @author Kris
 */
public class ServiciosExamenesConduccion {
    
    public static ObservableList<ExamenConduccion> obtenerExamenesTeoricos() throws Exception {
        return ConsultaExamen.obtenerExamenesTeoricosConsultas();
    }
    
    public static ObservableList<ExamenConduccion> obtenerExamenesPracticos() throws Exception {
        return ConsultaExamen.obtenerExamenesPracticosConsultas();
    }
    
    public static ExamenConduccion obtenerExamenesTeoricosPorID(Long Id) throws Exception {
        return ConsultaExamen.obtenerExamenesTeoricosPorIdConsultas(Id);
    }
    
    public static ExamenConduccion obtenerExamenesPracticosPorID(Long Id) throws Exception {
        return ConsultaExamen.obtenerExamenesPracticosPorIdConsultas(Id);
    }
    
    public static ObservableList<ExamenConduccion> obtenerExamenesTeoricosPorIDRol(Long Id) throws Exception {
        return ConsultaExamen.obtenerExamenesTeoricosPorIdRolConsultas(Id);
    }
    
    public static ObservableList<ExamenConduccion> obtenerExamenesTeoricosPorIDRolAprobados(Long Id,String aprobado) throws Exception {
       ObservableList<ExamenConduccion>examenesPorResultado=FXCollections.observableArrayList();
       ObservableList<ExamenConduccion>examenes=ConsultaExamen.obtenerExamenesTeoricosPorIdRolConsultas(Id);
        for(ExamenConduccion exa: examenes)
        {
            if(aprobado.equalsIgnoreCase("aprobado"))
            {
                if(exa.isAprobado())
                    examenesPorResultado.add(exa);
            }
            else
            {
                if(!exa.isAprobado())
                    examenesPorResultado.add(exa);
            }
        }
        return examenesPorResultado;
    }
    
    public static ObservableList<ExamenConduccion> obtenerExamenesPracticosPorIDRolAprobados(Long Id,String aprobado) throws Exception {
       ObservableList<ExamenConduccion>examenesPorResultado=FXCollections.observableArrayList();
       ObservableList<ExamenConduccion>examenes=ConsultaExamen.obtenerExamenesPracticosPorIdRolConsultas(Id);
        for(ExamenConduccion exa: examenes)
        {
            if(aprobado.equalsIgnoreCase("aprobado"))
            {
                if(exa.isAprobado())
                    examenesPorResultado.add(exa);
            }
            else
            {
                if(!exa.isAprobado())
                    examenesPorResultado.add(exa);
            }
        }
        return examenesPorResultado;
    }
    
    public static ObservableList<ExamenConduccion> obtenerExamenesPracticosPorIDRol(Long Id) throws Exception {
        return ConsultaExamen.obtenerExamenesPracticosPorIdRolConsultas(Id);
    }
    
    public static void crearExamenTeorico(ExamenConduccion examen) throws Exception
    {
        ConsultaExamen.crearExamenTeorico(examen);
    }
    
     public static void crearExamenPractico(ExamenConduccion examen) throws Exception
    {
        ConsultaExamen.crearExamenPractico(examen);
    }
     
     
    public static ArrayList<ExamenConduccion> obtenerExamenesTeoricosPorCI(String ci) throws Exception {
        ArrayList<ExamenConduccion> examenes = new ArrayList<>();
        for(ExamenConduccion e : obtenerExamenesTeoricos())
        {
            if(e.getPersona().getCI().equals(ci))
            {
                examenes.add(e);
            }
        }
            return examenes;
    }
}
