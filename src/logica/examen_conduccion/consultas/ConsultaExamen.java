/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen_conduccion.consultas;

import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.entidad.modelos.EntidadRelacionada;
import logica.examen_conduccion.modelos.ExamenConduccion;
import logica.persona.modelos.Persona;
import logica.usuario.modelos.Usuario;

/**
 *
 * @author Kris
 */
public class ConsultaExamen {
    

    public static ObservableList<ExamenConduccion> obtenerExamenesTeoricosConsultas() throws Exception {
        ObservableList<ExamenConduccion> examenes = FXCollections.observableArrayList();

         String consulta = "SELECT et.\"Id\",et.\"Fecha\",et.\"Aprobado\","+
                    "p.\"Id\" as id_persona, " + 
                     "p.\"Nombre\" as nombre_persona, "+ 
                     "p.\"Apellidos\" as apellidos_persona, "+ 
                     "p.\"CI\" as ci_persona, "+ 
                     "u.\"Nombre\" as nombre_examinador, "+
                     "ent.\"Nombre\" as nombre_entidad "+ 
                     "FROM \"ExamenTeorico\" et  "+
                     "LEFT JOIN \"Persona\" p ON et.\"Id_Persona\" = p.\"Id\"" +
                     "LEFT JOIN \"Usuario\" u ON et.\"Id_Examinador\" = u.\"Id\"" + 
                     "LEFT JOIN \"Entidad\" ent ON et.\"Id_Entidad\" = ent.\"Id\"" ;
         
        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {
            try{
                ResultSet rs = stmt.executeQuery();         
                while (rs.next()) {
                    EntidadRelacionada entidad= new EntidadRelacionada(rs.getString("nombre_entidad"));
                    
                    Usuario examinador= new Usuario(rs.getString("nombre_examinador"));
                    
                    Persona persona= new Persona(rs.getLong("id_persona"),
                            rs.getString("nombre_persona"),
                            rs.getString("apellidos_persona"),
                            rs.getString("ci_persona"));
                    
                    ExamenConduccion teorico = new ExamenConduccion(
                            rs.getLong("Id"),
                            rs.getDate("Fecha"),
                            rs.getBoolean("Aprobado"),
                            entidad,
                            persona,
                            examinador,
                            "Teórico"
                            );              
                    examenes.add(teorico);
                }
            } catch (SQLException e) {
                throw new Exception("Error al obtener los examenes teoricos");
            }
            return examenes;
        }
    }
    

