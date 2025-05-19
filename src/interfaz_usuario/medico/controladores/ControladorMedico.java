/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.medico.controladores;


import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import gestor_interfaces.GestorEscenas;
import gestor_interfaces.modelos.Controlador;
import gestor_interfaces.modelos.MenuEstadisticas;
import gestor_tablas.GestorTablas;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logica.autentificacion.Autentificador;
import logica.examen_medico.modelos.ExamenMedico;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorMedico extends Controlador{
    
    @FXML
    private HBox VentanaPrincipal;
    
    @FXML
    private JFXButton Inicio;
    
    @FXML
    private JFXButton ExamenesMedicos;
    
    @FXML
    private Pane PanelInicio;
    
    @FXML
    private Pane PanelExamenes;
    
    @FXML
    private Button Registrar;
    
    @FXML
    private ProgressBar BarraProgresoAprobado;
    
    @FXML
    private ProgressBar BarraProgresoReprobado;
    
    @FXML
    private ProgressBar BarraProgresoAprobadoR;
    
    @FXML
    private ProgressBar BarraProgresoJoven;
    
    @FXML
    private ProgressBar BarraProgresoAdulto;
    
    @FXML
    private ProgressBar BarraProgresoAnciano;
    
    @FXML
    private Label LabelProgresoAprobado;
    
    @FXML
    private Label LabelProgresoReprobado;
    
    @FXML
    private Label LabelProgresoAprobadoR;
    
    @FXML
    private Label LabelProgresoJoven;
    
    @FXML
    private Label LabelProgresoAdulto;
    
    @FXML
    private Label LabelProgresoAnciano;
    
    @FXML
    private JFXButton BotonCerrarSesion;
    
    @FXML
    private TableView<ExamenMedico>TablaExamenesMedicos;
    
    @FXML
    private TableColumn<ExamenMedico, Date>ColumnaFecha;
    
    @FXML
    private TableColumn<ExamenMedico, String>ColumnaResultado;
    
    @FXML
    private TableColumn<ExamenMedico, String>ColumnaExaminado;
    
    @FXML
    private TableColumn<ExamenMedico, String>ColumnaClinica;
    
    @FXML
    private TableColumn<ExamenMedico, String>ColumnaDetalles;
    
    @FXML
    private TableColumn<ExamenMedico, String>ColumnaFoto;

    @FXML
    private Label LabelUsuarioNombre;
    
    @FXML
    private Label LabelCorreoUsuario;
    
    @FXML
    private Label LabelFechaHora;
    
    private ImageView ImagenInicio;
    private ImageView ImagenExamenesMedicos;
    
    @FXML
    public void initialize() {
        System.out.println("Controlador Medico Iniciado");
        
        ImagenInicio = (ImageView) Inicio.getGraphic();
        ImagenExamenesMedicos = (ImageView) ExamenesMedicos.getGraphic();
        
        GestorEscenas.configurarReloj(LabelFechaHora);
        
        BotonCerrarSesion.setOnAction(e ->
        {
            GestorEscenas.cerrarPrograma();
        });
        
        JFXButton[] BotonesConsumirTecla={Inicio,ExamenesMedicos};
        GestorEscenas.consumirTecla(BotonesConsumirTecla);
        
        Label[] PorcentajesBarra = {LabelProgresoAprobado,LabelProgresoReprobado,LabelProgresoAprobadoR,LabelProgresoJoven,LabelProgresoAdulto,LabelProgresoAnciano};
        ProgressBar[] BarrasProgreso = {BarraProgresoAprobado,BarraProgresoReprobado,BarraProgresoAprobadoR,BarraProgresoJoven,BarraProgresoAdulto,BarraProgresoAnciano};
        GestorEscenas.progresoLabel(PorcentajesBarra, BarrasProgreso);


        this.TransicionInicio();

    }
    
    @FXML
    public void TransicionExamenesMedico() {
        
        GestorTablas.ConfigurarColumnasExamenesMedicosMedicoUnico(ColumnaFoto,ColumnaExaminado, ColumnaFecha, ColumnaResultado, ColumnaClinica, ColumnaDetalles);
        GestorTablas.CargarTablaExamenesMedicosMedicoUnico(TablaExamenesMedicos, Autentificador.Usuario.getId());
        
        Pane[] PanelesOcultar={PanelInicio};
        GestorEscenas.mostrarOcultarPaneles(PanelExamenes,PanelesOcultar);
        
        JFXButton[] botones={Inicio};
        GestorEscenas.pintarBotones(ExamenesMedicos, botones);
        
        ImageView IconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-examen-medico-blanco.png")));
        ExamenesMedicos.setGraphic(IconoActivo);
        
        
        JFXButton[] BotonesPonerNormal={Inicio};
        GestorEscenas.pintarBotones(ExamenesMedicos,BotonesPonerNormal);
        
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInicio);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Inicio);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionInicio() {
       Pane[] PanelesOcultar={PanelExamenes};
       GestorEscenas.mostrarOcultarPaneles(PanelInicio,PanelesOcultar);
       
        JFXButton[] botones={ExamenesMedicos};
        GestorEscenas.pintarBotones(Inicio, botones);
        
        ImageView IconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-inicio-blanco.png")));
        Inicio.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenExamenesMedicos);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(ExamenesMedicos);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void RegistrarExamenMedico()
    {
        String Direccion = "/interfaz_usuario/medico/menu_auxiliares/registrar/registrar-examen-medico.fxml";
        Stage Padre = (Stage) Registrar.getScene().getWindow();
        
        try {
            GestorEscenas.cargarPanelAuxiliar(Padre, Direccion, true, "Registrar examen medico");
        } catch (Exception ex) {
            //CAPTURAR ERROR
        }
    }

    @Override
    public void Iniciar(MenuEstadisticas MenuEstadisticas) {
        
        LabelUsuarioNombre.setText(GestorEscenas.abreviarNombre(Autentificador.Usuario.getNombre()));
        Tooltip MouseNombre= new Tooltip(Autentificador.Usuario.getNombre());
        MouseNombre.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        LabelUsuarioNombre.setTooltip(MouseNombre);
        LabelUsuarioNombre.setMaxWidth(100);
        LabelCorreoUsuario.setText(GestorEscenas.seguridadCorreo(Autentificador.Usuario.getCorreo()));
        
    }

    @Override
    protected void CargarEstadisticas(MenuEstadisticas MenuEstadisticas) {
        
    }

}
