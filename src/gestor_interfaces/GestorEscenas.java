/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_interfaces;



import com.jfoenix.controls.JFXButton;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

     
     public static void CargarAlertaError(Window Padre, String Direccion,String Titulo,String Mensaje) throws Exception
     {
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
     
     public static void MostrarOcultarPaneles(Pane Mostrar,Pane... Ocultar)
     {
         Mostrar.setVisible(true);
         for(Pane Panel: Ocultar)
         {
             if(Panel.isVisible())
                Panel.setVisible(false);
         }
     }
     
   
     public static void PintarBotones(JFXButton boton,JFXButton... botones)
     {
         boton.getStyleClass().add("active");
         for(JFXButton bot: botones)
         {
             bot.getStyleClass().remove("active");
         }
     }
    
}
