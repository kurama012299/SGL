/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen.modelos;

import java.util.Date;
import logica.entidad.modelos.EntidadRelacionada;
import logica.persona.modelos.Persona;
import logica.usuario.modelos.Usuario;

/**
 *
 * @author Kris
 */
public class Examen {
    
    protected Long id;
    protected Date fecha;
    protected boolean aprobado;
    protected EntidadRelacionada entidad;
    protected Persona persona;
    protected Usuario examinador;
    protected String tipo;

    public Examen(Long Id, Date Fecha, boolean Aprobado, EntidadRelacionada Entidad, Persona Persona, Usuario Examinador,String Tipo) {
        this.id = Id;
        this.fecha = Fecha;
        this.aprobado = Aprobado;
        this.tipo = Tipo;
        this.entidad = Entidad;
        this.examinador=Examinador;
        this.persona=Persona;
        
    }
    
    public Examen( Date Fecha, boolean Aprobado, EntidadRelacionada Entidad, Persona Persona, Usuario Examinador,String Tipo) {
        this.fecha = Fecha;
        this.aprobado = Aprobado;
        this.tipo = Tipo;
        this.entidad = Entidad;
        this.examinador=Examinador;
        this.persona=Persona;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = Id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date Fecha) {
        this.fecha = Fecha;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean Aprobado) {
        this.aprobado = Aprobado;
    }

    public EntidadRelacionada getEntidad() {
        return entidad;
    }

    public void setEntidad(EntidadRelacionada Entidad) {
        this.entidad = Entidad;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona Persona) {
        this.persona = Persona;
    }

    public Usuario getExaminador() {
        return examinador;
    }

    public void setExaminador(Usuario Examinador) {
        this.examinador = Examinador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String Tipo) {
        this.tipo = Tipo;
    }
    
    @Override
    public String toString()
    {
         return "Id: "+ id +"\n"+
                "Fecha: "+ fecha +"\n"+
                "Aprobado: "+ aprobado +"\n"+
                "Tipo"+tipo+"\n"+
                "Entidad: " + entidad.getNombre() +"\n"+
                "Persona: " + persona.getNombre() +"\n"+
                "Examinador: " + examinador.getNombre()+"\n";
    }
}
