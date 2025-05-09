/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.errores.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorMenuAuxiliarDosAcciones {
    
    
    private boolean Acepto = false;
    
    @FXML
    public void initializate()
    {
        System.out.println("Controlador menu auxiliar dos acciones Activado");
    }
    
    
    @FXML
    private void Aceptar(ActionEvent event) {
        this.Acepto = true;
        cerrarVentana(event);
    }

    @FXML
    private void Cancelar(ActionEvent event) {
        this.Acepto = false;
        cerrarVentana(event);
    }

    private void cerrarVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    
    public boolean esAceptado() {
        return Acepto;
    }
}
