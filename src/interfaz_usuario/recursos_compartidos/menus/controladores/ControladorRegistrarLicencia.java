/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;


import com.jfoenix.controls.JFXButton;
import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import logica.examen_medico.modelos.ExamenMedico;
import logica.validaciones_generales.ValidacionCampoVacio;
import logica.validaciones_generales.ValidacionFecha;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorRegistrarLicencia {
    
    private ExamenMedico examenMedico;
    private Stage ventanaAnterior;
    @FXML private ComboBox cmbTipoLicencia;
    @FXML private RadioButton rbtMoto;
    @FXML private RadioButton rbtAuto;
    @FXML private RadioButton rbtCamion;
    @FXML private RadioButton rbtOmnibus;
    @FXML private TextArea txaRestricciones;
    @FXML private DatePicker dtpFechaEmision;
    @FXML private JFXButton btnRegistrar;
    @FXML private JFXButton btnCancelar;
    
     @FXML public void initialize()
    {
        System.out.println("Controlador Registrar licencia iniciado");
    }
    
    public void setDatos(ExamenMedico examenMedico,Stage ventana)
    {
        this.examenMedico=examenMedico;
        this.ventanaAnterior=ventana;
    }
    
    @FXML public void cerrar()
    {
        GestorEscenas.cerrar(btnCancelar);
    }
    
    @FXML public void registrar()
    {
        ValidacionCampoVacio validacionCampoVacio= new ValidacionCampoVacio();
        ValidacionFecha validacionFecha = new ValidacionFecha();
        
    }
}
