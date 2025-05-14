/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_tablas;


import gestor_interfaces.GestorEscenas;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorVerMasConductor;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.entidad.implementaciones.ServicioEntidad;
import logica.entidad.modelos.EntidadRelacionada;
import logica.examen_conduccion.implementaciones.ServiciosExamenes;
import logica.examen_conduccion.modelos.ExamenConduccion;
import logica.examen_medico.implementaciones.ServiciosExamenesMedicos;
import logica.examen_medico.modelos.ExamenMedico;
import logica.infraccion.implementaciones.ServicioInfraccion;
import logica.infraccion.modelos.Infraccion;
import logica.licencia.implementaciones.ServicioLicencia;
import logica.licencia.modelos.Licencia;
import logica.persona.implementaciones.ServicioConductor;
import logica.persona.modelos.Conductor;
import logica.persona.modelos.Persona;

/**
 *
 * @author Angel Hernandez
 */
public class GestorTablas {

    private static <T> void LlenarColumnaDetalles(TableView<T> Tabla, int cantidadFilas) {
        TableColumn<T, ?> ultimaColumna = (TableColumn<T, ?>) Tabla.getColumns().get(Tabla.getColumns().size() - 1);
        Platform.runLater(() -> {
            // Buscar TODAS las celdas visibles
            Set<Node> todasLasCeldas = Tabla.lookupAll(".table-cell");
            for (Node nodo : todasLasCeldas) {
                if (nodo instanceof TableCell) {
                    TableCell<T, ?> celda = (TableCell<T, ?>) nodo;
                    if (celda.getTableColumn().equals(ultimaColumna)) {
                        Label label = new Label("Ver más");
                        label.setStyle("-fx-cursor: hand; -fx-underline: true; -fx-text-fill: #8000ff; -fx-font-weight: bold;");
                        label.setOnMouseClicked(event -> {
                            T objetoFila = Tabla.getItems().get(celda.getIndex());
                            try {
                                MostrarDetalles(objetoFila,label.getScene().getWindow());
                            } catch (Exception ex) {
                                System.out.println("Error al cargar el menu ver mas: "+ex.getMessage());
                                GestorEscenas.CargarError(label.getScene().getWindow(), ex);
                            }
                        });                        
                        celda.setGraphic(label);
                        if (celda.getIndex() == cantidadFilas) {
                            break;
                        }             // Salir del bucle una vez encontrada
                    }
                }
            }
        });
    }
    
    
    private static void MostrarDetalles(Object Objeto,Window Ventana) throws Exception
    {
        if(Objeto instanceof Conductor)
        {
            Conductor Conductor = (Conductor) Objeto;
            Licencia Licencia = ServicioLicencia.ObtenerLicenciaPorId(Conductor.getIdLicencia());
            
            GestorEscenas.CargarVerMasConductor(Ventana, Conductor,Licencia);
        }
        else if(Objeto instanceof ExamenConduccion)
        {
            if(((ExamenConduccion)Objeto).getTipo().equalsIgnoreCase("Práctico") || ((ExamenConduccion)Objeto).getTipo().equalsIgnoreCase("Teórico"))
            {
                ExamenConduccion ExamenConduccion =(ExamenConduccion) Objeto;
                GestorEscenas.CargarVerMasExamenes(Ventana, ExamenConduccion, null);
            }
            else
            {
                ObservableList<ExamenMedico> ExamenesMedicos = ServiciosExamenesMedicos.ObtenerExamenesMedico();
                for(ExamenMedico Examen: ExamenesMedicos)
                {
                    if(Examen.getId().equals(((ExamenConduccion)Objeto).getId()))
                    {
                        ExamenMedico ExamenMedico =(ExamenMedico) Examen;
                        GestorEscenas.CargarVerMasExamenes(Ventana, null, ExamenMedico);
                    }
                }
                
            }
        }
        else if(Objeto instanceof ExamenMedico)    
        {
            ExamenMedico ExamenMedico =(ExamenMedico) Objeto;
            GestorEscenas.CargarVerMasExamenesMedicosAdmin(Ventana,ExamenMedico);
        }
            
            
            
        
    }
    
    
    private static <T> void LlenarColumnaFotos(TableView<T> Tabla, int cantidadFilas) {
        Image defaultIcon = new Image(
        Thread.currentThread().getContextClassLoader().getResourceAsStream(
            "interfaz_usuario/recursos_compartidos/imagenes/ico-cuenta-usuario.png"
        )
    );
        TableColumn<T, ?> PrimeraColumna = (TableColumn<T, ?>) Tabla.getColumns().get(0);
        Platform.runLater(() -> {
            // Buscar TODAS las celdas visibles
            Set<Node> todasLasCeldas = Tabla.lookupAll(".table-cell");
            for (Node nodo : todasLasCeldas) {
                if (nodo instanceof TableCell) {
                    TableCell<T, ?> celda = (TableCell<T, ?>) nodo;
                    if (celda.getTableColumn().equals(PrimeraColumna)) {
                        ImageView imageView = new ImageView();
                        imageView.setFitHeight(40);
                        imageView.setFitWidth(40);
                        imageView.setPreserveRatio(true);
                        imageView.setImage(defaultIcon);
                        celda.setGraphic(imageView);
                        if (celda.getIndex() == cantidadFilas) {
                            break;
                        }
                    }
                }
            }
        });
    }
    
