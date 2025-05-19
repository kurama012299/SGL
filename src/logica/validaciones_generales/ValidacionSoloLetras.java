/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.validaciones_generales;

/**
 *
 * @author Angel Hernandez
 */
public class ValidacionSoloLetras implements IValidacion{
    
    private final String NombreCampo;
    
    public ValidacionSoloLetras (String NombreCampo)
    {
        this.NombreCampo=NombreCampo;
    }

    @Override
    public void Validar(Object Entrada) throws Exception {
       String Texto = Entrada.toString();
         if (!Texto.matches("^[a-zA-Z\\s]+$")) 
            throw new Exception(NombreCampo + " solo puede contener letras (A-Z, a-z)");
    } 
}
