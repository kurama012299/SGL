/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.validaciones_generales;

/**
 *
 * @author Angel Hernandez
 */
public class ValidacionCampoVacio implements IValidacion{
    

  
    
    @Override
    public void Validar(Object Entrada,String NombreCampo) throws Exception {
        System.out.println(Entrada);
        if(Entrada==null)
            throw new Exception("El "+ NombreCampo+" es obligatorio");
        
        
        if(Entrada.toString().trim().isEmpty())
            throw new Exception("El "+ NombreCampo+" no puede estar vacio");
    }
    
    
}
