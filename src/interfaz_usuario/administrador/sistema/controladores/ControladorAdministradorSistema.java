/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.sistema.controladores;

import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorAdministradorSistema {
    
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
    private Button Clinica;
    
    @FXML
    private Button Autoescuela;
    
    @FXML
    private Button Entidades;
    
    @FXML
    private Button Reportes;
    
    @FXML
    private Pane PanelInicio;
    
    @FXML
    private Pane PanelExamenes;
    
    @FXML
    private Pane PanelInfracciones;
    
    @FXML
    private Pane PanelEntidades;
    
    @FXML
    private Pane PanelClinica;
    
    @FXML
    private Pane PanelAutoescuela;
    
    @FXML
    private Pane PanelLicencias;
    
    @FXML
    private Pane PanelConductores;
    
    @FXML
    private Button RegistrarAutoescuela;
    
    @FXML
    private Button RegistrarClinica;
    
    @FXML
    private Button RegistrarInfracciones;
    
    @FXML
    private Button RegistrarExamen;
    
    @FXML
    private Button RegistrarConductores;
    
    @FXML
    private Button RegistrarLicencia;
    
    
    @FXML
    public void initialize()
    {
        System.out.println("Controlador Administrador Sistema Iniciado");
    }
    
    
    @FXML
    public void TransicionLicencias()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelInicio, PanelConductores, PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelLicencias,PanelesOcultar);
    }
    
    @FXML
    public void TransicionConductores()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelInicio, PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelConductores,PanelesOcultar);
    }
    
    @FXML
    public void TransicionInicio()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelInicio,PanelesOcultar);
    }
    
    @FXML
    public void TransicionExamenes()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelInicio, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenes,PanelesOcultar);
    }
    
    @FXML
    public void TransicionInfracciones()
    {
        Pane[] PanelesOcultar={PanelInicio, PanelLicencias, PanelConductores, PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelInfracciones,PanelesOcultar);
    }
    
    @FXML
    public void TransicionClinica()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelExamenes, PanelInicio, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelClinica,PanelesOcultar);
        
    }
    
    @FXML
    public void TransicionAutoescuela()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelExamenes, PanelClinica, PanelInicio, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelAutoescuela,PanelesOcultar);
    }
    
    @FXML
    public void TransicionEntidades()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelExamenes, PanelClinica, PanelAutoescuela, PanelInicio};
        GestorEscenas.MostrarOcultarPaneles(PanelEntidades,PanelesOcultar);
    }
    
    
    @FXML
    public void TransicionRegistrarAutoescuela()
    {
        System.out.println("Pulso");
        String Direccion = "/interfaz_usuario/administrador/sistema/menu-auxiliares/registrar/registrar-autoescuela.fxml";
        Stage Padre = (Stage) RegistrarAutoescuela.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar Autoescuela");
        } catch (Exception ex) {
            //CAPTURAR ERROR
        }
    }
    
    
    @FXML
    public void TransicionRegistrarClinica()
    {
        String Direccion = "/interfaz_usuario/administrador/sistema/menu-auxiliares/registrar/registrar-clinica.fxml";
        Stage Padre = (Stage) RegistrarClinica.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar Clinica");
        } catch (Exception ex) {
            //CAPTURAR ERROR
           
        }
    }
    
    @FXML
    public void TransicionRegistrarInfracciones()
    {
        String Direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/registrar/registrar-infraccion.fxml";
        Stage Padre = (Stage) RegistrarInfracciones.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar Infraccion");
        } catch (Exception ex) {
            //CAPTURAR ERROR
           
        }
    }
    
    @FXML
    public void TransicionRegistrarExamen()
    {
        String Direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/registrar/registrar-examen.fxml";
        Stage Padre = (Stage) RegistrarExamen.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar examen");
        } catch (Exception ex) {
            //CAPTURAR ERROR
           
        }
    }
    
    @FXML
    public void TransicionRegistrarConductores()
    {
        String Direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/registrar/registrar-conductor.fxml";
        Stage Padre = (Stage) RegistrarConductores.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar Conductor");
        } catch (Exception ex) {
            //CAPTURAR ERROR
           
        }
    }
    
    @FXML
    public void TransicionRegistrarLicencia()
    {
        String Direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/registrar/registrar-licencia.fxml";
        Stage Padre = (Stage) RegistrarLicencia.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar Licencia");
        } catch (Exception ex) {
            //CAPTURAR ERROR
           
        }
    }
    
}
