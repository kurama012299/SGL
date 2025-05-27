/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_tablas;

import gestor_interfaces.GestorEscenas;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import logica.autentificacion.Autentificador;
import logica.entidad.implementaciones.ServicioEntidad;
import logica.entidad.modelos.EntidadRelacionada;
import logica.examen_conduccion.implementaciones.ServiciosExamenesConduccion;
import logica.examen_conduccion.modelos.ExamenConduccion;
import logica.examen_medico.implementaciones.ServiciosExamenesMedicos;
import logica.examen_medico.modelos.ExamenMedico;
import logica.infraccion.implementaciones.ServicioInfraccion;
import logica.infraccion.modelos.Infraccion;
import logica.licencia.implementaciones.ServicioLicencia;
import logica.licencia.modelos.Licencia;
import logica.persona.implementaciones.ServicioConductor;
import logica.persona.modelos.Conductor;



public class GestorTablas {

    private static <T> void llenarColumnaDetalles(TableView<T> Tabla, int cantidadFilas) {
        TableColumn<T, ?> ultimaColumna = (TableColumn<T, ?>) Tabla.getColumns().get(Tabla.getColumns().size() - 1);
        Platform.runLater(() -> {
            // Buscar TODAS las celdas visibles
            Set<Node> todasLasCeldas = Tabla.lookupAll(".table-cell");
            for (Node nodo : todasLasCeldas) {
                if (nodo instanceof TableCell) {
                    TableCell<T, ?> celda = (TableCell<T, ?>) nodo;
                    if (celda.getTableColumn().equals(ultimaColumna)) {
                        Label lblEtiqueta = new Label("Ver más");
                        lblEtiqueta.setStyle("-fx-cursor: hand; -fx-underline: true; -fx-text-fill: #8000ff; -fx-font-weight: bold;");
                        lblEtiqueta.setOnMouseClicked(event -> {
                            T objetoFila = Tabla.getItems().get(celda.getIndex());
                            try {
                                mostrarDetalles(objetoFila, lblEtiqueta.getScene().getWindow());
                            } catch (Exception ex) {
                                System.out.println("Error al cargar el menu ver mas: " + ex.getMessage());
                                GestorEscenas.cargarError(lblEtiqueta.getScene().getWindow(), ex);
                            }
                        });
                        celda.setGraphic(lblEtiqueta);
                        if (celda.getIndex() == cantidadFilas) {
                            break;
                        }             // Salir del bucle una vez encontrada
                    }
                }
            }
        });
    }

    
    private static void mostrarDetalles(Object objeto, Window ventana) throws Exception {
        switch (Autentificador.usuario.getRol()) {
            case "Administrador":
                if (objeto instanceof Conductor) {
                    Conductor conductor = (Conductor) objeto;
                    Licencia licencia = ServicioLicencia.ObtenerLicenciaPorId(conductor.getIdLicencia());
                    GestorEscenas.cargarVerMasConductores(ventana, conductor, licencia);

                } else if (objeto instanceof ExamenConduccion && !(objeto instanceof ExamenMedico)) {
                    if (((ExamenConduccion) objeto).getTipo().equalsIgnoreCase("Práctico") || ((ExamenConduccion) objeto).getTipo().equalsIgnoreCase("Teórico")) {
                        ExamenConduccion examenConduccion = (ExamenConduccion) objeto;
                        GestorEscenas.cargarVerMasExamenes(ventana, examenConduccion, null);
                    } else {
                        ObservableList<ExamenMedico> examenesMedicos = ServiciosExamenesMedicos.ObtenerExamenesMedico();
                        for (ExamenMedico examen : examenesMedicos) {
                            if (examen.getId().equals(((ExamenConduccion) objeto).getId())) {
                                ExamenMedico examenMedico = (ExamenMedico) examen;
                                GestorEscenas.cargarVerMasExamenes(ventana, null, examenMedico);
                            }
                        }
                    }
                } else if (objeto instanceof Infraccion) {
                    Infraccion infraccion = (Infraccion) objeto;
                    Licencia licencia = ServicioLicencia.ObtenerLicenciaPorId(infraccion.getIdLicencia());
                    GestorEscenas.cargarVerMasInfraccion(ventana, infraccion, licencia);
                } else if (objeto instanceof Licencia) {
                    Licencia licencia = (Licencia) objeto;
                    Conductor conductor = ServicioConductor.ObtenerConductorPorIdLicencia(licencia.getId());
                    GestorEscenas.cargarVerMasLicencias(ventana, conductor, licencia);
                } else if (objeto instanceof EntidadRelacionada) {
                    EntidadRelacionada entidad = (EntidadRelacionada) objeto;
                    if(entidad.getTipoEntidad().equalsIgnoreCase("Clinica")){
                       GestorEscenas.cargarVerMasClinicas(ventana, entidad);
                    }else if(entidad.getTipoEntidad().equalsIgnoreCase("Autoescuela")){
                        GestorEscenas.cargarVerMasAutoescuelas(ventana, entidad); 
                    }else 
                    GestorEscenas.cargarVerMasEntidades(ventana, entidad);
                }

                break;
            case "Administrador autoescuela":
                if (objeto instanceof ExamenConduccion) {
                    ExamenConduccion examenConduccion = (ExamenConduccion) objeto;
                    if (examenConduccion.getTipo().equalsIgnoreCase("Práctico")) {
                        GestorEscenas.cargarVerMasExamenesPracticosAdmin(ventana, examenConduccion);
                    } else {
                        GestorEscenas.cargarVerMasExamenesTeoricosAdmin(ventana, examenConduccion);
                    }
                }
                break;
            case "Administrador médico":
                if (objeto instanceof ExamenMedico) {
                    ExamenMedico examenMedico = (ExamenMedico) objeto;
                    GestorEscenas.cargarVerMasExamenesMedicosAdmin(ventana, examenMedico);
                }

                break;
            case "Trabajador autoescuela":
                if (objeto instanceof ExamenConduccion) {
                    ExamenConduccion examenConduccion = (ExamenConduccion) objeto;
                    if (examenConduccion.getTipo().equalsIgnoreCase("Práctico")) {
                        GestorEscenas.cargarVerMasExamenesPracticosTrabajador(ventana, examenConduccion);
                    } else {
                        GestorEscenas.cargarVerMasExamenesTeoricosTrabajador(ventana, examenConduccion);
                    }
                }
                break;
            case "Médico":
                if (objeto instanceof ExamenMedico) {
                    ExamenMedico examenMedico = (ExamenMedico) objeto;
                    GestorEscenas.cargarVerMasExamenesMedicosDoctor(ventana, examenMedico);
                }
                break;
            case "Trabajador centro":

                break;
            default:
            //capturar error
            }
    }

    
    private static <T> void llenarColumnaFotos(TableView<T> tabla, int cantidadFilas) {
        Image icono = new Image(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        "interfaz_usuario/recursos_compartidos/imagenes/ico-cuenta-usuario.png"
                )
        );
        TableColumn<T, ?> primeraColumna = (TableColumn<T, ?>) tabla.getColumns().get(0);
        Platform.runLater(() -> {
            // Buscar TODAS las celdas visibles
            Set<Node> todasLasCeldas = tabla.lookupAll(".table-cell");
            for (Node nodo : todasLasCeldas) {
                if (nodo instanceof TableCell) {
                    TableCell<T, ?> celda = (TableCell<T, ?>) nodo;
                    if (celda.getTableColumn().equals(primeraColumna)) {
                        ImageView iv = new ImageView();
                        iv.setFitHeight(40);
                        iv.setFitWidth(40);
                        iv.setPreserveRatio(true);
                        iv.setImage(icono);
                        celda.setGraphic(iv);
                        if (celda.getIndex() == cantidadFilas) {
                            break;
                        }
                    }
                }
            }
        });
    }

    
    public static void ConfigurarColumnasExamenes(
            
            TableColumn<ExamenConduccion, String> columnaFotoExamen,
            TableColumn<ExamenConduccion, String> columnaExaminadoExamen,
            TableColumn<ExamenConduccion, String> columnaTipoExamen,
            TableColumn<ExamenConduccion, Date> columnaFechaExamen,
            TableColumn<ExamenConduccion, String> columnaExaminadorExamen,
            TableColumn<ExamenConduccion, String> columnaResultadoExamen,
            TableColumn<ExamenConduccion, String> columnaDetallesExamen) {
        
        

        // Configuración de la columna de nombre completo
        columnaExaminadoExamen.setCellValueFactory(cellData -> {
            ExamenConduccion examen = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", examen.getPersona().getNombre(), examen.getPersona().getApellidos())
            );
        });
        columnaExaminadorExamen.setCellValueFactory(cellData -> {
            ExamenConduccion examen = cellData.getValue();
            return new SimpleStringProperty(examen.getExaminador().getNombre()
            );
        });

        columnaTipoExamen.setCellValueFactory(cellData -> {
            ExamenConduccion examen = cellData.getValue();
            return new SimpleStringProperty(examen.getTipo());
        });

        columnaResultadoExamen.setCellValueFactory(cellData -> {
        boolean aprobado = cellData.getValue().isAprobado();
        return new SimpleStringProperty(aprobado ? "Aprobado" : "Reprobado");
    });

        
        // Configuración estándar para otras columnas
        configurarColumnaPorDefecto(columnaFechaExamen, "Fecha");
    }

    
    public static void ConfigurarColumnasConductores(
            TableColumn<Conductor, String> columnaFoto,
            TableColumn<Conductor, String> columnaNombre,
            TableColumn<Conductor, String> columnaCi,
            TableColumn<Conductor, String> columnaTelefono,
            TableColumn<Conductor, String> columnaCorreo) {

        // Configuración de la columna de nombre completo
        columnaNombre.setCellValueFactory(cellData -> {
            Conductor conductor = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", conductor.getNombre(), conductor.getApellidos())
            );
        });

        columnaCi.setCellValueFactory(cellData -> {
            Conductor conductor = cellData.getValue();
            return new SimpleStringProperty(conductor.getCI());
        });

        // Configuración estándar para otras columnas
        configurarColumnaPorDefecto(columnaTelefono, "Telefono");
        configurarColumnaPorDefecto(columnaCorreo, "Correo");
        
    }

    
    private static <T, S> void configurarColumnaPorDefecto(TableColumn<T, S> columna, String propiedad) {
        if (columna != null) {
            columna.setCellValueFactory(new PropertyValueFactory<>(propiedad));
        }
    }

    
    public static void cargarTablaConductores(TableView<Conductor> tablaConductor) {
        try {
            ObservableList<Conductor> conductores = ServicioConductor.ObtenerConductores();
            tablaConductor.setItems(conductores);
            llenarColumnaDetalles(tablaConductor, tablaConductor.getItems().size() - 1);
            llenarColumnaFotos(tablaConductor, tablaConductor.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }

    
    public static void cargarTablaExamenes(TableView<ExamenConduccion> tablaExamenes) {
        try {
            ObservableList<ExamenConduccion> examenesPracticos = ServiciosExamenesConduccion.ObtenerExamenesPracticos();
            ObservableList<ExamenConduccion> examenesTeoricos = ServiciosExamenesConduccion.ObtenerExamenesTeoricos();
            ObservableList<ExamenMedico> examenesMedicos = ServiciosExamenesMedicos.ObtenerExamenesMedico();
            ObservableList<ExamenConduccion> examenesMedicosNuevos = FXCollections.observableArrayList();
            for (int i = 0; i < examenesMedicos.size(); i++) {
                ExamenConduccion examen = new ExamenConduccion(examenesMedicos.get(i).getId(), examenesMedicos.get(i).getFecha(), examenesMedicos.get(i).isAprobado(), examenesMedicos.get(i).getEntidad(), examenesMedicos.get(i).getPersona(), examenesMedicos.get(i).getExaminador(), examenesMedicos.get(i).getTipo());
                examenesMedicosNuevos.add(examen);
            }

            ObservableList<ExamenConduccion> examenesMostrar = FXCollections.concat(examenesTeoricos, examenesPracticos, examenesMedicosNuevos);
            tablaExamenes.setItems(examenesMostrar);
            llenarColumnaDetalles(tablaExamenes, tablaExamenes.getItems().size() - 1);
            llenarColumnaFotos(tablaExamenes, tablaExamenes.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }
    

    public static void cargarTablaExamenesPracticosAdminAutoescuela(TableView<ExamenConduccion> tablaExamenes) {
        try {
            ObservableList<ExamenConduccion> examenesPracticos = ServiciosExamenesConduccion.ObtenerExamenesPracticos();
            tablaExamenes.setItems(examenesPracticos);
            llenarColumnaDetalles(tablaExamenes, tablaExamenes.getItems().size() - 1);
            llenarColumnaFotos(tablaExamenes, tablaExamenes.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }

    
    public static void cargarTablaExamenesTeoricosAdminAutoescuela(TableView<ExamenConduccion> tablaExamenes) {
        try {
            ObservableList<ExamenConduccion> examenesTeoricos = ServiciosExamenesConduccion.ObtenerExamenesTeoricos();
            tablaExamenes.setItems(examenesTeoricos);
            llenarColumnaDetalles(tablaExamenes, tablaExamenes.getItems().size() - 1);
            llenarColumnaFotos(tablaExamenes, tablaExamenes.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }
    

    public static void configurarColumnasExamenesAdminAutoescuela(
            TableColumn<ExamenConduccion, String> columnaFotoExamen,
            TableColumn<ExamenConduccion, String> columnaExaminadoExamen,
            TableColumn<ExamenConduccion, Date> columnaFechaExamen,
            TableColumn<ExamenConduccion, String> columnaExaminadorExamen,
            TableColumn<ExamenConduccion, String> columnaResultadoExamen,
            TableColumn<ExamenConduccion, String> columnaAutoescuelaExamen,
            TableColumn<ExamenConduccion, String> columnaDetallesExamen) {

        // Configuración de la columna de nombre completo
        columnaExaminadoExamen.setCellValueFactory(cellData -> {
            ExamenConduccion persona = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", persona.getPersona().getNombre(), persona.getPersona().getApellidos())
            );
        });
        columnaExaminadorExamen.setCellValueFactory(cellData -> {
            ExamenConduccion persona = cellData.getValue();
            return new SimpleStringProperty(persona.getExaminador().getNombre()
            );
        });

        columnaAutoescuelaExamen.setCellValueFactory(cellData -> {
            ExamenConduccion examen = cellData.getValue();
            return new SimpleStringProperty(examen.getEntidad().getNombre());
        });

        columnaResultadoExamen.setCellValueFactory(cellData -> {
        boolean aprobado = cellData.getValue().isAprobado();
        return new SimpleStringProperty(aprobado ? "Aprobado" : "Reprobado");
    });
    
    
    

        // Configuración estándar para otras columnas
        configurarColumnaPorDefecto(columnaFechaExamen, "Fecha");
    }

    
    public static void cargarTablaExamenesMedicosAdminMedico(TableView<ExamenMedico> tablaExamenesMedicos) {
        try {
            ObservableList<ExamenMedico> examenesMedicos = ServiciosExamenesMedicos.ObtenerExamenesMedico();
            tablaExamenesMedicos.setItems(examenesMedicos);
            llenarColumnaDetalles(tablaExamenesMedicos, tablaExamenesMedicos.getItems().size() - 1);
            llenarColumnaFotos(tablaExamenesMedicos, tablaExamenesMedicos.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }

    
    public static void configurarColumnasExamenesMedicosAdminMedico(
            TableColumn<ExamenMedico, String> columnaFotoExamen,
            TableColumn<ExamenMedico, String> columnaExaminadoExamen,
            TableColumn<ExamenMedico, Date> columnaFechaExamen,
            TableColumn<ExamenMedico, String> columnaExaminadorExamen,
            TableColumn<ExamenMedico, String> columnaResultadoExamen,
            TableColumn<ExamenMedico, String> columnaClinicaExamen,
            TableColumn<ExamenMedico, String> columnaDetallesExamen) {

        // Configuración de la columna de nombre completo
        columnaExaminadoExamen.setCellValueFactory(cellData -> {
            ExamenMedico examen = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", examen.getPersona().getNombre(), examen.getPersona().getApellidos())
            );
        });
        columnaExaminadorExamen.setCellValueFactory(cellData -> {
            ExamenMedico examen = cellData.getValue();
            return new SimpleStringProperty(examen.getExaminador().getNombre()
            );
        });

        columnaClinicaExamen.setCellValueFactory(cellData -> {
            ExamenMedico examen = cellData.getValue();
            return new SimpleStringProperty(examen.getEntidad().getNombre());
        });

        columnaResultadoExamen.setCellValueFactory(cellData -> {
        ExamenMedico examen = cellData.getValue();
        if (examen.isAprobado()) {
            return examen.getRestricciones().isEmpty() 
                ? new SimpleStringProperty("Aprobado") 
                : new SimpleStringProperty("Aprobado Condicional");
        }
        return new SimpleStringProperty("Reprobado");
    });

   
        // Configuración estándar para otras columnas
        configurarColumnaPorDefecto(columnaFechaExamen, "Fecha");

    }

    
    public static void cargarTablaExamenesMedicosMedicoUnico(TableView<ExamenMedico> tablaExamenesMedicos, Long id) {
        try {
            ObservableList<ExamenMedico> examenesMedicos = ServiciosExamenesMedicos.ObtenerExamenesMedicoPorIdRol(id);
            tablaExamenesMedicos.setItems(examenesMedicos);
            llenarColumnaDetalles(tablaExamenesMedicos, tablaExamenesMedicos.getItems().size() - 1);
            llenarColumnaFotos(tablaExamenesMedicos, tablaExamenesMedicos.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }

    
    public static void configurarColumnasExamenesMedicosMedicoUnico(
            TableColumn<ExamenMedico, String> columnaFotoExamen,
            TableColumn<ExamenMedico, String> columnaExaminadoExamen,
            TableColumn<ExamenMedico, Date> columnaFechaExamen,
            TableColumn<ExamenMedico, String> columnaResultadoExamen,
            TableColumn<ExamenMedico, String> columnaClinicaExamen,
            TableColumn<ExamenMedico, String> columnaDetallesExamen) {

        // Configuración de la columna de nombre completo
        columnaExaminadoExamen.setCellValueFactory(cellData -> {
            ExamenMedico examen = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", examen.getPersona().getNombre(), examen.getPersona().getApellidos())
            );
        });

        columnaClinicaExamen.setCellValueFactory(cellData -> {
            ExamenMedico examen = cellData.getValue();
            return new SimpleStringProperty(examen.getEntidad().getNombre());
        });

        columnaResultadoExamen.setCellValueFactory(cellData -> {
            ExamenMedico examen = cellData.getValue();
            if (examen.isAprobado() && examen.getRestricciones().isEmpty()) {
                return new SimpleStringProperty("Aprobado");
            } else if (examen.isAprobado() && !examen.getRestricciones().isEmpty()) {
                return new SimpleStringProperty("Aprobado Condicional");
            }
            return new SimpleStringProperty("Reprobado");
        });

        // Configuración estándar para otras columnas
        configurarColumnaPorDefecto(columnaFechaExamen, "Fecha");

    }

    
    public static void cargarTablaExamenesTeoricosTraAutoescuela(TableView<ExamenConduccion> tablaExamenes, Long id) {
        try {
            ObservableList<ExamenConduccion> examenesTeoricos = ServiciosExamenesConduccion.ObtenerExamenesTeoricosPorIDRol(id);
            tablaExamenes.setItems(examenesTeoricos);
            llenarColumnaDetalles(tablaExamenes, tablaExamenes.getItems().size() - 1);
            llenarColumnaFotos(tablaExamenes, tablaExamenes.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }

    
    public static void cargarTablaExamenesPracticosTraAutoescuela(TableView<ExamenConduccion> tablaExamenes, Long id) {
        try {
            ObservableList<ExamenConduccion> examenesPracticos = ServiciosExamenesConduccion.ObtenerExamenesPracticosPorIDRol(id);
            tablaExamenes.setItems(examenesPracticos);
            llenarColumnaDetalles(tablaExamenes, tablaExamenes.getItems().size() - 1);
            llenarColumnaFotos(tablaExamenes, tablaExamenes.getItems().size() - 1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }

    
    public static void configurarColumnasExamenesTraAutoescuela(
            TableColumn<ExamenConduccion, String> columnaFotoExamen,
            TableColumn<ExamenConduccion, String> columnaExaminadoExamen,
            TableColumn<ExamenConduccion, Date> columnaFechaExamen,
            TableColumn<ExamenConduccion, String> columnaResultadoExamen,
            TableColumn<ExamenConduccion, String> columnaAutoescuelaExamen,
            TableColumn<ExamenConduccion, String> columnaDetallesExamen) {

        // Configuración de la columna de nombre completo
        columnaExaminadoExamen.setCellValueFactory(cellData -> {
            ExamenConduccion examen = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", examen.getPersona().getNombre(), examen.getPersona().getApellidos())
            );
        });
        columnaAutoescuelaExamen.setCellValueFactory(cellData -> {
            ExamenConduccion examen = cellData.getValue();
            return new SimpleStringProperty(examen.getEntidad().getNombre());
        });

        columnaResultadoExamen.setCellValueFactory(cellData -> {
            ExamenConduccion examen = cellData.getValue();
            if (examen.isAprobado()) {
                return new SimpleStringProperty("Aprobado");
            }
            return new SimpleStringProperty("Reprobado");
        });

        // Configuración estándar para otras columnas
        configurarColumnaPorDefecto(columnaFechaExamen, "Fecha");
    }

    
    public static void configurarColumnasEntidades(
            TableColumn<EntidadRelacionada, String> columnaDirectorEntidad,
            TableColumn<EntidadRelacionada, String> columnaNombreEntidad,
            TableColumn<EntidadRelacionada, String> columnaDireccionEntidad,
            TableColumn<EntidadRelacionada, String> columnaTelefonoEntidad,
            TableColumn<EntidadRelacionada, String> columnaCorreoEntidad,
            TableColumn<EntidadRelacionada, String> columnaDetallesEntidad) {

        // Configuración estándar para otras columnas
        configurarColumnaPorDefecto(columnaDirectorEntidad, "NombreDirector");
        configurarColumnaPorDefecto(columnaNombreEntidad, "Nombre");
        configurarColumnaPorDefecto(columnaDireccionEntidad, "Direccion");
        configurarColumnaPorDefecto(columnaTelefonoEntidad, "Telefono");
        configurarColumnaPorDefecto(columnaCorreoEntidad, "Correo");
    }

    
    public static void cargarTablaEntidades(TableView<EntidadRelacionada> tablaEntidad) {
        try {
            ObservableList<EntidadRelacionada> entidades = ServicioEntidad.ObtenerEntidadRelacionadas();
            tablaEntidad.setItems(entidades);
            llenarColumnaDetalles(tablaEntidad, tablaEntidad.getItems().size() - 1);
        } catch (Exception ex) {

        }
    }

    
    public static void configurarColumnasAutoescuelas(
            TableColumn<EntidadRelacionada, String> columnaDirectorAE,
            TableColumn<EntidadRelacionada, String> columnaNombreAE,
            TableColumn<EntidadRelacionada, String> columnaDireccionAE,
            TableColumn<EntidadRelacionada, String> columnaTelefonoAE,
            TableColumn<EntidadRelacionada, String> columnaCorreoAE,
            TableColumn<EntidadRelacionada, String> columnaDetallesAE) {

        // Configuración estándar para otras columnas
        configurarColumnaPorDefecto(columnaDirectorAE, "NombreDirector");
        configurarColumnaPorDefecto(columnaNombreAE, "Nombre");
        configurarColumnaPorDefecto(columnaDireccionAE, "Direccion");
        configurarColumnaPorDefecto(columnaTelefonoAE, "Telefono");
        configurarColumnaPorDefecto(columnaCorreoAE, "Correo");
    }

    
    public static void cargarTablaAutoescuelas(TableView<EntidadRelacionada> tablaAE) {
        try {
            ObservableList<EntidadRelacionada> autoescuelas = ServicioEntidad.ObtenerAutoescuelas();
            tablaAE.setItems(autoescuelas);
            llenarColumnaDetalles(tablaAE, tablaAE.getItems().size() - 1);
        } catch (Exception ex) {

        }
    }

    
    public static void configurarColumnasClinicas(
            TableColumn<EntidadRelacionada, String> columnaDirectorClinica,
            TableColumn<EntidadRelacionada, String> columnaNombreClinica,
            TableColumn<EntidadRelacionada, String> columnaDireccionClinica,
            TableColumn<EntidadRelacionada, String> columnaTelefonoClinica,
            TableColumn<EntidadRelacionada, String> columnaCorreoClinica,
            TableColumn<EntidadRelacionada, String> columnaDetallesClinica) {

        // Configuración estándar para otras columnas
        configurarColumnaPorDefecto(columnaDirectorClinica, "NombreDirector");
        configurarColumnaPorDefecto(columnaNombreClinica, "Nombre");
        configurarColumnaPorDefecto(columnaDireccionClinica, "Direccion");
        configurarColumnaPorDefecto(columnaTelefonoClinica, "Telefono");
        configurarColumnaPorDefecto(columnaCorreoClinica, "Correo");
    }

    
    public static void cargarTablaClinicas(TableView<EntidadRelacionada> tablaClinica) {
        try {
            ObservableList<EntidadRelacionada> clinicas = ServicioEntidad.ObtenerClinicas();
            tablaClinica.setItems(clinicas);
            llenarColumnaDetalles(tablaClinica, tablaClinica.getItems().size() - 1);
        } catch (Exception ex) {

        }
    }

    
    public static void configurarColumnasInfracciones(
            TableColumn<Infraccion, String> columnaFotoInfraccion,
            TableColumn<Infraccion, String> columnaNombreInfraccion,
            TableColumn<Infraccion, String> columnaTipoInfraccion,
            TableColumn<Infraccion, Date> columnaFechaInfraccion,
            TableColumn<Infraccion, String> columnaLugarInfraccion,
            TableColumn<Infraccion, Long> columnaLicenciaInfraccion,
            TableColumn<Infraccion, Integer> columnaPtosDeducidosInfraccion,
            TableColumn<Infraccion, String> columnaDetallesInfraccion) {

        columnaTipoInfraccion.setCellValueFactory(cellData -> {
            Infraccion infraccion = cellData.getValue();
            return new SimpleStringProperty(infraccion.getGravedad()
            );
        });

        columnaNombreInfraccion.setCellValueFactory(cellData -> {
            Infraccion infraccion = cellData.getValue();
            Conductor conductor = null;
            try {
                conductor = ServicioConductor.ObtenerConductorPorIdLicencia(infraccion.getIdLicencia());
            } catch (Exception ex) {

            }

            return new SimpleStringProperty(
                    String.format("%s %s", conductor.getNombre(), conductor.getApellidos())
            );
        });

        // Configuración estándar para otras columnas
        columnaFotoInfraccion.setCellValueFactory(cellData -> {
            try {
                Infraccion infraccion = cellData.getValue();

                return new SimpleStringProperty(ServicioConductor.ObtenerConductorPorIdLicencia(infraccion.getIdLicencia()).getFoto()
                );
            } catch (Exception ex) {
                return null;
            }
        });

        // Configuración estándar para otras columnas
        configurarColumnaPorDefecto(columnaLicenciaInfraccion, "IdLicencia");
        configurarColumnaPorDefecto(columnaFechaInfraccion, "Fecha");
        configurarColumnaPorDefecto(columnaLugarInfraccion, "Lugar");
        configurarColumnaPorDefecto(columnaPtosDeducidosInfraccion, "PuntosDeducidos");

    }

    
    public static void cargarTablaInfracciones(TableView<Infraccion> tablaInfraccion) {
        try {
            ObservableList<Infraccion> infracciones = ServicioInfraccion.ObtenerInfracciones();
            tablaInfraccion.setItems(infracciones);
            llenarColumnaDetalles(tablaInfraccion, tablaInfraccion.getItems().size() - 1);
            llenarColumnaFotos(tablaInfraccion, tablaInfraccion.getItems().size() - 1);
        } catch (Exception ex) {

        }
    }

    
    public static void configurarColumnasLicencias(
            TableColumn<Licencia, String> columnaFotoLicencia,
            TableColumn<Licencia, String> columnaNombreLicencia,
            TableColumn<Licencia, String> columnaTipoLicencia,
            TableColumn<Licencia, Date> columnaEmisionLicencia,
            TableColumn<Licencia, Date> columnaVencimientoLicencia,
            TableColumn<Licencia, Integer> columnaPuntosLicencia,
            TableColumn<Licencia, String> columnaDetallesLicencia) {

        columnaTipoLicencia.setCellValueFactory(cellData -> {
            Licencia licencia = cellData.getValue();
            return new SimpleStringProperty(licencia.getTipo()
            );
        });

        columnaNombreLicencia.setCellValueFactory(cellData -> {
            try {
                Licencia licencia = cellData.getValue();
                Conductor conductor = null;
                conductor = ServicioConductor.ObtenerConductorPorIdLicencia(licencia.getId());
                return new SimpleStringProperty(
                        String.format("%s %s", conductor.getNombre(), conductor.getApellidos())
                );
            } catch (Exception ex) {

                return null;
            }
        });

        columnaFotoLicencia.setCellValueFactory(cellData -> {
            try {
                Licencia licencia = cellData.getValue();
                return new SimpleStringProperty(ServicioConductor.ObtenerConductorPorIdLicencia(licencia.getId()).getFoto()
                );
            } catch (Exception ex) {

                return null;
            }
        });

        // Configuración estándar para otras columnas
        configurarColumnaPorDefecto(columnaEmisionLicencia, "FechaEmision");
        configurarColumnaPorDefecto(columnaVencimientoLicencia, "FechaVencimiento");
        configurarColumnaPorDefecto(columnaPuntosLicencia, "CantPuntos");

    }

    
    public static void cargarTablaLicencias(TableView<Licencia> tablaLicencia) {
        try {
            ObservableList<Licencia> licencias = ServicioLicencia.ObtenerLicencias();
            tablaLicencia.setItems(licencias);
            llenarColumnaDetalles(tablaLicencia, tablaLicencia.getItems().size() - 1);
            llenarColumnaFotos(tablaLicencia, tablaLicencia.getItems().size() - 1);
        } catch (Exception ex) {

        }
    }

}
