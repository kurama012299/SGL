/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.entidad.implementaciones;

import javafx.collections.ObservableList;
import logica.entidad.consultas.ConsultasEntidad;
import logica.entidad.modelos.EntidadRelacionada;
import logica.persona.consultas.ConsultasPersona;
import logica.persona.modelos.Conductor;

/**
 *
 * @author Adrian
 */
public class ServicioEntidad {
    
     public static ObservableList<EntidadRelacionada> ObtenerEntidadRelacionadas() throws Exception {
        return ConsultasEntidad.ObtenerEntidadesConsulta();
    }
    
    public static EntidadRelacionada ObtenerEntidadPorId(long Id) throws Exception
    {
        return ConsultasEntidad.ObtenerEntidadPorIdConsulta(Id);
    }
    
}
