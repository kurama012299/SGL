/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.infraccion.consultas;

import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.entidad.modelos.EntidadRelacionada;
import logica.infraccion.modelos.Infraccion;
import logica.persona.modelos.Conductor;
import logica.persona.modelos.Persona;

/**
 *
 * @author Adrian
 */
public class ConsultasInfraccion {
    
  public static ObservableList<Infraccion> ObtenerInfraccionesConsulta() throws SQLException, Exception {
    ObservableList<Infraccion> Infracciones = FXCollections.observableArrayList();
    
    String consulta = "SELECT \"Infraccion\".*, " +
                    "\"Gravedad\".\"Nombre\" AS nombre_gravedad, " +
                    "\"Persona\".\"Nombre\" AS nombre_persona, " +
                    "\"Persona\".\"Apellidos\" AS apellidos_persona, " +
                    "\"Persona\".\"Foto\" AS foto_persona, " +
                    "\"Persona\".\"Id_Licencia\" AS id_licencia, " +
                    "\"Persona\".\"CI\" AS ci_persona " +
                    "FROM \"Infraccion\" " +
                    "LEFT JOIN \"Gravedad\" ON \"Infraccion\".\"Id_Gravedad\" = \"Gravedad\".\"Id\" " + 
                    "LEFT JOIN \"Persona\" ON \"Infraccion\".\"Id_Licencia\" = \"Persona\".\"Id\"";
    
    try (Connection conn = ConectorBaseDato.Conectar();
         PreparedStatement pstmt = conn.prepareStatement(consulta);
         ResultSet rs = pstmt.executeQuery()) {
        
        while (rs.next()) {
            Infraccion gravedad = new Infraccion(rs.getString("nombre_gravedad"));
            Conductor Persona = new Conductor(
                rs.getString("nombre_persona"), 
                rs.getString("apellidos_persona"), 
                rs.getLong("id_licencia"), 
                rs.getString("ci_persona"),  
                rs.getString("foto_persona"));
            
            Infraccion Infraccion = new Infraccion(
                rs.getDate("Fecha"),
                rs.getString("Lugar"),
                rs.getString("Descripcion"),
                rs.getInt("PuntosDeducidos"),
                rs.getBoolean("Pagada"),
                rs.getString("Nombre_Oficial"),
                gravedad,
                Persona);
            
            Infracciones.add(Infraccion);
        }
    } catch (SQLException e) {
        throw new SQLException("Error al obtener el listado de infracciones", e);
    }
    
    return Infracciones;
}
  
   public static Infraccion ObtenerInfraccionPorIdConsulta(long Id )throws Exception {
        Infraccion Infraccion = null;

        String consulta = "SELECT \"Infraccion\".*, "+
                "\"Gravedad\".\"Nombre\" as nombre_gravedad, "+
                "\"Persona\".\"Nombre\" as nombre_persona, "+
                "\"Persona\".\"Apellidos\" as apellidos_persona "+
                "\"Persona\".\"Foto\" as foto_persona "+
                "\"Persona\".\"CI\" as ci_persona "+
                "\"Persona\".\"Id_Licencia\" as id_licencia "+
                 "FROM \"Infraccion\" "+
                 "LEFT JOIN \"Gravedad\" ON \"Infraccion\".\"Id_Gravedad\" = \"Gravedad\".\"Id\""+ 
                "LEFT JOIN \"Persona\" ON \"Infraccion\".\"Id_Licencia\" = \"Persona\".\"Id\""+
                "WHERE \"Id\" = ?";

        try (Connection conn = ConectorBaseDato.Conectar(); 
                PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setLong(1, Id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Infraccion Gravedad = new Infraccion(rs.getString("nombre_gravedad"));
                Conductor Persona = new Conductor(
                rs.getString("nombre_persona"), 
                rs.getString("apellidos_persona"), 
                rs.getLong("id_licencia"), 
                rs.getString("ci_persona"),  
                rs.getString("foto_persona"));
                
                Infraccion = new Infraccion(
                        rs.getDate("Fecha"),
                        rs.getString("Lugar"),
                        rs.getString("Descripcion"),
                        rs.getInt("PuntosDeducidos"),
                        rs.getBoolean("Pagada"),
                        rs.getString("Nombre_Oficial"),
                        Gravedad,
                        Persona);
                }
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener la entidad de la base de datos", e);
        }

        return Infraccion;
    }
    
}
