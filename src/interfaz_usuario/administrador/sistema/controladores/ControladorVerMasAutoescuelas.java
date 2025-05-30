/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.sistema.controladores;

import gestor_interfaces.GestorEscenas;
import gestor_interfaces.GestorEstadisticas;
import gestor_interfaces.modelos.Estadistica;
import infraestructura.ConectorBaseDato;
import interfaz_usuario.administrador.sistema.controladores.ControladorVerMasClinicas;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorRegistrarUsuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.entidad.implementaciones.ServicioEntidad;
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
    
    @FXML private Button btnEditar;
    
    @FXML private Button btnEliminar;
    
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
        
    btnEditar.setOnAction(e -> {

            if (btnEditar.getText().equals("Guardar")) {

                // Modo guardar
                try {
                    System.out.println("Guardando cambios...");

                    // Actualizar el objeto con los nuevos valores
                    autoescuela.setCorreo(txfCorreo.getText());
                    autoescuela.setNombreDirector(txfDirector.getText());
                    autoescuela.setTelefono(txfTelefono.getText());

                    // Guardar en la base de datos
                    ServicioEntidad.actualizarEntidad(autoescuela);

                    // Desactivar edición
                    txfCorreo.setEditable(false);
                    txfDirector.setEditable(false);
                    txfTelefono.setEditable(false);
                    txfCorreo.setDisable(true);
                    txfDirector.setDisable(true);
                    txfTelefono.setDisable(true);
                    editarCasillas(false);
                    btnEditar.setText("Editar");
                    GestorEscenas.cargarConfirmacion(btnEditar.getScene().getWindow(), "Cambios guardados");
                } catch (Exception ex) {
                    GestorEscenas.cargarError(btnEditar.getScene().getWindow(), ex);
                }

            } else {

                // Modo edición
                System.out.println("Modo edición activado");
                txfCorreo.setEditable(true);
                txfDirector.setEditable(true);
                txfTelefono.setEditable(true);
                txfCorreo.setDisable(false);
                txfDirector.setDisable(false);
                txfTelefono.setDisable(false);
                editarCasillas(true);
                btnEditar.setText("Guardar");
            }
        });
    
    btnEliminar.setOnAction(e -> {
            try {
                ServicioEntidad.eliminarEntidad(autoescuela.getId());
                GestorEscenas.cargarConfirmacion(btnEditar.getScene().getWindow(), "Entidad eliminada");
                GestorEscenas.cerrar(btnAtras);
            } catch (Exception ex) {
                Logger.getLogger(ControladorVerMasClinicas.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
    }

    private void editarCasillas(boolean enable) {
        String style = enable ? "-fx-background-color: #ffffff; -fx-border-color: #4CAF50;"
                : "-fx-background-color: #f4f4f4; -fx-border-color: #cccccc;";

        txfCorreo.setStyle(style);
        txfDirector.setStyle(style);
        txfTelefono.setStyle(style);
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
