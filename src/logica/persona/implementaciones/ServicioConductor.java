/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.persona.implementaciones;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.infraccion.consultas.ConsultasInfraccion;
import logica.infraccion.modelos.Infraccion;
import logica.licencia.implementaciones.ServicioLicencia;
import logica.licencia.modelos.Licencia;
import logica.persona.consultas.ConsultasPersona;
import logica.persona.modelos.Conductor;
import logica.persona.modelos.Persona;

/**
 *
 * @author Angel Hernandez
 */
public class ServicioConductor{

    
    public static ObservableList<Conductor> ObtenerConductores() throws Exception {
        return ConsultasPersona.obtenerConductoresConsulta();
    }
    
    public static Conductor ObtenerConductorPorId(long Id) throws Exception
    {
        return ConsultasPersona.obtenerConductorPorIdConsulta(Id);
    }
    
     public static Conductor ObtenerConductorPorIdLicencia(long IdLicencia) throws Exception
    {
        return ConsultasPersona.obtenerConductorPorIdLicenciaConsulta(IdLicencia);
    }
     
      public static ObservableList<Conductor> obtenerConductoresConLicenciaVencida(LocalDate fechaInicio, LocalDate fechaFin) throws Exception{
        return ConsultasPersona.obtenerConductoresConLicenciaVencidaPeriodo(fechaInicio, fechaFin);
    }
      
      public static ObservableList<Conductor> obtenerConductoresPorEstado(String estado) throws Exception
    {
        ObservableList<Conductor>conductoresPorEstado= FXCollections.observableArrayList();
        ObservableList<Licencia>licencias= ServicioLicencia.obtenerLicencias();
        int i=0;
        for (Conductor con : ConsultasPersona.obtenerConductoresConsulta()) {
            if (licencias.get(i++).getEstado().equalsIgnoreCase(estado)) {
                conductoresPorEstado.add(ServicioConductor.ObtenerConductorPorIdLicencia(licencias.get(i-1).getId()));
            }
        }
        return conductoresPorEstado;
    }
      
    public static void eliminarConductor(long id) throws Exception{
        ConsultasPersona.eliminarPersonaTotalmente(id);
    }

}
