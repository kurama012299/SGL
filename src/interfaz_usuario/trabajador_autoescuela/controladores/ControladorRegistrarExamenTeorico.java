/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.trabajador_autoescuela.controladores;

import gestor_interfaces.GestorEscenas;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import logica.autentificacion.Autentificador;
import logica.entidad.implementaciones.ServicioEntidad;
import logica.entidad.modelos.EntidadRelacionada;
import logica.examen_conduccion.implementaciones.ServiciosExamenesConduccion;
import logica.examen_conduccion.modelos.ExamenConduccion;
import logica.examen_conduccion.validaciones.ValidacionCrearExamenTeorico;
import logica.examen_medico.modelos.ExamenMedico;
import logica.persona.implementaciones.ServicioPersona;
import logica.validaciones_generales.ValidacionCampoVacio;
import logica.validaciones_generales.ValidacionCantidadCaracteresExacta;
import logica.validaciones_generales.ValidacionCarnet;
import logica.validaciones_generales.ValidacionComboBoxVacio;
import logica.validaciones_generales.ValidacionCompuesta;
import logica.validaciones_generales.ValidacionFecha;
import logica.validaciones_generales.ValidacionGrupoRadioButton;
import logica.validaciones_generales.ValidacionSoloLetras;
import logica.validaciones_generales.ValidacionSoloNumeros;

/**
 *
 * @author Kris
 */
public class ControladorRegistrarExamenTeorico {
    
    @FXML private TextField txfNombreExaminado;
    
    @FXML private TextField txfCi;
    
    @FXML private ComboBox cmbNombreEntidad;
    
    @FXML private DatePicker dpFechaExamen;
    
    @FXML private RadioButton rdbtAprobado;
    
    @FXML private Button btnCancelar;
    
    @FXML private Button btnRegistrar;
    
    @FXML public void initialize()
    {
        System.out.println("Controlador registrar Examen teorico iniciado");
        btnCancelar.setOnAction(e ->{
            GestorEscenas.cerrar(btnCancelar);
        });
        rellenarComboBox();
    }
    
    @FXML private void rellenarComboBox()
    {
        ObservableList<String>nombresAutoescuelas=FXCollections.observableArrayList();
        ObservableList<EntidadRelacionada>autoescuelas=FXCollections.observableArrayList();
        try {
            autoescuelas=ServicioEntidad.obtenerAutoescuelas();
            for(EntidadRelacionada aut: autoescuelas)
            {
                nombresAutoescuelas.add(aut.getNombre());
            }
            cmbNombreEntidad.setItems(nombresAutoescuelas);
        } catch (Exception ex) {
            GestorEscenas.cargarError(cmbNombreEntidad.getScene().getWindow(), ex);
        }
    }
    
    @FXML private void registrarValidar()
    {
        ValidacionCampoVacio validacionCampoVacio = new ValidacionCampoVacio();
        ValidacionSoloLetras validacionSoloLetras = new ValidacionSoloLetras();
        ValidacionSoloNumeros validacionSoloNumeros = new ValidacionSoloNumeros();
        
        ValidacionFecha validacionFecha = new ValidacionFecha();
        ValidacionCarnet validacionCarnet = new ValidacionCarnet();
        
        ValidacionCantidadCaracteresExacta validacionCantidadCaracteresExacta = new ValidacionCantidadCaracteresExacta(11);
        ValidacionComboBoxVacio validacionComboBoxVacio= new ValidacionComboBoxVacio();
        

        ValidacionCompuesta campoNombre = new ValidacionCompuesta(validacionCampoVacio,validacionSoloLetras);
        ValidacionCompuesta campoCarnet = new ValidacionCompuesta(validacionCantidadCaracteresExacta,validacionSoloNumeros,validacionCarnet);
       
        
        ExamenConduccion examen = null;
        ExamenMedico examenMedico;
        try {
            
            campoNombre.validar(txfNombreExaminado.getText(), "nombre");
            campoCarnet.validar(txfCi.getText(), "carnet identidad");
            validacionFecha.validar(dpFechaExamen.getValue(), "fecha examen");
            validacionComboBoxVacio.validar(cmbNombreEntidad, "nombre entidad");   
            
            System.out.println("Datos Correctos");
            
            examen = new ExamenConduccion(Date.from(dpFechaExamen.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    rdbtAprobado.isSelected(),
                    ServicioEntidad.obtenerEntidadPorNombre((String) cmbNombreEntidad.getValue()),
                    ServicioPersona.obtenerPersonaPorCi(txfCi.getText()),
                    Autentificador.usuario, "Teorico");
            examenMedico = ValidacionCrearExamenTeorico.validarCrearExamenTeorico(txfNombreExaminado.getText(),
                    txfCi.getText(), Autentificador.usuario);
            if (examen.getFecha().before(examenMedico.getFecha())) {
                throw new Exception("No puede realizar el examen teorico antes del medico");
            }
            ServiciosExamenesConduccion.crearExamenTeorico(examen);
            GestorEscenas.cerrar(btnCancelar);
            GestorEscenas.cargarConfirmacion(btnRegistrar.getScene().getWindow(), "Se ha registrado con éxito");
            GestorEscenas.cerrar(btnRegistrar);
        } catch (Exception ex) {
            GestorEscenas.cargarError(rdbtAprobado.getScene().getWindow(), ex);
        }
    }

   
    
}
