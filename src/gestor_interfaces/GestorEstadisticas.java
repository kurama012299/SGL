/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_interfaces;

import gestor_interfaces.modelos.Estadistica;
import gestor_interfaces.modelos.EstadisticaUsuario;
import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Angel Hernandez
 */
public class GestorEstadisticas {
    
    public static EstadisticaUsuario ObtenerEstadisticasUsuario(long IdUsuario) throws Exception {
        String Consulta = "SELECT * FROM obtenerestadisticasusuario (?)";
        EstadisticaUsuario Estadistica = null;

        try (Connection conn = ConectorBaseDato.Conectar();
                PreparedStatement pstmt = conn.prepareStatement(Consulta)) {

            pstmt.setLong(1, IdUsuario);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
            {
            Estadistica = new EstadisticaUsuario();
            Estadistica.setCantidadIniciosSesion(rs.getLong("inicios_sesion"));
            Estadistica.setUltimoInicioSesion(rs.getTimestamp("ultimo_inicio"));
            }


        } catch (Exception e) {
            throw new Exception("Error al obtener estadisticas del usuario");
        }
        
        return Estadistica;
    }
    
        public static ArrayList<Estadistica> ObtenerEstadisticasMenuAdministrador() throws Exception {
        ArrayList<Estadistica> estadisticas = new ArrayList<>();

        try (Connection conn = ConectorBaseDato.Conectar()) {
            // 1. Cantidad de conductores
            estadisticas.add(ObtenerCantidadConductores(conn));

            // 2. Cantidad de entidades
            estadisticas.add(ObtenerCantidadEntidades(conn));

            // 3. Exámenes reprobados
            estadisticas.add(ObtenerCantidadExamenesReprobados(conn));

            // 4. Porcentaje de infracciones por gravedad
            estadisticas.addAll(ObtenerPorcientoInfraccionesPorGravedad(conn));

            // 5. Porcentaje de licencias por tipo
            estadisticas.addAll(ObtenerPorcientoLicenciasPorTipo(conn));

        } catch (SQLException e) {
            System.err.println("Error al obtener estadísticas: " + e.getMessage());
        }

        return estadisticas;
    }


    public static Estadistica ObtenerCantidadConductores(Connection conn) throws SQLException {
        String sql = "SELECT * FROM ObtenerCantidadConductores()";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return new Estadistica(
                        "Cantidad Conductores",
                        rs.getLong("CantidadConductores")
                );
            }
        }
     return null;
    }

    public static Estadistica ObtenerCantidadEntidades(Connection conn) throws SQLException {
        String sql = "SELECT * FROM ObtenerCantidadEntidades()";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return new Estadistica(
                        "Cantidad Entidades",
                        rs.getLong("CantidadEntidades")
                );
            }
        }
        return null;
      
    }

    public static Estadistica ObtenerCantidadExamenesReprobados(Connection conn) throws SQLException {
        String sql = "SELECT * FROM ObtenerCantidadExamenesReprobados()";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return new Estadistica(
                        "Examenes reprobados",
                        rs.getLong("CantidadExamenesReprobados")
                );
            }
        }
        return null;
    }

    public static ArrayList<Estadistica> ObtenerPorcientoInfraccionesPorGravedad(Connection conn) throws SQLException {
        ArrayList<Estadistica> lista = new ArrayList<>();
        String sql = "SELECT * FROM ObtenerPorcentajeInfraccionesPorGravedad()";

        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Estadistica(
                        rs.getString("Nombre_Gravedad"),
                        rs.getDouble("Porcentaje")
                ));
            }
        }
        return lista;
    }

    public static ArrayList<Estadistica> ObtenerPorcientoLicenciasPorTipo(Connection conn) throws SQLException {
        ArrayList<Estadistica> lista = new ArrayList<>();
        String sql = "SELECT * FROM ObtenerPorcentajeLicenciasPorTipo()";

        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Estadistica(
                        rs.getString("TipoLicencia"),
                        rs.getDouble("Porcentaje")
                ));
            }
        }
        return lista;
    }
}
