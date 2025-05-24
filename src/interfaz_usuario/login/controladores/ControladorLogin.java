/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.login.controladores;


import gestor_interfaces.GestorEscenas;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import logica.autentificacion.Autentificador;



public class ControladorLogin {
    
    @FXML
    private Button Entrar;
    
    @FXML
    private HBox VentanaPrincipal;
    
    @FXML
    private TextField Usuario;
    
    @FXML
    private PasswordField Clave;
    
    @FXML
    private TextField txfOculto;
    
    @FXML private ImageView ivIconoContra;
    
    @FXML
    private Label OlvidasteClave;
    
    @FXML
    private Line LineaUsuario;
    
    @FXML
    private Line LineaClave;
    
    @FXML
    public void initialize() {
        System.out.println("Controlador Login Iniciado");
        GestorEscenas.ponerIconoVentana(VentanaPrincipal, "Login");
        ConfigurarNavegacionConTeclas(Usuario, Clave);
        GestorEscenas.configurarEfectoLinea(new ArrayList<TextField>(Arrays.asList(Clave,Usuario)), new ArrayList<Line>(Arrays.asList(LineaClave,LineaUsuario)));
        GestorEscenas.mostrarOcultarContra(Clave, txfOculto, ivIconoContra);
    }
    
    @FXML
    private void IniciarSesion() throws Exception {
        System.out.println("Intentando iniciar sesión...");

        String tipoUsuario;
        try {
            tipoUsuario = Autentificador.existeUsuario(Usuario.getText(), Clave.getText());

            switch (tipoUsuario) 
            {
                case "Administrador":
                    CargarMenuInicio("/interfaz_usuario/administrador/sistema/inicio-administrador.fxml");
                    break;

                case "Administrador autoescuela":
                    CargarMenuInicio("/interfaz_usuario/administrador/autoescuela/inicio-admin-autoescuela.fxml");
                    break;

                case "Administrador médico":
                    CargarMenuInicio("/interfaz_usuario/administrador/medico/inicio-admin-medico.fxml");
                    break;

                case "Trabajador autoescuela":
                    CargarMenuInicio("/interfaz_usuario/trabajador_autoescuela/inicio-trabajador.fxml");
                    break;

                case "Trabajador centro":
                    CargarMenuInicio("/interfaz_usuario/trabajador_centro/inicio-trabajador-centro.fxml");
                    break;

                case "Médico":
                    CargarMenuInicio("/interfaz_usuario/medico/inicio-medico.fxml");
                    break;
                default:
                    
            }
        } catch (Exception ex) 
        {
            GestorEscenas.cargarError(Entrar.getScene().getWindow(),ex);
        }
    }
    
    
    private void CargarMenuInicio(String Ruta) throws Exception
    {
        ((Stage) Entrar.getScene().getWindow()).close();
        GestorEscenas.cargarMenu(Ruta);
    }
    
    
    @FXML
    private void EntrarTeclaCorreo()
    {
        Usuario.setOnKeyPressed((t) -> {
            if(t.getCode() == KeyCode.ENTER){
                Entrar.fire();
            }
        });
    }        
    
    @FXML
    private void EntrarTeclaClave()
    {
        Clave.setOnKeyPressed((t) -> {
            if(t.getCode()== KeyCode.ENTER){
                Entrar.fire();
            }
        });
    }       
    
    //funcion para navegar con las teclas arriba y abajo
    @FXML
    private void ConfigurarNavegacionConTeclas(TextField Usuario,TextField Clave) {
        // Evento para el field usuario
        Usuario.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case DOWN:
                    Clave.requestFocus();
                    Usuario.setStyle("-fx-background-color: transparent");
                    event.consume();
                    break;
                case UP:
                    event.consume();
                    break;
                default:
                    break;
            }
        });

        // Evento para el field contraseña
        Clave.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case UP:
                    Usuario.requestFocus();
                    Clave.setStyle("-fx-background-color: transparent");
                    event.consume();
                    break;
                case DOWN:
                    event.consume();
                    break;
                default:
                    break;
            }
        });
    }
    
    //Funcion para el efecto de las lineas bajo los textfield
    
}


