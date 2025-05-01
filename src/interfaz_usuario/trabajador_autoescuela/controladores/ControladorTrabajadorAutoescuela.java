/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.trabajador_autoescuela.controladores;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import gestor_interfaces.GestorEscenas;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Kristian Aguila
 */
public class ControladorTrabajadorAutoescuela {
    
    @FXML
    private HBox VentanaPrincipal;
    
    @FXML
    private JFXButton Inicio;
    
    @FXML
    private JFXButton ExamenesTeoricos;
    
    @FXML
    private JFXButton ExamenesPracticos;
    
    @FXML 
    private Button RegistrarPractico;
    
    @FXML 
    private Button RegistrarTeorico;
    
    @FXML
    private Pane PanelInicio;
    
    @FXML
    private Pane PanelExamenesTeoricos;
    
    @FXML
    private Pane PanelExamenesPracticos;
    
    @FXML
    private ProgressBar ProgressbarAprobado;
    
    @FXML
    private Label LabelProgresoAprobado;
    
    @FXML
    private ProgressBar ProgressbarReprobado;
    
    @FXML
    private Label LabelProgresoReprobado;
    
    @FXML
    private ProgressBar ProgressbarTeorico;
    
    @FXML
    private Label LabelProgresoTeorico;
    
    @FXML
    private ProgressBar ProgressbarPractico;
    
    @FXML
    private Label LabelProgresoPractico;
    
    @FXML
    private Label EtiquetaRol;
       
    private ImageView ImagenTeorico;
    private ImageView ImagenPractico;
    private ImageView ImagenInicio;
   
    @FXML
    public void initialize() {
        
        System.out.println("Controlador TrabajadorAutoescuela Iniciado");
        ImagenTeorico = (ImageView) ExamenesTeoricos.getGraphic();
        ImagenPractico = (ImageView) ExamenesPracticos.getGraphic();
        ImagenInicio = (ImageView) Inicio.getGraphic();

        GestorEscenas.PonerIconoVentana(VentanaPrincipal, "Trabajador autoescuela");

        Label[] PorcentajesBarra = {LabelProgresoAprobado,LabelProgresoReprobado,LabelProgresoTeorico,LabelProgresoPractico};
        ProgressBar[] BarrasProgreso = {ProgressbarAprobado,ProgressbarReprobado,ProgressbarTeorico,ProgressbarPractico};
        GestorEscenas.ProgresoLabel(PorcentajesBarra, BarrasProgreso);
        
        JFXButton[] BotonesConsumirTecla={Inicio,ExamenesTeoricos,ExamenesPracticos};
        GestorEscenas.ConsumirTecla(BotonesConsumirTecla);
        
        SaltoLineaEtiqueta(EtiquetaRol);
        
        System.out.println("Controlador TrabajadorAutoescuela Iniciado");

        this.TranscisionInicio();
    }
                 
    @FXML
    public void TranscisionExamenesTeoricos() {
        Pane[] PanelesOcultar={PanelExamenesPracticos,PanelInicio};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenesTeoricos,PanelesOcultar);
        JFXButton[] botones={Inicio,ExamenesPracticos};
        GestorEscenas.PintarBotones(ExamenesTeoricos, botones);
        ImageView IconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-examen-teorico-blanco.png")));
        ExamenesTeoricos.setGraphic(IconoActivo);
        ImageView[]ImagenesCambiar={ImagenInicio,ImagenPractico};
        JFXButton[] BotonesCambiar={Inicio,ExamenesPracticos};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TranscisionExamenesPracticos() {
        Pane[] PanelesOcultar={PanelExamenesTeoricos,PanelInicio};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenesPracticos,PanelesOcultar);
        JFXButton[] botones={Inicio,ExamenesTeoricos};
        GestorEscenas.PintarBotones(ExamenesPracticos, botones);
        ImageView IconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-chofer-blanco.png")));
        ExamenesPracticos.setGraphic(IconoActivo);  
        ImageView[]ImagenesCambiar={ImagenInicio,ImagenTeorico};
        JFXButton[] BotonesCambiar={Inicio,ExamenesTeoricos};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
        
    }
    
    @FXML
    public void TranscisionInicio() {
        Pane[] PanelesOcultar={PanelExamenesPracticos,PanelExamenesTeoricos};
        GestorEscenas.MostrarOcultarPaneles(PanelInicio,PanelesOcultar);
        
        JFXButton[] botones={ExamenesTeoricos,ExamenesPracticos};
        GestorEscenas.PintarBotones(Inicio, botones);
        
        ImageView IconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-inicio-blanco.png")));
        Inicio.setGraphic(IconoActivo);  
        ExamenesTeoricos.setGraphic(ImagenTeorico);
        ExamenesPracticos.setGraphic(ImagenPractico);
    }
    
    @FXML
    public void RegistrarExamenPractico()
    {
        String Direccion = "/interfaz_usuario/trabajador_autoescuela/menu_auxiliares/registrar/registrar-examen-practico.fxml";
        Stage Padre = (Stage) RegistrarPractico.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar examen practico");
        } catch (Exception ex) {
            //CAPTURAR ERROR
        }
    }
    
     @FXML
    public void RegistrarExamenTeorico()
    {
        String Direccion = "/interfaz_usuario/trabajador_autoescuela/menu_auxiliares/registrar/registrar-examen-teorico.fxml";
        Stage Padre = (Stage) RegistrarPractico.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar examen teorico");
        } catch (Exception ex) {
            //CAPTURAR ERROR
        }
    }
    
    
    @FXML
    public void SaltoLineaEtiqueta(Label Etiqueta)
    {
        Etiqueta.setWrapText(true);
        Etiqueta.setMaxWidth(100);
         
    }
    
}
    
    

