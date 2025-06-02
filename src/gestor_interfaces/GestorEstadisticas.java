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
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.autentificacion.Autentificador;
import logica.examen_conduccion.implementaciones.ServiciosExamenesConduccion;
import logica.examen_conduccion.modelos.ExamenConduccion;
import logica.examen_medico.implementaciones.ServiciosExamenesMedicos;
import logica.examen_medico.modelos.ExamenMedico;
import logica.utiles.ConvertidorFecha;


/**
 *
 * @author Angel Hernandez
 */
public class GestorEstadisticas {
    
    public static EstadisticaUsuario obtenerEstadisticasUsuario(long IdUsuario) throws Exception {
        String Consulta = "SELECT * FROM obtenerestadisticasusuario (?)";
        EstadisticaUsuario Estadistica = null;

        try (Connection conn = ConectorBaseDato.conectar();
                PreparedStatement pstmt = conn.prepareStatement(Consulta)) {

            pstmt.setLong(1, IdUsuario);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
            {
            Estadistica = new EstadisticaUsuario();
            Estadistica.SetCantidadIniciosSesion(rs.getLong("inicios_sesion"));
            Estadistica.SetUltimoInicioSesion(rs.getTimestamp("ultimo_inicio"));
            }


        } catch (Exception e) {
            throw new Exception("Error al obtener estadisticas del usuario");
        }
        
        return Estadistica;
    }

    public static ArrayList<Estadistica> otenerEstadisticasMenuAdministrador() throws Exception {
        ArrayList<Estadistica> estadisticas = new ArrayList<>();

        try (Connection conn = ConectorBaseDato.conectar()) {
            // 1. Cantidad de conductores
            estadisticas.add(obtenerCantidadConductores(conn));

            // 2. Cantidad de entidades
            estadisticas.add(obtenerCantidadEntidades(conn));

            // 3. Exámenes reprobados
            estadisticas.add(obtenerCantidadExamenesReprobados(conn));

            // 4. Porcentaje de infracciones por gravedad
            estadisticas.addAll(obtenerPorcientoInfraccionesPorGravedad(conn));

            // 5. Porcentaje de licencias por tipo
            estadisticas.addAll(obtenerPorcientoLicenciasPorTipo(conn));

        } catch (SQLException e) {
            System.err.println("Error al obtener estadísticas: " + e.getMessage());
        }

        return estadisticas;
    }

    public static ArrayList<Estadistica> obtenerEstadisticasMenuAdministradorAutoescuela() throws Exception
    {
         ArrayList<Estadistica> estadisticas = new ArrayList<>();

        try (Connection conn = ConectorBaseDato.conectar()) {
            
            // 1. Total examenes
            estadisticas.add(obtenerCantidadExamenes(conn));
            // 2. Total trabajadores
            estadisticas.add(obtenerCantidadTrabajadores(conn));
            // 3. Exámenes reprobados
            estadisticas.add(obtenerCantidadExamenesReprobadosConduccion(conn));
            // 4. Examenes Teoricos
            estadisticas.add(obtenerCantidadExamenesTeoricos(conn));
            // 5. Examenes Practicos
            estadisticas.add(obtenerCantidadExamenesPracticos(conn));
            // 6. %Aprobados,Reprobados,Teorico,Practico,IndiceAprobado
            estadisticas.addAll(utilMenuAdministradorAutoescuela(estadisticas.get(0).GetValor(),
                                                                estadisticas.get(3).GetValor(),
                                                                estadisticas.get(4).GetValor(),
                                                                estadisticas.get(2).GetValor()));
            //7. Porciento Mes Anterior
            estadisticas.add(obtenerPorcientoMesAnterior(conn));
            
        } catch (SQLException e) {
            System.err.println("Error al obtener estadísticas: " + e.getMessage());
        }

        return estadisticas;
    }

