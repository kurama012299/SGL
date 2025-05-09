/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen.implementaciones;

import javafx.collections.ObservableList;
import logica.examen.consultas.ConsultaExamen;
import logica.examen.modelos.Examen;

/**
 *
 * @author Kris
 */
public class ServiciosExamenes {
    
    public static ObservableList<Examen> ObtenerExamenesTeoricos() throws Exception {
        return ConsultaExamen.ObtenerExamenesTeoricosConsultas();
    }
    
    public static ObservableList<Examen> ObtenerExamenesPracticos() throws Exception {
        return ConsultaExamen.ObtenerExamenesPracticosConsultas();
    }
}