    public static ObservableList<ExamenConduccion> obtenerExamenesPracticosConsultas() throws Exception {
        ObservableList<ExamenConduccion> examenes = FXCollections.observableArrayList();

         String consulta = "SELECT ep.\"Id\",ep.\"Fecha\",ep.\"Aprobado\","+
                    "p.\"Id\" as id_persona, " + 
                     "p.\"Nombre\" as nombre_persona, "+ 
                     "p.\"Apellidos\" as apellidos_persona, "+ 
                     "p.\"CI\" as ci_persona, "+ 
                     "u.\"Nombre\" as nombre_examinador, "+
                     "ent.\"Nombre\" as nombre_entidad "+ 
                     "FROM \"ExamenPractico\" ep  "+
                     "LEFT JOIN \"Persona\" p ON ep.\"Id_Persona\" = p.\"Id\"" +
                     "LEFT JOIN \"Usuario\" u ON ep.\"Id_Examinador\" = u.\"Id\"" + 
                     "LEFT JOIN \"Entidad\" ent ON ep.\"Id_Entidad\" = ent.\"Id\"" ;
        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {
            try{
                ResultSet rs = stmt.executeQuery();         
                while (rs.next()) {
                    EntidadRelacionada entidad= new EntidadRelacionada(rs.getString("nombre_entidad"));
                    
                    Usuario examinador= new Usuario(rs.getString("nombre_examinador"));
                    
                    Persona persona = new Persona(
                            rs.getLong("id_persona"),
                            rs.getString("nombre_persona"),
                            rs.getString("apellidos_persona"),
                            rs.getString("ci_persona"));
                    
                    ExamenConduccion practico = new ExamenConduccion(
                            rs.getLong("Id"),
                            rs.getDate("Fecha"),
                            rs.getBoolean("Aprobado"),
                            entidad,
                            persona,
                            examinador,
                            "Práctico"
                            );              
                    examenes.add(practico);  
                }
            } catch (SQLException e) {
                throw new Exception("Error al obtener los examenes practicos");
            }
            return examenes;
        }
    }
    
    
    public static ExamenConduccion obtenerExamenesTeoricosPorIdConsultas(Long Id) throws Exception {

        ExamenConduccion ExamenTeorico=null;
        
         String consulta = "SELECT et.\"Id\",et.\"Fecha\",et.\"Aprobado\","+
                    "p.\"Id\" as id_persona, " + 
                     "p.\"Nombre\" as nombre_persona, "+ 
                     "p.\"Apellidos\" as apellidos_persona, "+ 
                     "p.\"CI\" as ci_persona, "+ 
                     "u.\"Nombre\" as nombre_examinador, "+
                     "ent.\"Nombre\" as nombre_entidad "+ 
                     "FROM \"ExamenTeorico\" et  "+
                     "LEFT JOIN \"Persona\" p ON et.\"Id_Persona\" = p.\"Id\"" +
                     "LEFT JOIN \"Usuario\" u ON et.\"Id_Examinador\" = u.\"Id\"" + 
                     "LEFT JOIN \"Entidad\" ent ON et.\"Id_Entidad\" = ent.\"Id\""+
                     "WHERE et.\"Id\" = ? ";
        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {
            try{
                stmt.setLong(1, Id);
                
                ResultSet rs = stmt.executeQuery();         
                if (rs.next()) {
                    EntidadRelacionada entidad= new EntidadRelacionada(rs.getString("nombre_entidad"));
                    
                    Usuario examinador= new Usuario(rs.getString("nombre_examinador"));
                    
                    Persona persona= new Persona(rs.getLong("id_persona"),
                            rs.getString("nombre_persona"),
                            rs.getString("apellidos_persona"),
                            rs.getString("ci_persona"));
                    
                    ExamenTeorico = new ExamenConduccion(
                            rs.getLong("Id"),
                            rs.getDate("Fecha"),
                            rs.getBoolean("Aprobado"),
                            entidad,
                            persona,
                            examinador,
                            "Teórico"
                            );               
                }
            } catch (SQLException e) {
                throw new Exception("Error al obtener los examenes teoricos");
            }
            return ExamenTeorico;
        }
    }

    
     public static ExamenConduccion obtenerExamenesPracticosPorIdConsultas(Long Id) throws Exception {

        ExamenConduccion ExamenPractico=null;
        
         String consulta = "SELECT ep.\"Id\",ep.\"Fecha\",ep.\"Aprobado\","+
                     "p.\"Id\" as id_persona, " + 
                     "p.\"Nombre\" as nombre_persona, "+ 
                     "p.\"Apellidos\" as apellidos_persona, "+ 
                     "p.\"CI\" as ci_persona, "+ 
                     "u.\"Nombre\" as nombre_examinador, "+
                     "ent.\"Nombre\" as nombre_entidad "+ 
                     "FROM \"ExamenPractico\" ep  "+
                     "LEFT JOIN \"Persona\" p ON ep.\"Id_Persona\" = p.\"Id\"" +
                     "LEFT JOIN \"Usuario\" u ON ep.\"Id_Examinador\" = u.\"Id\"" + 
                     "LEFT JOIN \"Entidad\" ent ON ep.\"Id_Entidad\" = ent.\"Id\""+
                     "WHERE ep.\"Id\" = ? ";
        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {
            try{
                stmt.setLong(1, Id);
                
                ResultSet rs = stmt.executeQuery();         
                if (rs.next()) {
                    EntidadRelacionada entidad= new EntidadRelacionada(rs.getString("nombre_entidad"));
                    
                    Usuario examinador= new Usuario(rs.getString("nombre_examinador"));
                    
                    Persona persona= new Persona(rs.getLong("id_persona"),
                            rs.getString("nombre_persona"),
                            rs.getString("apellidos_persona"),
                            rs.getString("ci_persona"));
                    
                    ExamenPractico = new ExamenConduccion(
                            rs.getLong("Id"),
                            rs.getDate("Fecha"),
                            rs.getBoolean("Aprobado"),
                            entidad,
                            persona,
                            examinador,
                            "Práctico"
                            );               
                }
            } catch (SQLException e) {
                throw new Exception("Error al obtener los examenes practicos");
            }
            return ExamenPractico;
        }
    }