    public static void ConfigurarColumnasExamenes(
            TableColumn<ExamenConduccion, String> ColumnaFotoExamen,
            TableColumn<ExamenConduccion, String> ColumnaExaminadoExamen,
            TableColumn<ExamenConduccion, String> ColumnaTipoExamen,
            TableColumn<ExamenConduccion, Date> ColumnaFechaExamen,
            TableColumn<ExamenConduccion, String> ColumnaExaminadorExamen,
            TableColumn<ExamenConduccion, String> ColumnaResultadoExamen,
            TableColumn<ExamenConduccion, String> ColumnaDetallesExamen) {

        // Configuración de la columna de nombre completo
        ColumnaExaminadoExamen.setCellValueFactory(cellData -> {
            ExamenConduccion Examen = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", Examen.getPersona().getNombre(), Examen.getPersona().getApellidos())
            );
        });
        ColumnaExaminadorExamen.setCellValueFactory(cellData -> {
            ExamenConduccion Examen = cellData.getValue();
            return new SimpleStringProperty(Examen.getExaminador().getNombre()
            );
        });

        ColumnaTipoExamen.setCellValueFactory(cellData -> {
            ExamenConduccion Examen = cellData.getValue();
            return new SimpleStringProperty(Examen.getTipo());
        });

        ColumnaResultadoExamen.setCellValueFactory(cellData -> {
            ExamenConduccion Examen = cellData.getValue();
            String result;
            if (Examen.isAprobado()) {
                return new SimpleStringProperty("Aprobado");
            }
            return new SimpleStringProperty("Reprobado");
        });

