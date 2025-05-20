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
public class ControladorVerMasAutoescuelas {

    private EntidadRelacionada autoescuela;

    @FXML
    TextField txfNombre;

    @FXML
    TextField txfDirector;

    @FXML
    TextField txfCantidadClientes;//Hacer Metodo en la base de datos

    @FXML
    TextField txfCantidadExaminadores;//Hacer Metodo en la base de datos

    @FXML
    TextField txfTelefono;

    @FXML
    TextField txfCorreo;

    @FXML
    TextField txfExamenesAprobados;

    @FXML
    TextArea txfDireccion;

    @FXML
    public void initialize() {
        System.out.println("Controlador ver mas Autoescuelas iniciado");
    }

    public void SetDatos(EntidadRelacionada autoescuela) throws Exception {
        this.autoescuela = autoescuela;
        Iniciar();
    }

    @FXML
    public void Iniciar() throws Exception {

        System.out.println("Iniciar llamado");

        String puntosStr = String.valueOf(Math.round(TotalExamenesAprobados()));
        txfExamenesAprobados.setText(puntosStr);
        txfDirector.setText(autoescuela.getNombreDirector());
        txfCorreo.setText(autoescuela.getCorreo());
        txfNombre.setText(autoescuela.getNombre());
        txfTelefono.setText(autoescuela.getTelefono());
        txfDireccion.setText(autoescuela.getDireccion());

    }

    private double TotalExamenesAprobados() throws SQLException, Exception {

        try (Connection conn = ConectorBaseDato.Conectar()) {
            Estadistica Estadistica = GestorEstadisticas.obtenerCantidadExamenesAutoescuelaAprobados(conn);
            return Estadistica.GetValor();
        }
    }

}
