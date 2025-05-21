/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logica.validaciones_generales.ValidacionCampoVacio;
import logica.validaciones_generales.ValidacionCantidadCaracteresExacta;
import logica.validaciones_generales.ValidacionCarnet;
import logica.validaciones_generales.ValidacionCompuesta;
import logica.validaciones_generales.ValidacionFecha;
import logica.validaciones_generales.ValidacionPuntosInfraccion;
import logica.validaciones_generales.ValidacionSoloLetras;
import logica.validaciones_generales.ValidacionSoloNumeros;

/**
 *
 * @author Adrian
 */
public class ControladorRegistrarInfraccion {

    @FXML
    private RadioButton rbtLeve;

    @FXML
    private RadioButton rbtGrave;

    @FXML
    private RadioButton rbtMuyGrave;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TextField txfNombreOficial;

    @FXML
    private TextField txfLugar;

    @FXML
    private TextField txfPuntos;

    @FXML
    private TextArea txfDescripcion;

    @FXML
    private TextField txfCarnet;

    @FXML
    private TextField txfNumeroLicencia;

    @FXML
    private DatePicker dtFecha;

    public void initialize() {
        System.out.println("Controlador Registrar Infraccion Activado");
        btnCancelar.setOnAction(e -> GestorEscenas.cerrar(btnCancelar));
    }

    @FXML
    private void botonRegistrar() {

        ValidacionCampoVacio validacionCampoVacio = new ValidacionCampoVacio();
        ValidacionSoloLetras validacionSoloLetras = new ValidacionSoloLetras();
        ValidacionSoloNumeros validacionSoloNumeros = new ValidacionSoloNumeros();
        ValidacionFecha validacionFecha = new ValidacionFecha();
        ValidacionCarnet validacionCarnet = new ValidacionCarnet();
        ValidacionCantidadCaracteresExacta validacionCantidadCaracteresExacta = new ValidacionCantidadCaracteresExacta(11);
        ValidacionCantidadCaracteresExacta validacionCantidadCaracteresExactaLicencia = new ValidacionCantidadCaracteresExacta(12);
        ValidacionPuntosInfraccion validacionPuntos = new ValidacionPuntosInfraccion();

        ValidacionCompuesta campoNombre = new ValidacionCompuesta(validacionCampoVacio, validacionSoloLetras);
        ValidacionCompuesta campoCarnet = new ValidacionCompuesta(validacionCantidadCaracteresExacta, validacionSoloNumeros, validacionCarnet);
        ValidacionCompuesta campoPuntos = new ValidacionCompuesta(validacionCampoVacio, validacionPuntos, validacionSoloNumeros);
        ValidacionCompuesta campoNumeroLicencia = new ValidacionCompuesta(validacionCantidadCaracteresExactaLicencia, validacionSoloNumeros);

        try {
            if (!rbtGrave.isSelected() && !rbtLeve.isSelected() && !rbtMuyGrave.isSelected()) {
                throw new Exception("Debe elegir una gravedad de infraccion");
            }

            campoCarnet.Validar(txfCarnet.getText(), "carnet identidad");
            campoNumeroLicencia.Validar(txfNumeroLicencia.getText(), "numero licencia");
            campoPuntos.Validar(txfPuntos.getText(), "puntos infraccion");
            validacionCampoVacio.Validar(txfLugar.getText(), "lugar infraccion ");
            campoNombre.Validar(txfNombreOficial.getText(), "nombre oficial");
            validacionCampoVacio.Validar(txfDescripcion.getText(), "descripcion");
            validacionFecha.Validar(dtFecha.getValue(), "fecha examen");

            System.out.println("Datos Correctos");
            GestorEscenas.cargarConfirmacion(btnRegistrar.getScene().getWindow(), "Se ha registrado con éxito");
            GestorEscenas.cerrar(btnRegistrar);

        } catch (Exception ex) {
            GestorEscenas.cargarError(btnRegistrar.getScene().getWindow(), ex);
        }

    }
}
