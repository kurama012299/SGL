/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.licencia.consultas;

import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.examen_medico.modelos.ExamenMedico;
import logica.licencia.modelos.Licencia;

/**
 *
 * @author Adrian
 */
public class ConsultasLicencia {

    public static ObservableList<Licencia> ObtenerLicenciasConsulta() throws Exception {
        ObservableList<Licencia> Licencias = FXCollections.observableArrayList();

        String consulta = "SELECT \"Licencia\".*, "
                + "\"Tipo\".\"Nombre\" AS nombre_tipo, "
                + "\"Estado\".\"Nombre\" AS nombre_estado "
                + "FROM \"Licencia\" "
                + "INNER JOIN \"Tipo\" ON \"Licencia\".\"Id_Tipo\" = \"Tipo\".\"Id\" "
                + "INNER JOIN \"Estado\" ON \"Licencia\".\"Id_Estado\" = \"Estado\".\"Id\" ";

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement pstmt = conn.prepareStatement(consulta); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                Licencia Licencia = new Licencia(
                        rs.getLong("Id"),
                        rs.getDate("Fecha_Emision"),
                        rs.getDate("Fecha_Vencimiento"),
                        rs.getBoolean("Renovada"),
                        rs.getInt("CantPuntos"),
                        rs.getString("nombre_tipo"),
                        rs.getString("nombre_estado"));

                CargarCategoriasLicencia(Licencia, conn);
                CargarRestriccionesLicencia(Licencia, conn);

                Licencias.add(Licencia);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el listado de Licencias", e);
        }
        return Licencias;
    }

    public static Licencia ObtenerLicenciaPorIdConsulta(long Id) throws Exception {
        Licencia Licencia = null;

        String consulta = "SELECT \"Licencia\".*, "
                + "\"Tipo\".\"Nombre\" AS nombre_tipo, "
                + "\"Estado\".\"Nombre\" AS nombre_estado "
                + "FROM \"Licencia\" "
                + "LEFT JOIN \"Tipo\" ON \"Licencia\".\"Id_Tipo\" = \"Tipo\".\"Id\" "
                + "LEFT JOIN \"Estado\" ON \"Licencia\".\"Id_Estado\" = \"Estado\".\"Id\" "
                + "WHERE \"Licencia\".\"Id\" = ?";

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setLong(1, Id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Licencia = new Licencia(
                            rs.getLong("Id"),
                            rs.getDate("Fecha_Emision"),
                            rs.getDate("Fecha_Vencimiento"),
                            rs.getBoolean("Renovada"),
                            rs.getInt("CantPuntos"),
                            rs.getString("nombre_tipo"),
                            rs.getString("nombre_estado"));

                    CargarCategoriasLicencia(Licencia, conn);
                    CargarRestriccionesLicencia(Licencia, conn);

                }
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener la Licencia de la base de datos", e);
        }
        return Licencia;
    }

    private static void CargarRestriccionesLicencia(Licencia Licencia, Connection conn) {
        String consulta = "SELECT r.\"Nombre\" "
                + "FROM \"Restriccion\" r "
                + "JOIN \"ExamenMedicoRestriccion\" emr ON r.\"Id\" = emr.\"Id_Restriccion\" "
                + "JOIN \"Licencia\" l ON emr.\"Id_ExamenMedico\" = l.\"Id_Examen_Medico\" "
                + "WHERE l.\"Id\" = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(consulta)) {
            pstmt.setLong(1, Licencia.getId());

            try (ResultSet rs = pstmt.executeQuery()) {
                // Inicializar la lista si es null
                if (Licencia.getRestricciones() == null) {
                    Licencia.SetRestricciones(new ArrayList<>());
                }

                while (rs.next()) {
                    Licencia.getRestricciones().add(rs.getString("Nombre"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar restricciones: " + e.getMessage());
            throw new RuntimeException("Error al cargar restricciones de la licencia", e);
        }
    }

    private static void CargarCategoriasLicencia(Licencia Licencia, Connection conn) {

        String consulta = "SELECT c.\"Nombre\" "
                + "FROM \"Categoria\" c "
                + "JOIN \"Licencia_Categoria\" lc ON c.\"Id\" = lc.\"Id_Categoria\" "
                + "WHERE lc.\"Id_Licencia\" = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(consulta)) {
            pstmt.setLong(1, Licencia.getId());

            // Inicializar la lista si es null
            if (Licencia.getCategorias() == null) {
                Licencia.setCategorias(new ArrayList<>());
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Licencia.getCategorias().add(rs.getString("Nombre"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar categorías para licencia ID: " + Licencia.getId());
            throw new RuntimeException("Error al cargar categorías de la licencia", e);
        }
    }

    public static int actualizarPuntosLicenciaConsulta(long idLicencia, int puntosASumar) throws SQLException, Exception {

        String consulta = "UPDATE \"Licencia\" SET \"CantPuntos\" = LEAST(\"CantPuntos\" + ?, 36), "
                + "\"Id_Estado\" = CASE WHEN \"CantPuntos\" + ? >= 36 THEN 4 ELSE \"Id_Estado\" END "
                + "WHERE \"Id\" = ? RETURNING \"CantPuntos\"";

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement pstmt = conn.prepareStatement(consulta)) {

            pstmt.setInt(1, puntosASumar);
            pstmt.setInt(2, puntosASumar);
            pstmt.setLong(3, idLicencia);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("CantPuntos");
                }
            }
        }
        throw new SQLException("No se pudo actualizar los puntos de la licencia");
    }

    public static void crearLicenciaConsulta(Licencia licencia, ExamenMedico examenMedico) throws Exception {
    Connection conn = null;
    try {
        conn = ConectorBaseDato.Conectar();
        conn.setAutoCommit(false); // Iniciar transacción

        long idTipo = obtenerIdTipoPorNombre(licencia.getTipo());
        long idEstado = obtenerIdEstadoPorNombre(licencia.getEstado());
        long idPersona = examenMedico.getPersona().getId(); // Obtener ID de la persona
        System.out.println(idPersona);

        // 1. Insertar la licencia
        String consultaLicencia = "INSERT INTO \"Licencia\" (\"Fecha_Emision\", \"Fecha_Vencimiento\", \"Renovada\", \"CantPuntos\", \"Id_Tipo\", \"Id_Estado\", \"Id_Examen_Medico\") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING \"Id\"";

        try (PreparedStatement stmt = conn.prepareStatement(consultaLicencia)) {
            // Convertir java.util.Date a java.sql.Date
            java.sql.Date fechaEmision = new java.sql.Date(licencia.getFechaEmision().getTime());
            java.sql.Date fechaVencimiento = new java.sql.Date(licencia.getFechaVencimiento().getTime());

            stmt.setDate(1, fechaEmision);
            stmt.setDate(2, fechaVencimiento);
            stmt.setBoolean(3, licencia.EstaRenovada());
            stmt.setInt(4, licencia.getCantPuntos());
            stmt.setLong(5, idTipo);
            stmt.setLong(6, idEstado);
            stmt.setLong(7, examenMedico.getId());

            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new SQLException("No se pudo obtener el ID de la licencia creada");
            }
            long idLicencia = rs.getLong("Id");

            // 2. Insertar las categorías asociadas
            if (licencia.getCategorias() != null && !licencia.getCategorias().isEmpty()) {
                String consultaCategorias = "INSERT INTO \"Licencia_Categoria\" (\"Id_Licencia\", \"Id_Categoria\") "
                        + "VALUES (?, (SELECT \"Id\" FROM \"Categoria\" WHERE \"Nombre\" = ?))";

                try (PreparedStatement stmtCategorias = conn.prepareStatement(consultaCategorias)) {
                    for (String categoria : licencia.getCategorias()) {
                        stmtCategorias.setLong(1, idLicencia);
                        stmtCategorias.setString(2, categoria);
                        stmtCategorias.addBatch();
                    }
                    stmtCategorias.executeBatch();
                }
            }

            // 3. Actualizar la persona con el ID de la licencia
            String actualizarPersona = "UPDATE \"Persona\" SET \"Id_Licencia\" = ? WHERE \"Id\" = ?";
            try (PreparedStatement stmtPersona = conn.prepareStatement(actualizarPersona)) {
                stmtPersona.setLong(1, idLicencia);
                stmtPersona.setLong(2, idPersona);
                int affectedRows = stmtPersona.executeUpdate();
                
                if (affectedRows == 0) {
                    throw new SQLException("No se pudo actualizar la persona con el ID de licencia");
                }
            }

            conn.commit(); // Confirmar transacción
        }
    } catch (SQLException e) {
        if (conn != null) {
            try {
                conn.rollback(); // Revertir en caso de error
            } catch (SQLException ex) {
                throw new Exception("Error al revertir la transacción", ex);
            }
        }
        throw new Exception("Error al crear la licencia: " + e.getMessage(), e);
    } finally {
        if (conn != null) {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}

    private static long obtenerIdTipoPorNombre(String nombreTipo) throws Exception {
        String consulta = "SELECT \"Id\" FROM \"Tipo\" WHERE \"Nombre\" = ?";

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement pstmt = conn.prepareStatement(consulta)) {

            pstmt.setString(1, nombreTipo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("Id");
                } else {
                    throw new Exception("No se encontró el tipo de licencia: " + nombreTipo);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener ID del tipo de licencia", e);
        }
    }

    private static long obtenerIdEstadoPorNombre(String nombreEstado) throws Exception {
        String consulta = "SELECT \"Id\" FROM \"Estado\" WHERE \"Nombre\" = ?";

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement pstmt = conn.prepareStatement(consulta)) {

            pstmt.setString(1, nombreEstado);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("Id");
                } else {
                    throw new Exception("No se encontró el estado de licencia: " + nombreEstado);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener ID del estado de licencia", e);
        }
    }
    
    public static long obtenerProximoIdLicenciaConsulta() throws Exception {
        long id = 0;

        String consulta = "SELECT nextval('\"Licencia_Codigo_seq\"') AS next_id";

        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement pstmt = conn.prepareStatement(consulta); ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                id = rs.getLong("next_id"); 
            } else {
                throw new Exception("No se pudo obtener el próximo ID de la secuencia");
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener el próximo ID de licencia");
        }

        return id;
    }
}
