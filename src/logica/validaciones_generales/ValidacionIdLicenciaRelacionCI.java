/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.validaciones_generales;
import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Adrian
 */
public class ValidacionIdLicenciaRelacionCI {
    
public static void validarRelacionCiLicencia(String CI, long idLicencia) throws SQLException, Exception {
    String sql = "SELECT 1 FROM \"Persona\" p " +
                "JOIN \"Licencia\" l ON p.\"Id_Licencia\" = l.\"Id\" " +
                "WHERE p.\"CI\" = ? AND l.\"Id\" = ?";
    
    try (Connection conn = ConectorBaseDato.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, CI);
        pstmt.setLong(2, idLicencia);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (!rs.next()) {
                throw new SQLException("La licencia con ID " + idLicencia + " no corresponde al CI " + CI);
            }
        }
    }
}
    
}
