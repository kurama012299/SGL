/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.autoescuela.controladores;

import com.jfoenix.controls.JFXButton;
import gestor_interfaces.GestorEscenas;
import gestor_interfaces.modelos.Controlador;
import gestor_interfaces.modelos.Estadistica;
import gestor_interfaces.modelos.MenuEstadisticas;
import gestor_tablas.GestorTablas;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logica.autentificacion.Autentificador;
import logica.examen_conduccion.modelos.ExamenConduccion;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorAdministradorAutoescuela extends Controlador{

    @FXML private JFXButton jfxbtnInicio;

    @FXML private JFXButton jfxbtnExamenTeorico;

    @FXML private JFXButton jfxbtnExamenPractico;

    @FXML private Pane pnlInicio;

    @FXML private Pane pnlExamenesTeoricos;

    @FXML private Pane pnlExamenesPracticos;

    @FXML private Button btnRegistrarExamenTeorico;

    @FXML private Button btnRegistrarExamenPractico;

    @FXML private HBox hbVentanaPrincipal;

    @FXML private ProgressBar pbarAprobado;

    @FXML private ProgressBar pbarReprobado;

    @FXML private Label lblProgresoAprobado;

    @FXML private Label lblProgresoReprobado;

    @FXML private ProgressBar pbarTeorico;

    @FXML private ProgressBar pbarPractico;

    @FXML private Label lblProgresoTeorico;

    @FXML private Label lblProgresoPractico;
    
    @FXML private JFXButton jfxbtnCerrarSesion;
    
    @FXML private TableView<ExamenConduccion>tblExamenesPracticos;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcFotoPractico;
    
    @FXML private TableColumn<ExamenConduccion, Date>tblcFechaPractico;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcExaminadorPractico;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcExaminadoPractico;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcResultadoPractico;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcAutoescuela;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcDetallesPractico;
    
    @FXML private TableView<ExamenConduccion>tblExamenesTeoricos;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcFotoTeorico;
    
    @FXML private TableColumn<ExamenConduccion, Date>tblcFechaTeorico;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcExaminadorTeorico;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcExaminadoTeorico;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcResultadoTeorico;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcAutoescuelaTeorico;
    
    @FXML private TableColumn<ExamenConduccion, String>tblcDetallesTeorico;
    
    @FXML private Label lblUsuarioNombre;
  
    @FXML private Label lblCorreoUsuario;
    
    @FXML private Label lblIniciosSesion;
    
    @FXML private Label lblUltimoInicioSesion;
    
    @FXML private Label lblTotalTrabajadores;
    
    @FXML private Label lblTotalExamenes;
    
    @FXML private Label lblIndiceAprobados;
   
    @FXML private Label lblExamenesPracticos;
    
    @FXML private Label lblExamenesTeoricos;
    
    @FXML private Label lblPorcientoAnteriorMes;
    
    @FXML private Label lblFechaHora;
    
    private ImageView ivImagenExamenesTeoricos;
    private ImageView ivImagenExamenesPracticos;
    private ImageView ivImagenInicio;

    @FXML public void initialize() {
        
        ivImagenInicio = (ImageView) jfxbtnInicio.getGraphic();
        ivImagenExamenesTeoricos = (ImageView) jfxbtnExamenTeorico.getGraphic();
        ivImagenExamenesPracticos = (ImageView) jfxbtnExamenPractico.getGraphic();

        GestorEscenas.configurarReloj(lblFechaHora);
        
        GestorEscenas.ponerIconoVentana(hbVentanaPrincipal, "Administrador autoescuela");
         
        jfxbtnCerrarSesion.setOnAction(e ->
        {
            GestorEscenas.cerrarPrograma();
        });
        
        JFXButton[] botonesConsumirTecla={jfxbtnInicio,jfxbtnExamenTeorico,jfxbtnExamenPractico};
        GestorEscenas.consumirTecla(botonesConsumirTecla);
        
        System.out.println("Controlador Administrador Autoescuela Iniciado");
        Label[] porcentajesBarra = {lblProgresoAprobado, lblProgresoReprobado, lblProgresoTeorico, lblProgresoPractico};
        ProgressBar[] barrasProgreso = {pbarAprobado, pbarReprobado, pbarTeorico, pbarPractico};
        GestorEscenas.progresoLabel(porcentajesBarra, barrasProgreso);
        
        
        this.transicionInicio();
    }

    @FXML public void transicionInicio() {
        Pane[] panelesOcultar = {pnlExamenesTeoricos, pnlExamenesPracticos};
        GestorEscenas.mostrarOcultarPaneles(pnlInicio, panelesOcultar);
        JFXButton[] botones={jfxbtnExamenTeorico,jfxbtnExamenPractico};
        GestorEscenas.pintarBotones(jfxbtnInicio, botones);
        
        ImageView ivIconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-inicio-blanco.png")));
        jfxbtnInicio.setGraphic(ivIconoActivo);  
        
        ArrayList<ImageView>imagenesCambiar= new ArrayList(){{
                add(ivImagenExamenesPracticos);
                add(ivImagenExamenesTeoricos);
                }};
        ArrayList<JFXButton>botonesCambiar= new ArrayList(){{
                add(jfxbtnExamenPractico);
                add(jfxbtnExamenTeorico);
                }};
        GestorEscenas.cambiarIconos(imagenesCambiar, botonesCambiar);   
    }

    @FXML public void transicionExamenPractico() {
        
        GestorTablas.configurarColumnasExamenesAdminAutoescuela(tblcFotoPractico, tblcExaminadoPractico, tblcFechaPractico, tblcExaminadorPractico, tblcResultadoPractico, tblcAutoescuela, tblcDetallesPractico);
        try {
            GestorTablas.cargarTablaExamenesPracticosAdminAutoescuela(tblExamenesPracticos);
        } catch (Exception ex) {
            GestorEscenas.cargarError(tblExamenesPracticos.getScene().getWindow(), ex);
        }
        
        
        Pane[] panelesOcultar = {pnlInicio, pnlExamenesTeoricos};
        GestorEscenas.mostrarOcultarPaneles(pnlExamenesPracticos, panelesOcultar);
        JFXButton[] botones = {jfxbtnExamenTeorico,jfxbtnInicio};
        GestorEscenas.pintarBotones(jfxbtnExamenPractico, botones);
        ImageView ivIconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-chofer-blanco.png")));
        jfxbtnExamenPractico.setGraphic(ivIconoActivo);
        
        
        ArrayList<ImageView>imagenesCambiar= new ArrayList(){{
                add(ivImagenExamenesTeoricos);
                add(ivImagenInicio);
                }};
        ArrayList<JFXButton>botonesCambiar= new ArrayList(){{
                add(jfxbtnExamenTeorico);
                add(jfxbtnInicio);
                }};
        GestorEscenas.cambiarIconos(imagenesCambiar, botonesCambiar);
    }

    @FXML public void transicionExamenTeorico() {
        
        GestorTablas.configurarColumnasExamenesAdminAutoescuela(tblcFotoTeorico, tblcExaminadoTeorico, tblcFechaTeorico, tblcExaminadorTeorico, tblcResultadoTeorico, tblcAutoescuelaTeorico, tblcDetallesTeorico);
        try {
            GestorTablas.cargarTablaExamenesTeoricosAdminAutoescuela(tblExamenesTeoricos);
        } catch (Exception ex) {
            GestorEscenas.cargarError(tblExamenesTeoricos.getScene().getWindow(), ex);
        }
        
        Pane[] panelesOcultar = {pnlInicio, pnlExamenesPracticos};
        GestorEscenas.mostrarOcultarPaneles(pnlExamenesTeoricos, panelesOcultar);
        JFXButton[] botones = {jfxbtnExamenPractico,jfxbtnInicio};
        GestorEscenas.pintarBotones(jfxbtnExamenTeorico, botones);
        ImageView ivIconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-examen-teorico-blanco.png")));
        jfxbtnExamenTeorico.setGraphic(ivIconoActivo);
        
        
        ArrayList<ImageView>imagenesCambiar= new ArrayList(){{
                add(ivImagenExamenesPracticos);
                add(ivImagenInicio);
                }};
        ArrayList<JFXButton>botonesCambiar= new ArrayList(){{
                add(jfxbtnExamenPractico);
                add(jfxbtnInicio);
                }};
        GestorEscenas.cambiarIconos(imagenesCambiar, botonesCambiar);
    }

    @FXML public void transicionRegistrarExamenPractico() {
        String direccion = "/interfaz_usuario/administrador/autoescuela/menu_auxiliares/registrar/registrar-examen-practico.fxml";
        Stage padre = (Stage) btnRegistrarExamenPractico.getScene().getWindow();

        try {
            GestorEscenas.cargarPanelAuxiliar(padre, direccion, true, "Registrar examen practico");
        } catch (Exception ex) {
            //CAPTURAR ERROR
        }
    }

    @FXML public void transicionRegistrarExamenTeorico() {
        String direccion = "/interfaz_usuario/administrador/autoescuela/menu_auxiliares/registrar/registrar-examen-teorico.fxml";
        Stage padre = (Stage) btnRegistrarExamenTeorico.getScene().getWindow();

        try {
            GestorEscenas.cargarPanelAuxiliar(padre, direccion, true, "Registrar examen teorico");
        } catch (Exception ex) {
            //CAPTURAR ERROR

        }
    }

    @Override public void Iniciar(MenuEstadisticas menuEstadisticas) {
        
        lblUsuarioNombre.setText(GestorEscenas.abreviarNombre(Autentificador.usuario.getNombre()));
        Tooltip mouseNombre= new Tooltip(Autentificador.usuario.getNombre());
        mouseNombre.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        lblUsuarioNombre.setTooltip(mouseNombre);
        lblUsuarioNombre.setMaxWidth(100);
        lblCorreoUsuario.setText(GestorEscenas.seguridadCorreo(Autentificador.usuario.getCorreo()));
        CargarEstadisticas(menuEstadisticas);
        
    }

    @Override protected void CargarEstadisticas(MenuEstadisticas menuEstadisticas) {
        
         lblUltimoInicioSesion.setText("Último inicio sesion hace " + menuEstadisticas.GetEstadisticaUsuario().GetUltimoInicioSesion());
         lblIniciosSesion.setText(String.valueOf(menuEstadisticas.GetEstadisticaUsuario().GetCantidadIniciosSesion()));
        for(Estadistica e : menuEstadisticas.getEstadisticas())
        {
            System.out.println(e);
            switch(e.GetCategoria())
            {
                
                case "Cantidad Examenes":
                    lblTotalExamenes.setText(String.valueOf(Math.round(e.GetValor())));
                    break;
                case "Cantidad Trabajadores":
                    lblTotalTrabajadores.setText(String.valueOf(Math.round(e.GetValor())));
                    break;
                case "Mes Anterior":
                    if(e.GetValor()>0)
                        lblPorcientoAnteriorMes.setText("+"+String.valueOf(Math.round(e.GetValor())+"%  respecto anterior mes"));
                    else
                    {
                        lblPorcientoAnteriorMes.setText("-"+String.valueOf(Math.round(e.GetValor())+"%  respecto anterior mes"));
                        lblPorcientoAnteriorMes.textFillProperty().setValue(Color.RED);
                    }
                    break;
                case "Cantidad Examenes Teoricos":
                    lblExamenesTeoricos.setText(String.valueOf(Math.round(e.GetValor())));
                    break;
                case "Cantidad Examenes Practicos":
                    lblExamenesPracticos.setText(String.valueOf(Math.round(e.GetValor())));
                    break;
                case "PorcientoTeorico":
                    lblProgresoTeorico.setText(String.valueOf(Math.round(e.GetValor()))+"%");
                    break;
                case "PorcientoPractico":
                    lblProgresoPractico.setText(String.valueOf(Math.round(e.GetValor()))+"%");
                    break;
                case "PorcientoReprobado":
                    lblProgresoReprobado.setText(String.valueOf(Math.round(e.GetValor()))+"%");
                    break;
                case "PorcientoAprobado":
                    lblProgresoAprobado.setText(String.valueOf(Math.round(e.GetValor()))+"%");
                    break;
                case "IndiceAprobados":
                    if(e.GetValor()>0)
                        lblIndiceAprobados.setText("+"+String.valueOf(Math.round(e.GetValor()))+"% índice aprobados");
                    else
                    {
                        lblIndiceAprobados.setText("-"+String.valueOf(Math.round(e.GetValor()))+"% índice aprobados");
                        lblIndiceAprobados.textFillProperty().setValue(Color.RED);
                    }
                    break;
            }
        }
    }
}
