/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.autoescuela.controladores;

import com.jfoenix.controls.JFXButton;
import gestor_interfaces.GestorEscenas;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logica.examen.modelos.Examen;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorAdministradorAutoescuela {

    @FXML
    private JFXButton Inicio;

    @FXML
    private JFXButton ExamenTeorico;

    @FXML
    private JFXButton ExamenPractico;

    @FXML
    private Pane PanelInicio;

    @FXML
    private Pane PanelExamenesTeoricos;

    @FXML
    private Pane PanelExamenesPracticos;

    @FXML
    private Button RegistrarExamenTeorico;

    @FXML
    private Button RegistrarExamenPractico;

    @FXML
    private Label EtiquetaRol;

    @FXML
    private HBox VentanaPrincipal;

    @FXML
    private ProgressBar BarraProgresoAprobado;

    @FXML
    private ProgressBar BarraProgresoReprobado;

    @FXML
    private Label LabelProgresoAprobado;

    @FXML
    private Label LabelProgresoReprobado;

    @FXML
    private ProgressBar BarraProgresoTeorico;

    @FXML
    private ProgressBar BarraProgresoPractico;

    @FXML
    private Label LabelProgresoTeorico;

    @FXML
    private Label LabelProgresoPractico;
    
    @FXML
    private JFXButton BotonCerrarSesion;
    
    @FXML
    private TableView<Examen>TablaExamenesPracticos;
    
    @FXML
    private TableColumn<Examen, String>ColumnaFotoPractico;
    
    @FXML
    private TableColumn<Examen, Date>ColumnaFechaPractico;
    
    @FXML
    private TableColumn<Examen, String>ColumnaExaminadorPractico;
    
    @FXML
    private TableColumn<Examen, String>ColumnaExaminadoPractico;
    
    @FXML
    private TableColumn<Examen, String>ColumnaResultadoPractico;
    
    @FXML
    private TableColumn<Examen, String>ColumnaAutoescuela;
    
    @FXML
    private TableColumn<Examen, String>ColumnaDetallesPractico;
    
    
    @FXML
    private TableView<Examen>TablaExamenesTeoricos;
    
    @FXML
    private TableColumn<Examen, String>ColumnaFotoTeorico;
    
    @FXML
    private TableColumn<Examen, Date>ColumnaFechaTeorico;
    
    @FXML
    private TableColumn<Examen, String>ColumnaExaminadorTeorico;
    
    @FXML
    private TableColumn<Examen, String>ColumnaExaminadoTeorico;
    
    @FXML
    private TableColumn<Examen, String>ColumnaResultadoTeorico;
    
    @FXML
    private TableColumn<Examen, String>ColumnaAutoescuelaTeorico;
    
    @FXML
    private TableColumn<Examen, String>ColumnaDetallesTeorico;
    
    
    
    

    private ImageView ImagenExamenesTeoricos;
    private ImageView ImagenExamenesPracticos;
    private ImageView ImagenInicio;

    @FXML
    public void initialize() {
        
        ImagenInicio = (ImageView) Inicio.getGraphic();
        ImagenExamenesTeoricos = (ImageView) ExamenTeorico.getGraphic();
        ImagenExamenesPracticos = (ImageView) ExamenPractico.getGraphic();

         
        BotonCerrarSesion.setOnAction(e ->
        {
            GestorEscenas.CerrarPrograma();
        });
        
        JFXButton[] BotonesConsumirTecla={Inicio,ExamenTeorico,ExamenPractico};
        GestorEscenas.ConsumirTecla(BotonesConsumirTecla);
        
        System.out.println("Controlador Administrador Autoescuela Iniciado");
        Label[] PorcentajesBarra = {LabelProgresoAprobado, LabelProgresoReprobado, LabelProgresoTeorico, LabelProgresoPractico};
        ProgressBar[] BarrasProgreso = {BarraProgresoAprobado, BarraProgresoReprobado, BarraProgresoTeorico, BarraProgresoPractico};
        GestorEscenas.ProgresoLabel(PorcentajesBarra, BarrasProgreso);
        
        
        this.TransicionInicio();
    }

    @FXML
    public void TransicionInicio() {
        Pane[] PanelesOcultar = {PanelExamenesTeoricos, PanelExamenesPracticos};
        GestorEscenas.MostrarOcultarPaneles(PanelInicio, PanelesOcultar);
        JFXButton[] botones={ExamenTeorico,ExamenPractico};
        GestorEscenas.PintarBotones(Inicio, botones);
        
        ImageView IconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-inicio-blanco.png")));
        Inicio.setGraphic(IconoActivo);  
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenExamenesPracticos);
                add(ImagenExamenesTeoricos);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(ExamenPractico);
                add(ExamenTeorico);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);   
    }

    @FXML
    public void TransicionExamenPractico() {
        
        GestorTablas.ConfigurarColumnasExamenesAdminAutoescuela(ColumnaFotoPractico, ColumnaExaminadoPractico, ColumnaFechaPractico, ColumnaExaminadorPractico, ColumnaResultadoPractico, ColumnaAutoescuela, ColumnaDetallesPractico);
        GestorTablas.CargarTablaExamenesPracticosAdminAutoescuela(TablaExamenesPracticos);
        
        
        Pane[] PanelesOcultar = {PanelInicio, PanelExamenesTeoricos};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenesPracticos, PanelesOcultar);
        JFXButton[] botones = {ExamenTeorico,Inicio};
        GestorEscenas.PintarBotones(ExamenPractico, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-chofer-blanco.png")));
        ExamenPractico.setGraphic(IconoActivo);
        
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenExamenesTeoricos);
                add(ImagenInicio);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(ExamenTeorico);
                add(Inicio);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }

    @FXML
    public void TransicionExamenTeorico() {
        
        GestorTablas.ConfigurarColumnasExamenesAdminAutoescuela(ColumnaFotoTeorico, ColumnaExaminadoTeorico, ColumnaFechaTeorico, ColumnaExaminadorTeorico, ColumnaResultadoTeorico, ColumnaAutoescuelaTeorico, ColumnaDetallesTeorico);
        GestorTablas.CargarTablaExamenesTeoricosAdminAutoescuela(TablaExamenesTeoricos);
        
        Pane[] PanelesOcultar = {PanelInicio, PanelExamenesPracticos};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenesTeoricos, PanelesOcultar);
        JFXButton[] botones = {ExamenPractico,Inicio};
        GestorEscenas.PintarBotones(ExamenTeorico, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-examen-teorico-blanco.png")));
        ExamenTeorico.setGraphic(IconoActivo);
        
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenExamenesPracticos);
                add(ImagenInicio);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(ExamenPractico);
                add(Inicio);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }

    @FXML
    public void TransicionRegistrarExamenPractico() {
        String Direccion = "/interfaz_usuario/administrador/autoescuela/menu_auxiliares/registrar/registrar-examen-practico.fxml";
        Stage Padre = (Stage) RegistrarExamenPractico.getScene().getWindow();

        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar examen practico");
        } catch (Exception ex) {
            //CAPTURAR ERROR
        }
    }

    @FXML
    public void TransicionRegistrarExamenTeorico() {
        String Direccion = "/interfaz_usuario/administrador/autoescuela/menu_auxiliares/registrar/registrar-examen-teorico.fxml";
        Stage Padre = (Stage) RegistrarExamenTeorico.getScene().getWindow();

        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar examen teorico");
        } catch (Exception ex) {
            //CAPTURAR ERROR

        }
    }
}
