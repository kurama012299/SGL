/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.restricciones.implementacion;

import java.util.ArrayList;
import logica.restricciones.consulta.ConsultaRestriccion;

/**
 *
 * @author Angel Hernandez
 */
public class ServicioRestriccion {
    
    public static ArrayList<String> obtenerRestricciones() throws Exception
    {
        return ConsultaRestriccion.obtenerNombresRestricciones();
    }
}