        // Configuración estándar para otras columnas
        ConfigurarColumnaStandard(ColumnaFechaExamen, "Fecha");
    }

    public static void ConfigurarColumnasConductores(
            TableColumn<Conductor, String> ColumnaFoto,
            TableColumn<Conductor, String> ColumnaNombre,
            TableColumn<Conductor, String> ColumnaCI,
            TableColumn<Conductor, String> ColumnaTelefono,
            TableColumn<Conductor, String> ColumnaCorreo) {

        // Configuración de la columna de nombre completo
        ColumnaNombre.setCellValueFactory(cellData -> {
            Conductor conductor = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", conductor.getNombre(), conductor.getApellidos())
            );
        });

        ColumnaCI.setCellValueFactory(cellData -> {
            Conductor conductor = cellData.getValue();
            return new SimpleStringProperty(conductor.getCI());
        });

        // Configuración estándar para otras columnas
        ConfigurarColumnaStandard(ColumnaTelefono, "Telefono");
        ConfigurarColumnaStandard(ColumnaCorreo, "Correo");
    }

    private static <T, S> void ConfigurarColumnaStandard(TableColumn<T, S> columna, String propiedad) {
        if (columna != null) {
            columna.setCellValueFactory(new PropertyValueFactory<>(propiedad));
        }
    }

    public static void CargarTablaConductores(TableView<Conductor> TablaConductor) {
        try {
            ObservableList<Conductor> Conductores = ServicioConductor.ObtenerConductores();
            TablaConductor.setItems(Conductores);
            LlenarColumnaDetalles(TablaConductor, TablaConductor.getItems().size() - 1);
            LlenarColumnaFotos(TablaConductor, TablaConductor.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }

    public static void CargarTablaExamenes(TableView<ExamenConduccion> TablaExamenes) {
        try {
            ObservableList<ExamenConduccion> ExamenesPracticos = ServiciosExamenes.ObtenerExamenesPracticos();
            ObservableList<ExamenConduccion> ExamenesTeoricos = ServiciosExamenes.ObtenerExamenesTeoricos();
            ObservableList<ExamenMedico> ExamenesMedicos = ServiciosExamenesMedicos.ObtenerExamenesMedico();
            ObservableList<ExamenConduccion> ExamenesMedicosNuevos = FXCollections.observableArrayList();
            for (int i = 0; i < ExamenesMedicos.size(); i++) {
                ExamenConduccion Examen = new ExamenConduccion(ExamenesMedicos.get(i).getId(), ExamenesMedicos.get(i).getFecha(), ExamenesMedicos.get(i).isAprobado(), ExamenesMedicos.get(i).getEntidad(), ExamenesMedicos.get(i).getPersona(), ExamenesMedicos.get(i).getExaminador(), ExamenesMedicos.get(i).getTipo());
                ExamenesMedicosNuevos.add(Examen);
            }

            ObservableList<ExamenConduccion> Examenes = FXCollections.concat(ExamenesTeoricos, ExamenesPracticos, ExamenesMedicosNuevos);
            TablaExamenes.setItems(Examenes);
            LlenarColumnaDetalles(TablaExamenes, TablaExamenes.getItems().size() - 1);
            LlenarColumnaFotos(TablaExamenes, TablaExamenes.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }

    public static void CargarTablaExamenesPracticosAdminAutoescuela(TableView<ExamenConduccion> TablaExamenes) {
        try {
            ObservableList<ExamenConduccion> ExamenesPracticos = ServiciosExamenes.ObtenerExamenesPracticos();
            TablaExamenes.setItems(ExamenesPracticos);
            LlenarColumnaDetalles(TablaExamenes, TablaExamenes.getItems().size() - 1);
            LlenarColumnaFotos(TablaExamenes, TablaExamenes.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }

    public static void CargarTablaExamenesTeoricosAdminAutoescuela(TableView<ExamenConduccion> TablaExamenes) {
        try {
            ObservableList<ExamenConduccion> ExamenesTeoricos = ServiciosExamenes.ObtenerExamenesTeoricos();
            TablaExamenes.setItems(ExamenesTeoricos);
            LlenarColumnaDetalles(TablaExamenes, TablaExamenes.getItems().size() - 1);
            LlenarColumnaFotos(TablaExamenes, TablaExamenes.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }

    public static void ConfigurarColumnasExamenesAdminAutoescuela(
            TableColumn<ExamenConduccion, String> ColumnaFotoExamen,
            TableColumn<ExamenConduccion, String> ColumnaExaminadoExamen,
            TableColumn<ExamenConduccion, Date> ColumnaFechaExamen,
            TableColumn<ExamenConduccion, String> ColumnaExaminadorExamen,
            TableColumn<ExamenConduccion, String> ColumnaResultadoExamen,
            TableColumn<ExamenConduccion, String> ColumnaAutoescuelaExamen,
            TableColumn<ExamenConduccion, String> ColumnaDetallesExamen) {

        // Configuración de la columna de nombre completo
        ColumnaExaminadoExamen.setCellValueFactory(cellData -> {
            ExamenConduccion Persona = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", Persona.getPersona().getNombre(), Persona.getPersona().getApellidos())
            );
        });
        ColumnaExaminadorExamen.setCellValueFactory(cellData -> {
            ExamenConduccion Persona = cellData.getValue();
            return new SimpleStringProperty(Persona.getExaminador().getNombre()
            );
        });

        ColumnaAutoescuelaExamen.setCellValueFactory(cellData -> {
            ExamenConduccion Examen = cellData.getValue();
            return new SimpleStringProperty(Examen.getEntidad().getNombre());
        });

        ColumnaResultadoExamen.setCellValueFactory(cellData -> {
            ExamenConduccion Examen = cellData.getValue();
            if (Examen.isAprobado()) {
                return new SimpleStringProperty("Aprobado");
            }
            return new SimpleStringProperty("Reprobado");
        });

        // Configuración estándar para otras columnas
        ConfigurarColumnaStandard(ColumnaFechaExamen, "Fecha");
    }

    public static void CargarTablaExamenesMedicosAdminMedico(TableView<ExamenMedico> TablaExamenesMedicos) {
        try {
            ObservableList<ExamenMedico> ExamenesMedicos = ServiciosExamenesMedicos.ObtenerExamenesMedico();
            TablaExamenesMedicos.setItems(ExamenesMedicos);
            LlenarColumnaDetalles(TablaExamenesMedicos, TablaExamenesMedicos.getItems().size() - 1);
            LlenarColumnaFotos(TablaExamenesMedicos, TablaExamenesMedicos.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }

    public static void ConfigurarColumnasExamenesMedicosAdminMedico(
            TableColumn<ExamenMedico, String> ColumnaFotoExamen,
            TableColumn<ExamenMedico, String> ColumnaExaminadoExamen,
            TableColumn<ExamenMedico, Date> ColumnaFechaExamen,
            TableColumn<ExamenMedico, String> ColumnaExaminadorExamen,
            TableColumn<ExamenMedico, String> ColumnaResultadoExamen,
            TableColumn<ExamenMedico, String> ColumnaClinicaExamen,
            TableColumn<ExamenMedico, String> ColumnaDetallesExamen) {

        // Configuración de la columna de nombre completo
        ColumnaExaminadoExamen.setCellValueFactory(cellData -> {
            ExamenMedico Examen = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", Examen.getPersona().getNombre(), Examen.getPersona().getApellidos())
            );
        });
        ColumnaExaminadorExamen.setCellValueFactory(cellData -> {
            ExamenMedico Examen = cellData.getValue();
            return new SimpleStringProperty(Examen.getExaminador().getNombre()
            );
        });

        ColumnaClinicaExamen.setCellValueFactory(cellData -> {
            ExamenMedico Examen = cellData.getValue();
            return new SimpleStringProperty(Examen.getEntidad().getNombre());
        });

        ColumnaResultadoExamen.setCellValueFactory(cellData -> {
            ExamenMedico Examen = cellData.getValue();
            if (Examen.isAprobado() && Examen.getRestricciones().isEmpty()) {
                return new SimpleStringProperty("Aprobado");
            } else if (Examen.isAprobado() && !Examen.getRestricciones().isEmpty()) {
                return new SimpleStringProperty("Aprobado Condicional");
            }
            return new SimpleStringProperty("Reprobado");
        });

        // Configuración estándar para otras columnas
        ConfigurarColumnaStandard(ColumnaFechaExamen, "Fecha");

    }

    public static void CargarTablaExamenesMedicosMedicoUnico(TableView<ExamenMedico> TablaExamenesMedicos, Long Id) {
        try {
            ObservableList<ExamenMedico> ExamenesMedicos = ServiciosExamenesMedicos.ObtenerExamenesMedicoPorIdRol(Id);
            TablaExamenesMedicos.setItems(ExamenesMedicos);
            LlenarColumnaDetalles(TablaExamenesMedicos, TablaExamenesMedicos.getItems().size() - 1);
            LlenarColumnaFotos(TablaExamenesMedicos, TablaExamenesMedicos.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }

    public static void ConfigurarColumnasExamenesMedicosMedicoUnico(
            TableColumn<ExamenMedico, String> ColumnaFotoExamen,
            TableColumn<ExamenMedico, String> ColumnaExaminadoExamen,
            TableColumn<ExamenMedico, Date> ColumnaFechaExamen,
            TableColumn<ExamenMedico, String> ColumnaResultadoExamen,
            TableColumn<ExamenMedico, String> ColumnaClinicaExamen,
            TableColumn<ExamenMedico, String> ColumnaDetallesExamen) {

        // Configuración de la columna de nombre completo
        ColumnaExaminadoExamen.setCellValueFactory(cellData -> {
            ExamenMedico Examen = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", Examen.getPersona().getNombre(), Examen.getPersona().getApellidos())
            );
        });

        ColumnaClinicaExamen.setCellValueFactory(cellData -> {
            ExamenMedico Examen = cellData.getValue();
            return new SimpleStringProperty(Examen.getEntidad().getNombre());
        });

        ColumnaResultadoExamen.setCellValueFactory(cellData -> {
            ExamenMedico Examen = cellData.getValue();
            if (Examen.isAprobado() && Examen.getRestricciones().isEmpty()) {
                return new SimpleStringProperty("Aprobado");
            } else if (Examen.isAprobado() && !Examen.getRestricciones().isEmpty()) {
                return new SimpleStringProperty("Aprobado Condicional");
            }
            return new SimpleStringProperty("Reprobado");
        });

        // Configuración estándar para otras columnas
        ConfigurarColumnaStandard(ColumnaFechaExamen, "Fecha");

    }

    public static void CargarTablaExamenesTeoricosTraAutoescuela(TableView<ExamenConduccion> TablaExamenes, Long Id) {
        try {
            ObservableList<ExamenConduccion> ExamenesTeoricos = ServiciosExamenes.ObtenerExamenesTeoricosPorIDRol(Id);
            TablaExamenes.setItems(ExamenesTeoricos);
            LlenarColumnaDetalles(TablaExamenes, TablaExamenes.getItems().size() - 1);
            LlenarColumnaFotos(TablaExamenes, TablaExamenes.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }

    public static void CargarTablaExamenesPracticosTraAutoescuela(TableView<ExamenConduccion> TablaExamenes, Long Id) {
        try {
            ObservableList<ExamenConduccion> ExamenesPracticos = ServiciosExamenes.ObtenerExamenesPracticosPorIDRol(Id);
            TablaExamenes.setItems(ExamenesPracticos);
            LlenarColumnaDetalles(TablaExamenes, TablaExamenes.getItems().size() - 1);
            LlenarColumnaFotos(TablaExamenes, TablaExamenes.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }

    public static void ConfigurarColumnasExamenesTraAutoescuela(
            TableColumn<ExamenConduccion, String> ColumnaFotoExamen,
            TableColumn<ExamenConduccion, String> ColumnaExaminadoExamen,
            TableColumn<ExamenConduccion, Date> ColumnaFechaExamen,
            TableColumn<ExamenConduccion, String> ColumnaResultadoExamen,
            TableColumn<ExamenConduccion, String> ColumnaAutoescuelaExamen,
            TableColumn<ExamenConduccion, String> ColumnaDetallesExamen) {

        // Configuración de la columna de nombre completo
        ColumnaExaminadoExamen.setCellValueFactory(cellData -> {
            ExamenConduccion Examen = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", Examen.getPersona().getNombre(), Examen.getPersona().getApellidos())
            );
        });
        ColumnaAutoescuelaExamen.setCellValueFactory(cellData -> {
            ExamenConduccion Examen = cellData.getValue();
            return new SimpleStringProperty(Examen.getEntidad().getNombre());
        });

        ColumnaResultadoExamen.setCellValueFactory(cellData -> {
            ExamenConduccion Examen = cellData.getValue();
            if (Examen.isAprobado()) {
                return new SimpleStringProperty("Aprobado");
            }
            return new SimpleStringProperty("Reprobado");
        });

        // Configuración estándar para otras columnas
        ConfigurarColumnaStandard(ColumnaFechaExamen, "Fecha");
    }

    public static void ConfigurarColumnasEntidades(
            TableColumn<EntidadRelacionada, String> ColumnaDirectorEntidad,
            TableColumn<EntidadRelacionada, String> ColumnaNombreEntidad,
            TableColumn<EntidadRelacionada, String> ColumnaDireccionEntidad,
            TableColumn<EntidadRelacionada, String> ColumnaTelefonoEntidad,
            TableColumn<EntidadRelacionada, String> ColumnaCorreoEntidad,
            TableColumn<EntidadRelacionada, String> ColumnaDetallesEntidad) {

        // Configuración estándar para otras columnas
        ConfigurarColumnaStandard(ColumnaDirectorEntidad, "NombreDirector");
        ConfigurarColumnaStandard(ColumnaNombreEntidad, "Nombre");
        ConfigurarColumnaStandard(ColumnaDireccionEntidad, "Direccion");
        ConfigurarColumnaStandard(ColumnaTelefonoEntidad, "Telefono");
        ConfigurarColumnaStandard(ColumnaCorreoEntidad, "Correo");
    }

    public static void CargarTablaEntidades(TableView<EntidadRelacionada> TablaEntidad) {
        try {
            ObservableList<EntidadRelacionada> Entidades = ServicioEntidad.ObtenerEntidadRelacionadas();
            TablaEntidad.setItems(Entidades);
            LlenarColumnaDetalles(TablaEntidad, TablaEntidad.getItems().size() - 1);
            LlenarColumnaFotos(TablaEntidad, TablaEntidad.getItems().size() - 1);
        } catch (Exception ex) {

        }
    }

    public static void ConfigurarColumnasAutoescuelas(
            TableColumn<EntidadRelacionada, String> ColumnaDirectorAE,
            TableColumn<EntidadRelacionada, String> ColumnaNombreAE,
            TableColumn<EntidadRelacionada, String> ColumnaDireccionAE,
            TableColumn<EntidadRelacionada, String> ColumnaTelefonoAE,
            TableColumn<EntidadRelacionada, String> ColumnaCorreoAE,
            TableColumn<EntidadRelacionada, String> ColumnaDetallesAE) {

        // Configuración estándar para otras columnas
        ConfigurarColumnaStandard(ColumnaDirectorAE, "NombreDirector");
        ConfigurarColumnaStandard(ColumnaNombreAE, "Nombre");
        ConfigurarColumnaStandard(ColumnaDireccionAE, "Direccion");
        ConfigurarColumnaStandard(ColumnaTelefonoAE, "Telefono");
        ConfigurarColumnaStandard(ColumnaCorreoAE, "Correo");
    }

    public static void CargarTablaAutoescuelas(TableView<EntidadRelacionada> TablaAE) {
        try {
            ObservableList<EntidadRelacionada> Autoescuelas = ServicioEntidad.ObtenerAutoescuelas();
            TablaAE.setItems(Autoescuelas);
            LlenarColumnaDetalles(TablaAE, TablaAE.getItems().size() - 1);
            LlenarColumnaFotos(TablaAE, TablaAE.getItems().size() - 1);
        } catch (Exception ex) {

        }
    }

    public static void ConfigurarColumnasClinicas(
            TableColumn<EntidadRelacionada, String> ColumnaDirectorClinica,
            TableColumn<EntidadRelacionada, String> ColumnaNombreClinica,
            TableColumn<EntidadRelacionada, String> ColumnaDireccionClinica,
            TableColumn<EntidadRelacionada, String> ColumnaTelefonoClinica,
            TableColumn<EntidadRelacionada, String> ColumnaCorreoClinica,
            TableColumn<EntidadRelacionada, String> ColumnaDetallesClinica) {

        // Configuración estándar para otras columnas
        ConfigurarColumnaStandard(ColumnaDirectorClinica, "NombreDirector");
        ConfigurarColumnaStandard(ColumnaNombreClinica, "Nombre");
        ConfigurarColumnaStandard(ColumnaDireccionClinica, "Direccion");
        ConfigurarColumnaStandard(ColumnaTelefonoClinica, "Telefono");
        ConfigurarColumnaStandard(ColumnaCorreoClinica, "Correo");
    }

    public static void CargarTablaClinicas(TableView<EntidadRelacionada> TablaClinica) {
        try {
            ObservableList<EntidadRelacionada> Clinicas = ServicioEntidad.ObtenerClinicas();
            TablaClinica.setItems(Clinicas);
            LlenarColumnaDetalles(TablaClinica, TablaClinica.getItems().size() - 1);
            LlenarColumnaFotos(TablaClinica, TablaClinica.getItems().size() - 1);
        } catch (Exception ex) {

        }
    }

    public static void ConfigurarColumnasInfracciones(
            TableColumn<Infraccion, String> ColumnaFotoInfraccion,
            TableColumn<Infraccion, String> ColumnaNombreInfraccion,
            TableColumn<Infraccion, String> ColumnaTipoInfraccion,
            TableColumn<Infraccion, Date> ColumnaFechaInfraccion,
            TableColumn<Infraccion, String> ColumnaLugarInfraccion,
            TableColumn<Infraccion, Long> ColumnaLicenciaInfraccion,
            TableColumn<Infraccion, Integer> ColumnaPtosDeducidosInfraccion,
            TableColumn<Infraccion, String> ColumnaDetallesInfraccion) {

        ColumnaTipoInfraccion.setCellValueFactory(cellData -> {
            Infraccion Gravedad = cellData.getValue();
            return new SimpleStringProperty(Gravedad.getNombreGravedad().getGravedad()
            );
        });

        ColumnaNombreInfraccion.setCellValueFactory(cellData -> {
            Infraccion Persona = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", Persona.getNombrePersona().getNombre(), Persona.getNombrePersona().getApellidos())
            );
        });

        ColumnaFotoInfraccion.setCellValueFactory(cellData -> {
            Infraccion Persona = cellData.getValue();
            return new SimpleStringProperty(Persona.getNombrePersona().getFoto()
            );
        });

        ColumnaLicenciaInfraccion.setCellValueFactory((TableColumn.CellDataFeatures<Infraccion, Long> cellData) -> {
            Infraccion Persona = cellData.getValue();
            return new SimpleObjectProperty<>(Persona.getNombrePersona().getIdLicencia()
            );
        });

        // Configuración estándar para otras columnas
        ConfigurarColumnaStandard(ColumnaFechaInfraccion, "Fecha");
        ConfigurarColumnaStandard(ColumnaLugarInfraccion, "Lugar");
        ConfigurarColumnaStandard(ColumnaPtosDeducidosInfraccion, "PuntosDeducidos");

    }

    public static void CargarTablaInfracciones(TableView<Infraccion> TablaInfraccion) {
        try {
            ObservableList<Infraccion> Infracciones = ServicioInfraccion.ObtenerInfracciones();
            TablaInfraccion.setItems(Infracciones);
            LlenarColumnaDetalles(TablaInfraccion, TablaInfraccion.getItems().size() - 1);
            LlenarColumnaFotos(TablaInfraccion, TablaInfraccion.getItems().size() - 1);
        } catch (Exception ex) {

        }
    }

    public static void ConfigurarColumnasLicencias(
            TableColumn<Licencia, String> ColumnaFotoLicencia,
            TableColumn<Licencia, String> ColumnaNombreLicencia,
            TableColumn<Licencia, String> ColumnaTipoLicencia,
            TableColumn<Licencia, Date> ColumnaEmisionLicencia,
            TableColumn<Licencia, Date> ColumnaVencimientoLicencia,
            TableColumn<Licencia, Integer> ColumnaPuntosLicencia,
            TableColumn<Licencia, String> ColumnaDetallesLicencia) {

        ColumnaTipoLicencia.setCellValueFactory(cellData -> {
            Licencia Tipo = cellData.getValue();
            return new SimpleStringProperty(Tipo.getTipoLic().getTipo()
            );
        });

        ColumnaNombreLicencia.setCellValueFactory(cellData -> {
            Licencia Persona = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", Persona.getPersona().getNombre(), Persona.getPersona().getApellidos())
            );
        });

        ColumnaFotoLicencia.setCellValueFactory(cellData -> {
            Licencia Persona = cellData.getValue();
            return new SimpleStringProperty(Persona.getPersona().getFoto()
            );
        });

        // Configuración estándar para otras columnas
        ConfigurarColumnaStandard(ColumnaEmisionLicencia, "FechaEmision");
        ConfigurarColumnaStandard(ColumnaVencimientoLicencia, "FechaVencimiento");
        ConfigurarColumnaStandard(ColumnaPuntosLicencia, "CantPuntos");

    }

    public static void CargarTablaLicencias(TableView<Licencia> TablaLicencia) {
        try {
            ObservableList<Licencia> Licencias = ServicioLicencia.ObtenerLicencias();
            TablaLicencia.setItems(Licencias);
            LlenarColumnaDetalles(TablaLicencia, TablaLicencia.getItems().size() - 1);
            LlenarColumnaFotos(TablaLicencia, TablaLicencia.getItems().size() - 1);
        } catch (Exception ex) {

        }
    }

}
