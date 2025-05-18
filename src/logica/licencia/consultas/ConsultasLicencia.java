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
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.licencia.modelos.Licencia;

/**
 *
 * @author Adrian
 */
public class ConsultasLicencia {

    public static ObservableList<Licencia> ObtenerLicenciasConsulta() throws Exception {
        ObservableList<Licencia> Licencias = FXCollections.observableArrayList();

        String consulta = "SELECT \"Licencia\".*, "
                + "\"Tipo\".\"Nombre\" AS nombre_tipo, "
                + "\"Estado\".\"Nombre\" AS nombre_estado "
                + "FROM \"Licencia\" "
                + "INNER JOIN \"Tipo\" ON \"Licencia\".\"Id_Tipo\" = \"Tipo\".\"Id\" "
                + "INNER JOIN \"Estado\" ON \"Licencia\".\"Id_Estado\" = \"Estado\".\"Id\" ";

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement pstmt = conn.prepareStatement(consulta); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
              
                Licencia Licencia = new Licencia(
                        rs.getLong("Id"),
                        rs.getDate("Fecha_Emision"),
                        rs.getDate("Fecha_Vencimiento"),
                        rs.getBoolean("Renovada"),
                        rs.getInt("CantPuntos"),
                        rs.getString("nombre_tipo"),
                        rs.getString("nombre_estado"));
                
                CargarCategoriasLicencia(Licencia, conn);
                CargarRestriccionesLicencia(Licencia, conn);
                
                Licencias.add(Licencia);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el listado de Licencias", e);
        }
System.out.print(Licencias.get(0));
        return Licencias;
    }

    public static Licencia ObtenerLicenciaPorIdConsulta(long Id) throws Exception {
        Licencia Licencia = null;

        String consulta = "SELECT \"Licencia\".*, "
                + "\"Tipo\".\"Nombre\" AS nombre_tipo, "
                + "\"Estado\".\"Nombre\" AS nombre_estado "
                + "FROM \"Licencia\" "
                + "LEFT JOIN \"Tipo\" ON \"Licencia\".\"Id_Tipo\" = \"Tipo\".\"Id\" "
                + "LEFT JOIN \"Estado\" ON \"Licencia\".\"Id_Estado\" = \"Estado\".\"Id\" "
                + "WHERE \"Licencia\".\"Id\" = ?";

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setLong(1, Id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Licencia = new Licencia(
                        rs.getLong("Id"),
                        rs.getDate("Fecha_Emision"),
                        rs.getDate("Fecha_Vencimiento"),
                        rs.getBoolean("Renovada"),
                        rs.getInt("CantPuntos"),
                        rs.getString("nombre_tipo"),
                        rs.getString("nombre_estado"));
                    
                    CargarCategoriasLicencia(Licencia, conn);
                    CargarRestriccionesLicencia(Licencia, conn);
                    
                }
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener la Licencia de la base de datos", e);
        }
System.out.print(Licencia);
        return Licencia;
    }
    
    private static void CargarRestriccionesLicencia(Licencia Licencia, Connection conn) {
    String consulta = "SELECT r.\"Nombre\" "
                    + "FROM \"Restriccion\" r "
                    + "JOIN \"ExamenMedicoRestriccion\" emr ON r.\"Id\" = emr.\"Id_Restriccion\" "
                    + "JOIN \"Licencia\" l ON emr.\"Id_ExamenMedico\" = l.\"Id_Examen_Medico\" "
                    + "WHERE l.\"Id\" = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(consulta)) {
        pstmt.setLong(1, Licencia.GetId());
        
        try (ResultSet rs = pstmt.executeQuery()) {
            // Inicializar la lista si es null
            if (Licencia.GetRestricciones() == null) {
                Licencia.SetRestricciones(new ArrayList<>());
            }
            
            while (rs.next()) {
                Licencia.GetRestricciones().add(rs.getString("Nombre"));
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar restricciones: " + e.getMessage());
        throw new RuntimeException("Error al cargar restricciones de la licencia", e);
    }
}
    
    private static void CargarCategoriasLicencia(Licencia Licencia, Connection conn) {
        
    String consulta = "SELECT c.\"Nombre\" "
                    + "FROM \"Categoria\" c "
                    + "JOIN \"Licencia_Categoria\" lc ON c.\"Id\" = lc.\"Id_Categoria\" "
                    + "WHERE lc.\"Id_Licencia\" = ?";  

    try (PreparedStatement pstmt = conn.prepareStatement(consulta)) {
        pstmt.setLong(1, Licencia.GetId());
        
        // Inicializar la lista si es null
        if (Licencia.GetCategorias() == null) {
            Licencia.SetCategorias(new ArrayList<>());
        }

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Licencia.GetCategorias().add(rs.getString("Nombre"));
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar categorías para licencia ID: " + Licencia.GetId());
        throw new RuntimeException("Error al cargar categorías de la licencia", e);
    }
}

}
