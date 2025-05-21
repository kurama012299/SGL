/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import com.jfoenix.controls.JFXButton;
import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logica.examen_medico.modelos.ExamenMedico;
import logica.validaciones_generales.ValidacionCompuesta;

/**
 *
 * @author Kris
 */
public class ControladorRegistrarPersona {
    
    private ExamenMedico examenMedico=null;
    
    @FXML private JFXButton btnRegistrar;
    
    @FXML private JFXButton btnCancelar;
    
    @FXML private TextArea txaRestricciones;
    
    @FXML private TextField txfCorreo;
    
    @FXML private TextField txfTelefono;
    
    @FXML private TextField txfDireccion;
    
    @FXML public void initialize()
    {
        System.out.println("Controlador Registrar persona iniciado");
        
        btnCancelar.setOnAction(e ->{
            GestorEscenas.cerrar(btnCancelar);
        });
    }
    
    public void setDatos(ExamenMedico examenMedico)
    {
        this.examenMedico=examenMedico;
    }
    
    
    @FXML private void mostrarRestricciones()
    {
        if(examenMedico.getRestricciones().size()==0)
        {
            txaRestricciones.setText("Ninguna");
        }
        else
        {
            txaRestricciones.setText(GestorEscenas.convertirArrayRestricciones(examenMedico.getRestricciones()).toString());
        }
    }
    
    
   /* @FXML private void botonRegistrar()
    {
        Validacion
        ValidacionCompuesta campoTelefono= new ValidacionCompuesta()
    }*/
}
