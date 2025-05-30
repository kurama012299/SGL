/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.restricciones.consulta;

import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Angel Hernandez
 */
public class ConsultaRestriccion {

    public static ArrayList<String> obtenerNombresRestricciones() throws Exception {
        ArrayList<String> nombresRestricciones = new ArrayList<>();

        String query = "SELECT \"Nombre\" FROM \"Restriccion\"";

        try (Connection conn = ConectorBaseDato.conectar();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                nombresRestricciones.add(rs.getString("Nombre")); // Debe coincidir con el nombre de columna en la consulta
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener restricciones: " + e.getMessage());
        }
        return nombresRestricciones;
    }
}
