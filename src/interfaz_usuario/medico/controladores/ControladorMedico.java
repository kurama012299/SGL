/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.medico.controladores;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import gestor_interfaces.GestorEscenas;
import javafx.stage.Stage;

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
    private Button Registrar;
    
    @FXML
    public void initialize() {
        System.out.println("Controlador Medico Iniciado");
    }
    
    @FXML
    public void TranscisionExamenesMedico() {
        Pane[] PanelesOcultar={PanelInicio};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenes,PanelesOcultar);
    }
    
    @FXML
    public void TranscisionInicio() {
       Pane[] PanelesOcultar={PanelExamenes};
       GestorEscenas.MostrarOcultarPaneles(PanelInicio,PanelesOcultar);
    }
    
    @FXML
    public void RegistrarExamenMedico()
    {
        String Direccion = "/interfaz_usuario/medico/menu_auxiliares/registrar/registrar-examen-medico.fxml";
        Stage Padre = (Stage) Registrar.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar examen medico");
        } catch (Exception ex) {
            //CAPTURAR ERROR
        }
    }

}
