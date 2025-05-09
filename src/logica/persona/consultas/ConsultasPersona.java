/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.persona.consultas;

import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.persona.modelos.Conductor;


/**
 *
 * @author Angel Hernandez
 */
public class ConsultasPersona {
    
    public static ObservableList<Conductor> ObtenerConductoresConsulta() throws Exception {
        ObservableList<Conductor> Conductores = FXCollections.observableArrayList();
        
        String consulta = "SELECT * FROM \"Persona\" WHERE \"Id_Licencia\"<>0"; 
        
        try (Connection conn = ConectorBaseDato.Conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(consulta)) {
            
            while (rs.next()) {
                Conductor Conductor = new Conductor(
                        rs.getLong("Id"),
                        rs.getString("Nombre"),
                        rs.getString("Apellidos"),
                        rs.getString("CI"),
                        rs.getDate("FechaNacimiento"),
                        rs.getString("Direccion"),
                        rs.getString("Telefono"),
                        rs.getString("Correo"),
                        rs.getString("Foto"),
                        rs.getLong("Id_Licencia"));
               
                
                Conductores.add(Conductor);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener conductores: " + e.getMessage());
            e.printStackTrace();
        }
        
        return Conductores;
    }
    
    
    public static Conductor ObtenerConductorPorIdConsulta(long Id) throws Exception {
        Conductor Conductor = null;

        String consulta = "SELECT * FROM \"Persona\" WHERE id = ?";

        try (Connection conn = ConectorBaseDato.Conectar(); 
                PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setLong(1, Id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Conductor = new Conductor(
                        rs.getLong("Id"),
                        rs.getString("Nombre"),
                        rs.getString("Apellidos"),
                        rs.getString("CI"),
                        rs.getDate("FechaNacimiento"),
                        rs.getString("Direccion"),
                        rs.getString("Telefono"),
                        rs.getString("Correo"),
                        rs.getString("Foto"),
                        rs.getLong("Id_Licencia")
                    );
                }
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener el conductor de la base de datos", e);
        }

        return Conductor;
    }
}
