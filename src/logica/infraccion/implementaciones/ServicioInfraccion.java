/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.infraccion.implementaciones;

import java.time.LocalDate;

import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
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
    
    public static ObservableList<Infraccion> obtenerInfraccionesPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
        // Validar las fechas de entrada
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Ambas fechas deben ser proporcionadas");
        }
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

        // Obtener todas las infracciones
        ObservableList<Infraccion> infracciones = ConsultasInfraccion.ObtenerInfraccionesConsulta();

        // Convertir LocalDate a Date para comparación
        Date fechaInicioDate = java.sql.Date.valueOf(fechaInicio);
        Date fechaFinDate = java.sql.Date.valueOf(fechaFin.plusDays(1)); // +1 día para incluir la fecha fin

        // Filtrar por el rango de fechas
        List<Infraccion> infraccionesFiltradas = infracciones.stream()
                .filter(infraccion -> infraccion.getFecha() != null)
                .filter(infraccion
                        -> !infraccion.getFecha().before(fechaInicioDate)
                && infraccion.getFecha().before(fechaFinDate))
                .sorted((inf1, inf2) -> inf2.getFecha().compareTo(inf1.getFecha())) // Orden descendente
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(infraccionesFiltradas);
    }

    
    public static Infraccion ObtenerInfraccionPorId(long Id) throws Exception
    {
        return ConsultasInfraccion.ObtenerInfraccionPorIdConsulta(Id);
    }
    
    public static int ObtenerCantidadInfraccionesPorId(long Id) throws Exception
    {
        return ConsultasInfraccion.ObtenerCantidadInfraccionesPorIdConsulta(Id);
    }
    
    public static void crearInfraccionBaseDatos(Infraccion infraccion) throws Exception{
         ConsultasInfraccion.crearInfraccionConsulta(infraccion);
     }
    
    public static long obtenerIdGravedad(String nombreGravedad) throws SQLException, Exception {
    String sql = "SELECT \"Id\" FROM \"Gravedad\" WHERE \"Nombre\" = ?";
    
    try (Connection conn = ConectorBaseDato.Conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, nombreGravedad);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getLong("Id");
            }
        }
    }
    throw new SQLException("No se encontró la gravedad: " + nombreGravedad);
}
}
