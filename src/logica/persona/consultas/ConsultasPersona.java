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
import logica.persona.modelos.Persona;
import java.sql.Types;
import java.time.LocalDate;


/**
 *
 * @author Angel Hernandez
 */
public class ConsultasPersona {
    
    public static ObservableList<Persona> obtenerPersonasConsulta() throws Exception {
        ObservableList<Persona> personas = FXCollections.observableArrayList();
        
        String consulta = "SELECT * FROM \"Persona\" "; 
        
        try (Connection conn = ConectorBaseDato.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(consulta)) {
            
            while (rs.next()) {
                Persona persona = new Persona(
                        rs.getLong("Id"),
                        rs.getString("Nombre"),
                        rs.getString("Apellidos"),
                        rs.getString("CI"),
                        rs.getDate("FechaNacimiento"),
                        rs.getString("Direccion"),
                        rs.getString("Telefono"),
                        rs.getString("Correo"),
                        rs.getString("Foto"));
                
                personas.add(persona);
            }
            
        } catch (SQLException e) {
            throw new Exception("Error al obtener personas");
        }
        
        return personas;
    }
    
    
    public static Persona obtenerPersonaPorCIConsulta(String ci) throws Exception {
        Persona persona = null;

        String consulta = "SELECT * FROM \"Persona\" WHERE \"CI\" = ?";

        try (Connection conn = ConectorBaseDato.conectar(); 
                PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setString(1, ci);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    persona = new Persona(
                        rs.getLong("Id"),
                        rs.getString("Nombre"),
                        rs.getString("Apellidos"),
                        rs.getString("CI"),
                        rs.getDate("FechaNacimiento"),
                        rs.getString("Direccion"),
                        rs.getString("Telefono"),
                        rs.getString("Correo"),
                        rs.getString("Foto")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener persona: "+e.getMessage());
            throw new Exception("Error al obtener la persona");
        }

        return persona;
    }
    
    
    public static ObservableList<Conductor> obtenerConductoresConsulta() throws Exception {
        ObservableList<Conductor> Conductores = FXCollections.observableArrayList();
        
        String consulta = "SELECT * FROM \"Persona\" WHERE \"Id_Licencia\"<>0"; 
        
        try (Connection conn = ConectorBaseDato.conectar();
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
            System.out.println("Error al obtener conductores: "+e.getMessage());
            throw new Exception("Error al obtener conductores");
        }
        
        return Conductores;
    }
    
    
    public static Conductor obtenerConductorPorIdConsulta(long Id) throws Exception {
        Conductor Conductor = null;

        String consulta = "SELECT * FROM \"Persona\" WHERE \"Id\" = ?";

        try (Connection conn = ConectorBaseDato.conectar(); 
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
            System.out.println("Error al obtener conductores: "+e.getMessage());
            throw new Exception("Error al obtener el conductor");
        }

        return Conductor;
    }
    
    public static Conductor obtenerConductorPorIdLicenciaConsulta(long IdLicencia) throws Exception {
        Conductor Conductor = null;

        String consulta = "SELECT * FROM \"Persona\" WHERE \"Id_Licencia\" = ?";

        try (Connection conn = ConectorBaseDato.conectar(); 
                PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setLong(1, IdLicencia);

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
            System.out.println("Error al obtener conductores: "+e.getMessage());
            throw new Exception("Error al obtener el conductor");
        }

        return Conductor;
    }
    
    public static long crearPersonaConsulta(Persona persona) throws Exception {
        String consulta = "INSERT INTO \"Persona\" (\"Nombre\", \"Apellidos\", \"CI\", \"FechaNacimiento\", "
                + "\"Direccion\", \"Telefono\", \"Correo\", \"Foto\", \"Id_Licencia\") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement stmt = conn.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS)) {

            // Establecer parámetros
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellidos());
            stmt.setString(3, persona.getCI());
            stmt.setDate(4, new java.sql.Date(persona.getFechaNacimiento().getTime()));
            stmt.setString(5, persona.getDireccion());
            stmt.setString(6, persona.getTelefono());
            stmt.setString(7, persona.getCorreo());
            stmt.setString(8, persona.getFoto());
            stmt.setNull(9, Types.BIGINT);

            // Ejecutar inserción
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo insertar la persona");
            }
            
             try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
        }
        throw new SQLException("No se obtuvo el ID generado");

        } catch (SQLException e) {
            throw new Exception("Error al crear la persona en la base de datos");
        }
    }
    
    public static ObservableList<Conductor> obtenerConductoresConLicenciaVencidaPeriodo(LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
    // Validar las fechas de entrada
    if (fechaInicio == null || fechaFin == null) {
        throw new IllegalArgumentException("Ambas fechas deben ser proporcionadas");
    }
    if (fechaInicio.isAfter(fechaFin)) {
        throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
    }

    ObservableList<Conductor> conductoresVencidos = FXCollections.observableArrayList();
    
    // Consulta SQL optimizada que obtiene directamente los conductores con licencias vencidas en el periodo
    String consulta = "SELECT p.*, l.\"Fecha_Vencimiento\" " +
                     "FROM \"Persona\" p " +
                     "JOIN \"Licencia\" l ON p.\"Id_Licencia\" = l.\"Id\" " +
                     "WHERE p.\"Id_Licencia\" IS NOT NULL " +
                     "AND l.\"Fecha_Vencimiento\" BETWEEN ? AND ? " +
                     "AND l.\"Id_Estado\" = (SELECT \"Id\" FROM \"Estado\" WHERE \"Nombre\" = 'Vencida')";
    
    try (Connection conn = ConectorBaseDato.conectar();
         PreparedStatement stmt = conn.prepareStatement(consulta)) {
        
        // Convertir LocalDate a java.sql.Date
        stmt.setDate(1, java.sql.Date.valueOf(fechaInicio));
        stmt.setDate(2, java.sql.Date.valueOf(fechaFin));
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Conductor conductor = new Conductor(
                        rs.getLong("Id"),
                        rs.getString("Nombre"),
                        rs.getString("Apellidos"),
                        rs.getString("CI"),
                        rs.getLong("Id_Licencia"));
               
                
                conductoresVencidos.add(conductor);
            }
        }
        
    } /*catch (SQLException e) {
        System.out.println("Error al obtener conductores con licencia vencida: " + e.getMessage());
        throw new Exception("Error al obtener conductores con licencia vencida en el periodo especificado");
    }*/
    
    return conductoresVencidos;
}
}
