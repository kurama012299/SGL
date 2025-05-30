/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.validaciones_generales;

/**
 *
 * @author Angel Hernandez
 */
public class ValidacionCantidadCaracteresMinima implements IValidacion{

    private int CantidadCaracteres;

    
    
    public ValidacionCantidadCaracteresMinima(int CantidadCaracteres)
    {
        this.CantidadCaracteres=CantidadCaracteres;
    }
    
    
    @Override
    public void validar(Object Texto,String NombreCampo) throws Exception {
        if(Texto.toString().length()<CantidadCaracteres)
            throw new Exception("El tamaño mínimo en "+ NombreCampo+ " es de "+CantidadCaracteres);
    }
    
}
