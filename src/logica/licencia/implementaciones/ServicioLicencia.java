/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.licencia.implementaciones;

import javafx.collections.ObservableList;
import logica.licencia.consultas.ConsultasLicencia;
import logica.licencia.modelos.Licencia;


/**
 *
 * @author Adrian
 */
public class ServicioLicencia {
    
    public static ObservableList<Licencia> ObtenerConductores() throws Exception {
        return ConsultasLicencia.ObtenerLicenciasConsulta();
    }
    
    public static Licencia ObtenerConductorPorId(long Id) throws Exception
    {
        return ConsultasLicencia.ObtenerLicenciaPorIdConsulta(Id);
    }
    
}
