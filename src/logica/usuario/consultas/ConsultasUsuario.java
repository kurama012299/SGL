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
import java.util.ArrayList;
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
    
    public static Usuario obtenerUsuarioPorNombreConsulta(String nombre) throws Exception {
        String consulta = """
    SELECT u."Id", u."Nombre", u."Correo", u."Foto", 
           u."Id_Entidad_Perteneciente", r."Nombre" AS "NombreRol"
    FROM "Usuario" u
    JOIN "Rol" r ON u."Id_Rol" = r."Id"
    WHERE u."Nombre" = ?
    """;

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setString(1, nombre);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Construir el objeto Usuario manejando posibles valores nulos
                    return new Usuario(
                            rs.getString("Nombre"),
                            rs.getString("Correo"),
                            rs.getString("NombreRol"),
                            rs.getString("Foto"),
                            rs.getObject("Id_Entidad_Perteneciente", Long.class),
                            rs.getLong("Id")
                    );
                } else {
                    throw new Exception("Usuario con nombre '" + nombre + "' no encontrado");
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener usuario por nombre: " + e.getMessage(), e);
        }
    }

    public static ArrayList<Usuario> obtenerCorreosUsuariosConsulta() throws Exception {
         
        ArrayList<Usuario> usuarios = new ArrayList<>();

         String consulta = "SELECT u.\"Correo\",u.\"Nombre\""+
                     "FROM \"Usuario\"u";
                    
        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {
            try{
                ResultSet rs = stmt.executeQuery();         
                while (rs.next()) {
                    Usuario usuarioNuevo= new Usuario(
                            rs.getString("Correo"),
                            rs.getString("Nombre"));
                    usuarios.add(usuarioNuevo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return usuarios;
        }
    }
    
    public static ArrayList<Usuario> obtenerUsuariosExamenesConduccionConsulta() throws Exception {
         
        ArrayList<Usuario> usuarios = new ArrayList<>();

         String consulta = "SELECT ur.\"Nombre\" "+
                     "FROM \"Usuario\" ur "+
                     "WHERE ur.\"Id_Rol\" IN(?, ?, ?, ?)";
                    
        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {
            stmt.setInt(1, 1);
            stmt.setInt(2, 2);
            stmt.setInt(3, 4);
            stmt.setInt(4, 5);
            try{
                ResultSet rs = stmt.executeQuery();         
                while (rs.next()) {
                    Usuario usuarioNuevo= new Usuario(
                            rs.getString("Nombre"));
                    usuarios.add(usuarioNuevo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return usuarios;
        }
    }
    
    public static ArrayList<Usuario> obtenerUsuariosExamenesMedicoConsulta() throws Exception {
         
        ArrayList<Usuario> usuarios = new ArrayList<>();

         String consulta = "SELECT u.\"Nombre\" "+
                     "FROM \"Usuario\" u " +
                     "WHERE u.\"Id_Rol\" IN(?, ?, ?, ?)";
                    
        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {
            stmt.setInt(1, 1);
            stmt.setInt(2, 3);
            stmt.setInt(3, 4);
            stmt.setInt(4, 6);
            try{
                ResultSet rs = stmt.executeQuery();         
                while (rs.next()) {
                    Usuario usuarioNuevo= new Usuario(
                            rs.getString("Nombre"));
                    usuarios.add(usuarioNuevo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return usuarios;
        }
    }
     
    public static long crearUsuarioConsulta(Usuario usuario,Long idRol,String clave) throws SQLException, Exception {
        String guardar = "INSERT INTO \"Usuario\" (\"Correo\", \"Clave\", \"Nombre\", \"Id_Rol\", \"Foto\", \"Id_Entidad_Perteneciente\") VALUES (?, ?, ?, ?, ?, ?) RETURNING \"Id\"";

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement pstmt = conn.prepareStatement(guardar)) {

            pstmt.setString(1, usuario.getCorreo());
            pstmt.setString(2, clave);
            pstmt.setString(3, usuario.getNombre());
            pstmt.setLong(4, idRol);
            pstmt.setString(5, usuario.getFoto());
            pstmt.setLong(6, usuario.getEntidadPerteneciente());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong(1); // Retorna el ID generado
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar usuario: " + e.getMessage());
            throw e;
        }
        throw new SQLException("Error al crear el usuario en la base de datos");
    }
}
