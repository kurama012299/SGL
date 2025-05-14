/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_interfaces.modelos;

/**
 *
 * @author Angel Hernandez
 */
public class Estadistica {
    private String Categoria;
    private double Valor;

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double Valor) {
        this.Valor = Valor;
    }

    public Estadistica(String Categoria, double Valor) {
        this.Categoria = Categoria;
        this.Valor = Valor;
    }
    
    
    @Override 
    public String toString()
    {
        return Categoria+": "+Valor;
    }
    
}
