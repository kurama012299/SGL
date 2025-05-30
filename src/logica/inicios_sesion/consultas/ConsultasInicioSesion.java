/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.inicios_sesion.consultas;


import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.time.LocalDateTime;

/**
 *
 * @author Angel Hernandez
 */
public class ConsultasInicioSesion {
    
    
    public static void crearInicioSesion(long id) throws Exception {
        LocalDateTime FechaInicio = LocalDateTime.now();

        // Consulta SQL con parámetros preparados
        String Consulta = "INSERT INTO \"InicioSesion\" (\"Id_Usuario\", \"Fecha\") "
                + "VALUES (?, ?)";

        try (Connection conexion = ConectorBaseDato.conectar();
        PreparedStatement pstmt = conexion.prepareStatement(Consulta)) {


            pstmt.setLong(1, id);
            pstmt.setObject(2, FechaInicio);


            int filasAfectadas = pstmt.executeUpdate();


            if (filasAfectadas > 0) {
                System.out.println("Registro de inicio de sesión creado exitosamente.");
            } else 
                throw new Exception("Error al registrar inicio de sesión");
        }
    }

}
