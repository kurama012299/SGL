/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.sistema.controladores;

import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.entidad.modelos.EntidadRelacionada;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorVerMasClinicas {
    
    private EntidadRelacionada clinica;

    @FXML TextField txfNombre;

    @FXML TextField txfDirector;

    @FXML TextField txfCantidadPacientes;//Hacer Metodo en la base de datos
    
    @FXML TextField txfCantidadMedicos;//Hacer Metodo en la base de datos

    @FXML TextField txfTelefono;

    @FXML TextField txfCorreo;

    @FXML TextField txfExamenesA;//Hacer Metodo en la base de datos
    
    @FXML TextField txfExamenesD;//Hacer Metodo en la base de datos

    @FXML TextArea txfDireccion;
    
    @FXML private Button btnAtras;

    @FXML private Button btnagregarUsuario;
    
    @FXML public void initialize() {
        System.out.println("Controlador ver mas Clinicas iniciado");
    }

    public void SetDatos(EntidadRelacionada Clinica) throws Exception {
        this.clinica = Clinica;
        Iniciar();
    }

    @FXML
    public void Iniciar() throws Exception {

        System.out.println("Iniciar llamado");
        
        btnAtras.setOnAction(e ->{
            GestorEscenas.cerrar(btnAtras);
        });
        txfDirector.setText(clinica.getNombreDirector());
        txfCorreo.setText(clinica.getCorreo());
        txfNombre.setText(clinica.getNombre());
        txfTelefono.setText(clinica.getTelefono());
        txfDireccion.setText(clinica.getDireccion());
        
    }

     @FXML private void transicionregistrarUsuario() 
    {
       Window ventanaActual = txfNombre.getScene().getWindow();
        try {
            GestorEscenas.cargarRegistrarUsuario(ventanaActual, (Stage)txfNombre.getScene().getWindow(), null,clinica);
        } catch (Exception e) {
            GestorEscenas.cargarError(ventanaActual, e);
        }
       
    }
}
