/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen_medico.modelos;

import java.util.ArrayList;
import java.util.Date;
import logica.entidad.modelos.EntidadRelacionada;
import logica.examen.modelos.Examen;
import logica.persona.modelos.Persona;
import logica.usuario.modelos.Usuario;

/**
 *
 * @author Angel Hernandez
 */
public class ExamenMedico extends Examen{
    
    private ArrayList<String> Restricciones= new ArrayList<>();
    
    public ExamenMedico(Long Id, Date Fecha, boolean Aprobado, EntidadRelacionada Entidad, Persona Persona, Usuario Examinador,ArrayList<String> Restricciones) {
        super(Id, Fecha, Aprobado, Entidad, Persona, Examinador,"Médico");
        
        this.Restricciones=Restricciones;
    }
    

    public ExamenMedico( Date Fecha, boolean Aprobado, EntidadRelacionada Entidad, Persona Persona, Usuario Examinador,ArrayList<String> Restricciones) {
        super(Fecha, Aprobado, Entidad, Persona, Examinador,"Médico");
        
        this.Restricciones=Restricciones;
    }
    
    public ArrayList<String> getRestricciones() {
        return Restricciones;
    }
    
    
    
    @Override
    public String toString()
    {
         return "Id: "+ Id +"\n"+
                "Fecha: "+ Fecha +"\n"+
                "Aprobado: "+ Aprobado +"\n"+
                "Tipo: "+Tipo+"\n"+ 
                "Entidad: " + Entidad +"\n"+
                "Persona: " + Persona +"\n"+
                "Examinador: " + Examinador+"\n"+
                "Restricciones" + Restricciones ;
    }
    
}
