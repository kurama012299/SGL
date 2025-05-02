/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_interfaces;



import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;




/**
 *
 * @author Angel Hernandez
 */
public class GestorEscenas  {

    public static void CargarMenu(String Direccion) throws Exception {
        try {

            // Usar ruta relativa desde los recursos
            URL Url = GestorEscenas.class.getResource(Direccion);

            FXMLLoader Cargador = new FXMLLoader(Url);
            Parent Ruta = Cargador.load();

            Stage Ventana = new Stage();
            Ventana.setScene(new Scene(Ruta));
            Ventana.setTitle("SGL");
            Ventana.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("No se encuentra la interfaz");
        }
    }

    public static void CargarAlertaError(Window Padre, String Direccion, String Titulo, String Mensaje) throws Exception {
        CargarPanelAuxiliar(Padre, Direccion, true, Titulo);
    }

    public static void CargarPanelAuxiliar(Window Padre, String Direccion, boolean Modal, String Titulo) throws Exception {
        try {

            URL Url = GestorEscenas.class.getResource(Direccion);
            FXMLLoader Cargador = new FXMLLoader(Url);
            Parent Ruta = Cargador.load();

            Scene Escena = new Scene(Ruta);
            Stage Ventana = new Stage();
            Ventana.initOwner(Padre);

            if (Modal) {
                Ventana.initModality(Modality.WINDOW_MODAL);
            }

            Ventana.setTitle(Titulo);
            Ventana.setScene(Escena);

            if (Modal) {
                Ventana.showAndWait();
            } else {
                Ventana.show();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al cargar la interfaz");
        }
    }

    //Funcion para mostrar el primer panel y los demas ocultarlos
    public static void MostrarOcultarPaneles(Pane Mostrar, Pane... Ocultar) {
        Mostrar.setVisible(true);
        for (Pane Panel : Ocultar) {
            if (Panel.isVisible()) {
                Panel.setVisible(false);
            }
        }
    }

    //Funcion para pintar el primer boton y los demas ocultarlos
    public static void PintarBotones(JFXButton boton, JFXButton... botones) {
        boton.getStyleClass().add("active");
        for (JFXButton bot : botones) {
            bot.getStyleClass().removeAll("active");
        }
    }

    //Funcion para Ingresarle el icono a la ventana por parametro,asi como titulo
    public static void PonerIconoVentana(HBox box, String TituloVentana) {
        Platform.runLater(() -> {
            Stage stage = (Stage) box.getScene().getWindow();
            try {
                Image icon = new Image(GestorEscenas.class.getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-empresa.png"));
                stage.getIcons().add(icon);
                stage.setTitle(TituloVentana);
            } catch (NullPointerException e) {
                System.err.println("No se encontró el archivo de icono");
            }
        });
    }
    
    //Funcion para Unir el los labels con las barras de progreso asi dicen mismo porcentaje
    public static void ProgresoLabel(Label[] label, ProgressBar[] barra) {
        int i=0;
        for(Label l:label)
        {
            l.textProperty().bind(Bindings.concat(Bindings.format("%.0f",Bindings.multiply(barra[i].progressProperty(), 100)),"%"));
            i++;
        }
    }
    
    //Funcion para evitar escribir las teclas de enter y espacio
    public static void ConsumirTecla(JFXButton... Boton)
    {
        for (JFXButton b : Boton) {
            b.addEventFilter(KeyEvent.KEY_PRESSED, evento -> {
                if (evento.getCode() == KeyCode.ENTER || evento.getCode() == KeyCode.SPACE) {
                    evento.consume();
                }
            });
        }
    }
    
    //Funcion para cambiar iconos de ciertos botones con dos arreglos
    public static void CambiarIconos(ArrayList<ImageView> Imagenes, ArrayList<JFXButton> Botones) {
        for(int i=0;i<Botones.size();i++)
        {
            Botones.get(i).setGraphic(Imagenes.get(i));
        }
    }
     
    //Funcion para cuadno llegue a un extremo determinado de texto un label haga salto de linea
    public static void SaltoLineaEtiqueta(Label Etiqueta)
    {
        Etiqueta.setWrapText(true);
        Etiqueta.setMaxWidth(100);
    }
    
    
}
