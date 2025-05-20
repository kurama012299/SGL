/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.sistema.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logica.entidad.modelos.EntidadRelacionada;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorVerMasClinicas {
    
    private EntidadRelacionada Clinica;

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

    
    @FXML public void initialize() {
        System.out.println("Controlador ver mas Clinicas iniciado");
    }

    public void SetDatos(EntidadRelacionada Clinica) throws Exception {
        this.Clinica = Clinica;
        Iniciar();
    }

    @FXML
    public void Iniciar() throws Exception {

        System.out.println("Iniciar llamado");
        
        txfDirector.setText(Clinica.getNombreDirector());
        txfCorreo.setText(Clinica.getCorreo());
        txfNombre.setText(Clinica.getNombre());
        txfTelefono.setText(Clinica.getTelefono());
        txfDireccion.setText(Clinica.getDireccion());
        
    }

    @FXML private void cerrar()
    {
        Stage ventana = (Stage) btnAtras.getScene().getWindow();
        ventana.close();
    }
    
}
