/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen_medico.implementaciones;

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
    
    public static ObservableList<ExamenMedico> ObtenerExamenesMedicoPorIdRol(Long Id) throws Exception {
        return ConsultaExamenMedico.ObtenerExamenesMedicosRestriccionPorIdRolConsulta(Id);
    }
    
    public static ExamenMedico ObtenerExamenesMedicoPorId(Long Id) throws Exception {
        return ConsultaExamenMedico.ObtenerExamenesMedicosRestriccionPorIdConsulta(Id);
    }
    
    public static void crearExamenMedico(ExamenMedico examenMedico) throws Exception
    {
        ConsultaExamenMedico.crearExamenMedicoConsulta(examenMedico);
    }
}