     public static ObservableList<ExamenConduccion> obtenerExamenesPracticosPorIdRolConsultas(Long Id) throws Exception {
        ObservableList<ExamenConduccion> examenes = FXCollections.observableArrayList();

         String consulta = "SELECT ep.\"Id\",ep.\"Fecha\",ep.\"Aprobado\","+
                     "p.\"Id\" as id_persona, " + 
                     "p.\"Nombre\" as nombre_persona, "+ 
                     "p.\"Apellidos\" as apellidos_persona, "+ 
                     "p.\"CI\" as ci_persona, "+ 
                     "u.\"Nombre\" as nombre_examinador, "+
                     "ent.\"Nombre\" as nombre_entidad "+ 
                     "FROM \"ExamenPractico\" ep  "+
                     "LEFT JOIN \"Persona\" p ON ep.\"Id_Persona\" = p.\"Id\"" +
                     "LEFT JOIN \"Usuario\" u ON ep.\"Id_Examinador\" = u.\"Id\"" + 
                     "LEFT JOIN \"Entidad\" ent ON ep.\"Id_Entidad\" = ent.\"Id\""+
                     "WHERE ep.\"Id_Examinador\" = ? ";
        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {
            try{
                stmt.setLong(1, Id);
                
            
                ResultSet rs = stmt.executeQuery();         
                while (rs.next()) {
                    EntidadRelacionada entidad= new EntidadRelacionada(rs.getString("nombre_entidad"));
                    
                    Usuario examinador= new Usuario(rs.getString("nombre_examinador"));
                    
                    Persona persona= new Persona(rs.getLong("id_persona"),
                            rs.getString("nombre_persona"),
                            rs.getString("apellidos_persona"),
                            rs.getString("ci_persona"));
                    
                    ExamenConduccion practico = new ExamenConduccion(
                            rs.getLong("Id"),
                            rs.getDate("Fecha"),
                            rs.getBoolean("Aprobado"),
                            entidad,
                            persona,
                            examinador,
                            "Práctico"
                            );              
                    examenes.add(practico);  
                }
            } catch (SQLException e) {
                throw new Exception("Error al obtener los examenes practicos");
            }
            return examenes;
        }
     }
     
     
     public static ObservableList<ExamenConduccion> obtenerExamenesTeoricosPorIdRolConsultas(Long Id) throws Exception {
        ObservableList<ExamenConduccion> examenes = FXCollections.observableArrayList();

         String consulta = "SELECT ep.\"Id\",ep.\"Fecha\",ep.\"Aprobado\","+
                    "p.\"Id\" as id_persona, " + 
                     "p.\"Nombre\" as nombre_persona, "+ 
                     "p.\"Apellidos\" as apellidos_persona, "+ 
                     "p.\"CI\" as ci_persona, "+ 
                     "u.\"Nombre\" as nombre_examinador, "+
                     "ent.\"Nombre\" as nombre_entidad "+ 
                     "FROM \"ExamenTeorico\" ep  "+
                     "LEFT JOIN \"Persona\" p ON ep.\"Id_Persona\" = p.\"Id\"" +
                     "LEFT JOIN \"Usuario\" u ON ep.\"Id_Examinador\" = u.\"Id\"" + 
                     "LEFT JOIN \"Entidad\" ent ON ep.\"Id_Entidad\" = ent.\"Id\""+
                     "WHERE ep.\"Id_Examinador\" = ? ";
        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {
            try{
                stmt.setLong(1, Id);
                
            
                ResultSet rs = stmt.executeQuery();         
                while (rs.next()) {
                    EntidadRelacionada entidad= new EntidadRelacionada(rs.getString("nombre_entidad"));
                    
                    Usuario examinador= new Usuario(rs.getString("nombre_examinador"));
                    
                    Persona persona= new Persona(rs.getLong("id_persona"),
                            rs.getString("nombre_persona"),
                            rs.getString("apellidos_persona"),
                            rs.getString("ci_persona"));
                    
                    ExamenConduccion practico = new ExamenConduccion(
                            rs.getLong("Id"),
                            rs.getDate("Fecha"),
                            rs.getBoolean("Aprobado"),
                            entidad,
                            persona,
                            examinador,
                            "Teorico"
                            );              
                    examenes.add(practico);  
                }
            } catch (SQLException e) {
                throw new Exception("Error al obtener los examenes teoricos");
            }
            return examenes;
        }
     }
     
     
     public static boolean crearExamenTeorico(ExamenConduccion examen) throws Exception {
        String consulta = "INSERT INTO \"ExamenTeorico\" (\"Fecha\", \"Aprobado\", \"Id_Persona\", \"Id_Examinador\", \"Id_Entidad\") "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {

            java.sql.Date fechaSql = new java.sql.Date(examen.getFecha().getTime());

            stmt.setDate(1, fechaSql);
            stmt.setBoolean(2, examen.isAprobado());
            stmt.setLong(3, examen.getPersona().getId());
            stmt.setLong(4, examen.getExaminador().getId());
            stmt.setLong(5, examen.getEntidad().getId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            throw new Exception("Error al crear el examen teórico");
        }
    }
     
     
     public static boolean crearExamenPractico(ExamenConduccion examen) throws Exception {
        String consulta = "INSERT INTO \"ExamenPractico\" (\"Fecha\", \"Aprobado\", \"Id_Persona\", \"Id_Examinador\", \"Id_Entidad\") "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {

            java.sql.Date fechaSql = new java.sql.Date(examen.getFecha().getTime());

            stmt.setDate(1, fechaSql);
            stmt.setBoolean(2, examen.isAprobado());
            stmt.setLong(3, examen.getPersona().getId());
            stmt.setLong(4, examen.getExaminador().getId());
            stmt.setLong(5, examen.getEntidad().getId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            throw new Exception("Error al crear el examen teórico" );
        }
    }
}
