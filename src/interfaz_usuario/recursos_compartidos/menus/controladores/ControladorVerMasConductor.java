/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import logica.persona.modelos.Conductor;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorVerMasConductor {
    
    private Conductor Conductor;
    
    @FXML
    TextField TextFieldCI;
    
    @FXML
    TextField TextFieldTelefono;
    
    @FXML
    TextField TextFieldCorreo;
    
    @FXML
    TextField TextFieldNombre;
    
    @FXML
    TextField TextFieldDireccion;


    @FXML
    public void initialize()
    {
        System.out.println("Controlador ver mas conductor iniciado");
    }
    
    public void SetDatos(Conductor Conductor)
    {
        this.Conductor=Conductor;
        Iniciar();
    }
    
    public void Iniciar()
    {
        System.out.println("Iniciar llamado");
        TextFieldCI.setText(Conductor.getCI());
        TextFieldTelefono.setText(Conductor.getTelefono());
        TextFieldCorreo.setText(Conductor.getCorreo());
        TextFieldDireccion.setText(Conductor.getDireccion());
        TextFieldNombre.setText(Conductor.getNombre()+" "+ Conductor.getApellidos());
    }
}
