/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.medico.controladores;

import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorAdministradorMedico {
    
    @FXML
    private Button Inicio;
    
    @FXML
    private Button Examenes;
    
    @FXML
    private Button Registrar;
    
    @FXML
    private Pane PanelInicio;
    
    @FXML
    private Pane PanelExamenes;
    
    @FXML
    public void TransicionInicio()
    {
        Pane[] PanelesOcultar={PanelExamenes};
        GestorEscenas.MostrarOcultarPaneles(PanelInicio, PanelesOcultar);
    }
    
    @FXML
    public void TransicionExamenes()
    {
        Pane[] PanelesOcultar={PanelInicio};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenes, PanelesOcultar);
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
