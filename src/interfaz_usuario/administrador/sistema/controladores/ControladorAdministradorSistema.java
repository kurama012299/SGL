/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.sistema.controladores;

import com.jfoenix.controls.JFXButton;
import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorAdministradorSistema {
    
    @FXML
    private JFXButton Inicio;
    
    @FXML
    private JFXButton Licencias;
    
    @FXML
    private JFXButton Conductores;
    
    @FXML
    private JFXButton Infracciones;
    
    @FXML
    private JFXButton Examenes;
    
    @FXML
    private JFXButton Clinica;
    
    @FXML
    private JFXButton Autoescuela;
    
    @FXML
    private JFXButton Entidades;
    
    @FXML
    private JFXButton Reportes;
    
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
    
    private ImageView ImagenLicencias;
    private ImageView ImagenConductores;
    private ImageView ImagenInicio;
    private ImageView ImagenExamenes;
    private ImageView ImagenInfracciones;
    private ImageView ImagenReportes; 
    private ImageView ImagenAutoescuela;
    private ImageView ImagenClinica;
    private ImageView ImagenEntidades; 
    
    
    @FXML
    public void initialize()
    {
        ImagenLicencias = (ImageView) Licencias.getGraphic();
        ImagenConductores = (ImageView) Conductores.getGraphic();
        ImagenInicio = (ImageView) Inicio.getGraphic();
        ImagenExamenes = (ImageView) Examenes.getGraphic();
        ImagenInfracciones = (ImageView) Infracciones.getGraphic();
        ImagenReportes = (ImageView) Reportes.getGraphic();
        ImagenAutoescuela = (ImageView) Autoescuela.getGraphic();
        ImagenClinica = (ImageView) Clinica.getGraphic();
        ImagenEntidades = (ImageView) Entidades.getGraphic();
        JFXButton[] BotonesConsumirTecla = {Inicio, Examenes, Licencias, Conductores, Infracciones, Reportes, Autoescuela, Clinica, Entidades};
        GestorEscenas.ConsumirTecla(BotonesConsumirTecla);
        
        System.out.println("Controlador Administrador Sistema Iniciado");
        this.TransicionInicio();
    }
    
    
    @FXML
    public void TransicionLicencias()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelInicio, PanelConductores, PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelLicencias,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Examenes, Reportes, Autoescuela, Clinica, Entidades};
        GestorEscenas.PintarBotones(Licencias, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-licencia-blanco.png")));
        Licencias.setGraphic(IconoActivo);
        ImageView[] ImagenesCambiar = {ImagenInfracciones, ImagenInicio, ImagenConductores, ImagenExamenes, ImagenReportes, ImagenAutoescuela, ImagenClinica, ImagenEntidades};
        JFXButton[] BotonesCambiar = {Inicio, Conductores, Infracciones, Examenes, Reportes, Autoescuela, Clinica, Entidades};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionConductores()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelInicio, PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelConductores,PanelesOcultar);
        JFXButton[] botones = {Inicio, Licencias, Infracciones, Examenes, Reportes, Autoescuela, Clinica, Entidades};
        GestorEscenas.PintarBotones(Conductores, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-conductor-blanco.png")));
        Conductores.setGraphic(IconoActivo);
        ImageView[] ImagenesCambiar = {ImagenInfracciones, ImagenInicio, ImagenLicencias, ImagenExamenes, ImagenReportes, ImagenAutoescuela, ImagenClinica, ImagenEntidades};
        JFXButton[] BotonesCambiar = {Inicio, Licencias, Infracciones, Examenes, Reportes, Autoescuela, Clinica, Entidades};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionInicio()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelInicio,PanelesOcultar);
        JFXButton[] botones = {Licencias, Conductores, Infracciones, Examenes, Reportes,Autoescuela,Clinica,Entidades};
        GestorEscenas.PintarBotones(Inicio, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-inicio-blanco.png")));
        Inicio.setGraphic(IconoActivo);
        ImageView[] ImagenesCambiar = {ImagenInfracciones, ImagenLicencias, ImagenConductores, ImagenExamenes, ImagenReportes,ImagenAutoescuela,ImagenClinica, ImagenEntidades};
        JFXButton[] BotonesCambiar = {Licencias, Conductores, Infracciones, Examenes, Reportes, Autoescuela,Clinica,Entidades};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionExamenes()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelInicio, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenes,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Licencias, Reportes,Autoescuela,Clinica,Entidades};
        GestorEscenas.PintarBotones(Examenes, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-examen-blanco.png")));
        Examenes.setGraphic(IconoActivo);
        ImageView[] ImagenesCambiar = {ImagenAutoescuela,ImagenClinica,ImagenEntidades,ImagenInfracciones, ImagenInicio, ImagenConductores, ImagenLicencias, ImagenReportes};
        JFXButton[] BotonesCambiar = {Inicio, Conductores, Infracciones, Licencias,Autoescuela,Clinica,Entidades, Reportes};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionInfracciones()
    {
        Pane[] PanelesOcultar={PanelInicio, PanelLicencias, PanelConductores, PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelInfracciones,PanelesOcultar);
        JFXButton[] botones = {Inicio,Autoescuela,Clinica,Conductores,Entidades,Examenes,Licencias,Reportes};
        GestorEscenas.PintarBotones(Infracciones, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-infraccion-blanco.png")));
        Infracciones.setGraphic(IconoActivo);
        ImageView[] ImagenesCambiar = {ImagenInicio,ImagenAutoescuela,ImagenClinica,ImagenConductores,ImagenEntidades,ImagenExamenes,ImagenLicencias,ImagenReportes};
        JFXButton[] BotonesCambiar = {Inicio,Autoescuela,Clinica,Conductores,Entidades,Examenes,Licencias,Reportes};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionClinica()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelExamenes, PanelInicio, PanelAutoescuela, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelClinica,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Licencias, Reportes,Autoescuela,Entidades,Examenes};
        GestorEscenas.PintarBotones(Clinica, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-clinica-blanco.png")));
        Clinica.setGraphic(IconoActivo);
        ImageView[] ImagenesCambiar = {ImagenInfracciones,ImagenAutoescuela,ImagenExamenes,ImagenEntidades, ImagenInicio, ImagenConductores, ImagenLicencias, ImagenReportes};
        JFXButton[] BotonesCambiar = {Inicio, Conductores, Infracciones, Licencias, Reportes,Autoescuela,Entidades,Examenes};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
        
    }
    
    @FXML
    public void TransicionAutoescuela()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelExamenes, PanelClinica, PanelInicio, PanelEntidades};
        GestorEscenas.MostrarOcultarPaneles(PanelAutoescuela,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Licencias, Reportes,Clinica,Entidades,Examenes};
        GestorEscenas.PintarBotones(Autoescuela, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-autoescuela-blanco.png")));
        Autoescuela.setGraphic(IconoActivo);
        ImageView[] ImagenesCambiar = {ImagenInfracciones,ImagenClinica,ImagenExamenes,ImagenEntidades, ImagenInicio, ImagenConductores, ImagenLicencias, ImagenReportes};
        JFXButton[] BotonesCambiar = {Inicio, Conductores, Infracciones, Licencias, Reportes,Clinica,Entidades,Examenes};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionEntidades()
    {
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelExamenes, PanelClinica, PanelAutoescuela, PanelInicio};
        GestorEscenas.MostrarOcultarPaneles(PanelEntidades,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Licencias, Reportes,Autoescuela,Clinica,Examenes};
        GestorEscenas.PintarBotones(Entidades, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-entidades-blanco.png")));
        Entidades.setGraphic(IconoActivo);
        ImageView[] ImagenesCambiar = {ImagenInfracciones,ImagenAutoescuela,ImagenExamenes,ImagenClinica, ImagenInicio, ImagenConductores, ImagenLicencias, ImagenReportes};
        JFXButton[] BotonesCambiar = {Inicio, Conductores, Infracciones, Licencias, Reportes,Autoescuela,Clinica,Examenes};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionReportes() {
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Examenes, Licencias,Entidades,Clinica,Autoescuela};
        GestorEscenas.PintarBotones(Reportes, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-reporte-blanco.png")));
        Reportes.setGraphic(IconoActivo);
        ImageView[] ImagenesCambiar = {ImagenAutoescuela,ImagenClinica,ImagenEntidades,ImagenInfracciones, ImagenInicio, ImagenConductores, ImagenExamenes, ImagenLicencias};
        JFXButton[] BotonesCambiar = {Inicio, Conductores, Infracciones, Examenes, Licencias,Autoescuela,Entidades,Clinica};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
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
