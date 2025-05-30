/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.entidad.implementaciones;

import java.util.ArrayList;
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
    
    public static ObservableList<EntidadRelacionada> ObtenerAutoescuelas() throws Exception {
        return ConsultasEntidad.ObtenerAutoescuelasConsulta();
    }
    
    public static EntidadRelacionada ObtenerAutoescuelaPorId(long Id) throws Exception
    {
        return ConsultasEntidad.ObtenerAutoescuelaPorIdConsulta(Id);
    }
    
    public static EntidadRelacionada ObtenerEntidadPorNombre(String nombre) throws Exception
    {
        return ConsultasEntidad.ObtenerEntidadPorNombreConsulta(nombre);
    }
    
    public static ObservableList<EntidadRelacionada> ObtenerClinicas() throws Exception {
        return ConsultasEntidad.ObtenerClinicasConsulta();
    }
    
    public static EntidadRelacionada ObtenerClinicaPorId(long Id) throws Exception
    {
        return ConsultasEntidad.ObtenerClinicaPorIdConsulta(Id);
    }
    
    public static ArrayList<String> obtenerNombresClinicas() throws Exception
    {
        ArrayList<String>nombresAutoescuelas= new ArrayList<>();
        ObservableList<EntidadRelacionada>autoescuelas=ObtenerAutoescuelas();
        for(EntidadRelacionada aut: autoescuelas)
        {
            nombresAutoescuelas.add(aut.getNombre());
        }
        return nombresAutoescuelas;
    }
    
     public static ArrayList<String> obtenerNombresAutoescuelas() throws Exception
    {
        ArrayList<String>nombresClinicas= new ArrayList<>();
        ObservableList<EntidadRelacionada>clinicas=ObtenerClinicas();
        for(EntidadRelacionada aut: clinicas)
        {
            nombresClinicas.add(aut.getNombre());
        }
        return nombresClinicas;
    }
     
     public static long guardarEntidadBaseDatos(EntidadRelacionada entidad) throws Exception{
         return ConsultasEntidad.guardarEntidad(entidad);
     }
     
     public static void actualizarEntidad(EntidadRelacionada entidad) throws Exception{
         ConsultasEntidad.actualizarEntidadConsulta(entidad);
     }
     
     public static void eliminarEntidad(Long id) throws Exception {
         ConsultasEntidad.eliminarEntidadConsulta(id);
     }
    
}
