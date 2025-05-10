/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen.consultas;

import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.entidad.modelos.EntidadRelacionada;
import logica.examen.modelos.Examen;
import logica.examen_medico.modelos.ExamenMedico;
import logica.persona.modelos.Persona;
import logica.usuario.modelos.Usuario;

/**
 *
 * @author Kris
 */
public class ConsultaExamen {
    
    //Consulta para los examenesTeoricos
    public static ObservableList<Examen> ObtenerExamenesTeoricosConsultas() throws Exception {
        ObservableList<Examen> examenes = FXCollections.observableArrayList();

         String consulta = "SELECT et.\"Id\",et.\"Fecha\",et.\"Aprobado\","+
                     "p.\"Nombre\" as nombre_persona, "+ 
                     "p.\"Apellidos\" as apellidos_persona, "+ 
                     "p.\"CI\" as ci_persona, "+ 
                     "u.\"Nombre\" as nombre_examinador, "+
                     "ent.\"Nombre\" as nombre_entidad "+ 
                     "FROM \"ExamenTeorico\" et  "+
                     "LEFT JOIN \"Persona\" p ON et.\"Id_Persona\" = p.\"Id\"" +
                     "LEFT JOIN \"Usuario\" u ON et.\"Id_Examinador\" = u.\"Id\"" + 
                     "LEFT JOIN \"Entidad\" ent ON et.\"Id_Entidad\" = ent.\"Id\"" ;
        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {
            try{
                ResultSet rs = stmt.executeQuery();         
                while (rs.next()) {
                    EntidadRelacionada entidad= new EntidadRelacionada(rs.getString("nombre_entidad"));
                    Usuario examinador= new Usuario(rs.getString("nombre_examinador"));
                    Persona persona= new Persona(rs.getString("nombre_persona"),rs.getString("apellidos_persona"), rs.getString("ci_persona"));
                    Examen teorico = new Examen(
                            rs.getLong("Id"),
                            rs.getDate("Fecha"),
                            rs.getBoolean("Aprobado"),
                            entidad,
                            persona,
                            examinador
                            );              
                    examenes.add(teorico);
                    
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return examenes;
        }
    }
    
    
    //Consulta para los examenesPracticos
    public static ObservableList<Examen> ObtenerExamenesPracticosConsultas() throws Exception {
        ObservableList<Examen> examenes = FXCollections.observableArrayList();

         String consulta = "SELECT ep.\"Id\",ep.\"Fecha\",ep.\"Aprobado\","+
                     "p.\"Nombre\" as nombre_persona, "+ 
                     "p.\"Apellidos\" as apellidos_persona, "+ 
                     "p.\"CI\" as ci_persona, "+ 
                     "u.\"Nombre\" as nombre_examinador, "+
                     "ent.\"Nombre\" as nombre_entidad "+ 
                     "FROM \"ExamenTeorico\" ep  "+
                     "LEFT JOIN \"Persona\" p ON ep.\"Id_Persona\" = p.\"Id\"" +
                     "LEFT JOIN \"Usuario\" u ON ep.\"Id_Examinador\" = u.\"Id\"" + 
                     "LEFT JOIN \"Entidad\" ent ON ep.\"Id_Entidad\" = ent.\"Id\"" ;
        try (Connection conn = ConectorBaseDato.Conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {
            try{
                ResultSet rs = stmt.executeQuery();         
                while (rs.next()) {
                    EntidadRelacionada entidad= new EntidadRelacionada(rs.getString("nombre_entidad"));
                    Usuario examinador= new Usuario(rs.getString("nombre_examinador"));
                    Persona persona= new Persona(rs.getString("nombre_persona"),rs.getString("apellidos_persona"), rs.getString("ci_persona"));
                    Examen teorico = new Examen(
                            rs.getLong("Id"),
                            rs.getDate("Fecha"),
                            rs.getBoolean("Aprobado"),
                            entidad,
                            persona,
                            examinador
                            );              
                    examenes.add(teorico);  
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return examenes;
        }
    }


}
