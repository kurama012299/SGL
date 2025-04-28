/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.medico.controladores;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private Pane PanelDatos;
    
    @FXML
    public void initialize() {
        System.out.println("Controlador Medico Iniciado");
    }
    
    @FXML
    public void TranscisionExamenesMedico() {
        
    }

}
