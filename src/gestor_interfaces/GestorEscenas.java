/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_interfaces;



import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import gestor_interfaces.modelos.Controlador;
import gestor_interfaces.modelos.Estadistica;
import gestor_interfaces.modelos.MenuEstadisticas;
import infraestructura.ConectorBaseDato;
import interfaz_usuario.administrador.autoescuela.controladores.ControladorVerMasExamenesPracticos;
import interfaz_usuario.administrador.autoescuela.controladores.ControladorVerMasExamenesTeoricos;
import interfaz_usuario.recursos_compartidos.errores.controladores.ControladorMenuAuxiliarUnaAccion;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorVerMasConductor;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorVerMasExamenes;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javafx.application.Platform;
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
import interfaz_usuario.administrador.medico.controladores.ControladorVerMasExamenesMedicos;
import interfaz_usuario.administrador.sistema.controladores.ControladorVerMasAutoescuelas;
import interfaz_usuario.administrador.sistema.controladores.ControladorVerMasClinicas;
import interfaz_usuario.administrador.sistema.controladores.ControladorVerMasEntidades;
import interfaz_usuario.medico.controladores.ControladorVerMasExamenesMedicosDoctor;
import interfaz_usuario.recursos_compartidos.errores.controladores.ControladorMenuAuxiliarDosAcciones;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorMenuReportesPeriodo;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorMenuReportesTipo;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorRegistrarInfraccion;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorRegistrarLicencia;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorRegistrarPersona;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorRegistrarUsuario;
import interfaz_usuario.trabajador_autoescuela.controladores.ControladorVerMasExamenesPracticosTrabajador;
import interfaz_usuario.trabajador_autoescuela.controladores.ControladorVerMasExamenesTeoricosTrabajador;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorVerMasInfracciones;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorVerMasLicencias;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import logica.entidad.modelos.EntidadRelacionada;
import logica.infraccion.modelos.Infraccion;
import logica.persona.modelos.Persona;
import java.util.Date;


/**
 *
 * @author Angel Hernandez
 */
