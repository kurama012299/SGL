/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.medico.controladores;

import com.jfoenix.controls.JFXRadioButton;
import gestor_interfaces.GestorEscenas;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.autentificacion.Autentificador;
import logica.entidad.implementaciones.ServicioEntidad;
import logica.examen_medico.implementaciones.ServiciosExamenesMedicos;
import logica.examen_medico.modelos.ExamenMedico;
import logica.examen_medico.validaciones.ValidacionCrearExamenMedico;
import logica.persona.implementaciones.ServicioPersona;
import logica.persona.modelos.Persona;
import logica.restricciones.implementacion.ServicioRestriccion;
import logica.usuario.implementaciones.ServicioUsuario;
import logica.usuario.modelos.Usuario;
import logica.validaciones_generales.ValidacionCampoVacio;
import logica.validaciones_generales.ValidacionCantidadCaracteresExacta;
import logica.validaciones_generales.ValidacionCarnet;
import logica.validaciones_generales.ValidacionComboBoxVacio;
import logica.validaciones_generales.ValidacionCompuesta;
import logica.validaciones_generales.ValidacionFecha;
import logica.validaciones_generales.ValidacionSoloLetras;
import logica.validaciones_generales.ValidacionSoloNumeros;

/**
 *
 * @author Adrian
 */
public class ControladorRegistrarExamenMedico {
    
    @FXML private ScrollPane scrpRestricciones;
    @FXML private Label lblRestricciones;
    @FXML private Button btnCancelar;
    @FXML private Button btnRegistrar;
    @FXML private TextField txfNombre;
    @FXML private TextField txfCarnet;
    @FXML private DatePicker dtFecha;
    @FXML private ComboBox<String> cmbNombreEntidad;
    @FXML private RadioButton rbtAprobado;
    
    public void initialize() {
        System.out.println("Controlador Registrar Examen Médico Activado");
        
        // Configuración inicial
        btnCancelar.setOnAction(e -> GestorEscenas.cerrar(btnCancelar));
        btnRegistrar.setText("Registrar");
        
        // Cargar combobox y restricciones
        cargarDatosIniciales();
        
        cmbNombreEntidad.setVisible(true);
        cmbNombreEntidad.setDisable(false);
        
        // Configurar visibilidad de restricciones (siempre visible para médico)
        visibilidadRestricciones(true);
    }
    
    private void cargarDatosIniciales() {
        try {
            // Cargar examinadores y clínicas
            List<Usuario> examinadores = ServicioUsuario.obtenerUsuariosExamenesMedicos();
            List<String> clinicas = ServicioEntidad.obtenerNombresClinicas();
            
            cmbNombreEntidad.getItems().setAll(clinicas);
            
            // Cargar restricciones en el ScrollPane
            cargarRestricciones();
            
        } catch (Exception e) {
            GestorEscenas.cargarError(btnCancelar.getScene().getWindow(), e);
        }
    }
    
