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

    private Long id;
    private Date fechaEmision;
    private Date fechaVencimiento;
    private boolean renovada;
    private int cantPuntos;
    private String tipo;
    private String estado;
    private ArrayList<String> restricciones;
    private ArrayList<String> categorias;

    public Licencia(Long Id, Date FechaEmision, Date FechaVencimiento, boolean Renovada, int CantPuntos, String Tipo, String Estado) {
        this.id = Id;
        this.fechaEmision = FechaEmision;
        this.fechaVencimiento = FechaVencimiento;
        this.renovada = Renovada;
        this.cantPuntos = CantPuntos;
        this.tipo = Tipo;
        this.estado = Estado;
    }
    
        public Licencia( Date FechaEmision, Date FechaVencimiento, boolean Renovada, int CantPuntos, String Tipo, String Estado) {
        this.fechaEmision = FechaEmision;
        this.fechaVencimiento = FechaVencimiento;
        this.renovada = Renovada;
        this.cantPuntos = CantPuntos;
        this.tipo = Tipo;
        this.estado = Estado;
    }
    
    public Long getId() {
        return id;
    }
     

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public boolean EstaRenovada() {
        return renovada;
    }

    public int getCantPuntos() {
        return cantPuntos;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public ArrayList<String> getRestricciones() {
        return restricciones;
    }

    public ArrayList<String> getCategorias() {
        return categorias;
    }

    public void AgregarRestriccion(String Restriccion) {
        restricciones.add(Restriccion);
    }

    public void agregarCategoria(String Categoria) {
        categorias.add(Categoria);
    }

    @Override
    public String toString() {
        return "Id: " + id + "\n"
                + "FechaEmision: " + fechaEmision + "\n"
                + "FechaVencimiento: " + fechaVencimiento + "\n"
                + "Renovada: " + renovada + "\n"
                + "CantPuntos: " + cantPuntos + "\n"
                + "Tipo: " + tipo + "\n"
                + "Estado: " + estado + "\n"
                + "Restricciones: " + restricciones + "\n"
                + "Categorias: " + categorias + "\n";
    }

    
    public void setId(Long Id) {
        this.id = Id;
    }

    
    public void setFechaEmision(Date FechaEmision) {
        this.fechaEmision = FechaEmision;
    }

    
    public void setFechaVencimiento(Date FechaVencimiento) {
        this.fechaVencimiento = FechaVencimiento;
    }

    
    public void settRenovada(boolean Renovada) {
        this.renovada = Renovada;
    }

 
    public void setCantPuntos(int CantPuntos) {
        this.cantPuntos = CantPuntos;
    }

    
    public void setTipo(String Tipo) {
        this.tipo = Tipo;
    }

    
    public void setEstado(String Estado) {
        this.estado = Estado;
    }

    
    public void setRestricciones(ArrayList<String> Restricciones) {
        this.restricciones = Restricciones;
    }

    
    public void setCategorias(ArrayList<String> Categorias) {
        this.categorias = Categorias;
    }
}
