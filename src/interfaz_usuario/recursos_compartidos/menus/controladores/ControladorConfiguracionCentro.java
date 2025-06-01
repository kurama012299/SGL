/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logica.centro.implementaciones.ServiciosCentro;
import logica.centro.modelos.Centro;

/**
 *
 * @author Kris
 */
public class ControladorConfiguracionCentro {
    
    @FXML private Button btnCerrar;
    
    @FXML private Button btnGuardar;
    
    @FXML private TextField txfDirectorGeneral;
    
    @FXML private TextField txfSecretarioGeneral;
    
    @FXML private TextField txfTelefono;
    
    @FXML private TextField txfContabilidad;
    
    @FXML private TextField txfRecursosHumanos;
    
    private Centro centro;
    
    public void initialize() throws Exception
    {
        centro=ServiciosCentro.obtenerCentro();
        System.out.println("Controlador configuracion del centro iniciado");
        cargarTxf();
        
        btnCerrar.setOnAction(e ->{
            GestorEscenas.cerrar(btnCerrar);
        });
        
        btnGuardar.setOnAction(e ->{
            centro.setNombreDirectorGeneral(txfDirectorGeneral.getText());
            centro.setNombreJefeDptoCont(txfContabilidad.getText());
            centro.setNombreJefeDptoRH(txfRecursosHumanos.getText());
            centro.setNombreSecretarioGS(txfSecretarioGeneral.getText());
            centro.setTelefono(txfTelefono.getText());
            try {
                ServiciosCentro.actualizarCentro(centro);
                GestorEscenas.cargarConfirmacion(btnGuardar.getScene().getWindow(), "Datos guardados");
                GestorEscenas.cerrar(btnCerrar);
            } catch (Exception ex) {
               GestorEscenas.cargarError(btnGuardar.getScene().getWindow(), ex);
            }
        });
    }
    
    private void cargarTxf()
    {
        txfDirectorGeneral.setText(centro.getNombreDirectorGeneral());
        txfContabilidad.setText(centro.getNombreJefeDptoCont());
        txfRecursosHumanos.setText(centro.getNombreJefeDptoRH());
        txfSecretarioGeneral.setText(centro.getNombreSecretarioGS());
        txfTelefono.setText(centro.getTelefono());
    }
    
    
}
