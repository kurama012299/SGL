/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.persona.implementaciones;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.licencia.implementaciones.ServicioLicencia;
import logica.licencia.modelos.Licencia;
import logica.persona.consultas.ConsultasPersona;
import logica.persona.modelos.Conductor;

/**
 *
 * @author Angel Hernandez
 */
public class ServicioConductor{

    
    public static ObservableList<Conductor> obtenerConductores() throws Exception {
        return ConsultasPersona.obtenerConductoresConsulta();
    }
    
    public static Conductor obtenerConductorPorId(long Id) throws Exception
    {
        return ConsultasPersona.obtenerConductorPorIdConsulta(Id);
    }
    
     public static Conductor obtenerConductorPorIdLicencia(long IdLicencia) throws Exception
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
                conductoresPorEstado.add(ServicioConductor.obtenerConductorPorIdLicencia(licencias.get(i-1).getId()));
            }
        }
        return conductoresPorEstado;
    }
      
    public static void eliminarConductor(long id) throws Exception{
        ConsultasPersona.eliminarPersonaTotalmente(id);
    }

}
