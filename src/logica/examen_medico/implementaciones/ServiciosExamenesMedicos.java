/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen_medico.implementaciones;


import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.examen_medico.consultas.ConsultaExamenMedico;
import logica.examen_medico.modelos.ExamenMedico;

/**
 *
 * @author Kris
 */
public class ServiciosExamenesMedicos {
    
    public static ObservableList<ExamenMedico> obtenerExamenesMedico() throws Exception {
        return ConsultaExamenMedico.obtenerExamenesMedicosRestriccionConsulta();
    }
    
    public static ObservableList<ExamenMedico> obtenerExamenesMedicoPorIdExaminador(Long Id,String resultado) throws Exception {
        ObservableList<ExamenMedico>examenesPorResultado=FXCollections.observableArrayList();
        ObservableList<ExamenMedico>examenesMedicos=ConsultaExamenMedico.obtenerExamenesMedicosRestriccionPorIdExaminadorConsulta(Id);
        for(ExamenMedico exa: examenesMedicos)
        {
           if(resultado.equals("Aprobado"))
           {
               if(exa.isAprobado()==true && exa.getRestricciones().size()==0)
                   examenesPorResultado.add(exa);
           }
           else if(resultado.equals("Reprobado"))
           {
               if (exa.isAprobado()==false) 
                  examenesPorResultado.add(exa);
           }
           else if(resultado.equals("Aprobado condicional"))
           {
               if(exa.isAprobado()==true && exa.getRestricciones().size()!=0)
               {
                   examenesPorResultado.add(exa);
               }
           }
           else 
           {
               examenesPorResultado.add(exa);
           }
        }
        return examenesPorResultado;
        
    }
    
    public static ExamenMedico obtenerExamenesMedicoPorId(Long Id) throws Exception {
        return ConsultaExamenMedico.obtenerExamenesMedicosRestriccionPorIdConsulta(Id);
    }
    
    
    public static ArrayList<ExamenMedico> obtenerExamenesMedicoPorCI(String ci) throws Exception {
        ArrayList<ExamenMedico> examenes = new ArrayList<>();
        for(ExamenMedico e : obtenerExamenesMedico())
        {
            if(e.getPersona().getCI().equals(ci))
            {
                examenes.add(e);
            }
        }
            return examenes;
    }
    
    public static void crearExamenMedico(ExamenMedico examenMedico) throws Exception
    {
        ConsultaExamenMedico.crearExamenMedicoConsulta(examenMedico);
    }
    
    public static LocalDate obtenerFechaMasViejaExamen() throws Exception
    {
        return ConsultaExamenMedico.obtenerFechaMasViejaExamen();
    }
}
