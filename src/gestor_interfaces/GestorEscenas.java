/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_interfaces;



import com.jfoenix.controls.JFXButton;
import gestor_interfaces.modelos.Controlador;
import gestor_interfaces.modelos.Estadistica;
import gestor_interfaces.modelos.EstadisticaUsuario;
import gestor_interfaces.modelos.MenuEstadisticas;
import interfaz_usuario.recursos_compartidos.errores.controladores.ControladorMenuAuxiliarUnaAccion;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorVerMasConductor;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorVerMasExamenes;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.stage.StageStyle;
import javafx.stage.Window;
import logica.examen_conduccion.modelos.ExamenConduccion;
import logica.examen_medico.modelos.ExamenMedico;
import logica.autentificacion.Autentificador;
import logica.licencia.modelos.Licencia;
import logica.persona.modelos.Conductor;




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

            Controlador controlador = Cargador.getController();
            MenuEstadisticas MenuEstadisticas = new MenuEstadisticas();
            MenuEstadisticas.setEstadisticaUsuario(GestorEstadisticas.ObtenerEstadisticasUsuario(Autentificador.Usuario.getId()));
            
            ArrayList<Estadistica> Estadisticas = new ArrayList<>();
            
            switch (Autentificador.Usuario.getRol()) 
            {
                case "Administrador":
                    Estadisticas = GestorEstadisticas.ObtenerEstadisticasMenuAdministrador();
                    
                    break;

                case "Administrador autoescuela":
                    
                    break;

                case "Administrador médico":
                    
                    break;

                case "Trabajador autoescuela":
                    
                    break;

                case "Trabajador centro":
                    
                    break;

                case "Médico":
                    
                    break;
            }
            MenuEstadisticas.setEstadisticas(Estadisticas);
            controlador.Iniciar(MenuEstadisticas);

            Stage Ventana = new Stage(); 
            Ventana.setScene(new Scene(Ruta));
            Ventana.initStyle(StageStyle.UTILITY);
            Ventana.show();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            throw new Exception("No se encuentra la interfaz");
        }
    }

    public static void CargarError(Window VentanaPadre, Exception Ex) {
        try {
            // Cargar el panel de error
            URL Url = GestorEscenas.class.getResource("/interfaz_usuario/recursos_compartidos/errores/mensaje-error.fxml");
            FXMLLoader cargador = new FXMLLoader(Url);
            Parent Root = cargador.load();

            // Obtener el controlador y configurar el mensaje
            ControladorMenuAuxiliarUnaAccion controlador = cargador.getController();
            controlador.Iniciar(Ex.getMessage()); 

            // Configurar la ventana
            Stage Ventana = new Stage();
            Ventana.initStyle(StageStyle.UTILITY);
            Ventana.initOwner(VentanaPadre);
            Ventana.initModality(Modality.WINDOW_MODAL);
            Ventana.setScene(new Scene(Root));

            // Mostrar la ventana
            Ventana.showAndWait();

        } catch (IOException e) {
            // Fallback básico si falla la carga del FXML
            Alert alertaSimple = new Alert(Alert.AlertType.ERROR);
            alertaSimple.setTitle("Error crítico");
            alertaSimple.setHeaderText("No se pudo cargar el panel de error");
            alertaSimple.setContentText(e.getMessage());
            alertaSimple.showAndWait();
        }
    }

    public static void CargarPanelAuxiliar(Window Padre, String Direccion, boolean Modal, String Titulo) throws Exception {
        try {

            URL Url = GestorEscenas.class.getResource(Direccion);
            FXMLLoader Cargador = new FXMLLoader(Url);
            Parent Ruta = Cargador.load();

            Scene Escena = new Scene(Ruta);
            Stage Ventana = new Stage();
            Ventana.initOwner(Padre);
            Ventana.initStyle(StageStyle.UTILITY);

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
    
    
    public static void CargarVerMasConductor(Window Padre, Conductor Conductor,Licencia Licencia) throws Exception {
        try {
            String Direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/ver-mas/menu-ver-mas-conductores.fxml";
            URL Url = GestorEscenas.class.getResource(Direccion);
            FXMLLoader Cargador = new FXMLLoader(Url);
            Parent Ruta = Cargador.load();
            ControladorVerMasConductor controlador = Cargador.getController();
            controlador.SetDatos(Conductor,Licencia); 

            Scene Escena = new Scene(Ruta);
            Stage Ventana = new Stage();
            Ventana.initOwner(Padre);
            Ventana.initStyle(StageStyle.UTILITY);


            Ventana.initModality(Modality.WINDOW_MODAL);
            

            Ventana.setTitle("");
            Ventana.setScene(Escena);
            Ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al cargar la interfaz");
        }
    }

    
    public static void CargarVerMasExamenes(Window Padre, ExamenConduccion ExamenConduccion,ExamenMedico ExamenMedico) throws Exception {
        try {
            String Direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/ver-mas/menu-ver-mas-examenes.fxml";
            URL Url = GestorEscenas.class.getResource(Direccion);
            FXMLLoader Cargador = new FXMLLoader(Url);
            Parent Ruta = Cargador.load();
            ControladorVerMasExamenes controlador = Cargador.getController();
            controlador.SetDatos(ExamenConduccion,ExamenMedico); 

            Scene Escena = new Scene(Ruta);
            Stage Ventana = new Stage();
            Ventana.initOwner(Padre);
            Ventana.initStyle(StageStyle.UTILITY);


            Ventana.initModality(Modality.WINDOW_MODAL);
            

            Ventana.setTitle("");
            Ventana.setScene(Escena);
            Ventana.showAndWait();
          
            

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
    public static void ProgresoLabel(Label[] labels, ProgressBar[] progressBars) {
        if (progressBars == null || labels == null || progressBars.length != labels.length) {
            throw new IllegalArgumentException("Los arrays no pueden ser nulos y deben tener la misma longitud");
        }

        for (int i = 0; i < progressBars.length; i++) {
            final int index = i;
            // Listener para cambios en el Label
            labels[i].textProperty().addListener((obs, oldVal, newVal) -> {
                try {
                    // Eliminar el símbolo % si existe y convertir a double
                    String text = newVal.replace("%", "").trim();
                    double value = Double.parseDouble(text) / 100.0;
                    progressBars[index].setProgress(value);
                } catch (NumberFormatException e) {
                    // Manejar error si el texto no es un número válido
                    progressBars[index].setProgress(0);
                }
            });

            // Establecer valor inicial
            try {
                String text = labels[i].getText().replace("%", "").trim();
                double value = Double.parseDouble(text) / 100.0;
                progressBars[i].setProgress(value);
            } catch (NumberFormatException e) {
                progressBars[i].setProgress(0);
            }
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
     
    
    //Funcion para cerrar el programa
    public static void CerrarPrograma()
    {
        Platform.exit();
    }
}
