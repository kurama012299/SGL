/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_interfaces;

import gestor_interfaces.modelos.EstadisticaUsuario;
import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Angel Hernandez
 */
public class GestorEstadisticas {
    
    public static EstadisticaUsuario ObtenerEstadisticasUsuario(long IdUsuario) throws Exception {
        String Consulta = "SELECT * FROM obtenerestadisticasusuario (?)";
        EstadisticaUsuario Estadistica = null;

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement pstmt = conn.prepareStatement(Consulta)) {

            pstmt.setLong(1, IdUsuario);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
            {
            Estadistica = new EstadisticaUsuario();
            Estadistica.setCantidadIniciosSesion(rs.getLong("inicios_sesion"));
            Estadistica.setUltimoInicioSesion(rs.getDate("ultimo_inicio"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Estadistica;
    }
    
}