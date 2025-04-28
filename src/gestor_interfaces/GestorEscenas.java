/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_interfaces;


import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;




/**
 *
 * @author Angel Hernandez
 */
public class GestorEscenas  {
    
    public static void CargarMenu(String Direccion) throws Exception {
        try {

         // Usar ruta relativa desde los recursos
        URL url = GestorEscenas.class.getResource(Direccion);
        
       
        FXMLLoader cargador = new FXMLLoader(url);
        Parent root = cargador.load();
        
        Stage escena = new Stage();
        escena.setScene(new Scene(root));
        escena.setTitle("SGL");
        escena.show();
        
        } catch (Exception e) {
            throw new Exception("No se encuentra la interfaz");
        }
    }

     
     public static void CargarPanelDatos(Pane PanelDatosOcultar, String DireccionNuevoPanel)
     {
         PanelDatosOcultar.setVisible(false);
     }
     
     
     public static void CargarAlertaError(Pane EscenaPrincipal,String Mensaje)
     {
         
     } 
}
