/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen.modelos;

import java.util.Date;

/**
 *
 * @author Angel Hernandez
 */
public class Examen {
    protected Long Id;
    protected Date Fecha;
    protected boolean Aprobado;
    protected Long IdEntidad;
    protected Long IdPersona;
    protected Long IdExaminador;

    public Examen(Long Id, Date Fecha, boolean Aprobado, Long IdEntidad, Long IdPersona, Long IdExaminador) {
        this.Id = Id;
        this.Fecha = Fecha;
        this.Aprobado = Aprobado;
        this.IdEntidad = IdEntidad;
        this.IdPersona = IdPersona;
        this.IdExaminador = IdExaminador;
    }

    public Long getId() {
        return Id;
    }

    public Date getFecha() {
        return Fecha;
    }

    public boolean isAprobado() {
        return Aprobado;
    }

    public Long getIdEntidad() {
        return IdEntidad;
    }

    public Long getIdPersona() {
        return IdPersona;
    }

    public Long getIdExaminador() {
        return IdExaminador;
    }
    
    @Override
    public String toString()
    {
         return "Id: "+ Id +"\n"+
                "Fecha: "+ Fecha +"\n"+
                "Aprobado: "+ Aprobado +"\n"+
                "IdEntidad: " + IdEntidad +"\n"+
                "IdPersona: " + IdPersona +"\n"+
                "IdExaminador: " + IdExaminador+"\n";
    }
}
