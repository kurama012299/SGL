/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.sistema.controladores;

import com.jfoenix.controls.JFXButton;
import gestor_interfaces.GestorEscenas;
import gestor_interfaces.modelos.Controlador;
import gestor_interfaces.modelos.Estadistica;
import gestor_interfaces.modelos.MenuEstadisticas;
import gestor_tablas.GestorTablas;
import java.util.ArrayList;
import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import logica.excel_gestor.GestorExcel;
import logica.autentificacion.Autentificador;
import logica.entidad.modelos.EntidadRelacionada;
import logica.examen_conduccion.modelos.ExamenConduccion;
import logica.gestion_imagenes.GestorImagenes;
import logica.infraccion.modelos.Infraccion;
import logica.licencia.modelos.Licencia;
import logica.persona.modelos.Conductor;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorAdministradorSistema extends Controlador{
    
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
    private Pane PanelReportes;
    
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
    private ProgressBar BarraProgresoLicenciaA;
    
    @FXML
    private ProgressBar BarraProgresoLicenciaB;
    
    @FXML
    private ProgressBar BarraProgresoLicenciaC;
    
    @FXML
    private ProgressBar BarraProgresoLicenciaD;
    
    @FXML
    private ProgressBar BarraProgresoLicenciaE;
    
    @FXML
    private ProgressBar BarraProgresoInfraccionLeve;
    
    @FXML
    private ProgressBar BarraProgresoInfraccionGrave;
    
    @FXML
    private ProgressBar BarraProgresoInfraccionMGrave;
    
    @FXML
    private Label LabelLicenciaA;
    
    @FXML
    private Label LabelLicenciaB;
    
    @FXML
    private Label LabelLicenciaC;
  
    @FXML
    private Label LabelLicenciaD;
    
    @FXML
    private Label LabelLicenciaE;
    
    @FXML
    private Label LabelInfraccionLeve;
    
    @FXML
    private Label LabelInfraccionGrave;
    
    @FXML
    private Label LabelInfraccionMGrave;
    
    @FXML
    private JFXButton BotonCerrarSesion;
    
    @FXML
    private TableView<Conductor> TablaConductor;
    
    @FXML
    private TableView<ExamenConduccion> TablaExamenes;
    
    @FXML
    private TableColumn<ExamenConduccion, Void> ColumnaFotoExamen;
    
    @FXML
    private TableColumn<ExamenConduccion, String> ColumnaExaminadoExamen;
    
    @FXML
    private TableColumn<ExamenConduccion, String> ColumnaTipoExamen;

    @FXML
    private TableColumn<ExamenConduccion, Date> ColumnaFechaExamen;
    
    @FXML
    private TableColumn<ExamenConduccion, String> ColumnaExaminadorExamen;
    
    @FXML
    private TableColumn<ExamenConduccion, String> ColumnaResultadoExamen;
    
    @FXML
    private TableColumn<ExamenConduccion, Void> ColumnaDetallesExamen;
    
    @FXML
    private TableColumn<Conductor, Void> ColumnaFoto;
    
    @FXML
    private TableColumn<Conductor, String> ColumnaNombre;
    
    @FXML
    private TableColumn<Conductor, String> ColumnaCI;
    
    @FXML
    private TableColumn<Conductor, String> ColumnaTelefono;
    
    @FXML
    private TableColumn<Conductor, String> ColumnaCorreo;
    
    @FXML
    private TableColumn<Conductor, Void> ColumnaDetalles;
    
    @FXML
    private TableView<EntidadRelacionada> TablaEntidad;
    
    @FXML
    private TableView<Infraccion> TablaInfraccion;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDirectorEntidad;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaNombreEntidad;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDireccionEntidad;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaTelefonoEntidad;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaCorreoEntidad;
    
    @FXML
    private TableColumn<EntidadRelacionada, Void> ColumnaDetallesEntidad;
    
    @FXML
    private TableColumn<Infraccion, Void> ColumnaFotoInfraccion;
    
    @FXML
    private TableColumn<Infraccion, String> ColumnaNombreInfraccion;
    
    @FXML
    private TableColumn<Infraccion, String> ColumnaTipoInfraccion;
    
    @FXML
    private TableColumn<Infraccion, Date> ColumnaFechaInfraccion;
    
    @FXML
    private TableColumn<Infraccion, String> ColumnaLugarInfraccion;
    
    @FXML
    private TableColumn<Infraccion, Long> ColumnaLicenciaInfraccion;
    
    @FXML
    private TableColumn<Infraccion, Integer> ColumnaPtosDeducidosInfraccion;
    
    @FXML
    private TableColumn<Infraccion, Void> ColumnaDetallesInfraccion;
    
    @FXML
    private TableView<EntidadRelacionada> TablaAE;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDirectorAE;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaNombreAE;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDireccionAE;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaTelefonoAE;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaCorreoAE;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDetallesAE;
    
    @FXML
    private TableView<EntidadRelacionada> TablaClinica;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDirectorClinica;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaNombreClinica;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDireccionClinica;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaTelefonoClinica;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaCorreoClinica;
    
    @FXML
    private TableColumn<EntidadRelacionada, String> ColumnaDetallesClinica;
    
    
     @FXML
    private TableView<Licencia> TablaLicencia;
    
    @FXML
    private TableColumn<Licencia, Void> ColumnaFotoLicencia;
    
    @FXML
    private TableColumn<Licencia, String> ColumnaNombreLicencia;
    
    @FXML
    private TableColumn<Licencia, String> ColumnaTipoLicencia;
    
    @FXML
    private TableColumn<Licencia, Date> ColumnaEmisionLicencia;
    
    @FXML
    private TableColumn<Licencia, Date> ColumnaVencimientoLicencia;
    
    @FXML
    private TableColumn<Licencia, Integer> ColumnaPuntosLicencia;
    
    @FXML
    private TableColumn<Licencia, Void> ColumnaDetallesLicencia;
    
    @FXML
    private Label LabelUltimoInicioSesion;
    
    @FXML
    private Label LabelIniciosSesion;
    
   @FXML
   private Label LabelTotalConductores;
   
   @FXML
   private Label LabelExamenesReprobados;
   
   @FXML
   private Label LabelTotalEntidades;
   
   @FXML
   private Button BotonExportarConductores;
   
   @FXML
   private Label LabelUsuarioNombre;
   
   @FXML
   private Label LabelCorreoUsuario;
   
    @FXML
   private ImageView ImagenUsuario;
        
   @FXML
   private Label LabelFechaHora;
   
   @FXML
   private StackPane spnlReporteLicencias;
   
   @FXML
   private StackPane spnlReporteExamenes;
      
   @FXML
   private StackPane spnlReporteInfracciones;
   
   @FXML
   private StackPane spnlReporteInfraccionesTipo;
   
   @FXML
   private StackPane spnlReporteConductores;

   @FXML
   private ImageView imgReporteLicencias;
   
   @FXML
   private ImageView imgReporteExamenes;
   
   @FXML
   private ImageView imgReporteInfracciones;
   
   @FXML
   private ImageView imgReporteInfraccionesTipo;
   
   @FXML
   private ImageView imgReporteConductores;
   
   @FXML private HBox hbVentanaPrincipal;
   
   @FXML private ImageView ivImagenConfig;
    
   @FXML private ContextMenu menuConfig;
   
   @FXML private Button btnEscondidoConfiguracion;
   
   @FXML private RadioButton rdbtLeve;
   
   @FXML private RadioButton rdbtGrave;
   
   @FXML private RadioButton rdbtMuyGrave;
   
   @FXML private RadioButton rdbtExamenTeorico;
   
   @FXML private RadioButton rdbtExamenPractico;
   
   @FXML private RadioButton rdbtExamenMedico;
   
   @FXML private RadioButton rdbtClinica;
   
   @FXML private RadioButton rdbtAutoescuela;
   
   @FXML private RadioButton rdbtVencida;
   
   @FXML private RadioButton rdbtSuspendida;
   
   @FXML private RadioButton rdbtRevocada;
   
   @FXML private RadioButton rdbtVigente;
   
   @FXML private RadioButton rdbtTipoLicenciaA;
   
   @FXML private RadioButton rdbtTipoLicenciaB;
   
   @FXML private RadioButton rdbtTipoLicenciaC;
   
   @FXML private RadioButton rdbtTipoLicenciaD;
   
   @FXML private RadioButton rdbtTipoLicenciaE;
   
   @FXML private Button btnExportarAutoescuela;
   
   @FXML private Button btnExportarClinica;
   
   @FXML private Button btnExportarEntidades;
   
   @FXML private Button btnExportarInfracciones;
   
   @FXML private Button btnExportarExamenes;
   
   @FXML private Button btnExportarLicencias;
   
   private RadioButton seleccionadoEntidad=null;
   private RadioButton seleccionadoTipoExamen=null;
   private RadioButton seleccionadoGravedadInfraccion=null;
   private RadioButton seleccionadoEstadoConductor=null;
   private RadioButton seleccionadoTipoLicencia=null;
   
   
   private ArrayList<RadioButton> grupoRadioEntidades= new ArrayList<>();
   private ArrayList<RadioButton> grupoRadioTipoExamen= new ArrayList<>();
   private ArrayList<RadioButton> grupoRadioGravedadInfraccion= new ArrayList<>();
   private ArrayList<RadioButton> grupoRadioEstadoConductores= new ArrayList<>();
   private ArrayList<RadioButton> grupoRadioTipoLicencia= new ArrayList<>();

   
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
    public void initialize() throws Exception 
    {
        
        accionFiltros();
        configuracionCentro();
        
        ImagenLicencias = (ImageView) Licencias.getGraphic();
        ImagenConductores = (ImageView) Conductores.getGraphic();
        ImagenInicio = (ImageView) Inicio.getGraphic();
        ImagenExamenes = (ImageView) Examenes.getGraphic();
        ImagenInfracciones = (ImageView) Infracciones.getGraphic();
        ImagenReportes = (ImageView) Reportes.getGraphic();
        ImagenAutoescuela = (ImageView) Autoescuela.getGraphic();
        ImagenClinica = (ImageView) Clinica.getGraphic();
        ImagenEntidades = (ImageView) Entidades.getGraphic();
       
        
        ImagenUsuario.setImage(GestorImagenes.cargarImagen(Autentificador.usuario.getFoto()));
        
        GestorEscenas.ponerIconoVentana(hbVentanaPrincipal, "Administrador");
        GestorEscenas.configurarReloj(LabelFechaHora);
        
        configurarReportes();
        
        BotonCerrarSesion.setOnAction(e
                -> {
            try {
                GestorEscenas.cargarMensajeCerrarSesion(BotonCerrarSesion.getScene().getWindow());

            } catch (Exception ex) {
                GestorEscenas.cargarError(BotonCerrarSesion.getScene().getWindow(), ex);
            }

        });
        
        JFXButton[] BotonesConsumirTecla = {Inicio, Examenes, Licencias, Conductores, Infracciones, Reportes, Autoescuela, Clinica, Entidades};
        GestorEscenas.consumirTecla(BotonesConsumirTecla);
        
        GestorEscenas.suprimirReordenamientoTablas(TablaAE);
        GestorEscenas.suprimirReordenamientoTablas(TablaClinica);
        GestorEscenas.suprimirReordenamientoTablas(TablaConductor);
        GestorEscenas.suprimirReordenamientoTablas(TablaEntidad);
        GestorEscenas.suprimirReordenamientoTablas(TablaExamenes);
        GestorEscenas.suprimirReordenamientoTablas(TablaInfraccion);
        GestorEscenas.suprimirReordenamientoTablas(TablaLicencia);
        
        System.out.println("Controlador Administrador Sistema Iniciado");
        this.TransicionInicio();   
    }
    
    @FXML private void limpiarFiltros()
    {
        ArrayList<RadioButton> botonesFiltros= new ArrayList<>();
        botonesFiltros.addAll(grupoRadioGravedadInfraccion);
        botonesFiltros.addAll(grupoRadioEntidades);
        botonesFiltros.addAll(grupoRadioEstadoConductores);
        botonesFiltros.addAll(grupoRadioTipoExamen);
        botonesFiltros.addAll(grupoRadioTipoLicencia);
        for(RadioButton rb: botonesFiltros)
            rb.setSelected(false);
        
    }
    
    @FXML private void accionFiltros()
    {
        grupoRadioEntidades.add(rdbtClinica);
        grupoRadioEntidades.add(rdbtAutoescuela);

        grupoRadioGravedadInfraccion.add(rdbtLeve);
        grupoRadioGravedadInfraccion.add(rdbtGrave);
        grupoRadioGravedadInfraccion.add(rdbtMuyGrave);
        
        grupoRadioEstadoConductores.add(rdbtRevocada);
        grupoRadioEstadoConductores.add(rdbtSuspendida);
        grupoRadioEstadoConductores.add(rdbtVencida);
        grupoRadioEstadoConductores.add(rdbtVigente);
        
        grupoRadioTipoExamen.add(rdbtExamenMedico);
        grupoRadioTipoExamen.add(rdbtExamenPractico);
        grupoRadioTipoExamen.add(rdbtExamenTeorico);
        
       
        grupoRadioTipoLicencia.add(rdbtTipoLicenciaA);
        grupoRadioTipoLicencia.add(rdbtTipoLicenciaB);
        grupoRadioTipoLicencia.add(rdbtTipoLicenciaC);
        grupoRadioTipoLicencia.add(rdbtTipoLicenciaD);
        grupoRadioTipoLicencia.add(rdbtTipoLicenciaE);
        
         for(RadioButton rb: grupoRadioTipoLicencia)
            {
                rb.setOnAction(e -> {
                    manejarSeleccionTipoLicencia(rb);
                    GestorTablas.cargarTablaLicencias(TablaLicencia, rb.getText());
                    eventoEstaSeleccionadoTipoLicencia();
                });
            } 
         
         
        for(RadioButton rb: grupoRadioTipoExamen)
            {
                rb.setOnAction(e -> {
                    manejarSeleccionTipoExamenes(rb);
                    GestorTablas.cargarTablaExamenes(TablaExamenes, rb.getText());
                    eventoEstaSeleccionadoTipoExamen();
                });
            } 
        
        for(RadioButton rb: grupoRadioEntidades)
            {
                rb.setOnAction(e -> {
                    manejarSeleccionEntidades(rb);
                    GestorTablas.cargarTablaEntidades(TablaEntidad, rb.getText());
                    eventoEstaSeleccionadoEntidades();
                });
            }
        
        for(RadioButton rb: grupoRadioGravedadInfraccion)
            {
                rb.setOnAction(e -> {
                    manejarSeleccionGravedad(rb);
                    GestorTablas.cargarTablaInfracciones(TablaInfraccion, rb.getText());
                    eventoEstaSeleccionadoInfraccion();
                });
            }
        
        for(RadioButton rb: grupoRadioEstadoConductores)
            {
                rb.setOnAction(e -> {
                    manejarSeleccionEstado(rb);
                    try {
                        GestorTablas.cargarTablaConductores(TablaConductor, rb.getText());
                    } catch (Exception ex) {
                        GestorEscenas.cargarError(BotonCerrarSesion.getScene().getWindow(), ex);
                    }
                    eventoEstaSeleccionadoConductor();
                });
            }
        
    }
    
     @FXML private void eventoEstaSeleccionadoEntidades()
    {
        if(!rdbtClinica.isSelected() && !rdbtAutoescuela.isSelected())
        {
            TablaEntidad.getItems().clear();
            GestorTablas.cargarTablaEntidades(TablaEntidad,"");
        }        
    }
     
     @FXML private void eventoEstaSeleccionadoTipoLicencia()
    {
        if(!rdbtTipoLicenciaA.isSelected() && !rdbtTipoLicenciaB.isSelected() && !rdbtTipoLicenciaC.isSelected() && !rdbtTipoLicenciaD.isSelected() && !rdbtTipoLicenciaE.isSelected())
        {
            TablaLicencia.getItems().clear();
            GestorTablas.cargarTablaLicencias(TablaLicencia,"");
        }        
    }
     
      @FXML private void eventoEstaSeleccionadoTipoExamen()
    {
        if(!rdbtExamenMedico.isSelected() && !rdbtExamenPractico.isSelected() && !rdbtExamenTeorico.isSelected())
        {
            TablaExamenes.getItems().clear();
            GestorTablas.cargarTablaExamenes(TablaExamenes,"");
        }     
    }
      
     
    @FXML private void eventoEstaSeleccionadoInfraccion()
    {
        if(!rdbtGrave.isSelected() && !rdbtLeve.isSelected() && !rdbtMuyGrave.isSelected())
        {
            TablaInfraccion.getItems().clear();
            GestorTablas.cargarTablaInfracciones(TablaInfraccion,"");
        }        
    }
     
    @FXML private void eventoEstaSeleccionadoConductor()
    {
        if(!rdbtVencida.isSelected() && !rdbtVigente.isSelected() && !rdbtSuspendida.isSelected() && !rdbtRevocada.isSelected())
        {
            TablaConductor.getItems().clear();
            try {
                GestorTablas.cargarTablaConductores(TablaConductor," ");
            } catch (Exception ex) {
                GestorEscenas.cargarError(BotonCerrarSesion.getScene().getWindow(), ex);
            }
        }        
    }
     
    @FXML private void manejarSeleccionEntidades(RadioButton radioClickeado)
    {
        if (seleccionadoEntidad == radioClickeado) {
            radioClickeado.setSelected(false);
            seleccionadoEntidad = null;
        } else {
            seleccionadoEntidad = radioClickeado;
            for (RadioButton rb : grupoRadioEntidades) {
                rb.setSelected(rb == radioClickeado);
            }
        }
    }
    
    @FXML private void manejarSeleccionEstado(RadioButton radioClickeado)
    {
        if (seleccionadoEstadoConductor == radioClickeado) {
            radioClickeado.setSelected(false);
            seleccionadoEstadoConductor = null;
        } else {
            seleccionadoEstadoConductor = radioClickeado;
            for (RadioButton rb : grupoRadioEstadoConductores) {
                rb.setSelected(rb == radioClickeado);
            }
        }
    }
    
    @FXML private void manejarSeleccionTipoExamenes(RadioButton radioClickeado)
    {
        if (seleccionadoTipoExamen == radioClickeado) {
            radioClickeado.setSelected(false);
            seleccionadoTipoExamen = null;
        } else {
            seleccionadoTipoExamen = radioClickeado;
            for (RadioButton rb : grupoRadioTipoExamen) {
                rb.setSelected(rb == radioClickeado);
            }
        }
    }
    
    @FXML private void manejarSeleccionTipoLicencia(RadioButton radioClickeado)
    {
        if (seleccionadoTipoLicencia == radioClickeado) {
            radioClickeado.setSelected(false);
            seleccionadoTipoLicencia = null;
        } else {
            seleccionadoTipoLicencia = radioClickeado;
            for (RadioButton rb : grupoRadioTipoLicencia) {
                rb.setSelected(rb == radioClickeado);
            }
        }
    }
    
    @FXML private void manejarSeleccionGravedad(RadioButton radioClickeado)
    {
        if (seleccionadoGravedadInfraccion == radioClickeado) {
            radioClickeado.setSelected(false);
            seleccionadoGravedadInfraccion = null;
        } else {
            seleccionadoGravedadInfraccion = radioClickeado;
            for (RadioButton rb : grupoRadioGravedadInfraccion) {
                rb.setSelected(rb == radioClickeado);
            }
        }
    }
    
    
    @FXML private void configuracionCentro()
    {
        ListView<String>lista= new ListView<>();
        lista.getItems().addAll("Reporte centro","Opciones");
        lista.setPrefSize(110, 51);
        lista.getStylesheets().add(getClass().getResource("/interfaz_usuario/recursos_compartidos/estilos/EstilosListView.css").toExternalForm());
        Popup menuContexto = new Popup();
        menuContexto.getContent().add(lista);
        menuContexto.setAutoHide(true);
        ivImagenConfig.setOnMouseEntered(e
                -> {
            ivImagenConfig.setScaleX(1.2);
            ivImagenConfig.setScaleY(1.2);
        });
        ivImagenConfig.setOnMouseExited(e
                -> {
            ivImagenConfig.setScaleX(1);
            ivImagenConfig.setScaleY(1);
        });
        ivImagenConfig.setOnMouseClicked(e -> {
            if(!menuContexto.isShowing())
            {
                lista.getSelectionModel().clearSelection();
                menuContexto.show(ivImagenConfig,e.getScreenX(),e.getScreenY());
            }
        });
        lista.getSelectionModel().selectedItemProperty().addListener((obs,valorViejo,nuevoValor)->{
            if(nuevoValor!=null)
            {
                if (((String) nuevoValor).equalsIgnoreCase("Reporte centro")) {
                    String direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_configuracion/menu-configuracion-centro.fxml";
                    try {
                        GestorEscenas.cargarPanelAuxiliar(hbVentanaPrincipal.getScene().getWindow(), direccion, true, "Informacion Centro");
                    } catch (Exception ex) {
                        GestorEscenas.cargarError(menuConfig, ex);
                    }

                }
            }
            menuContexto.hide();
        });
        btnEscondidoConfiguracion.setDisable(true);
           
    }
    
    
    private void configurarReporteLicencias() {
    configurarHover(spnlReporteLicencias, imgReporteLicencias, () -> {
        try {
            GestorEscenas.cargarMenuReportesPeriodo(spnlReporteLicencias.getScene().getWindow(),
                    "Licencias emitidas"); 
        } catch (Exception ex) {
             GestorEscenas.cargarError(spnlReporteExamenes.getScene().getWindow(), ex);
        }
    });
}

    private void configurarReporteInfracciones() {
        configurarHover(spnlReporteInfracciones, imgReporteInfracciones, () -> {
            try {
                GestorEscenas.cargarMenuReportesPeriodo(spnlReporteLicencias.getScene().getWindow(),
                    "Infracciones emitidas"); 
            } catch (Exception ex) {
                GestorEscenas.cargarError(spnlReporteInfracciones.getScene().getWindow(), ex);
            }
        });
    }
    
    private void configurarReporteExamenes() {
        configurarHover(spnlReporteExamenes, imgReporteExamenes, () -> {
            try {
                GestorEscenas.cargarMenuReportesPeriodo(spnlReporteExamenes.getScene().getWindow(),
                    "Examenes emitidos"); 
            } catch (Exception ex) {
                GestorEscenas.cargarError(spnlReporteExamenes.getScene().getWindow(), ex);
            }
        });
    }
    
    private void configurarReporteInfraccionesTipo()
    {
        configurarHover(spnlReporteInfraccionesTipo, imgReporteInfraccionesTipo, () -> {
            try {
                GestorEscenas.cargarMenuReportesTipo(spnlReporteInfraccionesTipo.getScene().getWindow(),
                    "Infracciones por tipo"); 
            } catch (Exception ex) {
                GestorEscenas.cargarError(spnlReporteInfraccionesTipo.getScene().getWindow(), ex);
            }
        });
    }
    
     private void configurarReporteConductoresLicenciasVencidas() {
    configurarHover(spnlReporteConductores, imgReporteConductores, () -> {
        try {
            GestorEscenas.cargarMenuReportesPeriodo(spnlReporteConductores.getScene().getWindow(),
                    "Licencias vencidas"); 
        } catch (Exception ex) {
             GestorEscenas.cargarError(spnlReporteConductores.getScene().getWindow(), ex);
        }
    });
}

  

    private void configurarHover(StackPane container, ImageView preview, Runnable accionReporte) {
        // Efectos hover
        container.setOnMouseEntered(e -> {
            preview.setScaleX(1.1);
            preview.setScaleY(1.1);
            container.setStyle("-fx-background-color: #34495e;");
        });

        container.setOnMouseExited(e -> {
            preview.setScaleX(1);
            preview.setScaleY(1);
            container.setStyle("-fx-background-color: transparent;");
        });

        // Acción al hacer click
        container.setOnMouseClicked(e -> {
            try {
                accionReporte.run();
                //GestorEscenas.cargarConfirmacion(BotonCerrarSesion.getScene().getWindow(), "Reporte generado correctamente");
            } catch (Exception ex) {
            }
        });
    }
    
    private void configurarReportes()
    {
        configurarReporteLicencias();
        configurarReporteInfracciones();
        configurarReporteExamenes();
        configurarReporteInfraccionesTipo();
        configurarReporteConductoresLicenciasVencidas();
    }
    
    @FXML
    public void TransicionLicencias()
    {
        limpiarFiltros();
        GestorTablas.configurarColumnasLicencias(ColumnaFotoLicencia, ColumnaNombreLicencia, ColumnaTipoLicencia, ColumnaEmisionLicencia, ColumnaVencimientoLicencia, ColumnaPuntosLicencia, ColumnaDetallesLicencia);
        GestorTablas.cargarTablaLicencias(TablaLicencia,"");
        Pane[] PanelesOcultar={PanelInfracciones, PanelInicio,PanelReportes, PanelConductores, PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.mostrarOcultarPaneles(PanelLicencias,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Examenes, Reportes, Autoescuela, Clinica, Entidades};
        GestorEscenas.pintarBotones(Licencias, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-licencia-blanco.png")));
        Licencias.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInfracciones);
                add(ImagenInicio);
                add(ImagenConductores);
                add(ImagenExamenes);
                add(ImagenReportes);
                add(ImagenAutoescuela);
                add(ImagenClinica);
                add(ImagenEntidades);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Infracciones);
                add(Inicio);
                add(Conductores);
                add(Examenes);
                add(Reportes);
                add(Autoescuela);
                add(Clinica);
                add(Entidades);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionConductores() {
        limpiarFiltros();
        //Llenado de tabla

        GestorTablas.ConfigurarColumnasConductores(ColumnaFoto, ColumnaNombre, ColumnaCI, ColumnaTelefono, ColumnaCorreo, ColumnaDetalles);
        try {
            GestorTablas.cargarTablaConductores(TablaConductor, " ");
        } catch (Exception ex) {
            GestorEscenas.cargarError(BotonCerrarSesion.getScene().getWindow(), ex);
        }

        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelReportes,PanelInicio, PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.mostrarOcultarPaneles(PanelConductores,PanelesOcultar);
        JFXButton[] botones = {Inicio, Licencias, Infracciones, Examenes, Reportes, Autoescuela, Clinica, Entidades};
        GestorEscenas.pintarBotones(Conductores, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-conductor-blanco.png")));
        Conductores.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInfracciones);
                add(ImagenInicio);
                add(ImagenLicencias);
                add(ImagenExamenes);
                add(ImagenReportes);
                add(ImagenAutoescuela);
                add(ImagenClinica);
                add(ImagenEntidades);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Infracciones);
                add(Inicio);
                add(Licencias);
                add(Examenes);
                add(Reportes);
                add(Autoescuela);
                add(Clinica);
                add(Entidades);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionInicio()
    {
        limpiarFiltros();
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores,PanelReportes, PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.mostrarOcultarPaneles(PanelInicio,PanelesOcultar);
        JFXButton[] botones = {Licencias, Conductores, Infracciones, Examenes, Reportes,Autoescuela,Clinica,Entidades};
        GestorEscenas.pintarBotones(Inicio, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-inicio-blanco.png")));
        Inicio.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInfracciones);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenExamenes);
                add(ImagenReportes);
                add(ImagenAutoescuela);
                add(ImagenClinica);
                add(ImagenEntidades);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Infracciones);
                add(Conductores);
                add(Licencias);
                add(Examenes);
                add(Reportes);
                add(Autoescuela);
                add(Clinica);
                add(Entidades);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionExamenes()
    {
        limpiarFiltros();
        GestorTablas.configurarColumnasExamenes(ColumnaFotoExamen, ColumnaExaminadoExamen, ColumnaTipoExamen, ColumnaFechaExamen, ColumnaExaminadorExamen, ColumnaResultadoExamen, ColumnaDetallesExamen);
        try {
            GestorTablas.cargarTablaExamenes(TablaExamenes,"");
        } catch (Exception ex) {
            GestorEscenas.cargarError(BotonCerrarSesion.getScene().getWindow(), ex);
        }
        
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores,PanelReportes, PanelInicio, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.mostrarOcultarPaneles(PanelExamenes,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Licencias, Reportes,Autoescuela,Clinica,Entidades};
        GestorEscenas.pintarBotones(Examenes, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-examen-blanco.png")));
        Examenes.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInfracciones);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenInicio);
                add(ImagenReportes);
                add(ImagenAutoescuela);
                add(ImagenClinica);
                add(ImagenEntidades);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Infracciones);
                add(Conductores);
                add(Licencias);
                add(Inicio);
                add(Reportes);
                add(Autoescuela);
                add(Clinica);
                add(Entidades);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionInfracciones()
    {
        limpiarFiltros();
        GestorTablas.configurarColumnasInfracciones(ColumnaFotoInfraccion, ColumnaNombreInfraccion, ColumnaTipoInfraccion, ColumnaFechaInfraccion, ColumnaLugarInfraccion, ColumnaLicenciaInfraccion, ColumnaPtosDeducidosInfraccion, ColumnaDetallesInfraccion);
        GestorTablas.cargarTablaInfracciones(TablaInfraccion,"");
        Pane[] PanelesOcultar={PanelInicio, PanelLicencias, PanelConductores, PanelReportes,PanelExamenes, PanelClinica, PanelAutoescuela, PanelEntidades};
        GestorEscenas.mostrarOcultarPaneles(PanelInfracciones,PanelesOcultar);
        JFXButton[] botones = {Inicio,Autoescuela,Clinica,Conductores,Entidades,Examenes,Licencias,Reportes};
        GestorEscenas.pintarBotones(Infracciones, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-infraccion-blanco.png")));
        Infracciones.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenExamenes);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenInicio);
                add(ImagenReportes);
                add(ImagenAutoescuela);
                add(ImagenClinica);
                add(ImagenEntidades);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Examenes);
                add(Conductores);
                add(Licencias);
                add(Inicio);
                add(Reportes);
                add(Autoescuela);
                add(Clinica);
                add(Entidades);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionClinica()
    {
        limpiarFiltros();
        GestorTablas.configurarColumnasClinicas(ColumnaDirectorClinica, ColumnaNombreClinica,ColumnaDireccionClinica, ColumnaTelefonoClinica, ColumnaCorreoClinica, ColumnaDetallesClinica);
        GestorTablas.cargarTablaClinicas(TablaClinica);
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores, PanelReportes,PanelExamenes, PanelInicio, PanelAutoescuela, PanelEntidades};
        GestorEscenas.mostrarOcultarPaneles(PanelClinica,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Licencias, Reportes,Autoescuela,Entidades,Examenes};
        GestorEscenas.pintarBotones(Clinica, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-clinica-blanco.png")));
        Clinica.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenExamenes);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenInicio);
                add(ImagenReportes);
                add(ImagenAutoescuela);
                add(ImagenInfracciones);
                add(ImagenEntidades);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Examenes);
                add(Conductores);
                add(Licencias);
                add(Inicio);
                add(Reportes);
                add(Autoescuela);
                add(Infracciones);
                add(Entidades);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
        
    }
    
    @FXML
    public void TransicionAutoescuela()
    {
        limpiarFiltros();
        GestorTablas.configurarColumnasAutoescuelas(ColumnaDirectorAE, ColumnaNombreAE,ColumnaDireccionAE, ColumnaTelefonoAE, ColumnaCorreoAE, ColumnaDetallesAE);
        GestorTablas.cargarTablaAutoescuelas(TablaAE);
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores,PanelReportes, PanelExamenes, PanelClinica, PanelInicio, PanelEntidades};
        GestorEscenas.mostrarOcultarPaneles(PanelAutoescuela,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Licencias, Reportes,Clinica,Entidades,Examenes};
        GestorEscenas.pintarBotones(Autoescuela, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-autoescuela-blanco.png")));
        Autoescuela.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenExamenes);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenInicio);
                add(ImagenReportes);
                add(ImagenClinica);
                add(ImagenInfracciones);
                add(ImagenEntidades);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Examenes);
                add(Conductores);
                add(Licencias);
                add(Inicio);
                add(Reportes);
                add(Clinica);
                add(Infracciones);
                add(Entidades);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionEntidades()
    {
        limpiarFiltros();
        GestorTablas.configurarColumnasEntidades(ColumnaDirectorEntidad, ColumnaNombreEntidad,ColumnaDireccionEntidad, ColumnaTelefonoEntidad, ColumnaCorreoEntidad, ColumnaDetallesEntidad);
        GestorTablas.cargarTablaEntidades(TablaEntidad,"");
        
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias, PanelConductores,PanelReportes, PanelExamenes, PanelClinica, PanelAutoescuela, PanelInicio};
        GestorEscenas.mostrarOcultarPaneles(PanelEntidades,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Licencias, Reportes,Autoescuela,Clinica,Examenes};
        GestorEscenas.pintarBotones(Entidades, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-entidades-blanco.png")));
        Entidades.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenExamenes);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenInicio);
                add(ImagenReportes);
                add(ImagenClinica);
                add(ImagenInfracciones);
                add(ImagenAutoescuela);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Examenes);
                add(Conductores);
                add(Licencias);
                add(Inicio);
                add(Reportes);
                add(Clinica);
                add(Infracciones);
                add(Autoescuela);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionReportes() {
        limpiarFiltros();
        Pane[] PanelesOcultar={PanelInfracciones, PanelLicencias,PanelEntidades, PanelConductores, PanelExamenes, PanelClinica, PanelAutoescuela, PanelInicio};
        GestorEscenas.mostrarOcultarPaneles(PanelReportes,PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Examenes, Licencias,Entidades,Clinica,Autoescuela};
        GestorEscenas.pintarBotones(Reportes, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-reporte-blanco.png")));
        Reportes.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenExamenes);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenInicio);
                add(ImagenEntidades);
                add(ImagenClinica);
                add(ImagenInfracciones);
                add(ImagenAutoescuela);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Examenes);
                add(Conductores);
                add(Licencias);
                add(Inicio);
                add(Entidades);
                add(Clinica);
                add(Infracciones);
                add(Autoescuela);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
        
       
    }
    
    
    @FXML
    public void TransicionRegistrarAutoescuela()
    {
        System.out.println("Pulso");
        String Direccion = "/interfaz_usuario/administrador/sistema/menu-auxiliares/registrar/registrar-autoescuela.fxml";
        Stage Padre = (Stage) RegistrarAutoescuela.getScene().getWindow();
        
        try {
            GestorEscenas.cargarPanelAuxiliar(Padre, Direccion, true, "Registrar Autoescuela");
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
            GestorEscenas.cargarPanelAuxiliar(Padre, Direccion, true, "Registrar Clinica");
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
            GestorEscenas.cargarPanelAuxiliar(Padre, Direccion, true, "Registrar Infraccion");
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
            GestorEscenas.cargarPanelAuxiliar(Padre, Direccion, true, "Registrar examen");
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
            GestorEscenas.cargarPanelAuxiliar(Padre, Direccion, true, "Registrar Conductor");
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
            GestorEscenas.cargarPanelAuxiliar(Padre, Direccion, true, "Registrar Licencia");
        } catch (Exception ex) {
            //CAPTURAR ERROR
           
        }
        
    }

    @Override
    public void Iniciar(MenuEstadisticas MenuEstadisticas) 
    {
        LabelUsuarioNombre.setText(GestorEscenas.abreviarNombre(Autentificador.usuario.getNombre()));
        Tooltip MouseNombre= new Tooltip(Autentificador.usuario.getNombre());
        MouseNombre.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        LabelUsuarioNombre.setTooltip(MouseNombre);
        LabelUsuarioNombre.setMaxWidth(100);
        LabelCorreoUsuario.setText(GestorEscenas.seguridadCorreo(Autentificador.usuario.getCorreo()));
        CargarEstadisticas(MenuEstadisticas);
    }
    
    @FXML
    protected void CargarEstadisticas(MenuEstadisticas MenuEstadisticas)
    {
        
        LabelUltimoInicioSesion.setText("Último inicio sesion hace " + MenuEstadisticas.GetEstadisticaUsuario().GetUltimoInicioSesion());
        LabelIniciosSesion.setText(String.valueOf(MenuEstadisticas.GetEstadisticaUsuario().GetCantidadIniciosSesion()));
        for(Estadistica e : MenuEstadisticas.getEstadisticas())
        {
            switch(e.GetCategoria())
            {
                case "Cantidad Conductores":
                    LabelTotalConductores.setText(String.valueOf(Math.round(e.GetValor())));
                    break;
                case "Cantidad Entidades":
                    LabelTotalEntidades.setText(String.valueOf(Math.round(e.GetValor())));
                    break;
                case "Examenes reprobados":
                    LabelExamenesReprobados.setText(String.valueOf(Math.round(e.GetValor())));
                    break;
                case "Leve":
                    LabelInfraccionLeve.setText(String.valueOf(Math.round(e.GetValor()))+"%");
                    break;
                case "Grave":
                    LabelInfraccionGrave.setText(String.valueOf(Math.round(e.GetValor()))+"%");
                    break;
                case "Muy grave":
                    LabelInfraccionMGrave.setText(String.valueOf(Math.round(e.GetValor()))+"%");
                    break;
                case "A":
                    LabelLicenciaA.setText(String.valueOf(Math.round(e.GetValor()))+"%");
                    break;
                case "B":
                    LabelLicenciaB.setText(String.valueOf(Math.round(e.GetValor()))+"%");
                    break;
                case "C":
                    LabelLicenciaC.setText(String.valueOf(Math.round(e.GetValor()))+"%");
                    break;
                case "D":
                    LabelLicenciaD.setText(String.valueOf(Math.round(e.GetValor()))+"%");
                    break;
                case "E":
                    LabelLicenciaE.setText(String.valueOf(Math.round(e.GetValor()))+"%");
                    break;
            }
        }
        Label[] PorcentajesBarra = {LabelLicenciaA,LabelLicenciaB,LabelLicenciaC,LabelLicenciaD,LabelLicenciaE,LabelInfraccionLeve,LabelInfraccionGrave,LabelInfraccionMGrave};
        ProgressBar[] BarrasProgreso = {BarraProgresoLicenciaA,BarraProgresoLicenciaB,BarraProgresoLicenciaC,BarraProgresoLicenciaD,BarraProgresoLicenciaE,BarraProgresoInfraccionLeve,BarraProgresoInfraccionGrave,BarraProgresoInfraccionMGrave};
        GestorEscenas.progresoLabel(PorcentajesBarra, BarrasProgreso);
    }
    
    @FXML
    public void ExportarConductores()
    {
        try {
            GestorExcel.exportarConductoresExcel(TablaConductor.getItems(), "Tabla conductores SGL",(Stage)BotonExportarConductores.getScene().getWindow());
        } catch (Exception ex) {
            GestorEscenas.cargarError(BotonExportarConductores.getScene().getWindow(), ex);
        }
    }
    
    @FXML
    public void ExportarAutoescuelas()
    {
        try {
            GestorExcel.exportarEntidadesTipoExcel(TablaAE.getItems(), "Tabla autoescuela SGL",(Stage)btnExportarAutoescuela.getScene().getWindow());
        } catch (Exception ex) {
            GestorEscenas.cargarError(btnExportarAutoescuela.getScene().getWindow(), ex);
        }
    }
    
    @FXML
    public void ExportarClinicas()
    {
        try {
            GestorExcel.exportarEntidadesTipoExcel(TablaClinica.getItems(), "Tabla clínicas SGL",(Stage)btnExportarClinica.getScene().getWindow());
        } catch (Exception ex) {
            GestorEscenas.cargarError(btnExportarClinica.getScene().getWindow(), ex);
        }
    }
    
    @FXML
    public void ExportarEntidades()
    {
        try {
            GestorExcel.exportarEntidadesGeneralesExcel(TablaEntidad.getItems(), "Tabla entidades SGL",(Stage)btnExportarEntidades.getScene().getWindow());
        } catch (Exception ex) {
            GestorEscenas.cargarError(btnExportarEntidades.getScene().getWindow(), ex);
        }
    }
        
    
    @FXML
    public void ExportarInfracciones()
    {
        try {
            GestorExcel.exportarInfraccionesExcel(TablaInfraccion.getItems(), "Tabla infracciones SGL",(Stage)btnExportarInfracciones.getScene().getWindow());
        } catch (Exception ex) {
            GestorEscenas.cargarError(btnExportarInfracciones.getScene().getWindow(), ex);
        }
    }
    
    
    @FXML
    public void ExportarExamenes()
    {
        try {
            GestorExcel.exportarExamenesExcel(TablaExamenes.getItems(), "Tabla examenes SGL",(Stage)btnExportarExamenes.getScene().getWindow());
        } catch (Exception ex) {
            GestorEscenas.cargarError(btnExportarExamenes.getScene().getWindow(), ex);
        }
    }
    
    @FXML
    public void ExportarLicencias()
    {
        try {
            GestorExcel.exportarLicenciasExcel(TablaLicencia.getItems(), "Tabla licencias SGL",(Stage)btnExportarLicencias.getScene().getWindow());
        } catch (Exception ex) {
            GestorEscenas.cargarError(btnExportarLicencias.getScene().getWindow(), ex);
        }
    }
    
}
