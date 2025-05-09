/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.centro.implementaciones;

import logica.centro.consultas.ConsultaCentro;
import logica.centro.modelos.Centro;

/**
 *
 * @author Kris
 */
public class ServiciosCentro {
    public static Centro ObtenerCentro() throws Exception {
        return ConsultaCentro.ObtenerCentroConsulta();
    }
}