    public static Estadistica obtenerCantidadConductores(Connection conn) throws SQLException {
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
    
    public static Estadistica obtenerPorcientoMesAnterior(Connection conn) throws SQLException {
        String sql = "SELECT * FROM obtenerporcientoexamenesmes()";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return new Estadistica(
                        "Mes Anterior",
                        rs.getLong("porcentaje_diferencia")
                );
            }
        }
     return null;
    }
    
    public static Estadistica obtenerCantidadTrabajadores(Connection conn) throws SQLException {
        String sql = "SELECT * FROM ObtenerCantidadTrabajadores()";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return new Estadistica(
                        "Cantidad Trabajadores",
                        rs.getLong("total_trabajadores")
                );
            }
        }
     return null;
    }

    public static Estadistica obtenerCantidadExamenesTeoricos(Connection conn) throws SQLException {
        String sql = "SELECT * FROM ObtenerCantidadExamenesTeoricos()";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return new Estadistica(
                        "Cantidad Examenes Teoricos",
                        rs.getLong("total_examenes_teoricos")
                );
            }
        }
     return null;
    }
    
    public static Estadistica obtenerCantidadExamenesPracticos(Connection conn) throws SQLException {
        String sql = "SELECT * FROM ObtenerCantidadExamenesPracticos()";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return new Estadistica(
                        "Cantidad Examenes Practicos",
                        rs.getLong("total_examenes_practicos")
                );
            }
        }
     return null;
    }
    
    public static Estadistica obtenerCantidadExamenes(Connection conn) throws SQLException {
        String sql = "SELECT * FROM ObtenerCantidadExamenes()";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return new Estadistica(
                        "Cantidad Examenes",
                        rs.getLong("total_examenes")
                );
            }
        }
     return null;
    }
    
    public static Estadistica obtenerCantidadEntidades(Connection conn) throws SQLException {
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

    public static Estadistica obtenerCantidadExamenesReprobados(Connection conn) throws SQLException {
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
    
    public static Estadistica obtenerCantidadExamenesReprobadosConduccion(Connection conn) throws SQLException {
        String sql = "SELECT * FROM ObtenerCantidadExamenesReprobadosConduccion()";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return new Estadistica(
                        "Examenes reprobados",
                        rs.getLong("cantidadexamenesreprobados")
                );
            }
        }
        return null;
    }

    public static ArrayList<Estadistica> obtenerPorcientoInfraccionesPorGravedad(Connection conn) throws SQLException {
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

    public static ArrayList<Estadistica> obtenerPorcientoLicenciasPorTipo(Connection conn) throws SQLException {
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
    
    private static ArrayList<Estadistica> utilMenuAdministradorAutoescuela(double TotalExamenes , double ExamenesTeoricos, double ExamenesPracticos, double CantReprobados){
        ArrayList<Estadistica> Estadisticas = new ArrayList<>();
        
        Estadistica PorcientoTeorico = new Estadistica("PorcientoTeorico", Math.round(ExamenesTeoricos/TotalExamenes*100));
        
        Estadistica PorcientoPractico = new Estadistica("PorcientoPractico", Math.round(ExamenesPracticos/TotalExamenes*100));
        
        Estadistica PorcientoReprobado = new Estadistica("PorcientoReprobado", Math.round(CantReprobados/TotalExamenes*100));
        
        Estadistica PorcientoAprobado = new Estadistica("PorcientoAprobado", Math.round((TotalExamenes-CantReprobados)/TotalExamenes*100));
        
        Estadistica IndiceAprobados = new Estadistica("IndiceAprobados",  PorcientoAprobado.GetValor()-PorcientoReprobado.GetValor());
        
        Estadisticas.add(PorcientoTeorico);
        Estadisticas.add(PorcientoPractico);
        Estadisticas.add(PorcientoReprobado);
        Estadisticas.add(PorcientoAprobado);
        Estadisticas.add(IndiceAprobados);
        
        return Estadisticas;
    }
    
    public static Estadistica obtenerCantidadExamenesClinica(Connection conn) throws SQLException{
        
        Estadistica TotalExamenes = obtenerCantidadExamenes(conn);
        Estadistica TotalExamenesP = obtenerCantidadExamenesPracticos(conn);
        Estadistica TotalExamenesT = obtenerCantidadExamenesTeoricos(conn);
        
        double CantidadExamenesClinica = TotalExamenes.GetValor() - (TotalExamenesP.GetValor() + TotalExamenesT.GetValor());
        
        return new Estadistica(
                        "Examenes Clinica",
                        CantidadExamenesClinica
                );
        
        
    }
    
     public static Estadistica obtenerCantidadExamenesAutoescuela(Connection conn) throws SQLException{
        
        Estadistica TotalExamenesP = obtenerCantidadExamenesPracticos(conn);
        Estadistica TotalExamenesT = obtenerCantidadExamenesTeoricos(conn);
        
        double CantidadExamenesAutoescuela = TotalExamenesP.GetValor() + TotalExamenesT.GetValor();
        
        return new Estadistica(
                        "Examenes Autoescuela",
                        CantidadExamenesAutoescuela
                );
        
        
    }
     
     public static Estadistica obtenerCantidadExamenesAutoescuelaAprobados(Connection conn) throws SQLException{
         
         Estadistica examenesAutoescuela = obtenerCantidadExamenesAutoescuela(conn);
         Estadistica examenesReprobadosAutoescuela = obtenerCantidadExamenesReprobadosConduccion(conn);
         double examenesAprobados = examenesAutoescuela.GetValor() - examenesReprobadosAutoescuela.GetValor();
         
         return new Estadistica(
                        "Examenes Aprobados",
                        examenesAprobados
                );
     } 
     
     private static ArrayList<Estadistica> obtenerPorcientoResultados(ObservableList<ExamenMedico> examenes){
         int aprobado=0;
         int reprobado=0;
         int aprobadoRestricciones=0;
         
         for(ExamenMedico e : examenes)
         {
             if(e.isAprobado())
             {
                 if(e.getRestricciones().isEmpty())
                     aprobado++;
                 else
                     aprobadoRestricciones++;
             }
             else
             {
                 reprobado++;
             }
         }
         
         Estadistica porcientoAprobado = new Estadistica("PorcientoAprobado", (aprobado*100)/examenes.size());
         Estadistica porcientoAprobadoRestricciones = new Estadistica("PorcientoAprobadoRestricciones", (aprobadoRestricciones*100)/examenes.size());
         Estadistica porcientoReprobado = new Estadistica("PorcientoReprobado", (reprobado*100)/examenes.size());
         
         ArrayList<Estadistica> porcientoResultado= new ArrayList<>();
         porcientoResultado.add(porcientoAprobado);
         porcientoResultado.add(porcientoAprobadoRestricciones);
         porcientoResultado.add(porcientoReprobado);
         return porcientoResultado;
     }
     
     private static Estadistica cantidadExamenesMedicosReprobados(ObservableList<ExamenMedico> examenes){

         int reprobado=0;

         
         for(ExamenMedico e : examenes)
         {
             if(!e.isAprobado())
                reprobado++;
         }
         
         Estadistica reprobados = new Estadistica("Reprobados", reprobado);
         return reprobados;
     }
     
     private static ArrayList<Estadistica> obtenerPorcientoEdades(ObservableList<ExamenMedico> examenes) throws Exception{
         
         int de18a40=0;
         int de40a60=0;
         int de60a70=0;

         
         for (ExamenMedico e : examenes) {
             int edad = calcularEdad(ConvertidorFecha.convertirFecha(e.getPersona().getCI().substring(0, 6)));

             if (edad < 40) {
                 de18a40++;
             } else if (edad >= 40 && edad < 60) {
                 de40a60++;
             } else if (edad >= 60 && edad <= 70) {
                 de60a70++;
             }
         }
         
         
         Estadistica porciento18a40 = new Estadistica("Porciento18a40", (de18a40*100)/examenes.size());
         Estadistica porciento40a60 = new Estadistica("Porciento40a60", (de40a60*100)/examenes.size());
         Estadistica porciento60a70 = new Estadistica("Porciento60a70", (de60a70*100)/examenes.size());
         
         ArrayList<Estadistica> porcientoResultado= new ArrayList<>();
         porcientoResultado.add(porciento18a40);
         porcientoResultado.add(porciento40a60);
         porcientoResultado.add(porciento60a70);
         return porcientoResultado;
     }
    
    private static int calcularEdad(Date fechaNacimiento) {
        // Convertir Date a LocalDate
        LocalDate fechaNac = fechaNacimiento.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate ahora = LocalDate.now();

        // Calcular periodo entre ambas fechas
        Period periodo = Period.between(fechaNac, ahora);

        return periodo.getYears();
    }
     
    public static ArrayList<Estadistica> obtenerEstadisticasMedico() throws Exception
    {
         ArrayList<Estadistica> estadisticas = new ArrayList<>();
         ObservableList<ExamenMedico> examenes = ServiciosExamenesMedicos.obtenerExamenesMedicoPorIdExaminador(Autentificador.usuario.getId(),"");

        try (Connection conn = ConectorBaseDato.conectar()) {
            
            // 1. Total pacientes
            estadisticas.add(new Estadistica("CantPacientes", examenes.size()));
            // 2. Total examenes
            estadisticas.add(new Estadistica("CantExamenes", examenes.size()));
            // 3. Porciento resultados
            estadisticas.addAll(obtenerPorcientoResultados(examenes));
            // 4. Reprobados
            estadisticas.add(cantidadExamenesMedicosReprobados(examenes));
            // 5.Porciento edades
            estadisticas.addAll(obtenerPorcientoEdades(examenes));
           
            
        } catch (SQLException e) {
            System.err.println("Error al obtener estadísticas: " + e.getMessage());
        }

        return estadisticas;
    }
    
    
    public static ArrayList<Estadistica> obtenerEstadisticasTrabajadorAutoescuela() throws Exception
    {
         ArrayList<Estadistica> estadisticas = new ArrayList<>();
         
         // 1.Cantidad de Examinados
         estadisticas.add(cantidadExaminadosPorExaminador(Autentificador.usuario.getId()));
          // 2.Cantidad de Examenes
         estadisticas.add(cantidadExamenesPorExaminador(Autentificador.usuario.getId()));
         // 3.Cantidad de Examenes Teoricos
         estadisticas.add(cantidadExamenesTeoricosPorExaminador(Autentificador.usuario.getId()));
          // 4.Cantidad de Examenes Practicos
         estadisticas.add(cantidadExamenesPracticosPorExaminador(Autentificador.usuario.getId()));
         // 5.Porciento aprobado
         estadisticas.addAll(porcientoAprobadoPorExaminador(Autentificador.usuario.getId()));
         // 6.Porciento aprobado
         estadisticas.addAll(porcientoTipoExamenPorExaminador(Autentificador.usuario.getId()));
         
         return estadisticas;
    }
    
    private static Estadistica cantidadExaminadosPorExaminador(long idExaminador) throws Exception
    {
        return new Estadistica("CantidadExaminados",ServiciosExamenesConduccion.
                obtenerExamenesPracticosPorIDRol(idExaminador).size()+
                ServiciosExamenesConduccion.obtenerExamenesTeoricosPorIDRol(idExaminador).size());
    }
    
    private static Estadistica cantidadExamenesPorExaminador(long idExaminador) throws Exception
    {
        return new Estadistica("CantidadExamenes",ServiciosExamenesConduccion.
                obtenerExamenesPracticosPorIDRol(idExaminador).size()+
                ServiciosExamenesConduccion.obtenerExamenesTeoricosPorIDRol(idExaminador).size());
    }
    
    private static Estadistica cantidadExamenesTeoricosPorExaminador(long idExaminador) throws Exception
    {
        return new Estadistica("CantidadExamenesTeoricos",
                ServiciosExamenesConduccion.obtenerExamenesTeoricosPorIDRol(idExaminador).size());
    }
    
    private static Estadistica cantidadExamenesPracticosPorExaminador(long idExaminador) throws Exception
    {
        return new Estadistica("CantidadExamenesPracticos",ServiciosExamenesConduccion.
                obtenerExamenesPracticosPorIDRol(idExaminador).size());
    }
    
    private static ArrayList<Estadistica> porcientoAprobadoPorExaminador(long idExaminador) throws Exception
    {
        ObservableList<ExamenConduccion> examenes = FXCollections.observableArrayList();
        examenes.addAll(ServiciosExamenesConduccion.obtenerExamenesTeoricosPorIDRol(idExaminador));
        examenes.addAll(ServiciosExamenesConduccion.obtenerExamenesPracticosPorIDRol(idExaminador));
        
        int reprobados=0;
        
        for(ExamenConduccion e : examenes)
        {
            if(!e.isAprobado())
                reprobados++;
        }
        
        Estadistica porcientoAprobado = new Estadistica("PorcientoAprobado",(examenes.size()-reprobados)*100/examenes.size());
        Estadistica porcientoReprobado = new Estadistica("PorcientoReprobado",(reprobados)*100/examenes.size());
        
        ArrayList<Estadistica> estadisticas = new ArrayList<>();
        estadisticas.add(porcientoAprobado);
        estadisticas.add(porcientoReprobado);
        return estadisticas;
    }
    
    private static ArrayList<Estadistica> porcientoTipoExamenPorExaminador(long idExaminador) throws Exception
    {
        ObservableList<ExamenConduccion> examenes = FXCollections.observableArrayList();
        examenes.addAll(ServiciosExamenesConduccion.obtenerExamenesTeoricosPorIDRol(idExaminador));
        examenes.addAll(ServiciosExamenesConduccion.obtenerExamenesPracticosPorIDRol(idExaminador));

        Estadistica porcientoTeorico = new Estadistica("PorcientoTeorico",ServiciosExamenesConduccion.obtenerExamenesTeoricosPorIDRol(idExaminador).size()*100/examenes.size());
        Estadistica porcientoPractico = new Estadistica("PorcientoPractico",ServiciosExamenesConduccion.obtenerExamenesPracticosPorIDRol(idExaminador).size()*100/examenes.size());
        
        ArrayList<Estadistica> estadisticas = new ArrayList<>();
        estadisticas.add(porcientoTeorico);
        estadisticas.add(porcientoPractico);
        return estadisticas;
    }
    
}
