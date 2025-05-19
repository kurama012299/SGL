/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.validaciones_generales;

/**
 *
 * @author Angel Hernandez
 */
public class ValidacionSoloNumeros implements IValidacion{
       
    private final String NombreCampo;
    
    public ValidacionSoloNumeros(String NombreCampo)
    {
        this.NombreCampo=NombreCampo;
    }

    @Override
    public void Validar(Object Entrada) throws Exception {
       String Texto = Entrada.toString();
         if (!Texto.matches("^[0-9]+$")) 
            throw new Exception(NombreCampo + " solo puede contener letras (A-Z, a-z)");
    } 
}
