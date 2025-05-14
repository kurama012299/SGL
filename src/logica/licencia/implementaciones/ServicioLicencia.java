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
    
    public static ObservableList<Licencia> ObtenerLicencias() throws Exception {
        return ConsultasLicencia.ObtenerLicenciasConsulta();
    }
    
    public static Licencia ObtenerLicenciaPorId(long Id) throws Exception
    {
        return ConsultasLicencia.ObtenerLicenciaPorIdConsulta(Id);
    }
      
}
