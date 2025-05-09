/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.medico.controladores;

import com.jfoenix.controls.JFXButton;
import gestor_interfaces.GestorEscenas;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorAdministradorMedico {
    
    @FXML
    private JFXButton Inicio;
    
    @FXML
    private JFXButton Examenes;
    
    @FXML
    private Button Registrar;
    
    @FXML
    private Pane PanelInicio;
    
    @FXML
    private Pane PanelExamenes;
    
    @FXML
    private HBox VentanaPrincipal;
    
    @FXML
    private Label EtiquetaRol;
    
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
    
    
    private ImageView ImagenExamenes;
    private ImageView ImagenInicio;
    
    public void initialize() {
        
        
        ImagenInicio = (ImageView) Inicio.getGraphic();
        ImagenExamenes = (ImageView) Examenes.getGraphic();
        
        BotonCerrarSesion.setOnAction(e ->
        {
            GestorEscenas.CerrarPrograma();
        });
        
        JFXButton[] BotonesConsumirTecla={Inicio,Examenes};
        GestorEscenas.ConsumirTecla(BotonesConsumirTecla);
        
        GestorEscenas.SaltoLineaEtiqueta(EtiquetaRol);
        
        
        Label[] PorcentajesBarra = {LabelProgresoAprobado,LabelProgresoReprobado,LabelProgresoAprobadoR,LabelProgresoJoven,LabelProgresoAdulto,LabelProgresoAnciano};
        ProgressBar[] BarrasProgreso = {BarraProgresoAprobado,BarraProgresoReprobado,BarraProgresoAprobadoR,BarraProgresoJoven,BarraProgresoAdulto,BarraProgresoAnciano};
        GestorEscenas.ProgresoLabel(PorcentajesBarra, BarrasProgreso);
        
        System.out.println("Controlador Administrador Medico Iniciado");
        this.TransicionInicio();
    }

    @FXML
    public void TransicionInicio()
    {
        Pane[] PanelesOcultar={PanelExamenes};
        GestorEscenas.MostrarOcultarPaneles(PanelInicio, PanelesOcultar);
        JFXButton[] botones = {Examenes};
        GestorEscenas.PintarBotones(Inicio, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-inicio-blanco.png")));
        Inicio.setGraphic(IconoActivo);
        
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenExamenes);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Examenes);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionExamenes()
    {
        Pane[] PanelesOcultar={PanelInicio};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenes, PanelesOcultar);
        JFXButton[] botones = {Inicio};
        GestorEscenas.PintarBotones(Examenes, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-examen-blanco.png")));
        Examenes.setGraphic(IconoActivo);
        
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInicio);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Inicio);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionRegistrarExamenes()
    {
        String Direccion = "/interfaz_usuario/administrador/medico/menu_auxiliares/registrar/registrar-examen-medico.fxml";
        Stage Padre = (Stage) Registrar.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar examen");
        } catch (Exception ex) {
            //CAPTURAR ERROR
           
        }
    }
    
    
}
