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
import logica.persona.modelos.Persona;

/**
 *
 * @author Adrian
 */
public class ConsultasInfraccion {
    
   public static ObservableList<Infraccion> ObtenerInfraccionesConsulta() throws Exception {
        ObservableList<Infraccion> Infracciones = FXCollections.observableArrayList();
        
        String consulta = "SELECT \"Infraccion\".*, "+
                "\"Gravedad\".\"Nombre\" as nombre_gravedad, "+
                "\"Persona\".\"Nombre\" as nombre_persona, "+
                "\"Persona\".\"Apellidos\" as apellidos_persona "+
                 "FROM \"Infraccion\" "+
                 "LEFT JOIN \"Gravedad\" ON \"Infraccion\".\"Id_Gravedad\" = \"Gravedad\".\"Id\""+ 
                "LEFT JOIN \"Persona\" ON \"Infraccion\".\"Id_Licencia\" = \"Persona\".\"Id_Licencia\"";
                
                
        try (Connection conn = ConectorBaseDato.Conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(consulta)) {
            
            while (rs.next()) {
                Infraccion Gravedad = new Infraccion(rs.getString("nombre_gravedad"));
                Persona Persona = new Persona(rs.getString("nombre_persona"), rs.getString("apellidos_persona"));
                Infraccion Infraccion = new Infraccion(
                        rs.getLong("Id"),
                        rs.getDate("Fecha"),
                        rs.getString("Lugar"),
                        rs.getString("Descripcion"),
                        rs.getInt("PuntosDeducidos"),
                        rs.getBoolean("Pagada"),
                        rs.getLong("Id_Licencia"),
                        rs.getString("Nombre_Oficial"),
                        Gravedad,
                        Persona);
                        
                                   
                
                Infracciones.add(Infraccion);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener infracciones: " + e.getMessage());
            e.printStackTrace();
        }
        return Infracciones;
    }
    
    
    public static Infraccion ObtenerInfraccionPorIdConsulta(long Id) throws Exception {
        Infraccion Infraccion = null;

        String consulta = "SELECT \"Infraccion\".*, "+
                "\"Gravedad\".\"Nombre\" as nombre_gravedad, "+
                "\"Persona\".\"Nombre\" as nombre_persona, "+
                "\"Persona\".\"Apellidos\" as apellidos_persona "+
                 "FROM \"Infraccion\" "+
                 "LEFT JOIN \"Gravedad\" ON \"Infraccion\".\"Id_Gravedad\" = \"Gravedad\".\"Id\""+ 
                "LEFT JOIN \"Persona\" ON \"Infraccion\".\"Id_Licencia\" = \"Persona\".\"Id_Licencia\""+
                 "WHERE \"Infraccion\".\"Id\" = ?";

        try (Connection conn = ConectorBaseDato.Conectar(); 
                PreparedStatement stmt = conn.prepareStatement(consulta)) {

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
                        rs.getString("Nombre"),//Nombre de Gravedad
                        rs.getString("Nombre_Oficial")); 
                }
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener la infraccion de la base de datos", e);
        }

        return Infraccion;
    }
}
