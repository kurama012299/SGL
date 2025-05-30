/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infraestructura;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
/**
 *
 * @author Angel Hernandez
 */
public class ConectorBaseDato {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/sistemaGestionLicencias";
    private static final String USUARIO = "postgres";
    private static final String CLAVE = "Asbeel*04";
    private static HikariDataSource BD;
    
    private ConectorBaseDato(){
    
    }
    
    static {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(URL);
            config.setUsername(USUARIO);
            config.setPassword(CLAVE);
            config.setMaximumPoolSize(20); // Número máximo de conexiones
            config.setMinimumIdle(8);      // Conexiones mínimas inactivas
            config.setConnectionTimeout(5000); // 5 segundos
            config.setIdleTimeout(600000); // 10 minutos
            config.setMaxLifetime(1800000); // 30 minutos

            BD = new HikariDataSource(config);
            System.out.println("Pool de conexiones creado");
        } catch (Exception e) {
            System.err.println("Error al crear el pool de conexiones: " + e.getMessage());
        }
    }

    public static Connection conectar() throws Exception {
        return BD.getConnection(); // Obtiene una conexión del pool
    }

    public static void cerrarConexionBD() {
        if (BD != null && !BD.isClosed()) {
            BD.close();
            System.out.println("Pool de conexiones cerrado");
        }
    }
}
