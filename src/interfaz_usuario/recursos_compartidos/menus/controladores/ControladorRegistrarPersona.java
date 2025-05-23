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
import javafx.stage.Stage;
import logica.examen_medico.implementaciones.ServiciosExamenesMedicos;
import logica.examen_medico.modelos.ExamenMedico;
import logica.persona.implementaciones.ServicioPersona;
import logica.persona.modelos.Persona;
import logica.utiles.ConvertidorFecha;
import logica.validaciones_generales.ValidacionCampoVacio;
import logica.validaciones_generales.ValidacionCantidadCaracteresExacta;
import logica.validaciones_generales.ValidacionCompuesta;
import logica.validaciones_generales.ValidacionCorreo;
import logica.validaciones_generales.ValidacionSoloLetras;
import logica.validaciones_generales.ValidacionSoloNumeros;

/**
 *
 * @author Kris
 */
public class ControladorRegistrarPersona {
    

    
    @FXML private JFXButton btnRegistrar;
    
    @FXML private JFXButton btnCancelar;
    
    @FXML private TextArea txaRestricciones;
    
    @FXML private TextField txfCorreo;
    
    @FXML private TextField txfTelefono;
    
    @FXML private TextField txfDireccion;
    
    private Stage ventanaAnterior;
    
    private ExamenMedico examenMedico;
    
    private Persona persona;
    

    
    @FXML public void initialize()
    {
        System.out.println("Controlador Registrar persona iniciado");
        
        btnCancelar.setOnAction(e ->{
            GestorEscenas.cerrar(btnCancelar);
        });
    }
    
    public void setDatos(ExamenMedico examenMedico,Stage ventana,Persona persona)
    {
        this.examenMedico=examenMedico;
        this.ventanaAnterior=ventana;
        this.persona = persona;
        System.out.println(persona.getNombre());
        
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
    
    @FXML
    private void botonRegistrar() {
        System.out.println("Pulsado");
        ValidacionCampoVacio campoVacio = new ValidacionCampoVacio();
        ValidacionSoloLetras campoLetras = new ValidacionSoloLetras();
        ValidacionSoloNumeros campoNumerico = new ValidacionSoloNumeros();
        ValidacionCantidadCaracteresExacta campoCantidadExacta = new ValidacionCantidadCaracteresExacta(8);

        ValidacionCorreo campoCorreo = new ValidacionCorreo();

        ValidacionCompuesta campoDireccion = new ValidacionCompuesta(campoVacio, campoLetras);
        ValidacionCompuesta campoTelefono = new ValidacionCompuesta(campoVacio, campoNumerico, campoCantidadExacta);

        try {

            campoCorreo.Validar(txfCorreo, "Campo correo"); 
            campoTelefono.Validar(txfTelefono.getText(), "Campo telefono");
            campoDireccion.Validar(txfDireccion.getText(), "Campo direccion");


            persona.setCorreo(txfCorreo.getText());
            persona.setDireccion(txfDireccion.getText());
            persona.setFoto("");
            persona.setTelefono(txfTelefono.getText());
            persona.setFechaNacimiento(ConvertidorFecha.convertirFecha(persona.getCI().substring(0, 6)));
            
            long id=ServicioPersona.crearPersona(persona);
            
            persona.setId(id);
            
            examenMedico.setPersona(persona);

            ServiciosExamenesMedicos.crearExamenMedico(examenMedico);
            
            GestorEscenas.cargarConfirmacion(btnRegistrar.getScene().getWindow(), "Se ha registrado con éxito");
            GestorEscenas.cerrar(btnRegistrar);
            GestorEscenas.cerrar(ventanaAnterior);
        } catch (Exception ex) {
            GestorEscenas.cargarError(btnRegistrar.getScene().getWindow(), ex);
        }
    }
}
