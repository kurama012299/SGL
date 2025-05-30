/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.utiles;


import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Angel Hernandez
 */
public class ServicioUtil {
    
    public static ObservableList<String> obtenerTiposLicencia() throws  Exception
    {
        ObservableList<String> tiposLicencia = FXCollections.observableArrayList();
        

        String consulta = "SELECT \"Nombre\""
                + "FROM \"Tipo\"";

        try (Connection conn = ConectorBaseDato.conectar();
                PreparedStatement pstmt = conn.prepareStatement(consulta); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                tiposLicencia.add(rs.getString("Nombre"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el listado de tipos de licencia", e);
        }
        return tiposLicencia;
    }
}
