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
public class ValidacionFecha implements IValidacion {

    @Override
    public void validar(Object Entrada, String nombreCampo) throws Exception {

        LocalDate fecha = (LocalDate) Entrada;
        if (fecha == null) {
            throw new Exception("El campo " + nombreCampo + " es obligatorio");
        }

        LocalDate hoy = LocalDate.now();
        if (fecha.isAfter(hoy)) {
            throw new Exception("El campo " + nombreCampo + " no puede ser posterior a la fecha actual (" + hoy + ")");
        }

        int mesActual = hoy.getMonthValue();
        int mesFecha = fecha.getMonthValue();
        if (!(mesFecha == mesActual || mesFecha == mesActual - 1)) {
            throw new Exception("El campo " + nombreCampo + " debe estar en el mes actual (" + mesActual
                    + ") o el mes anterior (" + (mesActual - 1) + ")");
        }
    }

}
