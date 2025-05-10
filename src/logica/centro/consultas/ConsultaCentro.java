/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.centro.consultas;

import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.centro.modelos.Centro;
import logica.persona.modelos.Conductor;

/**
 *
 * @author Kris
 */
public class ConsultaCentro {
    
    
    public static Centro ObtenerCentroConsulta() throws Exception {
        Centro Centro = null;

        String consulta = "SELECT * FROM \"Centro\"";

        try (Connection conn = ConectorBaseDato.Conectar(); 
                PreparedStatement stmt = conn.prepareStatement(consulta)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Centro = new Centro(
                        rs.getString("Nombre"),
                        rs.getString("DireccionPostal"),
                        rs.getString("Telefono"),
                        rs.getString("NombreDirectorGeneral"),
                        rs.getString("NombreJefeDptoRH"),
                        rs.getString("NombreJefeDptoCont"),
                        rs.getString("NombreSecretarioGS"),
                        rs.getString("Logo")
                    );
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener el conductor de la base de datos", e);
        }
        System.out.println(Centro);
        return Centro;
    }
}

