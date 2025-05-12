 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.entidad.consultas;

import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.entidad.modelos.EntidadRelacionada;

/**
 *
 * @author Adrian
 */
public class ConsultasEntidad {
    
    public static ObservableList<EntidadRelacionada> ObtenerEntidadesConsulta() throws Exception {
        ObservableList<EntidadRelacionada> Entidades = FXCollections.observableArrayList();
        
        String consulta = "SELECT * FROM \"Entidad\""; 
        
        try (Connection conn = ConectorBaseDato.Conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(consulta)) {
            
            while (rs.next()) {
                EntidadRelacionada Entidad = new EntidadRelacionada(
                        rs.getLong("Id"),
                        rs.getString("Nombre"),
                        rs.getString("Direccion"),
                        rs.getString("Telefono"),
                        rs.getString("Correo"),
                        rs.getString("NombreDirector"),
                        rs.getString("Tipo_Entidad"));
               
                
                Entidades.add(Entidad);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener entidades: " + e.getMessage());
            e.printStackTrace();
        }
        
        return Entidades;
    }
    
    public static EntidadRelacionada ObtenerEntidadPorIdConsulta(long Id )throws Exception {
        EntidadRelacionada Entidad = null;

        String consulta = "SELECT * FROM \"Entidad\" WHERE Id = ?";

        try (Connection conn = ConectorBaseDato.Conectar(); 
                PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setLong(1, Id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Entidad = new EntidadRelacionada(
                        rs.getLong("Id"),
                        rs.getString("Nombre"),
                        rs.getString("Direccion"),
                        rs.getString("Telefono"),
                        rs.getString("Correo"),
                        rs.getString("NombreDirector"),
                        rs.getString("Tipo_Entidad"));
                }
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener la entidad de la base de datos", e);
        }

        return Entidad;
    }
    
    
public static ObservableList<EntidadRelacionada> ObtenerAutoescuelasConsulta() throws SQLException, Exception {
    ObservableList<EntidadRelacionada> Autoescuelas = FXCollections.observableArrayList();
    String consulta = "SELECT * FROM \"Entidad\" WHERE \"Entidad\".\"Tipo_Entidad\" = ?";
    
    try (Connection conn = ConectorBaseDato.Conectar();
         PreparedStatement pstmt = conn.prepareStatement(consulta)) {
        
        pstmt.setString(1, "Autoescuela"); // Establecer el parámetro de la consulta
        
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                EntidadRelacionada Autoescuela = new EntidadRelacionada(
                    rs.getLong("Id"),
                    rs.getString("Nombre"),
                    rs.getString("Direccion"),
                    rs.getString("Telefono"),
                    rs.getString("Correo"),
                    rs.getString("NombreDirector"),
                    "Autoescuela");
                
                Autoescuelas.add(Autoescuela);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener autoescuelas: " + e.getMessage());
        throw e; // Relanzar la excepción para manejo superior
    }
    
    return Autoescuelas;
}
    
    public static EntidadRelacionada ObtenerAutoescuelaPorIdConsulta(long Id )throws Exception {
        EntidadRelacionada Autoescuela = null;

        String consulta = "SELECT * FROM \"Entidad\" WHERE Id = ?";

        try (Connection conn = ConectorBaseDato.Conectar(); 
                PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setLong(1, Id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Autoescuela = new EntidadRelacionada(
                        rs.getLong("Id"),
                        rs.getString("Nombre"),
                        rs.getString("Direccion"),
                        rs.getString("Telefono"),
                        rs.getString("Correo"),
                        rs.getString("NombreDirector"),
                        rs.getString("Tipo_Entidad"));
                }
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener la entidad de la base de datos", e);
        }

        return Autoescuela;
    }
    
}
