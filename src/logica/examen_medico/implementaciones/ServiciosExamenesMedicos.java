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
    
    public static ObservableList<ExamenMedico> ObtenerConductores() throws Exception {
        return ConsultaExamenMedico.ObtenerExamenesMedicosRestriccionConsulta();
    }
}
