/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.medico.controladores;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorMedico {
    
    @FXML
    private Button Inicio;
    
    @FXML
    private Button ExamenesMedicos;
    
    @FXML
    private Pane PanelInicio;
    
    @FXML
    private Pane PanelExamenes;
    
    @FXML
    public void initialize() {
        System.out.println("Controlador Medico Iniciado");
    }
    
    @FXML
    public void TranscisionExamenesMedico() {
        PanelInicio.setVisible(false);
        PanelExamenes.setVisible(true);
    }
    
    @FXML
    public void TranscisionInicio() {
        PanelInicio.setVisible(true);
        PanelExamenes.setVisible(false);
    }

}
