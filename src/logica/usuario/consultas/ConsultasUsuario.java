/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.usuario.consultas;
import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.usuario.modelos.Usuario;
/**
 *
 * @author Angel Hernandez
 */
public class ConsultasUsuario {

    public static Usuario ObtenerUsuario(String EntradaCorreo, String EntradaClave) throws Exception {
        String Consulta = """
        SELECT u."Id",u."Nombre", u."Correo", r."Nombre" AS NombreRol, 
               u."Foto", u."Id_Entidad_Perteneciente"
        FROM "Usuario" u
        JOIN "Rol" r ON u."Id_Rol" = r."Id"
        WHERE u."Correo" = ? AND u."Clave" = ?
        """;

        try (Connection conn = ConectorBaseDato.Conectar(); 
        PreparedStatement stmt = conn.prepareStatement(Consulta)) {

            stmt.setString(1, EntradaCorreo);
            stmt.setString(2, EntradaClave);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Obtener valores, manejando posibles nulos
                Long Id = rs.getLong("Id");
                String Nombre = rs.getString("Nombre");
                String Correo = rs.getString("Correo");
                String NombreRol = rs.getString("NombreRol");
                String Foto = rs.getString("Foto");
                Long NumeroEntidad = rs.getObject("Id_Entidad_Perteneciente", Long.class);            
                return new Usuario(Nombre, Correo, NombreRol, Foto, NumeroEntidad,Id);
            } else {
                throw new Exception("Credenciales incorrectas o usuario no encontrado");
            }
        } catch (SQLException e) {
            throw new Exception("Error al consultar la base de datos: " + e.getMessage());
        }
    }
    
    public static Usuario obtenerUsuarioPorIdConsulta(Long idUsuario) throws Exception {
        String consulta = """
        SELECT u.*, r."Nombre" AS NombreRol
        FROM "Usuario" u
        JOIN "Rol" r ON u."Id_Rol" = r."Id"
        WHERE u."Id" = ?
        """;

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setLong(1, idUsuario);

            ResultSet rs = stmt.executeQuery();

            // Mapear todos los campos necesarios
            if (rs.next()) {
                // Obtener valores, manejando posibles nulos
                Long Id = rs.getLong("Id");
                String Nombre = rs.getString("Nombre");
                String Correo = rs.getString("Correo");
                String NombreRol = rs.getString("NombreRol");
                String Foto = rs.getString("Foto");
                Long NumeroEntidad = rs.getObject("Id_Entidad_Perteneciente", Long.class);
                return new Usuario(Nombre, Correo, NombreRol, Foto, NumeroEntidad, Id);

            } else {
                throw new Exception("Usuario no encontrado");
            }
        } catch (SQLException e) {
            throw new Exception("Error en la base de datos: " + e.getMessage());
        }
    }
}
