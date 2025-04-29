/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.trabajador_autoescuela.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import gestor_interfaces.GestorEscenas;

/**
 *
 * @author Kristian Aguila
 */
public class ControladorTrabajadorAutoescuela {
    
    @FXML
    private Button Inicio;
    
    @FXML
    private Button ExamenesTeoricos;
    
    @FXML
    private Button ExamenesPracticos;
    
    @FXML
    private Pane PanelInicio;
    
    @FXML
    private Pane PanelExamenesTeoricos;
    
    @FXML
    private Pane PanelExamenesPracticos;
    
    
    @FXML
    public void initialize() {
        System.out.println("Controlador TrabajadorAutoescuela Iniciado");
        PanelInicio.setVisible(true);
    }
                 
    @FXML
    public void TranscisionExamenesTeoricos() {
        Pane[] PanelesOcultar={PanelExamenesPracticos,PanelInicio};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenesTeoricos,PanelesOcultar);
    }
    
    @FXML
    public void TranscisionExamenesPracticos() {
        Pane[] PanelesOcultar={PanelExamenesTeoricos,PanelInicio};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenesPracticos,PanelesOcultar);
    }
    
    @FXML
    public void TranscisionInicio() {
        Pane[] PanelesOcultar={PanelExamenesPracticos,PanelExamenesTeoricos};
        GestorEscenas.MostrarOcultarPaneles(PanelInicio,PanelesOcultar);
    }
}
