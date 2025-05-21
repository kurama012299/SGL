/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.validaciones_generales;

import java.time.LocalDate;

/**
 *
 * @author Angel Hernandez
 */
public class ValidacionFecha implements IValidacion{

    @Override
    public void Validar(Object Entrada, String nombreCampo) throws Exception {

        LocalDate fecha = (LocalDate) Entrada;
        if(fecha == null)
            throw new Exception("El campo "+nombreCampo+ " es obligatorio");
        
        
        if(fecha.isAfter(LocalDate.now()) ||  LocalDate.now().getMonth().getValue()-1 <=  fecha.getMonth().getValue())
            throw new Exception("Revise el campo "+nombreCampo+ " es incorrecto");
    }
    
}
