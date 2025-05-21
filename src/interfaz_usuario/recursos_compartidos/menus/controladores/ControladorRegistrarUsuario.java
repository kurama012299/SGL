/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logica.entidad.modelos.EntidadRelacionada;
import logica.validaciones_generales.ValidacionCampoVacio;
import logica.validaciones_generales.ValidacionCantidadCaracteresMinima;
import logica.validaciones_generales.ValidacionCompuesta;
import logica.validaciones_generales.ValidacionCorreo;
import logica.validaciones_generales.ValidacionSoloLetras;

/**
 *
 * @author Kris
 */
public class ControladorRegistrarUsuario {
    
    @FXML private TextField txfNombreApellido;
    
    @FXML private TextField txfCorreo;
    
    @FXML private TextField txfClave;
    
    @FXML private Button btnAtras;
    
    @FXML private Button btnRegistrar;
    
    EntidadRelacionada autoescuela=null;
    private Stage ventanaAnterior;
    
    @FXML public void initialize()
    {
        System.out.println("Controlador registrar usuario iniciado");
        btnAtras.setOnAction(e ->{
            GestorEscenas.cerrar(btnAtras);
        });
    }
    
    @FXML public void setDatos(EntidadRelacionada autoescuela,Stage ventana)
    {
        this.autoescuela=autoescuela;
        this.ventanaAnterior=ventana;
    }
    
    @FXML public void botonRegistrar()
    {
        ValidacionCampoVacio campoVacio= new ValidacionCampoVacio();
        ValidacionCorreo campoCorreo= new ValidacionCorreo();
        ValidacionCantidadCaracteresMinima campoClave= new ValidacionCantidadCaracteresMinima(8);
        ValidacionSoloLetras campoLetras= new ValidacionSoloLetras();
        
        ValidacionCompuesta campoNombre= new ValidacionCompuesta(campoLetras,campoVacio);
        
        
        try {
             
            campoNombre.Validar(txfNombreApellido.getText(), "Campo nombre y apellidos");
            //campoCorreo.Validar(txfCorreo.getText(), "Campo correo");
            campoClave.Validar(txfClave.getText(), "Campo clave");
            
            System.out.println("Datos Correctos");
            GestorEscenas.cargarConfirmacion(btnRegistrar.getScene().getWindow(), "Se ha registrado con éxito");
            GestorEscenas.cerrar(btnRegistrar);
            GestorEscenas.cerrar(ventanaAnterior);
            
        } catch (Exception ex) {
            GestorEscenas.cargarError(btnRegistrar.getScene().getWindow(), ex);
        }
    }
}
