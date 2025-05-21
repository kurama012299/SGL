/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.sistema.controladores;

import gestor_interfaces.GestorEscenas;
import gestor_interfaces.GestorEstadisticas;
import gestor_interfaces.modelos.Estadistica;
import infraestructura.ConectorBaseDato;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorRegistrarUsuario;
import java.sql.Connection;
import java.sql.SQLException;
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
    
    @FXML private Button btnAtras;
    
    @FXML private Button btnagregarUsuario;

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
        btnAtras.setOnAction(e -> {
            GestorEscenas.cerrar(btnAtras);
        });
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
    
    @FXML private void transicionregistrarUsuario() 
    {
       Window ventanaActual = txfNombre.getScene().getWindow();
        try {
            GestorEscenas.cargarRegistrarUsuario(ventanaActual, (Stage)txfNombre.getScene().getWindow(), autoescuela,null);
        } catch (Exception e) {
            GestorEscenas.cargarError(ventanaActual, e);
        }
       
    }
    

}
