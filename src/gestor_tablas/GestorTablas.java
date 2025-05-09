/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_tablas;

import gestor_interfaces.GestorEscenas;
import java.io.File;
import java.util.Objects;
import java.util.Set;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logica.persona.implementaciones.ServicioConductor;
import logica.persona.modelos.Conductor;

/**
 *
 * @author Angel Hernandez
 */
public class GestorTablas {

    public static <T> void LlenarColumnaDetalles(TableView<T> Tabla, int cantidadFilas) {
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
                            MostrarDetalles(objetoFila);
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
    
    
    private static void MostrarDetalles(Object Objeto)
    {
        if(Objeto instanceof Conductor)
        {
            Conductor Conductor = (Conductor) Objeto;
            System.out.println(Conductor);
        }
    }
    
    
    public static <T> void LlenarColumnaFotos(TableView<T> Tabla, int cantidadFilas) {
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
            TablaConductor.getItems().clear();
            TablaConductor.setItems(Conductores);
        } catch (Exception ex) {
            //Capturar Error
        }
    }
}
