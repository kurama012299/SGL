/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.infraccion.consultas;

import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.entidad.modelos.EntidadRelacionada;
import logica.infraccion.modelos.Infraccion;
import logica.persona.modelos.Conductor;

/**
 *
 * @author Adrian
 */
public class ConsultasInfraccion {

    public static ObservableList<Infraccion> ObtenerInfraccionesConsulta() throws SQLException, Exception {
        ObservableList<Infraccion> Infracciones = FXCollections.observableArrayList();

        String consulta = "SELECT \"Infraccion\".*, "
                + "\"Gravedad\".\"Nombre\" AS nombre_gravedad "
                + "FROM \"Infraccion\" "
                + "LEFT JOIN \"Gravedad\" ON \"Infraccion\".\"Id_Gravedad\" = \"Gravedad\".\"Id\" ";

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement pstmt = conn.prepareStatement(consulta); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                Infraccion Infraccion = new Infraccion(
                        rs.getLong("Id"),
                        rs.getDate("Fecha"),
                        rs.getString("Lugar"),
                        rs.getString("Descripcion"),
                        rs.getInt("PuntosDeducidos"),
                        rs.getBoolean("Pagada"),
                        rs.getLong("Id_Licencia"),
                        rs.getString("nombre_gravedad"),
                        rs.getString("Nombre_Oficial"));

                Infracciones.add(Infraccion);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el listado de infracciones", e);
        }

        return Infracciones;
    }

    public static Infraccion ObtenerInfraccionPorIdConsulta(long Id) throws Exception {
        Infraccion Infraccion = null;

        String consulta = "SELECT \"Infraccion\".*, "
                + "\"Gravedad\".\"Nombre\" AS nombre_gravedad "
                + "FROM \"Infraccion\" "
                + "LEFT JOIN \"Gravedad\" ON \"Infraccion\".\"Id_Gravedad\" = \"Gravedad\".\"Id\" "
                + "WHERE \"Id\" = ?";

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setLong(1, Id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Infraccion = new Infraccion(
                        rs.getLong("Id"),
                        rs.getDate("Fecha"),
                        rs.getString("Lugar"),
                        rs.getString("Descripcion"),
                        rs.getInt("PuntosDeducidos"),
                        rs.getBoolean("Pagada"),
                        rs.getLong("Id_Licencia"),
                        rs.getString("nombre_gravedad"),
                        rs.getString("Nombre_Oficial"));

                }
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener la infraccion de la base de datos", e);
        }

        return Infraccion;
    }
    
    public static int ObtenerCantidadInfraccionesPorIdConsulta(Long Id) throws Exception{
        ObservableList<Infraccion> Infracciones =  ObtenerInfraccionesConsulta();
        int contador = 0;
        for(int i = 0; i<Infracciones.size(); i++)
            if(Infracciones.get(i).getIdLicencia().equals(Id))
                contador++;
        return contador;
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
    
    public static long obtenerIdLicencia(String CI) throws SQLException, Exception {
    String sql = "SELECT \"Id_Licencia\" FROM \"Persona\" WHERE \"CI\" = ?";
    
    try (Connection conn = ConectorBaseDato.Conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, CI);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getLong("Id_Licencia");
            }
        }
    }
    throw new SQLException("No se encontró la Persona con CI: " + CI);
}
    
    public static long guardarInfraccion(Infraccion infraccion, Conductor conductor) throws SQLException, Exception {
        
        long idGravedad = obtenerIdGravedad(infraccion.getGravedad());
        long idLicencia = obtenerIdLicencia(conductor.getCI());
        
        String guardar = "INSERT INTO \"Infraccion\" (\"Fecha\", \"Lugar\", \"Descripcion\", \"PuntosDeducidos\", \"Pagada\", \"Id_Licencia\", \"Id_Gravedad\", \"Nombre_Oficial\") VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING \"Id\"";

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement pstmt = conn.prepareStatement(guardar)) {

            pstmt.setDate(1, (Date) infraccion.getFecha());
            pstmt.setString(2, infraccion.getLugar());
            pstmt.setString(3, infraccion.getDescripcion());
            pstmt.setInt(4, infraccion.getPuntosDeducidos());
            pstmt.setBoolean(5, infraccion.isPagada());
            pstmt.setLong(6, idLicencia);
            pstmt.setLong(7, idGravedad);
            pstmt.setString(8, infraccion.getNombreOficial());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong(1); // Retorna el ID generado
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar infraccion: " + e.getMessage());
            throw e;
        }
        throw new SQLException("No se pudo guardar la infraccion ni obtener el ID");
    }
}
