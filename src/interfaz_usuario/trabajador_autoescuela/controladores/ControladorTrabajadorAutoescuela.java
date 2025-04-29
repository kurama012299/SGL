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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Kristian Aguila
 */
public class ControladorTrabajadorAutoescuela {
    
    
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
    
    private ImageView ImagenTeorico;
    private ImageView ImagenPractico;
    private ImageView ImagenInicio;
   
    @FXML
    public void initialize() {
        ImagenTeorico= (ImageView) ExamenesTeoricos.getGraphic();
        ImagenPractico= (ImageView) ExamenesPracticos.getGraphic();
        ImagenInicio= (ImageView) Inicio.getGraphic();
        
        System.out.println("Controlador TrabajadorAutoescuela Iniciado");
        PanelInicio.setVisible(true);
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
        System.out.println("Pulso");
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
        System.out.println("Pulso");
        String Direccion = "/interfaz_usuario/trabajador_autoescuela/menu_auxiliares/registrar/registrar-examen-teorico.fxml";
        Stage Padre = (Stage) RegistrarPractico.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar examen teorico");
        } catch (Exception ex) {
            //CAPTURAR ERROR
        }
    }
    
    
    
    
    
}
