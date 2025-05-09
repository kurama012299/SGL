/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.errores.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorMenuAuxiliarUnaAccion {

    @FXML
    Label Label;
    
    @FXML
    public void initializate()
    {
        System.out.println("Controlador menu auxiliar una accion Activado");
    }
    
    @FXML
    public void CerrarMenu(ActionEvent event)
    {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    public Label Iniciar(String Mensaje)
    {
        Label.setText(Mensaje);
        return Label;
    }
    
}
