/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen_medico.validaciones;

import javafx.collections.ObservableList;
import logica.persona.implementaciones.ServicioPersona;
import logica.persona.modelos.Persona;
import logica.usuario.modelos.Usuario;

/**
 *
 * @author Angel Hernandez
 */
public class ValidacionCrearExamenMedico {
    
    public static boolean validarExamenMedico(String nombre,String ci,Usuario u) throws Exception
    {
        boolean existePersona=false;
        ObservableList<Persona> personas = ServicioPersona.obtenerPersonas();
        
        for (Persona p : personas) {
            if (p.getCI().equals(ci)) {
                existePersona = true;
                String nombreApellidos = p.getNombre() + " " + p.getApellidos();
                if (!nombreApellidos.equalsIgnoreCase(nombre)) {
                    throw new Exception("El carnet no se corresponde al nombre");
                }
            }
        }
        
        if(u.getRol().equals("Administrador autoescuela") || u.getRol().equals("Trabajador centro") || u.getRol().equals("Trabajador autoescuela"))
            throw new Exception("Ese usuario no puede crear examenes medicos");
        return existePersona;
    }
}
