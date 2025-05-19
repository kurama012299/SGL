/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.medico.controladores;


import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import gestor_interfaces.GestorEscenas;
import gestor_interfaces.modelos.Controlador;
import gestor_interfaces.modelos.MenuEstadisticas;
import gestor_tablas.GestorTablas;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logica.autentificacion.Autentificador;
import logica.examen_medico.modelos.ExamenMedico;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorMedico extends Controlador{
    
    @FXML private HBox hbVentanaPrincipal;
    
    @FXML private JFXButton jfxbtnInicio;
    
    @FXML private JFXButton jfxbtnExamenesMedicos;
    
    @FXML private Pane pnlInicio;
    
    @FXML private Pane pnlExamenes;
    
    @FXML private Button btnRegistrar;
    
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
    
    @FXML private TableView<ExamenMedico>tblExamenesMedicos;
    
    @FXML private TableColumn<ExamenMedico, Date>tblcFecha;
    
    @FXML private TableColumn<ExamenMedico, String>tblcResultado;
    
    @FXML private TableColumn<ExamenMedico, String>tblcExaminado;
    
    @FXML private TableColumn<ExamenMedico, String>tblcClinica;
    
    @FXML private TableColumn<ExamenMedico, String>tblcDetalles;
    
    @FXML private TableColumn<ExamenMedico, String>tblcFoto;

    @FXML private Label lblUsuarioNombre;
    
    @FXML private Label lblCorreoUsuario;
    
    @FXML private Label lblFechaHora;
    
    private ImageView ivImagenInicio;
    private ImageView ivImagenExamenesMedicos;
    
    @FXML
    public void initialize() {
        System.out.println("Controlador Medico Iniciado");
        
        ivImagenInicio = (ImageView) jfxbtnInicio.getGraphic();
        ivImagenExamenesMedicos = (ImageView) jfxbtnExamenesMedicos.getGraphic();
        
        GestorEscenas.configurarReloj(lblFechaHora);
        
        jfxbtnCerrarSesion.setOnAction(e ->
        {
            GestorEscenas.cerrarPrograma();
        });
        
        JFXButton[] botonesConsumirTecla={jfxbtnInicio,jfxbtnExamenesMedicos};
        GestorEscenas.consumirTecla(botonesConsumirTecla);
        
        Label[] porcentajesBarra = {lblProgresoAprobado,lblProgresoReprobado,lblProgresoAprobadoR,lblProgresoJoven,lblProgresoAdulto,lblProgresoAnciano};
        ProgressBar[] barrasProgreso = {pbarAprobado,pbarReprobado,pbarAprobadoR,pbarJoven,pbarAdulto,pbarAnciano};
        GestorEscenas.progresoLabel(porcentajesBarra, barrasProgreso);
        this.transicionInicio();

    }
    
    @FXML
    public void transicionExamenesMedico() {
        
        GestorTablas.configurarColumnasExamenesMedicosMedicoUnico(tblcFoto,tblcExaminado, tblcFecha, tblcResultado, tblcClinica, tblcDetalles);
        GestorTablas.cargarTablaExamenesMedicosMedicoUnico(tblExamenesMedicos, Autentificador.Usuario.getId());
        
        Pane[] panelesOcultar={pnlInicio};
        GestorEscenas.mostrarOcultarPaneles(pnlExamenes,panelesOcultar);
        
        JFXButton[] botones={jfxbtnInicio};
        GestorEscenas.pintarBotones(jfxbtnExamenesMedicos, botones);
        
        ImageView ivIconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-examen-medico-blanco.png")));
        jfxbtnExamenesMedicos.setGraphic(ivIconoActivo);
        
        
        JFXButton[] botonesPonerNormal={jfxbtnInicio};
        GestorEscenas.pintarBotones(jfxbtnExamenesMedicos,botonesPonerNormal);
        
        
        ArrayList<ImageView>imagenesCambiar= new ArrayList(){{
                add(ivImagenInicio);
                }};
        ArrayList<JFXButton>botonesCambiar= new ArrayList(){{
                add(jfxbtnInicio);
                }};
        GestorEscenas.cambiarIconos(imagenesCambiar, botonesCambiar);
    }
    
    @FXML
    public void transicionInicio() {
       Pane[] panelesOcultar={pnlExamenes};
       GestorEscenas.mostrarOcultarPaneles(pnlInicio,panelesOcultar);
       
        JFXButton[] botones={jfxbtnExamenesMedicos};
        GestorEscenas.pintarBotones(jfxbtnInicio, botones);
        
        ImageView ivIconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-inicio-blanco.png")));
        jfxbtnInicio.setGraphic(ivIconoActivo);
        
        ArrayList<ImageView>imagenesCambiar= new ArrayList(){{
                add(ivImagenExamenesMedicos);
                }};
        ArrayList<JFXButton>botonesCambiar= new ArrayList(){{
                add(jfxbtnExamenesMedicos);
                }};
        GestorEscenas.cambiarIconos(imagenesCambiar, botonesCambiar);
    }
    
    @FXML
    public void registrarExamenMedico()
    {
        String direccion = "/interfaz_usuario/medico/menu_auxiliares/registrar/registrar-examen-medico.fxml";
        Stage padre = (Stage) btnRegistrar.getScene().getWindow();
        
        try {
            GestorEscenas.cargarPanelAuxiliar(padre, direccion, true, "Registrar examen medico");
        } catch (Exception ex) {
            //CAPTURAR ERROR
        }
    }

    @Override
    public void Iniciar(MenuEstadisticas menuEstadisticas) {
        
        lblUsuarioNombre.setText(GestorEscenas.abreviarNombre(Autentificador.Usuario.getNombre()));
        Tooltip mouseNombre= new Tooltip(Autentificador.Usuario.getNombre());
        mouseNombre.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        lblUsuarioNombre.setTooltip(mouseNombre);
        lblUsuarioNombre.setMaxWidth(100);
        lblCorreoUsuario.setText(GestorEscenas.seguridadCorreo(Autentificador.Usuario.getCorreo()));
        
    }

    @Override
    protected void CargarEstadisticas(MenuEstadisticas menuEstadisticas) {
        
    }

}