    private void cargarRestricciones() {
        try {
            List<String> restricciones = ServicioRestriccion.obtenerRestricciones();
            VBox contenedorRestricciones = new VBox(5);
            
            for (String restriccion : restricciones) {
                JFXRadioButton radioButton = new JFXRadioButton(restriccion);
                contenedorRestricciones.getChildren().add(radioButton);
            }
            
            // Configurar el ScrollPane
            scrpRestricciones.setContent(contenedorRestricciones);
            scrpRestricciones.setFitToWidth(true);
            
        } catch (Exception e) {
            System.err.println("Error al cargar restricciones: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void visibilidadRestricciones(boolean esVisible) {
        scrpRestricciones.setVisible(esVisible);
        lblRestricciones.setVisible(esVisible);
        scrpRestricciones.setManaged(esVisible);
        lblRestricciones.setManaged(esVisible);
    }
    
    @FXML 
    private void botonRegistrar() {
        ValidacionCampoVacio validacionCampoVacio = new ValidacionCampoVacio();
        ValidacionSoloLetras validacionSoloLetras = new ValidacionSoloLetras();
        ValidacionSoloNumeros validacionSoloNumeros = new ValidacionSoloNumeros();
        ValidacionFecha validacionFecha = new ValidacionFecha();
        ValidacionCarnet validacionCarnet = new ValidacionCarnet();
        ValidacionCantidadCaracteresExacta validacionCantidadCaracteresExacta = new ValidacionCantidadCaracteresExacta(11);
        ValidacionComboBoxVacio validacionComboBoxVacio = new ValidacionComboBoxVacio();
        
        ValidacionCompuesta campoNombre = new ValidacionCompuesta(validacionCampoVacio, validacionSoloLetras);
        ValidacionCompuesta campoCarnet = new ValidacionCompuesta(validacionCantidadCaracteresExacta, validacionSoloNumeros, validacionCarnet);

        try {
            // Validar campos obligatorios
            campoNombre.validar(txfNombre.getText(), "nombre");
            campoCarnet.validar(txfCarnet.getText(), "carnet identidad");
            validacionFecha.validar(dtFecha.getValue(), "fecha examen");
            validacionComboBoxVacio.validar(cmbNombreEntidad, "nombre entidad");

            Usuario examinador = Autentificador.usuario;
            
            ExamenMedico examenMedico = new ExamenMedico(
                    Date.from(dtFecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    rbtAprobado.isSelected(),
                    ServicioEntidad.obtenerEntidadPorNombre(cmbNombreEntidad.getValue()),
                    ServicioPersona.obtenerPersonaPorCi(txfCarnet.getText()),
                    examinador,
                    obtenerRestricciones());
            
            if (ValidacionCrearExamenMedico.validarExamenMedico(txfNombre.getText(), txfCarnet.getText(), examinador)) {
                ServiciosExamenesMedicos.crearExamenMedico(examenMedico);
                GestorEscenas.cerrar(btnCancelar);
            } else {
                Window ventanaActual = btnRegistrar.getScene().getWindow();
                Map<String,String> nombreApellidos = separarNombreApellidos(txfNombre.getText());
                Persona persona = new Persona(nombreApellidos.get("nombre"), nombreApellidos.get("apellidos"), txfCarnet.getText());
                GestorEscenas.cargarRegistrarPersona(ventanaActual, (Stage) btnRegistrar.getScene().getWindow(), examenMedico, persona);
            }

        } catch (Exception ex) {
            GestorEscenas.cargarError(btnRegistrar.getScene().getWindow(), ex);
        }
    }

    private ArrayList<String> obtenerRestricciones() {
        ArrayList<String> restricciones = new ArrayList<>();
        
        Node content = scrpRestricciones.getContent();
        if (content instanceof VBox) {
            VBox vbox = (VBox) content;
            for (Node node : vbox.getChildren()) {
                if (node instanceof JFXRadioButton) {
                    JFXRadioButton rb = (JFXRadioButton) node;
                    if (rb.isSelected()) {
                        restricciones.add(rb.getText().trim());
                    }
                }
            }
        }
        
        return restricciones;
    }
    
    public static Map<String, String> separarNombreApellidos(String nombreCompleto) {
        nombreCompleto = nombreCompleto.trim().replaceAll("\\s+", " ");
        String[] partes = nombreCompleto.split(" ");
        Map<String, String> resultado = new HashMap<>();

        if (partes.length == 0) {
            resultado.put("nombre", "");
            resultado.put("apellidos", "");
        } else if (partes.length <= 2) {
            resultado.put("nombre", partes[0]);
            resultado.put("apellidos", partes.length > 1 ? partes[1] : "");
        } else {
            int splitPoint = partes.length - 2;
            String nombre = String.join(" ", Arrays.copyOfRange(partes, 0, splitPoint));
            String apellidos = String.join(" ", Arrays.copyOfRange(partes, splitPoint, partes.length));
            resultado.put("nombre", nombre);
            resultado.put("apellidos", apellidos);
        }

        return resultado;
    }
}
