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
public class ValidacionConductorExiste {
    
    public static void Validar(String CI) throws Exception{
        verificarExistenciaCI(CI);
        verificarEsConductor(CI);
    }
 
public static void verificarExistenciaCI(String CI) throws SQLException, Exception {
    String sql = "SELECT 1 FROM \"Persona\" WHERE \"CI\" = ?";
    
    try (Connection conn = ConectorBaseDato.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, CI);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (!rs.next()) {
                throw new SQLException("No se encontró persona con CI: " + CI);
            }
        }
    }
}

/**
 * Verifica si la persona con el CI especificado es conductor (tiene licencia)
 * @param CI Número de cédula de la persona (se asume que ya se verificó su existencia)
 * @throws SQLException Si no es conductor (Id_Licencia es null) o hay error en la consulta
 */
public static void verificarEsConductor(String CI) throws SQLException, Exception {
    String sql = "SELECT \"Id_Licencia\" FROM \"Persona\" WHERE \"CI\" = ?";
    
    try (Connection conn = ConectorBaseDato.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, CI);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            rs.next();
            
            // Verificar si Id_Licencia es null
            if (rs.getObject("Id_Licencia") == null) {
                throw new SQLException("La persona con CI " + CI + " no es conductor");
            }
        }
    }
}

}
    

