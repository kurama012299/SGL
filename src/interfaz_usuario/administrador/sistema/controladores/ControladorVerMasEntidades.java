/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.sistema.controladores;

import gestor_interfaces.GestorEstadisticas;
import gestor_interfaces.modelos.Estadistica;
import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logica.entidad.modelos.EntidadRelacionada;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorVerMasEntidades {

    private EntidadRelacionada Entidad;

    @FXML
    TextField TextFieldNombre;

    @FXML
    TextField TextFieldDirector;

    @FXML
    TextField TextFieldCantidadPersonas;

    @FXML
    TextField TextFieldTelefono;

    @FXML
    TextField TextFieldCorreo;

    @FXML
    TextField TextFieldTipo;

    @FXML
    TextArea TextFieldDireccion;

    @FXML
    public void initialize() {
        System.out.println("Controlador ver mas Entidades iniciado");
    }

    public void SetDatos(EntidadRelacionada Entidad) throws Exception {
        this.Entidad = Entidad;
        Iniciar();
    }

    @FXML
    public void Iniciar() throws Exception {

        System.out.println("Iniciar llamado");

        if (Entidad.getTipoEntidad().equals("Clinica")) {

            String puntosStr = String.valueOf(Math.round(TotalExamenesClinica()));
            TextFieldCantidadPersonas.setText(puntosStr);

        } else {

            String puntosStr = String.valueOf(Math.round(TotalExamenesAutoescuela()));
            TextFieldCantidadPersonas.setText(puntosStr);
        }
        

        TextFieldDirector.setText(Entidad.getNombreDirector());
        TextFieldCorreo.setText(Entidad.getCorreo());
        TextFieldNombre.setText(Entidad.getNombre());
        TextFieldTelefono.setText(Entidad.getTelefono());
        TextFieldDireccion.setText(Entidad.getDireccion());
        TextFieldTipo.setText(Entidad.getTipoEntidad());
        
    }

    private double TotalExamenesClinica() throws SQLException, Exception {

        try (Connection conn = ConectorBaseDato.Conectar()) {
            Estadistica Estadistica = GestorEstadisticas.ObtenerCantidadExamenesClinica(conn);
            return Estadistica.GetValor();
        }
    }

    private double TotalExamenesAutoescuela() throws SQLException, Exception {

        try (Connection conn = ConectorBaseDato.Conectar()) {
        Estadistica Estadistica = GestorEstadisticas.ObtenerCantidadExamenesAutoescuela(conn);
        return Estadistica.GetValor();
    }

    }
}
