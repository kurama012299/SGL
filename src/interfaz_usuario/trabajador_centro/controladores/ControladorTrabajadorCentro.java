/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.trabajador_centro.controladores;

import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorTrabajadorCentro {
    
    @FXML
    private Button Inicio;
    
    @FXML
    private Button Licencias;
    
    @FXML
    private Button Conductores;
    
    @FXML
    private Button Infracciones;
    
    @FXML
    private Button Examenes;
    
    @FXML
    private Button Reportes;
    
    @FXML
    private Pane PanelInicio;
    
    @FXML
    private Pane PanelExamenes;
    
    @FXML
    private Pane PanelInfracciones;
    
    @FXML
    private Pane PanelLicencias;
    
    @FXML
    private Pane PanelConductores;
        
    @FXML
    public void initialize()
    {
        System.out.println("Controlador Administrador Trabajador Centro Iniciado");
    }
    
    
    @FXML
    public void TransicionLicencias()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelInicio, PanelConductores, PanelExamenes};
        GestorEscenas.MostrarOcultarPaneles(PanelLicencias,PanelesOcultar);
    }
    
    @FXML
    public void TransicionConductores()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelInicio, PanelExamenes};
        GestorEscenas.MostrarOcultarPaneles(PanelConductores,PanelesOcultar);
    }
    
    @FXML
    public void TransicionInicio()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelExamenes};
        GestorEscenas.MostrarOcultarPaneles(PanelInicio,PanelesOcultar);
    }
    
    @FXML
    public void TransicionExamenes()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelInicio};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenes,PanelesOcultar);
    }
    
    @FXML
    public void TransicionInfracciones()
    {
        Pane[] PanelesOcultar={PanelInicio, PanelLicencias, PanelConductores, PanelExamenes};
        GestorEscenas.MostrarOcultarPaneles(PanelInfracciones,PanelesOcultar);
    }
    
}
