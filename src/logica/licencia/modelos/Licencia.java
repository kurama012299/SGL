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

    public Licencia(Long Id, Date FechaEmision, Date FechaVencimiento, boolean Renovada, int CantPuntos, String Tipo, String Estado) {
        this.Id = Id;
        this.FechaEmision = FechaEmision;
        this.FechaVencimiento = FechaVencimiento;
        this.Renovada = Renovada;
        this.CantPuntos = CantPuntos;
        this.Tipo = Tipo;
        this.Estado = Estado;
    }
    
    public Long GetId() {
        return Id;
    }
     

    public Date GetFechaEmision() {
        return FechaEmision;
    }

    public Date GetFechaVencimiento() {
        return FechaVencimiento;
    }

    public boolean EstaRenovada() {
        return Renovada;
    }

    public int GetCantPuntos() {
        return CantPuntos;
    }

    public String GetTipo() {
        return Tipo;
    }

    public String GetEstado() {
        return Estado;
    }

    public ArrayList<String> GetRestricciones() {
        return Restricciones;
    }

    public ArrayList<String> GetCategorias() {
        return Categorias;
    }

    public void AgregarRestriccion(String Restriccion) {
        Restricciones.add(Restriccion);
    }

    public void agregarCategoria(String Categoria) {
        Categorias.add(Categoria);
    }

    @Override
    public String toString() {
        return "Id: " + Id + "\n"
                + "FechaEmision: " + FechaEmision + "\n"
                + "FechaVencimiento: " + FechaVencimiento + "\n"
                + "Renovada: " + Renovada + "\n"
                + "CantPuntos: " + CantPuntos + "\n"
                + "Tipo: " + Tipo + "\n"
                + "Estado: " + Estado + "\n"
                + "Restricciones: " + Restricciones + "\n"
                + "Categorias: " + Categorias + "\n";
    }

    
    public void SetId(Long Id) {
        this.Id = Id;
    }

    
    public void SetFechaEmision(Date FechaEmision) {
        this.FechaEmision = FechaEmision;
    }

    
    public void SetFechaVencimiento(Date FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }

    
    public void SetRenovada(boolean Renovada) {
        this.Renovada = Renovada;
    }

 
    public void SetCantPuntos(int CantPuntos) {
        this.CantPuntos = CantPuntos;
    }

    
    public void SetTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    
    public void SetEstado(String Estado) {
        this.Estado = Estado;
    }

    
    public void SetRestricciones(ArrayList<String> Restricciones) {
        this.Restricciones = Restricciones;
    }

    
    public void SetCategorias(ArrayList<String> Categorias) {
        this.Categorias = Categorias;
    }
}
