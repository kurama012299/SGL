/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.login.controladores;


import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logica.autentificacion.Autentificador;
import logica.inicios_sesion.consultas.ConsultasInicioSesion;


public class ControladorLogin {
    
    @FXML
    private Button Entrar;
    
    @FXML
    private HBox VentanaPrincipal;
    
    @FXML
    private TextField Usuario;
    
    @FXML
    private TextField Clave;
    
    @FXML
    private Label OlvidasteClave;
    
    @FXML
    public void initialize() {
        System.out.println("Controlador Login Iniciado");
        
        GestorEscenas.PonerIconoVentana(VentanaPrincipal, "Login");
    }
    
    @FXML
    private void IniciarSesion() {
        System.out.println("Intentando iniciar sesión...");

        String tipoUsuario;
        try {
            tipoUsuario = Autentificador.ExisteUsuario(Usuario.getText(), Clave.getText());

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
            System.out.println(ex.getMessage());
        }
    }
    
    
    private void CargarMenuInicio(String Ruta) throws Exception
    {
        ((Stage) Entrar.getScene().getWindow()).close();
        GestorEscenas.CargarMenu(Ruta);
    }
    
    
    @FXML
    private void EntrarTeclaCorreo()
    {
        Usuario.setOnKeyPressed((t) -> {
            if(t.getCode()== KeyCode.ENTER){
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

}