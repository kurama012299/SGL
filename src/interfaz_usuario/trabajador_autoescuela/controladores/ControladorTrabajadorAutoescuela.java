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
    private HBox BoxRoot;
    
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
    private Label LabelRol;
       
    private ImageView ImagenTeorico;
    private ImageView ImagenPractico;
    private ImageView ImagenInicio;
   
    @FXML
    public void initialize() {
        
        System.out.println("Controlador TrabajadorAutoescuela Iniciado");
        ImagenTeorico = (ImageView) ExamenesTeoricos.getGraphic();
        ImagenPractico = (ImageView) ExamenesPracticos.getGraphic();
        ImagenInicio = (ImageView) Inicio.getGraphic();

        ConsumirEnter(Inicio);
        ConsumirEnter(ExamenesTeoricos);
        ConsumirEnter(ExamenesPracticos);

        SaltoLineaLabel(LabelRol);
        GestorEscenas.PonerIconoVentana(BoxRoot, "Trabajador autoescuela");

        Label[] PorcentajesBarra = {LabelProgresoAprobado,LabelProgresoReprobado,LabelProgresoTeorico,LabelProgresoPractico};
        ProgressBar[] BarrasProgreso = {ProgressbarAprobado,ProgressbarReprobado,ProgressbarTeorico,ProgressbarPractico};
        GestorEscenas.ProgresoLabel(PorcentajesBarra, BarrasProgreso);
        
        this.TranscisionInicio();
    }
                 
    @FXML
    public void TranscisionExamenesTeoricos() {
        Pane[] PanelesOcultar={PanelExamenesPracticos,PanelInicio};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenesTeoricos,PanelesOcultar);
        JFXButton[] botones={Inicio,ExamenesPracticos};
        GestorEscenas.PintarBotones(ExamenesTeoricos, botones);
        ImageView IconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-examen_teorico_blanco.png")));
        ExamenesTeoricos.setGraphic(IconoActivo); 
        ExamenesPracticos.setGraphic(ImagenPractico);
        Inicio.setGraphic(ImagenInicio);
    }
    
    @FXML
    public void TranscisionExamenesPracticos() {
        Pane[] PanelesOcultar={PanelExamenesTeoricos,PanelInicio};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenesPracticos,PanelesOcultar);
        JFXButton[] botones={Inicio,ExamenesTeoricos};
        GestorEscenas.PintarBotones(ExamenesPracticos, botones);
        ImageView IconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-chofer_blanco.png")));
        ExamenesPracticos.setGraphic(IconoActivo);  
        ExamenesTeoricos.setGraphic(ImagenTeorico);
        Inicio.setGraphic(ImagenInicio);
    }
    
    @FXML
    public void TranscisionInicio() {
        Pane[] PanelesOcultar={PanelExamenesPracticos,PanelExamenesTeoricos};
        GestorEscenas.MostrarOcultarPaneles(PanelInicio,PanelesOcultar);
        
        JFXButton[] botones={ExamenesTeoricos,ExamenesPracticos};
        GestorEscenas.PintarBotones(Inicio, botones);
        
        ImageView IconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-inicio_blanco.png")));
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
    public void ConsumirEnter(JFXButton boton)
    {
        boton.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
        if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
            event.consume(); 
        }
    });
    }
    
    @FXML
    public void SaltoLineaLabel(Label label)
    {
        label.setWrapText(true);
        label.setMaxWidth(100);
         
    }
    
    
}
    
    

