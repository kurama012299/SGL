/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.validaciones_generales;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Angel Hernandez
 */
public class ValidacionCarnet implements IValidacion{

    @Override
    public void validar(Object Entrada, String nombreCampo) throws Exception {
        
        // Extraer los primeros 6 dígitos (AAMMDD)
        String fechaStr = Entrada.toString().substring(0, 6);

        // Convertir a formato LocalDate (asumiendo siglo 20 para años < 100)
        LocalDate fechaNacimiento;
        try {
            int año = Integer.parseInt(fechaStr.substring(0, 2));
            int mes = Integer.parseInt(fechaStr.substring(2, 4));
            int dia = Integer.parseInt(fechaStr.substring(4, 6));

            // Asumir que años < 100 son del siglo XX (ej: 99 -> 1999, 05 -> 2005)
            if (año <= LocalDate.now().getYear() % 100) {
                año += 2000;
            } else {
                año += 1900;
            }

            fechaNacimiento = LocalDate.of(año, mes, dia);
        } catch (Exception e) {
            throw new Exception("El campo " + nombreCampo + " tiene una fecha inválida en los primeros 6 dígitos");
        }

        // Verificar que la fecha no sea futura
        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new Exception("El campo " + nombreCampo + " contiene una fecha futura inválida");
        }

        // Verificar mayoría de edad (18+ años)
        Period edad = Period.between(fechaNacimiento, LocalDate.now());
        if (edad.getYears() < 18) {
            throw new Exception("El campo " + nombreCampo + " no cumple con la mayoría de edad (18+ años)");
        }
    }

}
