/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen_conduccion.modelos;

import java.util.Date;
import logica.entidad.modelos.EntidadRelacionada;
import logica.examen.modelos.Examen;
import logica.persona.modelos.Persona;
import logica.usuario.modelos.Usuario;

/**
 *
 * @author Angel Hernandez
 */
public class ExamenConduccion extends Examen {
    
    public ExamenConduccion(Long Id, Date Fecha, boolean Aprobado, EntidadRelacionada Entidad, Persona Persona, Usuario Examinador, String Tipo) {
        
        super(Id, Fecha, Aprobado, Entidad, Persona, Examinador, Tipo);
    }
    

    
}
