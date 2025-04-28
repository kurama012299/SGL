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
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import logica.autentificacion.Autentificador;


public class ControladorLogin {
    
    @FXML
    private Button Entrar;
    
    @FXML
    private TextField Usuario;
    
    @FXML
    private TextField Clave;
    
    @FXML
    private Label OlvidasteClave;
    
    @FXML
    public void initialize() {
        System.out.println("Controlador Login Iniciado");
    }
    
    @FXML
    private void IniciarSesion(ActionEvent event) {
        System.out.println("Intentando iniciar sesión...");

        String tipoUsuario;
        try {
            tipoUsuario = Autentificador.ExisteUsuario(Usuario.getText(), Clave.getText());

            switch (tipoUsuario) 
            {
                case "Administrador":
                    CargarMenuInicio("/interfaz_usuario/administrador/sistema/inicio-administrador.fxml");
                    break;

                case "AdministradorAutoescuela":
                    CargarMenuInicio("/interfaz_usuario/administrador/autoescuela/inicio-admin-autoescuela.fxml");
                    break;

                case "AdministradorMedico":
                    CargarMenuInicio("/interfaz_usuario/administrador/medico/inicio-admin-medico.fxml");
                    break;

                case "TrabajadorAutoescuela":
                    CargarMenuInicio("/interfaz_usuario/trabajador_autoescuela/inicio-trabajador.fxml");
                    break;

                case "TrabajadorCentro":
                    CargarMenuInicio("/interfaz_usuario/trabajador_centro/inicio-trabajador-centro.fxml");
                    break;

                case "Medico":
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

}