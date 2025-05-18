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

    
    public String GetCategoria() {
        return Categoria;
    }

    
    public void SetCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    
    public double GetValor() {
        return Valor;
    }

    
    public void SetValor(double Valor) {
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
