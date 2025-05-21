/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.validaciones_generales;

/**
 *
 * @author Angel Hernandez
 */
public class ValidacionCantidadCaracteresExacta implements IValidacion{
    
    private int CantidadCaracteres;    
    
    public ValidacionCantidadCaracteresExacta(int CantidadCaracteres)
    {
        this.CantidadCaracteres=CantidadCaracteres;
    }
    
    
    @Override
    public void Validar(Object Texto,String NombreCampo) throws Exception {
        System.out.println(Texto.toString().length()+" "+ Texto.toString());
        if(Texto.toString().length()!=CantidadCaracteres)
            throw new Exception("El tamaño de el "+ NombreCampo+ " debe ser "+CantidadCaracteres);
    }
}
