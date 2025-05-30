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
    
    private ArrayList<String> restricciones= new ArrayList<>();
    
    public ExamenMedico(Long Id, Date Fecha, boolean Aprobado, EntidadRelacionada Entidad, Persona Persona, Usuario Examinador,ArrayList<String> Restricciones) {
        super(Id, Fecha, Aprobado, Entidad, Persona, Examinador,"Médico");
        
        this.restricciones=Restricciones;
    }
    

    public ExamenMedico( Date Fecha, boolean Aprobado, EntidadRelacionada Entidad, Persona Persona, Usuario Examinador,ArrayList<String> Restricciones) {
        super(Fecha, Aprobado, Entidad, Persona, Examinador,"Médico");
        
        this.restricciones=Restricciones;
    }
    
    public ArrayList<String> getRestricciones() {
        return restricciones;
    }
    
    
    
    @Override
    public String toString()
    {
         return "Id: "+ id +"\n"+
                "Fecha: "+ fecha +"\n"+
                "Aprobado: "+ aprobado +"\n"+
                "Tipo: "+tipo+"\n"+ 
                "Entidad: " + entidad +"\n"+
                "Persona: " + persona +"\n"+
                "Examinador: " + examinador+"\n"+
                "Restricciones" + restricciones ;
    }
    
}
