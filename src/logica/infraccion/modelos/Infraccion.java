/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.infraccion.modelos;

import java.util.Date;
import logica.persona.modelos.Persona;

/**
 *
 * @author Angel Hernandez
 */
public class Infraccion {
    private Long Id;
    private Date Fecha;
    private String Lugar;
    private String Descripcion;
    private int PuntosDeducidos;
    private boolean Pagada;
    private Long IdLicencia;
    private String Gravedad;
    private String NombreOficial;
    private Infraccion NombreGravedad;
    private Persona NombrePersona;

    public Infraccion(Long Id, Date Fecha, String Lugar, String Descripcion, int PuntosDeducidos, boolean Pagada, Long IdLicencia, String Gravedad, String NombreOficial) {
        this.Id = Id;
        this.Fecha = Fecha;
        this.Lugar = Lugar;
        this.Descripcion = Descripcion;
        this.PuntosDeducidos = PuntosDeducidos;
        this.Pagada = Pagada;
        this.IdLicencia = IdLicencia;
        this.Gravedad = Gravedad;
        this.NombreOficial = NombreOficial;
    }
    
    public Infraccion(Long Id, Date Fecha, String Lugar, String Descripcion, int PuntosDeducidos, boolean Pagada, Long IdLicencia, String NombreOficial, Infraccion NombreGravedad, Persona NombrePersona) {
        this.Id = Id;
        this.Fecha = Fecha;
        this.Lugar = Lugar;
        this.Descripcion = Descripcion;
        this.PuntosDeducidos = PuntosDeducidos;
        this.Pagada = Pagada;
        this.IdLicencia = IdLicencia;
        this.NombreOficial = NombreOficial;
        this.NombreGravedad = NombreGravedad;
        this.NombrePersona = NombrePersona;
        
    }
    
    public Infraccion(String Gravedad){
        this.Gravedad = Gravedad;
    }

    public Long getId() {
        return Id;
    }

    public Date getFecha() {
        return Fecha;
    }

    public String getLugar() {
        return Lugar;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public int getPuntosDeducidos() {
        return PuntosDeducidos;
    }

    public boolean isPagada() {
        return Pagada;
    }

    public Long getIdLicencia() {
        return IdLicencia;
    }

    public String getGravedad() {
        return Gravedad;
    }

    public String getNombreOficial() {
        return NombreOficial;
    }
    
    public Persona getNombrePersona(){
            return NombrePersona;
}
    
    public Infraccion getNombreGravedad(){
        return NombreGravedad;
    }
    
    @Override
    public String toString()
    {
        return "Id: "+ Id +"\n"+
                "Fecha: "+ Fecha +"\n"+
                "Lugar: "+ Lugar +"\n"+
                "Descripcion: " + Descripcion +"\n"+
                "PuntosDeducidos: " + PuntosDeducidos +"\n"+
                "Pagada: "+ Pagada +"\n"+
                "IdLicencia: " + IdLicencia +"\n"+
                "Gravedad: " + Gravedad +"\n"+
                "NombreOficial: " + NombreOficial+"\n";
    }
    
}
