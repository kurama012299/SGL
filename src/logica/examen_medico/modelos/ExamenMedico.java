/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen_medico.modelos;

import java.util.ArrayList;
import java.util.Date;
import logica.examen.modelos.Examen;

/**
 *
 * @author Angel Hernandez
 */
public class ExamenMedico extends Examen{
    
    private ArrayList<String> Restricciones;
    
    public ExamenMedico(Long Id, Date Fecha, boolean Aprobado, Long IdEntidad, Long IdPersona, Long IdExaminador,ArrayList<String> Restricciones) {
        super(Id, Fecha, Aprobado, IdEntidad, IdPersona, IdExaminador);
        
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
                "IdEntidad: " + IdEntidad +"\n"+
                "IdPersona: " + IdPersona +"\n"+
                "IdExaminador: " + IdExaminador+"\n"+
                "Restricciones" + Restricciones ;
    }
    
}
