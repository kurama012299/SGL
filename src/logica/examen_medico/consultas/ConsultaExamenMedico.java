/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen_medico.consultas;

import infraestructura.ConectorBaseDato;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.entidad.modelos.EntidadRelacionada;
import logica.examen_medico.modelos.ExamenMedico;
import logica.persona.modelos.Aprendiz;
import logica.persona.modelos.Persona;
import logica.usuario.modelos.Usuario;

/**
 *
 * @author Kris
 */
public class ConsultaExamenMedico {
    
    //Consulta para Devolver todos los examenes medicos con reestricciones y objetos necesarios para ver mas
    public static ObservableList<ExamenMedico> ObtenerExamenesMedicosRestriccionConsulta() throws Exception {
        ObservableList<ExamenMedico> examenes = FXCollections.observableArrayList();

         String consulta = "SELECT em.\"Id\",em.\"Fecha\",em.\"Aprobado\","+
                     "p.\"Nombre\" as nombre_persona, "+ 
                     "p.\"Apellidos\" as apellidos_persona, "+ 
                     "p.\"CI\" as ci_persona, "+ 
                     "u.\"Nombre\" as nombre_examinador, "+
                     "ent.\"Nombre\" as nombre_entidad "+ 
                     "FROM \"ExamenMedico\" em  "+
                     "LEFT JOIN \"Persona\" p ON em.\"Id_Persona\" = p.\"Id\"" +
                     "LEFT JOIN \"Usuario\" u ON em.\"Id_Examinador\" = u.\"Id\"" + 
                     "LEFT JOIN \"Entidad\" ent ON em.\"Id_Entidad\" = ent.\"Id\"" ;
        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {
            try{
                ResultSet rs = stmt.executeQuery();         
                while (rs.next()) {
                    EntidadRelacionada entidad= new EntidadRelacionada(rs.getString("nombre_entidad"));
                    Usuario examinador= new Usuario(rs.getString("nombre_examinador"));
                    Persona persona= new Persona(rs.getString("nombre_persona"),rs.getString("apellidos_persona"), rs.getString("ci_persona"));
                    ExamenMedico examen = new ExamenMedico(
                            rs.getLong("Id"),
                            rs.getDate("Fecha"),
                            rs.getBoolean("Aprobado"),
                            entidad,
                            persona,
                            examinador,
                            new ArrayList<String>());
                    // 2. Cargar restricciones para este examen
                    CargarRestriccionesParaExamen(examen, conn);
                    examenes.add(examen);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return examenes;
        }
    }

    private static void CargarRestriccionesParaExamen(ExamenMedico examen, Connection conn) {
        String consulta = "SELECT r.\"Nombre\""
                + "FROM \"Restriccion\" r "
                + "JOIN \"ExamenMedicoRestriccion\" emr ON r.\"Id\" = emr.\"Id_Restriccion\""
                + "WHERE emr.\"Id_ExamenMedico\" = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(consulta)) {
            pstmt.setLong(1, examen.getId());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                examen.getRestricciones().add(rs.getString("Nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

