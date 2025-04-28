/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sgl.logica.validaciones_generales;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Angel Hernandez
 */
public class ValidacionCompuesta implements IValidacion{
    
    private final List<IValidacion> Validaciones; 


    public ValidacionCompuesta(IValidacion... validations) {
        this.Validaciones = Arrays.asList(validations);
    }

    @Override
    public void Validar(Object Texto) throws Exception {
        
        int i=0;
        while(i<Validaciones.size())
        {
            Validaciones.get(i).Validar(Texto.toString());
            i++;
        }
    }
}
