/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import gestor_interfaces.GestorEscenas;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import logica.validaciones_generales.ValidacionCampoVacio;
import logica.validaciones_generales.ValidacionCantidadCaracteresExacta;
import logica.validaciones_generales.ValidacionCompuesta;
import logica.validaciones_generales.ValidacionFecha;
import logica.validaciones_generales.ValidacionSoloLetras;
import logica.validaciones_generales.ValidacionSoloNumeros;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorRegistrarExamen {
    
    @FXML private RadioButton rbtMedico;
    
    @FXML private RadioButton rbtTeorico;
    
    @FXML private RadioButton rbtPractico;
    
    @FXML private ScrollPane scrpRestricciones;
    
    @FXML private Label lblRestricciones;
    
    @FXML private Button btnCancelar;
    
    @FXML private Button btnRegistrar;
    
    @FXML private TextField txfNombre;
    
    @FXML private TextField txfCarnet;
    
    @FXML private TextField txfNombreExaminador;
    
    @FXML private DatePicker dtFecha;
    
    public void initialize()
    {
        System.out.println("Controlador Registrar Examen Activado");
        visibilidadRestricciones(false);
    }
    
    @FXML public void seleccionarTipoMedico()
    {
        visibilidadRestricciones(true);
        btnRegistrar.setText("Siguiente");
    }
    
    @FXML public void seleccionarTipoConduccion()
    {
        visibilidadRestricciones(false);
        btnRegistrar.setText("Registrar");
    }
    
    private void visibilidadRestricciones(boolean esVisible)
    {
        scrpRestricciones.setVisible(esVisible);
        lblRestricciones.setVisible(esVisible);
        scrpRestricciones.setManaged(esVisible);
        lblRestricciones.setManaged(esVisible);
    }
    
    @FXML private void botonRegistrar()
    {
        
        ValidacionCampoVacio campoVacio = new ValidacionCampoVacio();
        ValidacionSoloLetras campoLetras = new ValidacionSoloLetras();
        ValidacionSoloNumeros campoNumerico = new ValidacionSoloNumeros();
        ValidacionFecha campoFecha = new ValidacionFecha();
        ValidacionCantidadCaracteresExacta campoCantidadExacta = new ValidacionCantidadCaracteresExacta(11);
        
        ValidacionCompuesta campoNombre = new ValidacionCompuesta(campoVacio,campoLetras);
        ValidacionCompuesta campoCarnet = new ValidacionCompuesta(campoCantidadExacta,campoNumerico);

        
        
        try {
            if(!rbtMedico.isSelected() && !rbtTeorico.isSelected() && !rbtPractico.isSelected())
                throw new Exception("Debe elegir un tipo de examen");
                    
            campoNombre.Validar(txfNombre.getText(), "nombre");
            campoCarnet.Validar(txfCarnet.getText(), "carnet identidad");
            campoNombre.Validar(txfNombreExaminador.getText(), "nombre examinador");
            campoFecha.Validar(dtFecha.getValue(), "fecha examen");
            
            System.out.println("Datos Correctos");
           
        } catch (Exception ex) {
            GestorEscenas.cargarError(btnRegistrar.getScene().getWindow(), ex);
        }
         
    }
}
