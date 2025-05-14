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
import logica.persona.modelos.Conductor;

/**
 *
 * @author Adrian
 */
public class ConsultasLicencia {
    
    public static ObservableList<Licencia> ObtenerLicenciasConsulta() throws Exception {
        ObservableList<Licencia> Licencias = FXCollections.observableArrayList();

        String consulta = "SELECT \"Licencia\".*, "
                + "\"Tipo\".\"Nombre\" AS nombre_tipo, "
                + "\"Persona\".\"Nombre\" AS nombre_persona, "
                + "\"Persona\".\"Apellidos\" AS apellidos_persona, "
                + "\"Persona\".\"Foto\" AS foto_persona, "
                + "\"Persona\".\"Id_Licencia\" AS id_licencia, "
                + "\"Persona\".\"CI\" AS ci_persona "
                + "FROM \"Licencia\" "
                + "LEFT JOIN \"Tipo\" ON \"Licencia\".\"Id_Tipo\" = \"Tipo\".\"Id\" "
                + "LEFT JOIN \"Persona\" ON \"Licencia\".\"Id\" = \"Persona\".\"Id_Licencia\"";

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement pstmt = conn.prepareStatement(consulta); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                Licencia Tipo = new Licencia(rs.getString("nombre_tipo"));
                Conductor Persona = new Conductor(
                        rs.getString("nombre_persona"),
                        rs.getString("apellidos_persona"),
                        rs.getLong("id_licencia"),
                        rs.getString("ci_persona"),
                        rs.getString("foto_persona"));

                Licencia Licencia = new Licencia(
                        rs.getLong("Id"),
                        rs.getDate("Fecha_Emision"),
                        rs.getDate("Fecha_Vencimiento"),
                        rs.getInt("CantPuntos"),
                        Tipo,
                        Persona);

                Licencias.add(Licencia);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el listado de Licencias", e);
        }

        return Licencias;
    }

    public static Licencia ObtenerLicenciaPorIdConsulta(long Id) throws Exception {
        Licencia Licencia = null;

        String consulta = "SELECT \"Licencia\".*, "
                + "\"Tipo\".\"Nombre\" AS nombre_tipo, "
                + "\"Persona\".\"Nombre\" AS nombre_persona, "
                + "\"Persona\".\"Apellidos\" AS apellidos_persona, "
                + "\"Persona\".\"Foto\" AS foto_persona, "
                + "\"Persona\".\"Id_Licencia\" AS id_licencia, "
                + "\"Persona\".\"CI\" AS ci_persona "
                + "FROM \"Licencia\" "
                + "LEFT JOIN \"Tipo\" ON \"Licencia\".\"Id_Tipo\" = \"Tipo\".\"Id\" "
                + "LEFT JOIN \"Persona\" ON \"Licencia\".\"Id\" = \"Persona\".\"Id_Licencia\" "
                + "WHERE \"Licencia\".\"Id\" = ?";

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setLong(1, Id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Licencia Tipo = new Licencia(rs.getString("nombre_tipo"));
                    Conductor Persona = new Conductor(
                            rs.getString("nombre_persona"),
                            rs.getString("apellidos_persona"),
                            rs.getLong("id_licencia"),
                            rs.getString("ci_persona"),
                            rs.getString("foto_persona"));

                    Licencia = new Licencia(
                            rs.getLong("Id"),
                            rs.getDate("Fecha_Emision"),
                            rs.getDate("Fecha_Vencimiento"),
                            rs.getInt("CantPuntos"),
                            Tipo,
                            Persona);
                }
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener la Licencia de la base de datos", e);
        }

        return Licencia;
    }
}

