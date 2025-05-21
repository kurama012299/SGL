/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import com.jfoenix.controls.JFXRadioButton;
import gestor_interfaces.GestorEscenas;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.restricciones.implementacion.ServicioRestriccion;
import logica.validaciones_generales.ValidacionCampoVacio;
import logica.validaciones_generales.ValidacionCantidadCaracteresExacta;
import logica.validaciones_generales.ValidacionCarnet;
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
    
    @FXML private AnchorPane apnlContenedorRestricciones;
    
    public void initialize()
    {
        System.out.println("Controlador Registrar Examen Activado");
        visibilidadRestricciones(false);
        btnCancelar.setOnAction(e -> GestorEscenas.cerrar(btnCancelar));
    }
    
    @FXML public void seleccionarTipoMedico()
    {
        visibilidadRestricciones(true);
        btnRegistrar.setText("Siguiente");
        try {
            GestorEscenas.generarRadioButtons(ServicioRestriccion.obtenerRestricciones(),apnlContenedorRestricciones);
        } catch (Exception ex) {
            GestorEscenas.cargarError(rbtMedico.getScene().getWindow(), ex);
        }
    }
    
    @FXML public void seleccionarTipoTeorico()
    {
        visibilidadRestricciones(false);
        btnRegistrar.setText("Registrar");
    }
    
    @FXML public void seleccionarTipoPractico()
    {
        visibilidadRestricciones(false);
        btnRegistrar.setText("Siguiente");
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
        
        ValidacionCampoVacio validacionCampoVacio = new ValidacionCampoVacio();
        ValidacionSoloLetras validacionSoloLetras = new ValidacionSoloLetras();
        ValidacionSoloNumeros validacionSoloNumeros = new ValidacionSoloNumeros();
        ValidacionFecha validacionFecha = new ValidacionFecha();
        ValidacionCarnet validacionCarnet = new ValidacionCarnet();
        ValidacionCantidadCaracteresExacta validacionCantidadCaracteresExacta = new ValidacionCantidadCaracteresExacta(11);
        
        ValidacionCompuesta campoNombre = new ValidacionCompuesta(validacionCampoVacio,validacionSoloLetras);
        ValidacionCompuesta campoCarnet = new ValidacionCompuesta(validacionCantidadCaracteresExacta,validacionSoloNumeros,validacionCarnet);

        
        
        try {
            if(!rbtMedico.isSelected() && !rbtTeorico.isSelected() && !rbtPractico.isSelected())
                throw new Exception("Debe elegir un tipo de examen");
                    
            campoNombre.Validar(txfNombre.getText(), "nombre");
            campoCarnet.Validar(txfCarnet.getText(), "carnet identidad");
            campoNombre.Validar(txfNombreExaminador.getText(), "nombre examinador");
            validacionFecha.Validar(dtFecha.getValue(), "fecha examen");
            
            System.out.println("Datos Correctos");
            if (rbtMedico.isSelected()) {
                if (rbtMedico.isSelected()) {
                    // Primero: Obtener referencia a la ventana actual
                    Window ventanaActual = rbtMedico.getScene().getWindow();

                    GestorEscenas.cargarRegistrarPersona(ventanaActual,(Stage) rbtMedico.getScene().getWindow());
                }
            }
           
        } catch (Exception ex) {
            GestorEscenas.cargarError(btnRegistrar.getScene().getWindow(), ex);
        }
         
    }
}
