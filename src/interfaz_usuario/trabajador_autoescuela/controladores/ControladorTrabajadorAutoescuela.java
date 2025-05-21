/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.trabajador_autoescuela.controladores;

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
import logica.examen_conduccion.modelos.ExamenConduccion;

/**
 *
 * @author Kristian Aguila
 */
public class ControladorTrabajadorAutoescuela extends Controlador{
    
    @FXML private HBox hbVentanaPrincipal;
    
    @FXML private JFXButton jfxbtnInicio;
    
    @FXML private JFXButton jfxbtnExamenesTeoricos;
    
    @FXML private JFXButton jfxbtnExamenesPracticos;
    
    @FXML private Button btnRegistrarPractico;
    
    @FXML private Button btnRegistrarTeorico;
    
    @FXML private Pane pnlInicio;
    
    @FXML private Pane pnlExamenesTeoricos;
    
    @FXML private Pane pnlExamenesPracticos;
    
    @FXML private ProgressBar pbarAprobado;
    
    @FXML private Label lblProgresoAprobado;
    
    @FXML private ProgressBar pbarReprobado;
    
    @FXML private Label lblProgresoReprobado;
    
    @FXML private ProgressBar pbarTeorico;
    
    @FXML private Label lblProgresoTeorico;
    
    @FXML private ProgressBar pbarPractico;
    
    @FXML private Label lblProgresoPractico;
    
    @FXML private JFXButton jfxbtnCerrarSesion;
    
    @FXML private TableView<ExamenConduccion>tblExamenesTeoricos;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcFotoTeorico;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcExaminadoTeorico;
    
    @FXML private TableColumn<ExamenConduccion, Date>tblcFechaTeorico;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcResultadoTeorico;
    
    @FXML private TableColumn<ExamenConduccion, String> tblcAutoescuelaTeorico;

    @FXML private TableColumn<ExamenConduccion, String>tblcDetallesTeorico;
    
    @FXML private TableView<ExamenConduccion>tblExamenesPracticos;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcFotoPractico;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcExaminadoPractico;
    
    @FXML private TableColumn<ExamenConduccion, Date>tblcFechaPractico;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcResultadoPractico;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcAutoescuelaPractico;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcDetallesPractico;
    
    @FXML private Label lblUsuarioNombre;
    
    @FXML private Label lblCorreoUsuario;
    
    @FXML private Label lblFechaHora;
       
    private ImageView ivTeorico;
    private ImageView ivPractico;
    private ImageView ivInicio;
   
    //Funcion de inicio del menu
    @FXML public void initialize() {
        
        System.out.println("Controlador TrabajadorAutoescuela Iniciado");
        
        GestorEscenas.ponerIconoVentana(hbVentanaPrincipal, "Trabajador autoescuela");
         
        ivTeorico = (ImageView) jfxbtnExamenesTeoricos.getGraphic();
        ivPractico = (ImageView) jfxbtnExamenesPracticos.getGraphic();
        ivInicio = (ImageView) jfxbtnInicio.getGraphic();
        
        GestorEscenas.configurarReloj(lblFechaHora);
        
        jfxbtnCerrarSesion.setOnAction(e ->
        {
            GestorEscenas.cerrarPrograma();
        });
        
        Label[] porcentajesBarra = {lblProgresoAprobado,lblProgresoReprobado,lblProgresoTeorico,lblProgresoPractico};
        ProgressBar[] barrasProgreso = {pbarAprobado,pbarReprobado,pbarTeorico,pbarPractico};
        GestorEscenas.progresoLabel(porcentajesBarra, barrasProgreso);
        
        JFXButton[] botonesConsumirTecla={jfxbtnInicio,jfxbtnExamenesTeoricos,jfxbtnExamenesPracticos};
        GestorEscenas.consumirTecla(botonesConsumirTecla);
        
        System.out.println("Controlador TrabajadorAutoescuela Iniciado");
        this.transcisionInicio();
        GestorEscenas.ponerIconoVentana(hbVentanaPrincipal, "Trabajador autoescuela");
    }
            
    //Funcion para hacer la transicion de un menu a otro en este caso(ExamenesTeoricos)
    @FXML public void transcisionExamenesTeoricos() {
        
        GestorTablas.configurarColumnasExamenesTraAutoescuela(tblcFotoTeorico, tblcExaminadoTeorico, tblcFechaTeorico, tblcResultadoTeorico, tblcAutoescuelaTeorico, tblcDetallesTeorico);
        GestorTablas.cargarTablaExamenesTeoricosTraAutoescuela(tblExamenesTeoricos, Autentificador.usuario.getId());
        
        Pane[] panelesOcultar = {pnlExamenesPracticos, pnlInicio};
        GestorEscenas.mostrarOcultarPaneles(pnlExamenesTeoricos, panelesOcultar);
        
        JFXButton[] botones = {jfxbtnInicio, jfxbtnExamenesPracticos};
        GestorEscenas.pintarBotones(jfxbtnExamenesTeoricos, botones);
        
        ImageView ivIconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-examen-teorico-blanco.png")));
        jfxbtnExamenesTeoricos.setGraphic(ivIconoActivo);

        ArrayList<ImageView> imagenesCambiar = new ArrayList() {
            {
                add(ivInicio);
                add(ivPractico);
            }
        };
        ArrayList<JFXButton> botonesCambiar = new ArrayList() {
            {
                add(jfxbtnInicio);
                add(jfxbtnExamenesPracticos);
            }
        };
        GestorEscenas.cambiarIconos(imagenesCambiar, botonesCambiar);
    }
    
