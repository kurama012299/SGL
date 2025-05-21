/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.medico.controladores;

import com.jfoenix.controls.JFXButton;
import gestor_interfaces.GestorEscenas;
import gestor_interfaces.modelos.Controlador;
import gestor_interfaces.modelos.MenuEstadisticas;
import gestor_tablas.GestorTablas;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logica.autentificacion.Autentificador;
import logica.examen_medico.modelos.ExamenMedico;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorAdministradorMedico extends Controlador{
    
    @FXML private JFXButton jfxbtnInicio;
    
    @FXML private JFXButton jfxbtnExamenes;
    
    @FXML private Button btnRegistrar;
    
    @FXML private Pane pnlInicio;
    
    @FXML private Pane pnlExamenes;
    
    @FXML private HBox hbVentanaPrincipal;
    
    @FXML private ProgressBar pbarAprobado;
    
    @FXML private ProgressBar pbarReprobado;
    
    @FXML private ProgressBar pbarAprobadoR;
    
    @FXML private ProgressBar pbarJoven;
    
    @FXML private ProgressBar pbarAdulto;
    
    @FXML private ProgressBar pbarAnciano;
    
    @FXML private Label lblProgresoAprobado;
    
    @FXML private Label lblProgresoReprobado;
    
    @FXML private Label lblProgresoAprobadoR;
    
    @FXML private Label lblProgresoJoven;
    
    @FXML private Label lblProgresoAdulto;
    
    @FXML private Label lblProgresoAnciano;
    
    @FXML private JFXButton jfxbtnCerrarSesion;
    
    @FXML private TableView<ExamenMedico>tblExamenesMedico;
    
    @FXML private TableColumn<ExamenMedico, String>tblcFoto;
    
    @FXML private TableColumn<ExamenMedico, String>tblcExaminado;
    
    @FXML private TableColumn<ExamenMedico, Date>tblcFecha;
    
    @FXML private TableColumn<ExamenMedico, String>tblcExaminador;
    
    @FXML private TableColumn<ExamenMedico, String>tblcResultado;
    
    @FXML private TableColumn<ExamenMedico, String>tblcClinica;
    
    @FXML private TableColumn<ExamenMedico, String>tblcDetalles;
    
    @FXML private Label lblUltimoInicioSesion;
    
    @FXML private Label lblIniciosSesion;
    
    @FXML private Label lblUsuarioNombre;
    
    @FXML private Label lblCorreoUsuario;
    
    @FXML private Label lblFechaHora;
    
    private ImageView ivImagenExamenes;
    private ImageView ivImagenInicio;
    
    public void initialize() {
               
        ivImagenInicio = (ImageView) jfxbtnInicio.getGraphic();
        ivImagenExamenes = (ImageView) jfxbtnExamenes.getGraphic();

        
        jfxbtnCerrarSesion.setOnAction(e ->
        {
            GestorEscenas.cerrarPrograma();
        });
        
        JFXButton[] botonesConsumirTecla={jfxbtnInicio,jfxbtnExamenes};
        GestorEscenas.consumirTecla(botonesConsumirTecla);
        
        GestorEscenas.configurarReloj(lblFechaHora);
        
        Label[] porcentajesBarra = {lblProgresoAprobado,lblProgresoReprobado,lblProgresoAprobadoR,lblProgresoJoven,lblProgresoAdulto,lblProgresoAnciano};
        ProgressBar[] barrasProgreso = {pbarAprobado,pbarReprobado,pbarAprobadoR,pbarJoven,pbarAdulto,pbarAnciano};
        GestorEscenas.progresoLabel(porcentajesBarra, barrasProgreso);
        
        System.out.println("Controlador Administrador Medico Iniciado");
        this.transicionInicio();
    }

    @FXML public void transicionInicio()
    {
        Pane[] panelesOcultar={pnlExamenes};
        GestorEscenas.mostrarOcultarPaneles(pnlInicio, panelesOcultar);
        JFXButton[] botones = {jfxbtnExamenes};
        GestorEscenas.pintarBotones(jfxbtnInicio, botones);
        ImageView ivIconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-inicio-blanco.png")));
        jfxbtnInicio.setGraphic(ivIconoActivo);
        
        
        ArrayList<ImageView>imagenesCambiar= new ArrayList(){{
                add(ivImagenExamenes);
                }};
        ArrayList<JFXButton>botonesCambiar= new ArrayList(){{
                add(jfxbtnExamenes);
                }};
        GestorEscenas.cambiarIconos(imagenesCambiar, botonesCambiar);
    }
    
    @FXML public void transicionExamenes()
    {
        GestorTablas.configurarColumnasExamenesMedicosAdminMedico(tblcFoto, tblcExaminado, tblcFecha, tblcExaminador, tblcResultado, tblcClinica, tblcDetalles);
        GestorTablas.cargarTablaExamenesMedicosAdminMedico(tblExamenesMedico);
        
        Pane[] panelesOcultar={pnlInicio};
        GestorEscenas.mostrarOcultarPaneles(pnlExamenes, panelesOcultar);
        JFXButton[] botones = {jfxbtnInicio};
        GestorEscenas.pintarBotones(jfxbtnExamenes, botones);
        ImageView ivIconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-examen-blanco.png")));
        jfxbtnExamenes.setGraphic(ivIconoActivo);
        
        
        ArrayList<ImageView>imagenesCambiar= new ArrayList(){{
                add(ivImagenInicio);
                }};
        ArrayList<JFXButton>botonesCambiar= new ArrayList(){{
                add(jfxbtnInicio);
                }};
        GestorEscenas.cambiarIconos(imagenesCambiar, botonesCambiar);
    }
    
    @FXML public void transicionRegistrarExamenes()
    {
        String direccion = "/interfaz_usuario/administrador/medico/menu_auxiliares/registrar/registrar-examen-medico.fxml";
        Stage padre = (Stage) btnRegistrar.getScene().getWindow();
        
        try {
            GestorEscenas.cargarPanelAuxiliar(padre, direccion, true, "Registrar examen");
        } catch (Exception ex) {
            //CAPTURAR ERROR
           
        }
    }

    @Override public void Iniciar(MenuEstadisticas menuEstadisticas) 
    {
        lblUsuarioNombre.setText(GestorEscenas.abreviarNombre(Autentificador.usuario.getNombre()));
        Tooltip mouseNombre= new Tooltip(Autentificador.usuario.getNombre());
        mouseNombre.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        lblUsuarioNombre.setTooltip(mouseNombre);
        lblUsuarioNombre.setMaxWidth(100);
        lblCorreoUsuario.setText(GestorEscenas.seguridadCorreo(Autentificador.usuario.getCorreo()));
    }

    @Override protected void CargarEstadisticas(MenuEstadisticas menuEstadisticas) {
        
    }
    
    
}
