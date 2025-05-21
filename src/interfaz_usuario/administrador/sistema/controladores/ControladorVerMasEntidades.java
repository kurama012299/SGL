/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.sistema.controladores;

import gestor_interfaces.GestorEscenas;
import gestor_interfaces.GestorEstadisticas;
import gestor_interfaces.modelos.Estadistica;
import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.SQLException;
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
public class ControladorVerMasEntidades {

    private EntidadRelacionada Entidad;

    @FXML private TextField TextFieldNombre;

    @FXML private TextField TextFieldDirector;

    @FXML private TextField TextFieldCantidadPersonas;

    @FXML private TextField TextFieldTelefono;

    @FXML private TextField TextFieldCorreo;

    @FXML private TextField TextFieldTipo;

    @FXML private TextArea TextFieldDireccion;
    
    @FXML private Button btnAtras;

    

    @FXML public void initialize() {
        System.out.println("Controlador ver mas Entidades iniciado");
    }

    public void setDatos(EntidadRelacionada Entidad) throws Exception {
        this.Entidad = Entidad;
        iniciar();
    }
    
    
    @FXML public void iniciar() throws Exception {

        System.out.println("Iniciar llamado");
        btnAtras.setOnAction(e -> {
            GestorEscenas.cerrar(btnAtras);
        });
        if (Entidad.getTipoEntidad().equals("Clinica")) {

            String puntosStr = String.valueOf(Math.round(totalExamenesClinica()));
            TextFieldCantidadPersonas.setText(puntosStr);

        } else {

            String puntosStr = String.valueOf(Math.round(totalExamenesAutoescuela()));
            TextFieldCantidadPersonas.setText(puntosStr);
        }
        

        TextFieldDirector.setText(Entidad.getNombreDirector());
        TextFieldCorreo.setText(Entidad.getCorreo());
        TextFieldNombre.setText(Entidad.getNombre());
        TextFieldTelefono.setText(Entidad.getTelefono());
        TextFieldDireccion.setText(Entidad.getDireccion());
        TextFieldTipo.setText(Entidad.getTipoEntidad());
        
    }

    private double totalExamenesClinica() throws SQLException, Exception {

        try (Connection conn = ConectorBaseDato.Conectar()) {
            Estadistica Estadistica = GestorEstadisticas.ObtenerCantidadExamenesClinica(conn);
            return Estadistica.GetValor();
        }
    }

    private double totalExamenesAutoescuela() throws SQLException, Exception {

        try (Connection conn = ConectorBaseDato.Conectar()) {
            Estadistica Estadistica = GestorEstadisticas.ObtenerCantidadExamenesAutoescuela(conn);
            return Estadistica.GetValor();
        }

    }
    
}
