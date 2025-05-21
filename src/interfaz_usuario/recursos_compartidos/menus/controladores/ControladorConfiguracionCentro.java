/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author Kris
 */
public class ControladorConfiguracionCentro {
    
    @FXML private Button btnCerrar;
    
    public void initialize()
    {
        System.out.println("Controlador configuracion del centro iniciado");
        btnCerrar.setOnAction(e ->{
            GestorEscenas.cerrar(btnCerrar);
        });
    }
}