    //Funcion para hacer la transicion de un menu a otro en este caso(ExamenesPracticos)
    @FXML public void transcisionExamenesPracticos() {
        
        GestorTablas.configurarColumnasExamenesTraAutoescuela(tblcFotoPractico, tblcExaminadoPractico, tblcFechaPractico, tblcResultadoPractico, tblcAutoescuelaPractico, tblcDetallesPractico);
        GestorTablas.cargarTablaExamenesPracticosTraAutoescuela(tblExamenesPracticos, Autentificador.usuario.getId());
        
        Pane[] panelesOcultar={pnlExamenesTeoricos,pnlInicio};
        GestorEscenas.mostrarOcultarPaneles(pnlExamenesPracticos,panelesOcultar);
        
        JFXButton[] botones={jfxbtnInicio,jfxbtnExamenesTeoricos};
        GestorEscenas.pintarBotones(jfxbtnExamenesPracticos, botones);
        
        ImageView ivIconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-chofer-blanco.png")));
        jfxbtnExamenesPracticos.setGraphic(ivIconoActivo);  
        
        ArrayList<ImageView>imagenesCambiar= new ArrayList(){{
                add(ivInicio);
                add(ivTeorico);
                }};
        ArrayList<JFXButton>botonesCambiar= new ArrayList(){{
                add(jfxbtnInicio);
                add(jfxbtnExamenesTeoricos);
                }};
        GestorEscenas.cambiarIconos(imagenesCambiar, botonesCambiar);
        
    }
    
    //Funcion para hacer la transicion de un menu a otro en este caso(Inicio)
    @FXML public void transcisionInicio() {
        
        Pane[] panelesOcultar={pnlExamenesPracticos,pnlExamenesTeoricos};
        GestorEscenas.mostrarOcultarPaneles(pnlInicio,panelesOcultar);
        
        JFXButton[] botones={jfxbtnExamenesTeoricos,jfxbtnExamenesPracticos};
        GestorEscenas.pintarBotones(jfxbtnInicio, botones);
        
        ImageView ivIconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-inicio-blanco.png")));
        jfxbtnInicio.setGraphic(ivIconoActivo);  
        
        ArrayList<ImageView>imagenesCambiar= new ArrayList(){{
                add(ivPractico);
                add(ivTeorico);
                }};
        ArrayList<JFXButton>botonesCambiar= new ArrayList(){{
                add(jfxbtnExamenesPracticos);
                add(jfxbtnExamenesTeoricos);
                }};
        GestorEscenas.cambiarIconos(imagenesCambiar, botonesCambiar);    
    }
    
    //Funcion para aparecer el menu de RegistrarExamen en este caso(Practico)
    @FXML public void registrarExamenPractico()
    {
        String direccion = "/interfaz_usuario/trabajador_autoescuela/menu_auxiliares/registrar/registrar-examen-practico.fxml";
        Stage padre = (Stage) btnRegistrarPractico.getScene().getWindow();
        try {
            GestorEscenas.cargarPanelAuxiliar(padre, direccion, true, "Registrar examen practico");
        } catch (Exception ex) {
            //CAPTURAR ERROR
        }
    }
    
    //Funcion para aparecer el menu de RegistrarExamen en este caso(Teorico)
    @FXML public void registrarExamenTeorico()
    {
        String direccion = "/interfaz_usuario/trabajador_autoescuela/menu_auxiliares/registrar/registrar-examen-teorico.fxml";
        Stage padre = (Stage) btnRegistrarTeorico.getScene().getWindow();
        
        try {
            GestorEscenas.cargarPanelAuxiliar(padre, direccion, true, "Registrar examen teorico");
        } catch (Exception ex) {
            //CAPTURAR ERROR
        }
    }

    
    @Override
    public void Iniciar(MenuEstadisticas menuEstadisticas) {
        
        lblUsuarioNombre.setText(GestorEscenas.abreviarNombre(Autentificador.usuario.getNombre()));
        Tooltip mouseNombre= new Tooltip(Autentificador.usuario.getNombre());
        mouseNombre.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        lblUsuarioNombre.setTooltip(mouseNombre);
        lblUsuarioNombre.setMaxWidth(100);
        lblCorreoUsuario.setText(GestorEscenas.seguridadCorreo(Autentificador.usuario.getCorreo()));
    }

    @Override
    protected void CargarEstadisticas(MenuEstadisticas menuEstadisticas) {
        
    }
    
   
    
}
    
    

