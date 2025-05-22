/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.infraccion.implementaciones;
import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.entidad.consultas.ConsultasEntidad;
import logica.entidad.modelos.EntidadRelacionada;
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
    
    public static ObservableList<Infraccion> ObtenerInfraccionesAnual() throws Exception {
    ObservableList<Infraccion> infracciones = ConsultasInfraccion.ObtenerInfraccionesConsulta();
    
    // Filtrar por año actual
    int añoActual = Year.now().getValue();
    
    // Filtrar y ordenar
    List<Infraccion> infraccionesFiltradas = infracciones.stream()
        .filter(infraccion -> infraccion.getFecha() != null)
        .filter(infraccion -> infraccion.getFecha().getYear() == añoActual)
        .sorted(Comparator.comparing(Infraccion::getFecha).reversed()) // Orden descendente (más reciente primero)
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
