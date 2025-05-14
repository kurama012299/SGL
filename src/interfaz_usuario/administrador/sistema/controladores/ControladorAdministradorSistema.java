/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.sistema.controladores;

import com.jfoenix.controls.JFXButton;
import gestor_interfaces.GestorEscenas;
import gestor_interfaces.modelos.Controlador;
import gestor_interfaces.modelos.Estadistica;
import gestor_interfaces.modelos.EstadisticaUsuario;
import gestor_interfaces.modelos.MenuEstadisticas;
import gestor_tablas.GestorTablas;
import java.util.ArrayList;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logica.entidad.modelos.EntidadRelacionada;
import logica.examen_conduccion.modelos.ExamenConduccion;
import logica.infraccion.modelos.Infraccion;
import logica.licencia.modelos.Licencia;
import logica.pdf_gestion.GestorPDF;
import logica.persona.modelos.Conductor;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorAdministradorSistema extends Controlador{
    
    @FXML
    private JFXButton Inicio;
    
    @FXML
    private JFXButton Licencias;
    
    @FXML
    private JFXButton Conductores;
    
    @FXML
    private JFXButton Infracciones;
    
    @FXML
    private JFXButton Examenes;
    
    @FXML
    private JFXButton Clinica;
    
    @FXML
    private JFXButton Autoescuela;
    
    @FXML
    private JFXButton Entidades;
    
    @FXML
    private JFXButton Reportes;
    
    @FXML
    private Pane PanelInicio;
    
    @FXML
    private Pane PanelExamenes;
    
    @FXML
    private Pane PanelInfracciones;
    
    @FXML
    private Pane PanelEntidades;
    
    @FXML
    private Pane PanelClinica;
    
    @FXML
    private Pane PanelAutoescuela;
    
    @FXML
    private Pane PanelLicencias;
    
    @FXML
    private Pane PanelConductores;
    
    @FXML
    private Button RegistrarAutoescuela;
    
    @FXML
    private Button RegistrarClinica;
    
    @FXML
    private Button RegistrarInfracciones;
    
    @FXML
    private Button RegistrarExamen;
    
    @FXML
    private Button RegistrarConductores;
    
    @FXML
    private Button RegistrarLicencia;
    
    
    @FXML
    private ProgressBar BarraProgresoLicenciaA;
    
    @FXML
    private ProgressBar BarraProgresoLicenciaB;
    
    @FXML
    private ProgressBar BarraProgresoLicenciaC;
    
    @FXML
    private ProgressBar BarraProgresoLicenciaD;
    
    @FXML
    private ProgressBar BarraProgresoLicenciaE;
    
    @FXML
    private ProgressBar BarraProgresoInfraccionLeve;
    
    @FXML
    private ProgressBar BarraProgresoInfraccionGrave;
    
    @FXML
    private ProgressBar BarraProgresoInfraccionMGrave;
    
    @FXML
    private Label LabelLicenciaA;
    
    @FXML
    private Label LabelLicenciaB;
    
    @FXML
    private Label LabelLicenciaC;
    
    @FXML
    private Label LabelLicenciaD;
    
    @FXML
    private Label LabelLicenciaE;
    
    @FXML
    private Label LabelInfraccionLeve;
    
    @FXML
    private Label LabelInfraccionGrave;
    
    @FXML
    private Label LabelInfraccionMGrave;
    
    @FXML
    private JFXButton BotonCerrarSesion;
    
    @FXML
    private TableView<Conductor> TablaConductor;
    
    @FXML
    private TableView<ExamenConduccion> TablaExamenes;
    
    @FXML
    private TableColumn<ExamenConduccion, String> ColumnaFotoExamen;
    
    @FXML
    private TableColumn<ExamenConduccion, String> ColumnaExaminadoExamen;
    
    @FXML
    private TableColumn<ExamenConduccion, String> ColumnaTipoExamen;

    @FXML
    private TableColumn<ExamenConduccion, Date> ColumnaFechaExamen;
    
    @FXML
    private TableColumn<ExamenConduccion, String> ColumnaExaminadorExamen;
    
    @FXML
    private TableColumn<ExamenConduccion, String> ColumnaResultadoExamen;
    
    @FXML
    private TableColumn<ExamenConduccion, String> ColumnaDetallesExamen;
    
    @FXML
    private TableColumn<Conductor, String> ColumnaFoto;
    
    @FXML
    private TableColumn<Conductor, String> ColumnaNombre;
    
    @FXML
    private TableColumn<Conductor, String> ColumnaCI;
    
    @FXML
    private TableColumn<Conductor, String> ColumnaTelefono;
    
    @FXML
    private TableColumn<Conductor, String> ColumnaCorreo;
    
    @FXML
    private TableView<EntidadRelacionada> TablaEntidad;
    
    @FXML
    private TableView<Infraccion> TablaInfraccion;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDirectorEntidad;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaNombreEntidad;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDireccionEntidad;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaTelefonoEntidad;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaCorreoEntidad;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDetallesEntidad;
    
    @FXML
    private TableColumn<Infraccion, String> ColumnaFotoInfraccion;
    
    @FXML
    private TableColumn<Infraccion, String> ColumnaNombreInfraccion;
    
    @FXML
    private TableColumn<Infraccion, String> ColumnaTipoInfraccion;
    
    @FXML
    private TableColumn<Infraccion, Date> ColumnaFechaInfraccion;
    
    @FXML
    private TableColumn<Infraccion, String> ColumnaLugarInfraccion;
    
    @FXML
    private TableColumn<Infraccion, Long> ColumnaLicenciaInfraccion;
    
    @FXML
    private TableColumn<Infraccion, Integer> ColumnaPtosDeducidosInfraccion;
    
    @FXML
    private TableColumn<Infraccion, String> ColumnaDetallesInfraccion;
    
    @FXML
    private TableView<EntidadRelacionada> TablaAE;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDirectorAE;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaNombreAE;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDireccionAE;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaTelefonoAE;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaCorreoAE;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDetallesAE;
    
    @FXML
    private TableView<EntidadRelacionada> TablaClinica;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDirectorClinica;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaNombreClinica;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDireccionClinica;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaTelefonoClinica;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaCorreoClinica;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDetallesClinica;
    
    
     @FXML
    private TableView<Licencia> TablaLicencia;
    
    @FXML
    private TableColumn<Licencia, String> ColumnaFotoLicencia;
    
    @FXML
    private TableColumn<Licencia, String> ColumnaNombreLicencia;
    
    @FXML
    private TableColumn<Licencia, String> ColumnaTipoLicencia;
    
    @FXML
    private TableColumn<Licencia, Date> ColumnaEmisionLicencia;
    
    @FXML
    private TableColumn<Licencia, Date> ColumnaVencimientoLicencia;
    
    @FXML
    private TableColumn<Licencia, Integer> ColumnaPuntosLicencia;
    
    @FXML
    private TableColumn<Licencia, String> ColumnaDetallesLicencia;
    
    @FXML
    private Label LabelUltimoInicioSesion;
    
    @FXML
    private Label LabelIniciosSesion;
    
   @FXML
   private Label LabelTotalConductores;
   
   @FXML
   private Label LabelExamenesReprobados;
   
   @FXML
   private Label LabelTotalEntidades;
        
    

    
    private ImageView ImagenLicencias;
    private ImageView ImagenConductores;
    private ImageView ImagenInicio;
    private ImageView ImagenExamenes;
    private ImageView ImagenInfracciones;
    private ImageView ImagenReportes; 
    private ImageView ImagenAutoescuela;
    private ImageView ImagenClinica;
    private ImageView ImagenEntidades; 
    
    
    @FXML
    public void initialize() throws Exception 
    {
 
        ImagenLicencias = (ImageView) Licencias.getGraphic();
        ImagenConductores = (ImageView) Conductores.getGraphic();
        ImagenInicio = (ImageView) Inicio.getGraphic();
        ImagenExamenes = (ImageView) Examenes.getGraphic();
        ImagenInfracciones = (ImageView) Infracciones.getGraphic();
        ImagenReportes = (ImageView) Reportes.getGraphic();
        ImagenAutoescuela = (ImageView) Autoescuela.getGraphic();
        ImagenClinica = (ImageView) Clinica.getGraphic();
        ImagenEntidades = (ImageView) Entidades.getGraphic();
        
        
        BotonCerrarSesion.setOnAction(e ->
        {
            GestorEscenas.CerrarPrograma();
        });
        
        JFXButton[] BotonesConsumirTecla = {Inicio, Examenes, Licencias, Conductores, Infracciones, Reportes, Autoescuela, Clinica, Entidades};
        GestorEscenas.ConsumirTecla(BotonesConsumirTecla);
        
        
        
        System.out.println("Controlador Administrador Sistema Iniciado");
        this.TransicionInicio();
    }
    

    
    @FXML
    public void TransicionLicencias()
    {
        
        GestorTablas.ConfigurarColumnasLicencias(ColumnaFotoLicencia, ColumnaNombreLicencia, ColumnaTipoLicencia, ColumnaEmisionLicencia, ColumnaVencimientoLicencia, ColumnaPuntosLicencia, ColumnaDetallesLicencia);
        GestorTablas.CargarTablaLicencias(TablaLicencia);
        Pane[] PanelesOcultar={PanelInfracciones, PanelInicio, PanelConductores, PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelLicencias,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Examenes, Reportes, Autoescuela, Clinica, Entidades};
        GestorEscenas.PintarBotones(Licencias, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-licencia-blanco.png")));
        Licencias.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInfracciones);
                add(ImagenInicio);
                add(ImagenConductores);
                add(ImagenExamenes);
                add(ImagenReportes);
                add(ImagenAutoescuela);
                add(ImagenClinica);
                add(ImagenEntidades);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Infracciones);
                add(Inicio);
                add(Conductores);
                add(Examenes);
                add(Reportes);
                add(Autoescuela);
                add(Clinica);
                add(Entidades);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionConductores()
    {
        //Llenado de tabla
        GestorTablas.ConfigurarColumnasConductores(ColumnaFoto, ColumnaNombre, ColumnaCI, ColumnaTelefono, ColumnaCorreo);
        GestorTablas.CargarTablaConductores(TablaConductor);
        
        
        
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelInicio, PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelConductores,PanelesOcultar);
        JFXButton[] botones = {Inicio, Licencias, Infracciones, Examenes, Reportes, Autoescuela, Clinica, Entidades};
        GestorEscenas.PintarBotones(Conductores, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-conductor-blanco.png")));
        Conductores.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInfracciones);
                add(ImagenInicio);
                add(ImagenLicencias);
                add(ImagenExamenes);
                add(ImagenReportes);
                add(ImagenAutoescuela);
                add(ImagenClinica);
                add(ImagenEntidades);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Infracciones);
                add(Inicio);
                add(Licencias);
                add(Examenes);
                add(Reportes);
                add(Autoescuela);
                add(Clinica);
                add(Entidades);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionInicio()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelInicio,PanelesOcultar);
        JFXButton[] botones = {Licencias, Conductores, Infracciones, Examenes, Reportes,Autoescuela,Clinica,Entidades};
        GestorEscenas.PintarBotones(Inicio, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-inicio-blanco.png")));
        Inicio.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInfracciones);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenExamenes);
                add(ImagenReportes);
                add(ImagenAutoescuela);
                add(ImagenClinica);
                add(ImagenEntidades);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Infracciones);
                add(Conductores);
                add(Licencias);
                add(Examenes);
                add(Reportes);
                add(Autoescuela);
                add(Clinica);
                add(Entidades);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionExamenes()
    {
        GestorTablas.ConfigurarColumnasExamenes(ColumnaFotoExamen, ColumnaExaminadoExamen, ColumnaTipoExamen, ColumnaFechaExamen, ColumnaExaminadorExamen,ColumnaResultadoExamen,ColumnaDetallesExamen);
        GestorTablas.CargarTablaExamenes(TablaExamenes);
        
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelInicio, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenes,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Licencias, Reportes,Autoescuela,Clinica,Entidades};
        GestorEscenas.PintarBotones(Examenes, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-examen-blanco.png")));
        Examenes.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInfracciones);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenInicio);
                add(ImagenReportes);
                add(ImagenAutoescuela);
                add(ImagenClinica);
                add(ImagenEntidades);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Infracciones);
                add(Conductores);
                add(Licencias);
                add(Inicio);
                add(Reportes);
                add(Autoescuela);
                add(Clinica);
                add(Entidades);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionInfracciones()
    {
        
        GestorTablas.ConfigurarColumnasInfracciones(ColumnaFotoInfraccion, ColumnaNombreInfraccion, ColumnaTipoInfraccion, ColumnaFechaInfraccion, ColumnaLugarInfraccion, ColumnaLicenciaInfraccion, ColumnaPtosDeducidosInfraccion, ColumnaDetallesInfraccion);
        GestorTablas.CargarTablaInfracciones(TablaInfraccion);
        Pane[] PanelesOcultar={PanelInicio, PanelLicencias, PanelConductores, PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelInfracciones,PanelesOcultar);
        JFXButton[] botones = {Inicio,Autoescuela,Clinica,Conductores,Entidades,Examenes,Licencias,Reportes};
        GestorEscenas.PintarBotones(Infracciones, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-infraccion-blanco.png")));
        Infracciones.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenExamenes);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenInicio);
                add(ImagenReportes);
                add(ImagenAutoescuela);
                add(ImagenClinica);
                add(ImagenEntidades);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Examenes);
                add(Conductores);
                add(Licencias);
                add(Inicio);
                add(Reportes);
                add(Autoescuela);
                add(Clinica);
                add(Entidades);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionClinica()
    {
        GestorTablas.ConfigurarColumnasClinicas(ColumnaDirectorClinica, ColumnaNombreClinica,ColumnaDireccionClinica, ColumnaTelefonoClinica, ColumnaCorreoClinica, ColumnaDetallesClinica);
        GestorTablas.CargarTablaClinicas(TablaClinica);
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelExamenes, PanelInicio, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelClinica,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Licencias, Reportes,Autoescuela,Entidades,Examenes};
        GestorEscenas.PintarBotones(Clinica, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-clinica-blanco.png")));
        Clinica.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenExamenes);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenInicio);
                add(ImagenReportes);
                add(ImagenAutoescuela);
                add(ImagenInfracciones);
                add(ImagenEntidades);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Examenes);
                add(Conductores);
                add(Licencias);
                add(Inicio);
                add(Reportes);
                add(Autoescuela);
                add(Infracciones);
                add(Entidades);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
        
    }
    
    @FXML
    public void TransicionAutoescuela()
    {
        GestorTablas.ConfigurarColumnasAutoescuelas(ColumnaDirectorAE, ColumnaNombreAE,ColumnaDireccionAE, ColumnaTelefonoAE, ColumnaCorreoAE, ColumnaDetallesAE);
        GestorTablas.CargarTablaAutoescuelas(TablaAE);
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelExamenes, PanelClinica, PanelInicio, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelAutoescuela,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Licencias, Reportes,Clinica,Entidades,Examenes};
        GestorEscenas.PintarBotones(Autoescuela, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-autoescuela-blanco.png")));
        Autoescuela.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenExamenes);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenInicio);
                add(ImagenReportes);
                add(ImagenClinica);
                add(ImagenInfracciones);
                add(ImagenEntidades);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Examenes);
                add(Conductores);
                add(Licencias);
                add(Inicio);
                add(Reportes);
                add(Clinica);
                add(Infracciones);
                add(Entidades);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionEntidades()
    {
        
        GestorTablas.ConfigurarColumnasEntidades(ColumnaDirectorEntidad, ColumnaNombreEntidad,ColumnaDireccionEntidad, ColumnaTelefonoEntidad, ColumnaCorreoEntidad, ColumnaDetallesEntidad);
        GestorTablas.CargarTablaEntidades(TablaEntidad);
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelExamenes, PanelClinica, PanelAutoescuela, PanelInicio};
        GestorEscenas.MostrarOcultarPaneles(PanelEntidades,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Licencias, Reportes,Autoescuela,Clinica,Examenes};
        GestorEscenas.PintarBotones(Entidades, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-entidades-blanco.png")));
        Entidades.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenExamenes);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenInicio);
                add(ImagenReportes);
                add(ImagenClinica);
                add(ImagenInfracciones);
                add(ImagenAutoescuela);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Examenes);
                add(Conductores);
                add(Licencias);
                add(Inicio);
                add(Reportes);
                add(Clinica);
                add(Infracciones);
                add(Autoescuela);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionReportes() {
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Examenes, Licencias,Entidades,Clinica,Autoescuela};
        GestorEscenas.PintarBotones(Reportes, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-reporte-blanco.png")));
        Reportes.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenExamenes);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenInicio);
                add(ImagenEntidades);
                add(ImagenClinica);
                add(ImagenInfracciones);
                add(ImagenAutoescuela);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Examenes);
                add(Conductores);
                add(Licencias);
                add(Inicio);
                add(Entidades);
                add(Clinica);
                add(Infracciones);
                add(Autoescuela);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
        
        GestorPDF.GenerarMostrarPDF("Prueba", "Reporte.pdf");
    }
    
    
    @FXML
    public void TransicionRegistrarAutoescuela()
    {
        System.out.println("Pulso");
        String Direccion = "/interfaz_usuario/administrador/sistema/menu-auxiliares/registrar/registrar-autoescuela.fxml";
        Stage Padre = (Stage) RegistrarAutoescuela.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar Autoescuela");
        } catch (Exception ex) {
            //CAPTURAR ERROR
        }
    }
    
    
    @FXML
    public void TransicionRegistrarClinica()
    {
        String Direccion = "/interfaz_usuario/administrador/sistema/menu-auxiliares/registrar/registrar-clinica.fxml";
        Stage Padre = (Stage) RegistrarClinica.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar Clinica");
        } catch (Exception ex) {
            //CAPTURAR ERROR
           
        }
    }
    
    @FXML
    public void TransicionRegistrarInfracciones()
    {
        String Direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/registrar/registrar-infraccion.fxml";
        Stage Padre = (Stage) RegistrarInfracciones.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar Infraccion");
        } catch (Exception ex) {
            //CAPTURAR ERROR
           
        }
    }
    
    @FXML
    public void TransicionRegistrarExamen()
    {
        String Direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/registrar/registrar-examen.fxml";
        Stage Padre = (Stage) RegistrarExamen.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar examen");
        } catch (Exception ex) {
            //CAPTURAR ERROR
           
        }
    }
    
    @FXML
    public void TransicionRegistrarConductores()
    {
        String Direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/registrar/registrar-conductor.fxml";
        Stage Padre = (Stage) RegistrarConductores.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar Conductor");
        } catch (Exception ex) {
            //CAPTURAR ERROR
           
        }
    }
    
    @FXML
    public void TransicionRegistrarLicencia()
    {
        String Direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/registrar/registrar-licencia.fxml";
        Stage Padre = (Stage) RegistrarLicencia.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar Licencia");
        } catch (Exception ex) {
            //CAPTURAR ERROR
           
        }
    }

    @Override
    public void Iniciar(MenuEstadisticas MenuEstadisticas) 
    {
        CargarEstadisticas(MenuEstadisticas);
    }
    
    @FXML
    protected void CargarEstadisticas(MenuEstadisticas MenuEstadisticas)
    {
        
        LabelUltimoInicioSesion.setText("Último inicio sesion hace " + MenuEstadisticas.getEstadisticaUsuario().getUltimoInicioSesion());
        LabelIniciosSesion.setText(String.valueOf(MenuEstadisticas.getEstadisticaUsuario().getCantidadIniciosSesion()));
        for(Estadistica e : MenuEstadisticas.getEstadisticas())
        {
            System.out.println(e);
            switch(e.getCategoria())
            {
                case "Cantidad Conductores":
                    LabelTotalConductores.setText(String.valueOf(Math.round(e.getValor())));
                    break;
                case "Cantidad Entidades":
                    LabelTotalEntidades.setText(String.valueOf(Math.round(e.getValor())));
                    break;
                case "Examenes reprobados":
                    LabelExamenesReprobados.setText(String.valueOf(Math.round(e.getValor())));
                    break;
                case "Leve":
                    LabelInfraccionLeve.setText(String.valueOf(Math.round(e.getValor()))+"%");
                    break;
                case "Grave":
                    LabelInfraccionGrave.setText(String.valueOf(Math.round(e.getValor()))+"%");
                    break;
                case "Muy grave":
                    LabelInfraccionMGrave.setText(String.valueOf(Math.round(e.getValor()))+"%");
                    break;
                case "A":
                    LabelLicenciaA.setText(String.valueOf(Math.round(e.getValor()))+"%");
                    break;
                case "B":
                    LabelLicenciaB.setText(String.valueOf(Math.round(e.getValor()))+"%");
                    break;
                case "C":
                    LabelLicenciaC.setText(String.valueOf(Math.round(e.getValor()))+"%");
                    break;
                case "D":
                    LabelLicenciaD.setText(String.valueOf(Math.round(e.getValor()))+"%");
                    break;
                case "E":
                    LabelLicenciaE.setText(String.valueOf(Math.round(e.getValor()))+"%");
                    break;
            }
        }
        Label[] PorcentajesBarra = {LabelLicenciaA,LabelLicenciaB,LabelLicenciaC,LabelLicenciaD,LabelLicenciaE,LabelInfraccionLeve,LabelInfraccionGrave,LabelInfraccionMGrave};
        ProgressBar[] BarrasProgreso = {BarraProgresoLicenciaA,BarraProgresoLicenciaB,BarraProgresoLicenciaC,BarraProgresoLicenciaD,BarraProgresoLicenciaE,BarraProgresoInfraccionLeve,BarraProgresoInfraccionGrave,BarraProgresoInfraccionMGrave};
        GestorEscenas.ProgresoLabel(PorcentajesBarra, BarrasProgreso);
    }
    
}
