/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.infraccion.implementaciones;
import javafx.collections.ObservableList;
import logica.infraccion.consultas.ConsultasInfraccion;
import logica.infraccion.modelos.Infraccion;

/**
 *
 * @author Adrian
 */
public class ServicioInfraccion {
    
    public static ObservableList<Infraccion> ObtenerInfracciones() throws Exception {
        return ConsultasInfraccion.ObtenerInfraccionesConsulta();
    }
    
    public static Infraccion ObtenerInfraccionPorId(long Id) throws Exception
    {
        return ConsultasInfraccion.ObtenerInfraccionPorIdConsulta(Id);
    }
    
    public static int ObtenerCantidadInfraccionesPorId(long Id) throws Exception
    {
        return ConsultasInfraccion.ObtenerCantidadInfraccionesPorIdConsulta(Id);
    }
}
