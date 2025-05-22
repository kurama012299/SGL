/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.sistema.controladores;

import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logica.entidad.implementaciones.ServicioEntidad;
import logica.entidad.modelos.EntidadRelacionada;
import logica.validaciones_generales.ValidacionCampoVacio;
import logica.validaciones_generales.ValidacionCantidadCaracteresExacta;
import logica.validaciones_generales.ValidacionCantidadCaracteresMaxima;
import logica.validaciones_generales.ValidacionCompuesta;
import logica.validaciones_generales.ValidacionCorreo;
import logica.validaciones_generales.ValidacionSoloLetras;
import logica.validaciones_generales.ValidacionSoloNumeros;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorRegistrarClinica {
    
     @FXML private TextField txfNombreClinica;
    
    @FXML private TextField txfNombreDirector;
    
    @FXML private TextField txfDireccion;
    
    @FXML private TextField txfTelefono;
    
    @FXML private TextField txfCorreo;
    
    @FXML private Button btnAtras;
    
    @FXML private Button btnRegistrar;
    
    public void initialize()
    {
        System.out.println("Controlador registrar clinica iniciado");
        btnAtras.setOnAction(e ->{
            GestorEscenas.cerrar(btnAtras);
        });
    }
    
    @FXML private void botonRegistrar()
    {
        ValidacionCampoVacio campoVacio= new ValidacionCampoVacio();
        ValidacionSoloLetras campoLetras= new ValidacionSoloLetras();
        ValidacionSoloNumeros campoNumeros= new ValidacionSoloNumeros();
        ValidacionCorreo campoCorreo = new ValidacionCorreo();
        ValidacionCantidadCaracteresMaxima campoDireccionMaximo= new ValidacionCantidadCaracteresMaxima(50,"Direccion");
        ValidacionCantidadCaracteresExacta campoTelefonoExacta= new ValidacionCantidadCaracteresExacta(8);
        
        ValidacionCompuesta campoNombre=new ValidacionCompuesta(campoLetras,campoVacio);
        ValidacionCompuesta campoTelefono= new ValidacionCompuesta(campoNumeros,campoTelefonoExacta);
        ValidacionCompuesta campoDireccion= new ValidacionCompuesta(campoDireccionMaximo,campoVacio);
        
        try {
            
            campoNombre.Validar(txfNombreClinica.getText(), "Nombre clinica");
            campoNombre.Validar(txfNombreDirector.getText(), "Nombre director");
            campoCorreo.Validar(txfCorreo.getText(), "Campo Correo");
            campoTelefono.Validar(txfTelefono.getText(), "Numero de telefono");
            campoDireccion.Validar(txfDireccion.getText(), "Campo direccion");

            
            EntidadRelacionada clinica = new EntidadRelacionada(
                    txfNombreClinica.getText(),
                    txfDireccion.getText(),
                    txfTelefono.getText(),
                    txfCorreo.getText(),
                    txfNombreDirector.getText(),
                    "Clinica");
            
            long idGenerado = ServicioEntidad.guardarEntidadBaseDatos(clinica);
            clinica.setId(idGenerado);
            GestorEscenas.cargarConfirmacion(btnRegistrar.getScene().getWindow(), "Se ha registrado con éxito");
            GestorEscenas.cerrar(btnRegistrar);
        } catch (Exception e) {
            GestorEscenas.cargarError(btnAtras.getScene().getWindow(), e);
        }
    }
}
