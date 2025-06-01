/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen_medico.implementaciones;


import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import logica.examen_medico.consultas.ConsultaExamenMedico;
import logica.examen_medico.modelos.ExamenMedico;

/**
 *
 * @author Kris
 */
public class ServiciosExamenesMedicos {
    
    public static ObservableList<ExamenMedico> ObtenerExamenesMedico() throws Exception {
        return ConsultaExamenMedico.ObtenerExamenesMedicosRestriccionConsulta();
    }
    
    public static ObservableList<ExamenMedico> ObtenerExamenesMedicoPorIdExaminador(Long Id) throws Exception {
        return ConsultaExamenMedico.ObtenerExamenesMedicosRestriccionPorIdExaminadorConsulta(Id);
    }
    
    public static ExamenMedico ObtenerExamenesMedicoPorId(Long Id) throws Exception {
        return ConsultaExamenMedico.ObtenerExamenesMedicosRestriccionPorIdConsulta(Id);
    }
    
    
    public static ArrayList<ExamenMedico> ObtenerExamenesMedicoPorCI(String ci) throws Exception {
        ArrayList<ExamenMedico> examenes = new ArrayList<>();
        for(ExamenMedico e : ObtenerExamenesMedico())
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
