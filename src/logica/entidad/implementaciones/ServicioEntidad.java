/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.entidad.implementaciones;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import logica.entidad.consultas.ConsultasEntidad;
import logica.entidad.modelos.EntidadRelacionada;

/**
 *
 * @author Adrian
 */
public class ServicioEntidad {
    
     public static ObservableList<EntidadRelacionada> obtenerEntidadRelacionadas() throws Exception {
        return ConsultasEntidad.obtenerEntidadesConsulta();
    }
    
    public static EntidadRelacionada obtenerEntidadPorId(long Id) throws Exception
    {
        return ConsultasEntidad.obtenerEntidadPorIdConsulta(Id);
    }
    
    public static ObservableList<EntidadRelacionada> obtenerAutoescuelas() throws Exception {
        return ConsultasEntidad.obtenerAutoescuelasConsulta();
    }
    
    public static EntidadRelacionada obtenerAutoescuelaPorId(long Id) throws Exception
    {
        return ConsultasEntidad.obtenerAutoescuelaPorIdConsulta(Id);
    }
    
    public static EntidadRelacionada obtenerEntidadPorNombre(String nombre) throws Exception
    {
        return ConsultasEntidad.obtenerEntidadPorNombreConsulta(nombre);
    }
    
    public static ObservableList<EntidadRelacionada> obtenerClinicas() throws Exception {
        return ConsultasEntidad.obtenerClinicasConsulta();
    }
    
    public static EntidadRelacionada obtenerClinicaPorId(long Id) throws Exception
    {
        return ConsultasEntidad.obtenerClinicaPorIdConsulta(Id);
    }
    
    public static ArrayList<String> obtenerNombresClinicas() throws Exception
    {
        ArrayList<String>nombresAutoescuelas= new ArrayList<>();
        ObservableList<EntidadRelacionada>autoescuelas=obtenerAutoescuelas();
        for(EntidadRelacionada aut: autoescuelas)
        {
            nombresAutoescuelas.add(aut.getNombre());
        }
        return nombresAutoescuelas;
    }
    
     public static ArrayList<String> obtenerNombresAutoescuelas() throws Exception
    {
        ArrayList<String>nombresClinicas= new ArrayList<>();
        ObservableList<EntidadRelacionada>clinicas=obtenerClinicas();
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
