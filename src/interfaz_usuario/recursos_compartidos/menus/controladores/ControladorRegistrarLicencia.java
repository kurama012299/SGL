/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;


import com.jfoenix.controls.JFXButton;
import gestor_interfaces.GestorEscenas;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import logica.examen_conduccion.implementaciones.ServiciosExamenesConduccion;
import logica.examen_conduccion.modelos.ExamenConduccion;
import logica.examen_medico.modelos.ExamenMedico;
import logica.licencia.implementaciones.ServicioLicencia;
import logica.licencia.modelos.Licencia;
import logica.utiles.ServicioUtil;


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
    @FXML private Label lblNumeroLicencia;
    private ExamenConduccion examen;
    
    private Date fechaEmision;
    
     @FXML public void initialize()
    {
        System.out.println("Controlador Registrar licencia iniciado");
        
        txaRestricciones.setEditable(false);
        try {
            System.out.println(ServicioUtil.obtenerTiposLicencia());
            cmbTipoLicencia.setItems(ServicioUtil.obtenerTiposLicencia());
        } catch (Exception ex) {
           GestorEscenas.cargarError(cmbTipoLicencia.getScene().getWindow(), ex);
        }
    }
    
    public void setDatos(ExamenMedico examenMedico,Stage ventana,ExamenConduccion examen)
    {
        this.examenMedico=examenMedico;
        this.ventanaAnterior=ventana;
        this.fechaEmision=examen.getFecha();
        this.examen=examen;
        
        dtpFechaEmision.setValue(fechaEmision.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        dtpFechaEmision.setEditable(false);
        try {
            lblNumeroLicencia.setText("Número licencia: "+ServicioLicencia.obtenerProximoIdLicencia());
        } catch (Exception ex) {
            GestorEscenas.cargarError(lblNumeroLicencia.getScene().getWindow(), ex);
        }
    }
    
    @FXML public void cerrar()
    {
        GestorEscenas.cerrar(btnCancelar);
    }
    
    @FXML public void registrar() throws Exception
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaEmision);
        calendar.add(Calendar.YEAR, 10);
        Date fechaVencimiento =calendar.getTime();
        if(!rbtMoto.isSelected() && !rbtAuto.isSelected()
                && !rbtCamion.isSelected() && !rbtOmnibus.isSelected())
            throw new Exception("Debe seleccionar una categoria");
        
        if(cmbTipoLicencia.getValue()==null)
            throw new Exception("Debe seleccionar un tipo de licencia");
        
        Licencia licencia = new Licencia(fechaEmision, fechaVencimiento, true, 0, cmbTipoLicencia.getValue().toString(), "Vigente");
        licencia.setCategorias(obtenerCategoria());
        
        ServiciosExamenesConduccion.crearExamenPractico(examen);
        ServicioLicencia.crearLicencia(licencia, examenMedico);
        
        GestorEscenas.cargarConfirmacion(btnRegistrar.getScene().getWindow(),"Licencia creada");
        GestorEscenas.cerrar(btnCancelar);
        GestorEscenas.cerrar(ventanaAnterior);
    }
    
    private ArrayList<String> obtenerCategoria()
    {
        ArrayList<String> categorias = new ArrayList();
        
        if(rbtMoto.isSelected())
            categorias.add("Moto");
        
        if(rbtAuto.isSelected())
            categorias.add("Automovil");
        
        if(rbtCamion.isSelected())
            categorias.add("Camion");
        
        if(rbtOmnibus.isSelected())
            categorias.add("Autobus");
        
        return categorias;
    }
}