public class GestorEscenas  {
    
    
    public static void cargarMenu(String direccion) throws Exception {
        try {

            // Usar ruta relativa desde los recurso

            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
     
            Controlador controlador = cargador.getController();
            MenuEstadisticas menuEstadisticas = new MenuEstadisticas();
            menuEstadisticas.SetEstadisticaUsuario(GestorEstadisticas.ObtenerEstadisticasUsuario(Autentificador.usuario.getId()));
            
            ArrayList<Estadistica> estadisticas = new ArrayList<>();
            
            switch (Autentificador.usuario.getRol()) 
            {
                case "Administrador":
                    estadisticas = GestorEstadisticas.ObtenerEstadisticasMenuAdministrador();
                    break;

                case "Administrador autoescuela":
                    estadisticas = GestorEstadisticas.ObtenerEstadisticasMenuAdministradorAutoescuela();
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

            menuEstadisticas.SetEstadisticas(estadisticas);
            controlador.Iniciar(menuEstadisticas);

            Stage ventana = new Stage(); 
            
            Scene scene= new Scene(ruta);
            scene.setUserData(ruta);
            ventana.setScene(scene);
            
            
            
            ventana.initStyle(StageStyle.DECORATED);
            ventana.setResizable(false);
            ventana.show();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            throw new Exception("No se encuentra la interfaz");
        }
    }
    
    
    public static void cargarError(Window ventanaPadre, Exception ex) {
        try {
            // Cargar el panel de error
            System.out.println(ex.getLocalizedMessage());
            System.out.println(ex.getMessage());
            URL url = GestorEscenas.class.getResource(GestorFXML.RutaMenuError);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent root = cargador.load();

            // Obtener el controlador y configurar el mensaje
            ControladorMenuAuxiliarUnaAccion controlador = cargador.getController();
            controlador.Iniciar(ex.getMessage()); 

            // Configurar la ventana
            Stage ventana = new Stage();
            ventana.initStyle(StageStyle.UTILITY);
            ventana.initOwner(ventanaPadre);
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.setScene(new Scene(root));

            // Mostrar la ventana
            ventana.showAndWait();

        } catch (IOException e) {
            // Fallback básico si falla la carga del FXML
            Alert alertaSimple = new Alert(Alert.AlertType.ERROR);
            alertaSimple.setTitle("Error crítico");
            alertaSimple.setHeaderText("No se pudo cargar el panel de error");
            alertaSimple.setContentText(e.getMessage());
            alertaSimple.showAndWait();
        }
    }
    
    public static void cargarConfirmacion(Window ventanaPadre, String mensaje) {
        try {
            // Cargar el panel de error
            URL url = GestorEscenas.class.getResource(GestorFXML.RutaMenuConfirmacion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent root = cargador.load();

            // Obtener el controlador y configurar el mensaje
            ControladorMenuAuxiliarUnaAccion controlador = cargador.getController();
            controlador.Iniciar(mensaje); 

            // Configurar la ventana
            Stage ventana = new Stage();
            ventana.initStyle(StageStyle.UTILITY);
            ventana.initOwner(ventanaPadre);
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.setScene(new Scene(root));

            // Mostrar la ventana
            ventana.showAndWait();

        } catch (IOException e) {
            // Fallback básico si falla la carga del FXML
            Alert alertaSimple = new Alert(Alert.AlertType.ERROR);
            alertaSimple.setTitle("Error crítico");
            alertaSimple.setHeaderText("No se pudo cargar el panel de error");
            alertaSimple.setContentText(e.getMessage());
            alertaSimple.showAndWait();
        }
    }

    
    public static void cargarMensajeCerrarSesion(Window ventanaPadre) {
        
        ControladorMenuAuxiliarDosAcciones controlador=null;
        
        try {
            // Cargar el panel de error
            URL url = GestorEscenas.class.getResource(GestorFXML.RutaMenuCerrarSesion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent root = cargador.load();

            // Obtener el controlador y configurar el mensaje
            controlador = cargador.getController();
           

            // Configurar la ventana
            Stage ventana = new Stage();
            ventana.initStyle(StageStyle.UTILITY);
            ventana.initOwner(ventanaPadre);
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.setScene(new Scene(root));

            // Mostrar la ventana
            ventana.showAndWait();

        } catch (IOException e) {
            // Fallback básico si falla la carga del FXML
            Alert alertaSimple = new Alert(Alert.AlertType.ERROR);
            alertaSimple.setTitle("Error crítico");
            alertaSimple.setHeaderText("No se pudo cargar el panel de error");
            alertaSimple.setContentText(e.getMessage());
            alertaSimple.showAndWait();
        }
       
        if (controlador.esAceptado()) {
            cerrarPrograma();
        }
    }

    
    public static void cargarPanelAuxiliar(Window padre, String direccion, boolean esModal, String titulo) throws Exception {
        try {


            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();

            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);

            if (esModal) {
                ventana.initModality(Modality.WINDOW_MODAL);
            }

            ventana.setTitle(titulo);
            ventana.setScene(escena);

            if (esModal) {
                ventana.showAndWait();
            } else {
                ventana.show();
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al cargar la interfaz");
        }
    }
    
   
    public static void cargarVerMasConductores(Window padre, Conductor conductor,Licencia licencia) throws Exception {
        try {
            String direccion = GestorFXML.RutaVerMasConductores;
            URL Url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(Url);
            Parent Ruta = cargador.load();
            ControladorVerMasConductor controlador = cargador.getController();
            controlador.SetDatos(conductor,licencia); 

            Scene escena = new Scene(Ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);
            ventana.setResizable(false);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al cargar la interfaz");
        }
    }
    
    public static void cargarMenuReportesPeriodo(Window padre, String reporte) throws Exception {
        try {
            String direccion = GestorFXML.RutaMenuReportesPerido;
            URL Url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(Url);
            Parent Ruta = cargador.load();
            ControladorMenuReportesPeriodo controlador = cargador.getController();
            controlador.setDatos(reporte); 

            Scene escena = new Scene(Ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);
            ventana.setResizable(false);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al cargar la interfaz");
        }
    }
    
    public static void cargarMenuReportesTipo(Window padre, String reporte) throws Exception {
        try {
            String direccion = GestorFXML.RutaMenuReportesTipo;
            URL Url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(Url);
            Parent Ruta = cargador.load();
            ControladorMenuReportesTipo controlador = cargador.getController();
            controlador.setDatos(reporte); 

            Scene escena = new Scene(Ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);
            ventana.setResizable(false);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al cargar la interfaz");
        }
    }

    
    public static void cargarVerMasExamenes(Window padre, ExamenConduccion examenConduccion,ExamenMedico examenMedico) throws Exception {
        try {
            String direccion = GestorFXML.RutaVerMasExamenes;
            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
            ControladorVerMasExamenes controlador = cargador.getController();
            controlador.SetDatos(examenConduccion,examenMedico); 

            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);
             ventana.setResizable(false);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al cargar la interfaz");
        }
    }
    
    
    public static void cargarVerMasExamenesMedicosAdmin(Window padre,ExamenMedico examenMedico) throws Exception {
        try {
            String direccion = GestorFXML.RutaVerMasExamenesMedicosAdmin;
            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
            ControladorVerMasExamenesMedicos controlador = (ControladorVerMasExamenesMedicos)cargador.getController();
            controlador.setDatos(examenMedico); 

            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);
             ventana.setResizable(false);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new Exception("Error al cargar la interfaz");
        }
    }
    
    
    public static void cargarVerMasExamenesPracticosAdmin(Window padre,ExamenConduccion examenConduccion) throws Exception {
        try {
            String direccion = GestorFXML.RutaVerMasExamenesPracticosAdmin;
            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
            ControladorVerMasExamenesPracticos controlador = (ControladorVerMasExamenesPracticos)cargador.getController();
            controlador.setDatos(examenConduccion); 

            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);
             ventana.setResizable(false);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new Exception("Error al cargar la interfaz");
        }
    }
    
    
    public static void cargarVerMasExamenesTeoricosAdmin(Window padre,ExamenConduccion examenConduccion) throws Exception {
        try {
            String direccion = GestorFXML.RutaVerMasExamenesTeoricosAdmin;
            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
            ControladorVerMasExamenesTeoricos controlador = (ControladorVerMasExamenesTeoricos)cargador.getController();
            controlador.setDatos(examenConduccion); 

            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);
             ventana.setResizable(false);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new Exception("Error al cargar la interfaz");
        }
    }
    
    
    public static void cargarVerMasExamenesMedicosDoctor(Window padre,ExamenMedico examenMedico) throws Exception {
        try {
            String direccion = GestorFXML.RutaVerMasExamenesMedicosDoctor;
            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
            ControladorVerMasExamenesMedicosDoctor controlador = (ControladorVerMasExamenesMedicosDoctor)cargador.getController();
            controlador.setDatos(examenMedico); 
            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);
             ventana.setResizable(false);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new Exception("Error al cargar la interfaz");
        }
    }
    
    
    public static void cargarVerMasExamenesTeoricosTrabajador(Window padre,ExamenConduccion examenConduccion) throws Exception {
        try {
            String direccion =GestorFXML.RutaVerMasExamenesTeoricosTrabajador;
            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
            ControladorVerMasExamenesTeoricosTrabajador controlador = (ControladorVerMasExamenesTeoricosTrabajador)cargador.getController();
            controlador.setDatos(examenConduccion); 

            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);
             ventana.setResizable(false);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new Exception("Error al cargar la interfaz");
        }
    }
  
    
    public static void cargarVerMasExamenesPracticosTrabajador(Window padre,ExamenConduccion examenConduccion) throws Exception {
        try {
            String direccion = GestorFXML.RutaVerMasExamenesPracticosTrabajador;
            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
            ControladorVerMasExamenesPracticosTrabajador controlador = (ControladorVerMasExamenesPracticosTrabajador)cargador.getController();
            controlador.setDatos(examenConduccion); 

            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);
             ventana.setResizable(false);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new Exception("Error al cargar la interfaz");
        }
    }
    
    //Funcion para mostrar el primer panel y los demas ocultarlos

    public static void mostrarOcultarPaneles(Pane mostrar, Pane... ocultar) {
        mostrar.setVisible(true);
        for (Pane panel : ocultar) {
            if (panel.isVisible()) {
                panel.setVisible(false);
}
        }
    }

    //Funcion para pintar el primer boton y los demas ocultarlos
    public static void pintarBotones(JFXButton boton, JFXButton... botones) {
        boton.getStyleClass().add("active");
        for (JFXButton bot : botones) {
            bot.getStyleClass().removeAll("active");
        }
    }

    //Funcion para Ingresarle el icono a la ventana por parametro,asi como titulo
    public static void ponerIconoVentana(HBox box, String tituloVentana) {
        Platform.runLater(() -> {
            Stage stage = (Stage) box.getScene().getWindow();
            try {
                Image icon = new Image(GestorEscenas.class.getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-empresa.png"));
                stage.getIcons().add(icon);
                stage.setTitle(tituloVentana);
            } catch (NullPointerException e) {
                System.err.println("No se encontró el archivo de icono");
            }
        });
    }
    
    //Funcion para Unir el los labels con las barras de progreso asi dicen mismo porcentaje
    public static void progresoLabel(Label[] labels, ProgressBar[] progressBars) {
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

    public static void consumirTecla(JFXButton... boton){
        for (JFXButton b : boton) {
            b.addEventFilter(KeyEvent.KEY_PRESSED, evento -> {
                if (evento.getCode() == KeyCode.ENTER || evento.getCode() == KeyCode.SPACE) {
                    evento.consume();
                }
            });
        }
    }
    
    //Funcion para cambiar iconos de ciertos botones con dos arreglos
    public static void cambiarIconos(ArrayList<ImageView> imagenes, ArrayList<JFXButton> botones) {
        for(int i=0;i<botones.size();i++)
        {
            botones.get(i).setGraphic(imagenes.get(i));
        }
    }
 
    public static String abreviarNombre(String nombreCompleto) {
        String[] partes = nombreCompleto.split(" ");
        if (partes.length >= 2) {
            return partes[0] + " " + partes[1].charAt(0) + "."; // Ej: "Juan P."
        }
        return nombreCompleto;
    }
    
    //Funcion para convertir el correo del usaurio en uno con ****
    public static String seguridadCorreo(String correo) {
        // Dividir el correo en nombre y dominio
        String securedUsername;
        String[] partes = correo.split("@");
        if (partes.length != 2) {
            return correo; // Si no es un correo válido, devolver original
        }
        String nombre = partes[0];
        String mail = partes[1];
        if(nombre.length()<4)
        {
            int visibleChars = Math.max(3, nombre.length());
            securedUsername = nombre.substring(0, visibleChars) + "*";
        }
        else if(nombre.length()>=4 && nombre.length()<10)
        {
            if (nombre.length() % 2 == 0) {
                int visibleChars = Math.max(nombre.length() / 2, nombre.length() / 4); // Mostrar al menos 1 carácter
                int asteriscos = nombre.length() / 2;
                String total = "*";
                total.repeat(asteriscos);
                securedUsername = nombre.substring(0, visibleChars) + total.repeat(asteriscos);
            } else {
                int visibleChars = Math.max(nombre.length() / 2, nombre.length() / 4); // Mostrar al menos 1 carácter
                int asteriscos = nombre.length() / 2+1 ;
                String total = "*";
                total.repeat(asteriscos);
                securedUsername = nombre.substring(0, visibleChars) + total.repeat(asteriscos);
            }
        }
        else
        {
        // Reemplazar parte del nombre con asteriscos (manteniendo al menos 1 carácter)
        int visibleChars = Math.max(6, nombre.length()-5); // Mostrar al menos 1 carácter
        securedUsername = nombre.substring(0, visibleChars) + "*****";
        }
        return securedUsername + "@" + mail;
    }
    
    public static StringBuilder convertirArrayRestricciones(ArrayList<String> restricciones) {
       
        StringBuilder transformado = new StringBuilder();
        for (String Elemento : restricciones) {
            if (transformado.length() > 0) {
                transformado.append("\n");
            }
            transformado.append(Elemento);
        }
        return transformado;
    }
    
    
    public static void configurarReloj(Label etiquetaReloj) {
        // Estilo del label
        etiquetaReloj.setStyle("-fx-font-size: 12px;");

        // Formateador de fecha/hora
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a");

        // Animación que se actualiza cada segundo
        Timeline lineaTiempo = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        event -> {
                            etiquetaReloj.setText(LocalDateTime.now().format(formateador));
                        }
                )
        );
        lineaTiempo.setCycleCount(Timeline.INDEFINITE);
        lineaTiempo.play();
    }
    
    
    public static void configurarEfectoLinea(ArrayList<TextField> cajaTexto, ArrayList<Line> linea) {
        linea.forEach(Line ->
        {
            Line.setStroke(Color.GRAY);
            Line.setStrokeWidth(1);
        });
        
        int i=0;
        // Cuando el TextField recibe foco
        for (Line li : linea) {
            cajaTexto.get(i).focusedProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal) {

                    Timeline lineaTiempo = new Timeline(
                            new KeyFrame(Duration.ZERO,
                                    new KeyValue(li.strokeProperty(), Color.GRAY),
                                    new KeyValue(li.strokeWidthProperty(), 1)
                            ),
                            new KeyFrame(Duration.millis(300),
                                    new KeyValue(li.strokeProperty(), Color.valueOf("#8000ff")),
                                    new KeyValue(li.strokeWidthProperty(), 2)
                            )
                    );
                    lineaTiempo.play();
                } else {
                    Timeline LineaTiempo = new Timeline(
                            new KeyFrame(Duration.ZERO,
                                    new KeyValue(li.strokeProperty(), Color.valueOf("#8000ff")),
                                    new KeyValue(li.strokeWidthProperty(), 2)
                            ),
                            new KeyFrame(Duration.millis(300),
                                    new KeyValue(li.strokeProperty(), Color.GRAY),
                                    new KeyValue(li.strokeWidthProperty(), 1)
                            )
                    );
                    LineaTiempo.play();
                }
            });
            i++;
        }
        
    }


    //Funcion para cerrar el programa
    public static void cerrarPrograma()
    {
        ConectorBaseDato.cerrarConexionBD();
        Platform.exit();
    }
    
    //Funcion para cerrarVentana
    public static void cerrar(Button btnAtras) {
        Stage ventana = (Stage) btnAtras.getScene().getWindow();
        ventana.close();
    }
    
     //Funcion para cerrarVentana
    public static void cerrar(Stage ventana) {
        ventana.close();
    }
    
    
    public static void cargarVerMasInfraccion(Window padre, Infraccion infraccion , Licencia licencia) throws Exception {
        try {
            String direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/ver-mas/menu-ver-mas-infraccion.fxml";
            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
            ControladorVerMasInfracciones controlador = cargador.getController();
            controlador.SetDatos(infraccion, licencia); 

            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Exception("Error al cargar la interfaz");
        }
    }
    
    
    public static void cargarVerMasLicencias(Window padre, Conductor conductor , Licencia licencia) throws Exception {
        try {
            String direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/ver-mas/menu-ver-mas-licencias.fxml";
            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
            ControladorVerMasLicencias controlador = cargador.getController();
            controlador.SetDatos(conductor, licencia); 

            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            throw new Exception("Error al cargar la interfaz");
        }
    }
     
      public static void cargarVerMasEntidades(Window padre,EntidadRelacionada entidad) throws Exception {
        try {
            String direccion = "/interfaz_usuario/administrador/sistema/menu-auxiliares/ver-mas/menu-ver-mas-entidades.fxml";
            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
            ControladorVerMasEntidades controlador = cargador.getController();
            controlador.setDatos(entidad); 

            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new Exception("Error al cargar la interfaz");
        }
      }
     
      public static void cargarRegistrarPersona(Window padre,Stage ventanaAnterior,ExamenMedico examenMedico,Persona persona) throws Exception {
        try {
            String direccion = GestorFXML.RutaRegistrarPersona;
            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
            ControladorRegistrarPersona controlador = cargador.getController();
            controlador.setDatos(examenMedico,ventanaAnterior,persona);

            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new Exception("Error al cargar la interfaz");
        }
      }
      
      public static void cargarRegistrarLicencia(Window padre,Stage ventanaAnterior,ExamenMedico examenMedico,ExamenConduccion examen) throws Exception {
        try {
            String direccion = GestorFXML.RutaRegistrarLicencia;
            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
            ControladorRegistrarLicencia controlador = cargador.getController();
            controlador.setDatos(examenMedico,ventanaAnterior,examen);

            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new Exception("Error al cargar la interfaz");
        }
      }
      
       public static void cargarRegistrarUsuario(Window padre,Stage ventanaAnterior,Object autoescuela,Object clinica) throws Exception {
        try {
            String direccion = GestorFXML.RutaRegistrarUsuario;
            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
            ControladorRegistrarUsuario controlador = cargador.getController();
            controlador.setDatos((EntidadRelacionada)autoescuela,(EntidadRelacionada)clinica,ventanaAnterior);

            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new Exception("Error al cargar la interfaz");
        }
      }
      
    public static void cargarCategorias(Licencia licencia, Pane categoriaMoto, Pane categoriaCarro,Pane categoriaOmnibus,Pane categoriaCamion){
        for(String categoria:licencia.getCategorias())
        {
            switch(categoria)
            {
                case "Moto":
                    categoriaMoto.setStyle("-fx-background-color: green;");
                    break;
                case "Automovil":
                    categoriaCarro.setStyle("-fx-background-color: green;");
                    break;
                case "Autobus":
                    categoriaOmnibus.setStyle("-fx-background-color: green;");
                    break;
                case "Camion":
                    categoriaCamion.setStyle("-fx-background-color: green;");
                    break;
            }
        }
    }
    
    public static void cargarVerMasClinicas(Window padre,EntidadRelacionada clinica) throws Exception {
        try {
            String direccion = "/interfaz_usuario/administrador/sistema/menu-auxiliares/ver-mas/menu-ver-mas-clinicas.fxml";
            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
            ControladorVerMasClinicas controlador = cargador.getController();
            controlador.SetDatos(clinica); 

            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new Exception("Error al cargar la interfaz");
        }
      }
    
    public static void cargarVerMasAutoescuelas(Window padre,EntidadRelacionada autoescuela) throws Exception {
        try {
            String direccion = "/interfaz_usuario/administrador/sistema/menu-auxiliares/ver-mas/menu-ver-mas-autoescuelas.fxml";
            URL url = GestorEscenas.class.getResource(direccion);
            FXMLLoader cargador = new FXMLLoader(url);
            Parent ruta = cargador.load();
            ControladorVerMasAutoescuelas controlador = cargador.getController();
            controlador.SetDatos(autoescuela); 

            Scene escena = new Scene(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(padre);
            ventana.initStyle(StageStyle.UTILITY);


            ventana.initModality(Modality.WINDOW_MODAL);
            

            ventana.setTitle("");
            ventana.setScene(escena);
            ventana.showAndWait();
          
            

        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new Exception("Error al cargar la interfaz");
        }
      }
    
    public static void generarRadioButtons(ArrayList<String> opciones, ScrollPane scrollPaneContenedor) {
        // Crear el AnchorPane que contendrá los RadioButtons
        AnchorPane apnlContenedor = new AnchorPane();

        // Crear el VBox para organizar los RadioButtons
        VBox vbox = new VBox();
        vbox.setSpacing(10);


        // Generar RadioButtons
        for (String opcion : opciones) {
            JFXRadioButton rb = new JFXRadioButton(opcion);
            rb.setStyle("-jfx-selected-color: #8000ff; -jfx-unselected-color: #5a5a5a;");
            rb.setUserData(opcion);
            vbox.getChildren().add(rb);
        }

        // Añadir el VBox al AnchorPane con anclajes
        apnlContenedor.getChildren().add(vbox);
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(vbox, 0.0);

        // Configurar el ScrollPane
        scrollPaneContenedor.setContent(apnlContenedor);
        scrollPaneContenedor.setFitToWidth(true);
        scrollPaneContenedor.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneContenedor.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // Ajustar el tamaño del contenido
        vbox.minWidthProperty().bind(scrollPaneContenedor.widthProperty().subtract(20));
        apnlContenedor.minHeightProperty().bind(vbox.heightProperty().add(30));
    }

    public static ArrayList<JFXRadioButton> obtenerRadioButtonsSeleccionados(AnchorPane apnlContenedor) {
        ArrayList<JFXRadioButton> seleccionados = new ArrayList<>();

        // Buscar recursivamente en todos los nodos
        for (Node node : apnlContenedor.getChildren()) {
            if (node instanceof ScrollPane) {
                ScrollPane scrollPane = (ScrollPane) node;
                Node content = scrollPane.getContent();

                if (content instanceof VBox) {
                    VBox vbox = (VBox) content;

                    for (Node child : vbox.getChildren()) {
                        if (child instanceof JFXRadioButton) {
                            JFXRadioButton rb = (JFXRadioButton) child;
                            if (rb.isSelected()) {
                                seleccionados.add(rb);
                            }
                        }
                    }
                }
            }
        }

        return seleccionados;
    }
    
    //Funcion para mostrar y ocultar texto en el campo de contrasenna
    public static void mostrarOcultarContra(PasswordField txfContra,TextField txfOculto,ImageView ivBoton)
    {
        txfOculto.setVisible(false);
        txfOculto.setManaged(false);
        ivBoton.setOnMouseEntered(e ->{
            ivBoton.setScaleX(1.3);
            ivBoton.setScaleY(1.3);
        });
        
        ivBoton.setOnMouseExited(e ->{
            ivBoton.setScaleX(1);
            ivBoton.setScaleY(1);
        });
        ivBoton.setOnMousePressed(e ->{
            txfOculto.setText(txfContra.getText());
            txfOculto.setVisible(true);
            txfOculto.setManaged(true);
            txfContra.setVisible(false);
            txfContra.setManaged(false);
        });
        ivBoton.setOnMouseReleased(e ->{
            txfContra.setText(txfOculto.getText());
            txfContra.setVisible(true);
            txfContra.setManaged(true);
            txfOculto.setVisible(false);
            txfOculto.setManaged(false);
        });   
    }

}
