/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.licencia.consultas;

import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.licencia.modelos.Licencia;

/**
 *
 * @author Adrian
 */
public class ConsultasLicencia {
    
    public static ObservableList<Licencia> ObtenerLicenciasConsulta() throws Exception {
    ObservableList<Licencia> licencias = FXCollections.observableArrayList();
    Map<Long, Licencia> mapaLicencias = new HashMap<>();
    
    //  Consulta para datos básicos de licencias
    String consultaLicencias = "SELECT \"Licencia\".*, \"Tipo\".\"Nombre\", \"Estado\".\"Nombre\" " +
                             "FROM \"Licencia\" " +
                             "LEFT JOIN \"Tipo\" ON \"Licencia\".\"Id_Tipo\" = \"Tipo\".\"Id\" " +  
                             "LEFT JOIN \"Estado\" ON \"Licencia\".\"Id_Estado\" = \"Estado\".\"Id\"";
    
    // Consulta para restricciones (a través del examen médico)
    String consultaRestricciones = "SELECT \"Licencia\".\"Id_Examen_Medico\", \"ExamenMedicoRestriccion\".\"Id_Restriccion\" " +
                                 "FROM \"Licencia\" " +
                                 "JOIN \"ExamenMedicoRestriccion\" ON \"Licencia\".\"Id_Examen_Medico\" = \"ExamenMedicoRestriccion\".\"Id_Restriccion\"";
    
    // Consulta para categorías(a traves del id de la licencia)
    String consultaCategorias = "SELECT \"Licencia\".\"Id\", \"Licencia_Categoria\".\"Id_Categoria\" " +
                                 "FROM \"Licencia\" " +
                                 "JOIN \"Licencia_Categoria\" ON \"Licencia\".\"Id\" = \"Licencia_Categoria\".\"Id\"";
    
    try (Connection conn = ConectorBaseDato.Conectar()) {
        // Obtener licencias básicas
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(consultaLicencias)) {
            
            while (rs.next()) {
                Licencia licencia = new Licencia(
                    rs.getLong("Id"),
                    rs.getDate("Fecha_Emision"),
                    rs.getDate("Fecha_Vencimiento"),
                    rs.getBoolean("Renovada"),
                    rs.getInt("CantPuntos"),
                    rs.getString("Nombre"), // Nombre del Tipo
                    rs.getString("Nombre")); // Nombre del Estado
               
                mapaLicencias.put(licencia.getId(), licencia);
                licencias.add(licencia);
            }
        }
        
        // Obtener restricciones
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(consultaRestricciones)) {
            
            while (rs.next()) {
                Long idLicencia = rs.getLong("Id");
                String restriccion = rs.getString("Nombre");
                
                if (mapaLicencias.containsKey(idLicencia)) {
                    mapaLicencias.get(idLicencia).agregarRestriccion(restriccion);
                }
            }
        }
        
        // Obtener categorías
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(consultaCategorias)) {
            
            while (rs.next()) {
                Long idLicencia = rs.getLong("Id_Licencia");
                String categoria = rs.getString("Categoria");
                
                if (mapaLicencias.containsKey(idLicencia)) {
                    mapaLicencias.get(idLicencia).agregarCategoria(categoria);
                }
            }
        }
        
    } catch (SQLException e) {
        throw new Exception("Error al obtener licencias: " + e.getMessage(), e);
    }
    
    return licencias;
}

    
    public static Licencia ObtenerLicenciaPorIdConsulta(long Id) throws Exception {
        Licencia Licencia = null;
    
    // Consulta para datos básicos de la licencia
    String consultaLicencia = "SELECT \"Licencia\".*, \"Tipo\".\"Nombre\", \"Estado\".\"Nombre\" " +
                            "FROM \"Licencia\" " +
                            "LEFT JOIN \"Tipo\" ON \"Licencia\".\"Id_Tipo\" = \"Tipo\".\"Id\" " +
                            "LEFT JOIN \"Estado\" ON \"Licencia\".\"Id_Estado\" = \"Estado\".\"Id\" " +
                            "WHERE \"Licencia\".\"Id\" = ?";
    
    //  Consulta para restricciones (a través del examen médico)
    String consultaRestricciones = "SELECT \"ExamenMedicoRestriccion\".\"Nombre\" " +
                                  "FROM \"Licencia\" " +
                                  "JOIN \"ExamenMedicoRestriccion\" ON \"Licencia\".\"Id_Examen_Medico\" = \"ExamenMedicoRestriccion\".\"Id\" " +
                                  "WHERE \"Licencia\".\"Id\" = ?";
    
    //  Consulta para categorías
    String consultaCategorias = "SELECT \"Licencia_Categoria\".\"Id_Categoria\" " +
                                  "FROM \"Licencia\" " +
                                  "JOIN \"Licencia_Categoria\" ON \"Licencia\".\"Id\" = \"Licencia_Categoria\".\"Id_Categoria\" " +
                                  "WHERE \"Licencia\".\"Id\" = ?";
    
    try (Connection conn = ConectorBaseDato.Conectar()) {
        // Obtener datos básicos de la licencia
        try (PreparedStatement stmt = conn.prepareStatement(consultaLicencia)) {
            stmt.setLong(1, Id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Licencia = new Licencia(
                        rs.getLong("Id"),
                        rs.getDate("Fecha_Emision"),
                        rs.getDate("Fecha_Vencimiento"),
                        rs.getBoolean("Renovada"),
                        rs.getInt("CantPuntos"),
                        rs.getString("Nombre"), // Nombre del Tipo
                        rs.getString("Nombre")); // Nombre del Estado
                }
            }
        }
        
        if (Licencia != null) {
            // Obtener restricciones
            try (PreparedStatement stmt = conn.prepareStatement(consultaRestricciones)) {
                stmt.setLong(1, Id);
                
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Licencia.agregarRestriccion(rs.getString("Nombre"));
                    }
                }
            }
            
            // Obtener categorías
            try (PreparedStatement stmt = conn.prepareStatement(consultaCategorias)) {
                stmt.setLong(1, Id);
                
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Licencia.agregarCategoria(rs.getString("Categoria"));
                    }
                }
            }
        }
        
    } catch (SQLException e) {
        System.out.println(e.getLocalizedMessage());
        throw new Exception("Error al obtener la licencia de la base de datos: ", e);
    }
    
    return Licencia;
}
    
}
