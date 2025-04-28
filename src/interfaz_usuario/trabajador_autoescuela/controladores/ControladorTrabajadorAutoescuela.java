/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.trabajador_autoescuela.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

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
        PanelInicio.setVisible(false);
        PanelExamenesPracticos.setVisible(false);
        PanelExamenesTeoricos.setVisible(true);
    }
    
    @FXML
    public void TranscisionExamenesPracticos() {
        PanelInicio.setVisible(false);
        PanelExamenesPracticos.setVisible(true);
        PanelExamenesTeoricos.setVisible(false);
    }
    
    @FXML
    public void TranscisionInicio() {
        PanelInicio.setVisible(true);
        PanelExamenesTeoricos.setVisible(false);
        PanelExamenesPracticos.setVisible(false);
    }
}
