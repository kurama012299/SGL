/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infraestructura;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Angel Hernandez
 */
public class ConectorBaseDato {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/sistemaGestionLicencias";
    private static final String USUARIO = "postgres";
    private static final String CLAVE = "Asbeel*04";

    public static Connection Conectar() throws Exception{
        try {
            Connection Conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Base dato conectada");
            return Conexion;
        } catch (Exception e) {
            throw new Exception("Error al conectar con la base de datos");
        } 
    }
}
