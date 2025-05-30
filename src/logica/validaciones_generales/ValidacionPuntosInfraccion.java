/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.validaciones_generales;

/**
 *
 * @author Adrian
 */
public class ValidacionPuntosInfraccion implements IValidacion {

    @Override
    public void validar(Object Entrada, String nombreCampo) throws Exception {

        String puntos = Entrada.toString();
        int puntosInt = Integer.parseInt(puntos.trim());

        if (puntosInt < 1 || puntosInt > 24) {
            throw new Exception("El campo " + nombreCampo + " contiene una cantidad de puntos invalidos");
        }
    }
}
