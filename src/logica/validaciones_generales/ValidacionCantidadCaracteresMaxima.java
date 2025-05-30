/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.validaciones_generales;

/**
 *
 * @author Angel Hernandez
 */
public class ValidacionCantidadCaracteresMaxima implements IValidacion{
    
    private int CantidadCaracteres;
    
    
    public ValidacionCantidadCaracteresMaxima(int CantidadCaracteres,String NombreCampo)
    {
        this.CantidadCaracteres=CantidadCaracteres;
    }
    
    
    @Override
    public void validar(Object Texto,String NombreCampo) throws Exception {
        if(Texto.toString().length()>CantidadCaracteres)
            throw new Exception("El tamaño máximo en "+ NombreCampo+ " es de "+CantidadCaracteres);
    }
}
