/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import gestor_interfaces.GestorEscenas;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logica.entidad.modelos.EntidadRelacionada;
import logica.usuario.implementaciones.ServicioUsuario;
import logica.usuario.modelos.Usuario;
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
    
    @FXML private PasswordField txfClave;
    
    @FXML private Button btnAtras;
    
    @FXML private Button btnRegistrar;
    
    @FXML private ImageView ivIconoContra;
    
    @FXML private TextField txfOculto;
    
    EntidadRelacionada autoescuela=null;
    EntidadRelacionada clinica=null;
    
    private Stage ventanaAnterior;
    private Image ivViejoIconoContra;
    private boolean esAutoescuela=false;
    
    @FXML public void initialize()
    {
        System.out.println("Controlador registrar usuario iniciado");
        
        ivViejoIconoContra=(Image)ivIconoContra.getImage();
        btnAtras.setOnAction(e ->{
            GestorEscenas.cerrar(btnAtras);
        });
        //mostrarOcultarContra();
    }
    
    /*@FXML private void mostrarOcultarContra()
    {
        txfOculto.setEditable(false);
        //Image ivIconoContraNuevo= new Image(getClass().getResourceAsStream("interfaz_usuario/recursos_compartidos/imagenes/ico-visible-contra.png"));
        ivIconoContra.setOnMouseEntered(e ->{
            ivIconoContra.setScaleX(1.3);
            ivIconoContra.setScaleY(1.3);
            //ivIconoContra.setImage(ivIconoContraNuevo);
        });
        
        ivIconoContra.setOnMouseExited(e ->{
            ivIconoContra.setScaleX(1);
            ivIconoContra.setScaleY(1);
            //ivIconoContra.setImage(ivViejoIconoContra);
        });
        
        ivIconoContra.setOnMouseClicked(e ->{
            //txfClave.setDisable(true);
            txfOculto.setText(txfClave.getText());
            //txfClave.setText(txfMostrado.getText());
            //ivIconoContra.setImage(ivIconoContraNuevo);
        });
        
    }*/
    
    @FXML public void setDatos(EntidadRelacionada autoescuela,EntidadRelacionada clinica,Stage ventana)
    {
        this.clinica=clinica;
        this.autoescuela=autoescuela;
        this.ventanaAnterior=ventana;
    }
    
    private void tipoEntidad()
    {
        if (autoescuela!=null) {
            esAutoescuela=true;
            System.out.println("es autoescuela");
        }
        else
            System.out.println("es clinica");
    }
    
    @FXML public void botonRegistrar()
    {
        
        tipoEntidad();
        
        ValidacionCampoVacio campoVacio= new ValidacionCampoVacio();
        ValidacionCorreo campoCorreo= new ValidacionCorreo();
        ValidacionCantidadCaracteresMinima campoClave= new ValidacionCantidadCaracteresMinima(8);
        ValidacionSoloLetras campoLetras= new ValidacionSoloLetras();
        
        ValidacionCompuesta campoNombre= new ValidacionCompuesta(campoLetras,campoVacio);
        
        
        try {
            Usuario usuarioGuardar=null; 
            
            campoNombre.Validar(txfNombreApellido.getText(), "Campo nombre y apellidos");
            campoCorreo.Validar(txfCorreo.getText(), "Campo correo");
            ArrayList<Usuario>usuariosCorreos= ServicioUsuario.obtenerCorreosUsuario();
            for(Usuario us:usuariosCorreos)
            {
                if(us.getCorreo().equalsIgnoreCase(txfCorreo.getText()))
                    throw new Exception("Este correo ya existe ");
            }
            campoClave.Validar(txfClave.getText(), "Campo clave");
            
            System.out.println("Datos Correctos");
            
            
            if(esAutoescuela==true)
            {
                usuarioGuardar = new Usuario(txfNombreApellido.getText(),
                        txfCorreo.getText(),
                        "Trabajador autoescuela",
                        "",
                        autoescuela.getId());
                ServicioUsuario.crearUsuarioBd(usuarioGuardar, Long.valueOf("5"), txfClave.getText());
            }
            else
            {
                usuarioGuardar = new Usuario(txfNombreApellido.getText(),
                        txfCorreo.getText(),
                        "Médico",
                        "",
                        clinica.getId());
                ServicioUsuario.crearUsuarioBd(usuarioGuardar, Long.valueOf("6"), txfClave.getText());
            }
            GestorEscenas.cargarConfirmacion(btnRegistrar.getScene().getWindow(), "Se ha registrado con éxito");
            GestorEscenas.cerrar(btnRegistrar);
            GestorEscenas.cerrar(ventanaAnterior);
        } catch (Exception ex) {
            GestorEscenas.cargarError(btnRegistrar.getScene().getWindow(), ex);
        }
    }
}
