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
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.infraccion.consultas.ConsultasInfraccion;
import logica.infraccion.modelos.Infraccion;
import logica.persona.modelos.Conductor;


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
    
    
    public static ObservableList<Infraccion> obtenerInfraccionesPorAnioYTipo(int anio, String tipoInfraccion) throws Exception {
    // Validación de parámetros mejorada
    if (tipoInfraccion == null || tipoInfraccion.trim().isEmpty()) {
        throw new IllegalArgumentException("El tipo de infracción no puede ser nulo o vacío");
    }
    if (anio < 1900 || anio > LocalDate.now().getYear() + 1) {
        throw new IllegalArgumentException("El año debe estar entre 1900 y " + (LocalDate.now().getYear() + 1));
    }

    // Obtener todas las infracciones con depuración detallada
    ObservableList<Infraccion> infracciones = ConsultasInfraccion.ObtenerInfraccionesConsulta();

    // Filtrar por año y tipo de infracción con conversión segura de fechas
    List<Infraccion> infraccionesFiltradas = infracciones.stream()
            .filter(infraccion -> {
                if (infraccion.getFecha() == null) {
                    System.out.println("Infracción " + infraccion.getId() + " descartada - fecha nula");
                    return false;
                }
                return true;
            })
            .filter(infraccion -> {
                try {
                    Date fechaInfraccion = infraccion.getFecha();
                    LocalDate fechaConvertida;
                    
                    // Manejo diferenciado para java.sql.Date
                    if (fechaInfraccion instanceof java.sql.Date) {
                        fechaConvertida = ((java.sql.Date) fechaInfraccion).toLocalDate();
                    } else {
                        fechaConvertida = fechaInfraccion.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                    }
                    
                    boolean coincideAnio = fechaConvertida.getYear() == anio;
                    if (!coincideAnio) {
                        System.out.println("Infracción " + infraccion.getId() + " descartada - año " + fechaConvertida.getYear());
                    }
                    return coincideAnio;
                } catch (Exception e) {
                    System.err.println("\nERROR en conversión de fecha - Infracción ID: " + infraccion.getId());
                    System.err.println("Tipo de fecha: " + infraccion.getFecha().getClass().getName());
                    System.err.println("Valor de fecha: " + infraccion.getFecha());
                    e.printStackTrace();
                    return false;
                }
            })
            .filter(infraccion -> {
                boolean gravedadCoincide = tipoInfraccion.equalsIgnoreCase(infraccion.getGravedad());
                if (!gravedadCoincide) {
                    System.out.println("Infracción " + infraccion.getId() + " descartada - gravedad '" + 
                            infraccion.getGravedad() + "' no coincide con '" + tipoInfraccion + "'");
                }
                return gravedadCoincide;
            })
            .sorted((inf1, inf2) -> {
                // Manejo seguro para ordenación descendente
                if (inf1.getFecha() == null || inf2.getFecha() == null) {
                    return 0;
                }
                
                try {
                    Date fecha1 = inf1.getFecha();
                    Date fecha2 = inf2.getFecha();
                    
                    // Conversión segura para comparación
                    Instant instant1 = (fecha1 instanceof java.sql.Date) ? 
                            Instant.ofEpochMilli(fecha1.getTime()) : fecha1.toInstant();
                    Instant instant2 = (fecha2 instanceof java.sql.Date) ? 
                            Instant.ofEpochMilli(fecha2.getTime()) : fecha2.toInstant();
                    
                    return instant2.compareTo(instant1);
                } catch (Exception e) {
                    System.err.println("Error al comparar fechas para ordenación");
                    return 0;
                }
            })
            .collect(Collectors.toList());

    return FXCollections.observableArrayList(infraccionesFiltradas);
}
}
