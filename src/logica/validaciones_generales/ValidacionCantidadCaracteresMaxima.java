/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sgl.logica.validaciones_generales;

/**
 *
 * @author Angel Hernandez
 */
public class ValidacionCantidadCaracteresMaxima implements IValidacion{
    
    private int CantidadCaracteres;
    private String NombreCampo;
    
    
    public ValidacionCantidadCaracteresMaxima(int CantidadCaracteres,String NombreCampo)
    {
        this.CantidadCaracteres=CantidadCaracteres;
        this.NombreCampo=NombreCampo;
    }
    
    
    @Override
    public void Validar(Object Texto) throws Exception {
        if(Texto.toString().length()>CantidadCaracteres)
            throw new Exception("El tamaño máximo en "+ NombreCampo+ " es de "+CantidadCaracteres);
    }
}
