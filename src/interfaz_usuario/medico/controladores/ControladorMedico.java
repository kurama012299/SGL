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
import gestor_interfaces.modelos.Estadistica;
import gestor_interfaces.modelos.MenuEstadisticas;
import gestor_tablas.GestorTablas;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
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
    
    @FXML private Label lblUltimoInicioSesion;
    
    @FXML private Label lblIniciosSesion;
    
    @FXML private Label lblCantPacientes;
    
    @FXML private Label lblCantExamenes;
    
    @FXML private Label lblCantReprobados;
    
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
    
    @FXML private TableColumn<ExamenMedico, Void>tblcDetalles;
    
    @FXML private TableColumn<ExamenMedico, Void>tblcFoto;

    @FXML private Label lblUsuarioNombre;
    
    @FXML private Label lblCorreoUsuario;
    
    @FXML private Label lblFechaHora;
    
    @FXML private RadioButton rdbtAprobadoCondicional;
    
    @FXML private RadioButton rdbtAprobado;
    
    @FXML private RadioButton rdbtReprobado;
    
    private RadioButton seleccionado;
    
    
    private ArrayList<RadioButton>grupoFiltro= new ArrayList<>();
    
    private ImageView ivImagenInicio;
    private ImageView ivImagenExamenesMedicos;
    
    @FXML
    public void initialize() {
        System.out.println("Controlador Medico Iniciado");
        
        accionFiltros();
        
        ivImagenInicio = (ImageView) jfxbtnInicio.getGraphic();
        ivImagenExamenesMedicos = (ImageView) jfxbtnExamenesMedicos.getGraphic();
        
        GestorEscenas.configurarReloj(lblFechaHora);
        
        GestorEscenas.ponerIconoVentana(hbVentanaPrincipal, "Médico");
         
        jfxbtnCerrarSesion.setOnAction(e ->
        {
            GestorEscenas.cerrarPrograma();
        });
        
        JFXButton[] botonesConsumirTecla={jfxbtnInicio,jfxbtnExamenesMedicos};
        GestorEscenas.consumirTecla(botonesConsumirTecla);
        
        this.transicionInicio();

    }
    
    @FXML private void accionFiltros()
    {
        grupoFiltro.add(rdbtAprobado);
        grupoFiltro.add(rdbtAprobadoCondicional);
        grupoFiltro.add(rdbtReprobado);
        
        for(RadioButton rb: grupoFiltro)
            {
                rb.setOnAction(e -> {
                    manejarSeleccion(rb);
                    GestorTablas.cargarTablaExamenesMedicosMedicoUnico(tblExamenesMedicos,Autentificador.usuario.getId(), rb.getText());
                    eventoEstaSeleccionado();
                });
            }
    }
    
     @FXML private void eventoEstaSeleccionado()
    {
        if(!rdbtAprobado.isSelected() && !rdbtAprobadoCondicional.isSelected() && !rdbtReprobado.isSelected())
        {
            tblExamenesMedicos.getItems().clear();
            GestorTablas.cargarTablaExamenesMedicosMedicoUnico(tblExamenesMedicos,Autentificador.usuario.getId(),"");
        }        
    }
     
     @FXML private void manejarSeleccion(RadioButton radioClickeado)
    {
        if (seleccionado == radioClickeado) {
            radioClickeado.setSelected(false);
            seleccionado = null;
        } else {
            seleccionado = radioClickeado;
            for (RadioButton rb : grupoFiltro) {
                rb.setSelected(rb == radioClickeado);
            }
        }
    }
     
     
    @FXML
    public void transicionExamenesMedico() {
        
        GestorTablas.configurarColumnasExamenesMedicosMedicoUnico(tblcFoto,tblcExaminado, tblcFecha, tblcResultado, tblcClinica, tblcDetalles);
        GestorTablas.cargarTablaExamenesMedicosMedicoUnico(tblExamenesMedicos, Autentificador.usuario.getId(),"");
        
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
            GestorEscenas.cargarError(padre, ex);
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
        CargarEstadisticas(menuEstadisticas);
    }

    @Override
    protected void CargarEstadisticas(MenuEstadisticas menuEstadisticas) {

        lblUltimoInicioSesion.setText("Último inicio sesion hace " + menuEstadisticas.GetEstadisticaUsuario().GetUltimoInicioSesion());
        lblIniciosSesion.setText(String.valueOf(menuEstadisticas.GetEstadisticaUsuario().GetCantidadIniciosSesion()));
        for (Estadistica e : menuEstadisticas.getEstadisticas()) {
            switch (e.GetCategoria()) {
                case "CantPacientes":
                    lblCantPacientes.setText(String.valueOf(Math.round(e.GetValor())));
                    break;
                case "CantExamenes":
                    lblCantExamenes.setText(String.valueOf(Math.round(e.GetValor())));
                    break;
                case "Porciento18a40":
                    lblProgresoJoven.setText(String.valueOf(Math.round(e.GetValor()))+ "%");
                    break;
                case "Porciento40a60":
                    lblProgresoAdulto.setText(String.valueOf(Math.round(e.GetValor())) + "%");
                    break;
                case "Porciento60a70":
                    lblProgresoAnciano.setText(String.valueOf(Math.round(e.GetValor())) + "%");
                    break;
                case "Reprobados":
                    lblCantReprobados.setText(String.valueOf(Math.round(e.GetValor())));
                    break;
                case "PorcientoAprobado":
                    lblProgresoAprobado.setText(String.valueOf(Math.round(e.GetValor())) + "%");
                    break;
                case "PorcientoAprobadoRestricciones":
                    lblProgresoAprobadoR.setText(String.valueOf(Math.round(e.GetValor())) + "%");
                    break;
                case "PorcientoReprobado":
                    lblCantReprobados.setText(String.valueOf(Math.round(e.GetValor())) + "%");
                    break;
            }
        }
        Label[] PorcentajesBarra = {lblProgresoAprobado, lblProgresoAprobadoR, lblProgresoReprobado, lblProgresoJoven, lblProgresoAdulto, lblProgresoAnciano};
        ProgressBar[] BarrasProgreso = {pbarAprobado, pbarAprobadoR, pbarReprobado, pbarJoven, pbarAdulto, pbarAnciano};
        GestorEscenas.progresoLabel(PorcentajesBarra, BarrasProgreso);

    }

}
