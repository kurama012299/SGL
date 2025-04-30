/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Angel Hernandez
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            
            URL urlFXML = getClass().getResource("/interfaz_usuario/login/login.fxml");
            
            Parent root = FXMLLoader.load(urlFXML);
            
            // Configurar la escena
            Scene scene = new Scene(root);
            primaryStage.setTitle("Interfaz desde FXML");
            primaryStage.setScene(scene);
            primaryStage.show();
            
            
        } catch (Exception e) {
            System.err.println("Error al cargar la interfaz:");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
}