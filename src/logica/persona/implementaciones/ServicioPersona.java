/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.persona.implementaciones;

import javafx.collections.ObservableList;
import logica.persona.consultas.ConsultasPersona;
import logica.persona.modelos.Persona;

/**
 *
 * @author Angel Hernandez
 */
public class ServicioPersona {
    
    public static ObservableList<Persona> obtenerPersonas() throws Exception
    {
        return ConsultasPersona.ObtenerPersonasConsulta();
    }
    
    public static Persona obtenerPersonaPorCi(String ci) throws Exception
    {
        return ConsultasPersona.ObtenerPersonaPorCIConsulta(ci);
    }
    
    public static long crearPersona(Persona persona) throws Exception
    {
        return ConsultasPersona.crearPersonaConsulta(persona);
    }
}
