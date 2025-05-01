/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.licencia.modelos;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Angel Hernandez
 */
public class Licencia {
    private Long Id;
    private Date FechaEmision;
    private Date FechaVencimiento;
    private boolean Renovada;
    private int CantPuntos;
    private String Tipo;
    private String Estado;
    private ArrayList<String> Restricciones;
    private ArrayList<String> Categorias;

    public Licencia(Long Id, Date FechaEmision, Date FechaVencimiento, boolean Renovada, int CantPuntos, String Tipo, String Estado, ArrayList<String> Restricciones, ArrayList<String> Categorias) {
        this.Id = Id;
        this.FechaEmision = FechaEmision;
        this.FechaVencimiento = FechaVencimiento;
        this.Renovada = Renovada;
        this.CantPuntos = CantPuntos;
        this.Tipo = Tipo;
        this.Estado = Estado;
        this.Restricciones = Restricciones;
        this.Categorias = Categorias;
    }

    public Long getId() {
        return Id;
    }

    public Date getFechaEmision() {
        return FechaEmision;
    }

    public Date getFechaVencimiento() {
        return FechaVencimiento;
    }

    public boolean isRenovada() {
        return Renovada;
    }

    public int getCantPuntos() {
        return CantPuntos;
    }

    public String getTipo() {
        return Tipo;
    }

    public String getEstado() {
        return Estado;
    }

    public ArrayList<String> getRestricciones() {
        return Restricciones;
    }

    public ArrayList<String> getCategorias() {
        return Categorias;
    }
    
    
     @Override
    public String toString()
    {
         return "Id: "+ Id +"\n"+
                "FechaEmision: "+ FechaEmision +"\n"+
                "FechaVencimiento: "+ FechaVencimiento +"\n"+
                "Renovada: " + Renovada +"\n"+
                "CantPuntos: " + CantPuntos +"\n"+
                "Tipo: "+ Tipo +"\n"+
                "Estado: " + Estado +"\n"+
                "Restricciones: " + Restricciones +"\n"+
                "Categorias: " + Categorias+"\n";
    }
}
