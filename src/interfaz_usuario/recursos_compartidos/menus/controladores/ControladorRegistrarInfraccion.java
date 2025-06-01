/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import gestor_interfaces.GestorEscenas;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import logica.infraccion.implementaciones.ServicioInfraccion;
import logica.infraccion.modelos.Infraccion;
import logica.licencia.implementaciones.ServicioLicencia;
import logica.licencia.modelos.Licencia;
import logica.validaciones_generales.ValidacionCampoVacio;
import logica.validaciones_generales.ValidacionCantidadCaracteresExacta;
import logica.validaciones_generales.ValidacionCantidadCaracteresMaxima;
import logica.validaciones_generales.ValidacionCarnet;
import logica.validaciones_generales.ValidacionCompuesta;
import logica.validaciones_generales.ValidacionConductorExiste;
import logica.validaciones_generales.ValidacionFecha;
import logica.validaciones_generales.ValidacionGrupoRadioButton;
import logica.validaciones_generales.ValidacionIdLicenciaRelacionCI;
import logica.validaciones_generales.ValidacionSoloLetras;
import logica.validaciones_generales.ValidacionSoloNumeros;


/*
 * @author Kris
 */
public class ControladorRegistrarInfraccion {
    
    @FXML private TextField txfCi;
    
    @FXML private TextField txfNumeroLicencia;
    
    @FXML private TextField txfLugarInfraccion;
    
    @FXML private TextField txfNombreOficial;
    
    @FXML private TextArea txaDescripcion;
    
    @FXML private ComboBox cmbPtosDeducidos;
    
    @FXML private DatePicker dpFechaInfraccion;
    
    @FXML private RadioButton rdbtnBotonPagado;
    
    @FXML private RadioButton rdbtnLeve;
    
    @FXML private RadioButton rdbtnGrave;
    
    @FXML private RadioButton rdbtnMuyGrave;
    
    @FXML private Button btnAtras;
    
    @FXML private Button btnRegistrar;
    
    @FXML private ToggleGroup grupoGravedadrdbt ;
    
    @FXML public void initialize()
    {
        System.out.println("Controlador registrar infraccion iniciado");
        btnAtras.setOnAction(e ->{
            GestorEscenas.cerrar(btnAtras);
        });
        llenarComboBox();
        automaticoCategoria();
    }
    
    @FXML private void llenarComboBox()
    {
        cmbPtosDeducidos.getItems().add("8");
        cmbPtosDeducidos.getItems().add("10");
        cmbPtosDeducidos.getItems().add("12");
    }
    
    @FXML private void automaticoCategoria()
    {
        ObjectProperty<RadioButton>seleccion= new SimpleObjectProperty<>();
        
       grupoGravedadrdbt.selectedToggleProperty().addListener((obs,valorViejo,valorNuevo)->{
           if(valorNuevo!= null)
           {
               seleccion.set((RadioButton)valorNuevo);
           }
       });
       cmbPtosDeducidos.getSelectionModel().selectedItemProperty().addListener((obs,valorViejo,valorNuevo)-> {
           if(valorNuevo!= null)
           {
               switch((String)valorNuevo){
                   case "8": rdbtnLeve.setSelected(true); break;
                   case "10":rdbtnGrave.setSelected(true); break;
                   case "12":rdbtnMuyGrave.setSelected(true);break;
               }
           }
       });
       
       seleccion.addListener((obs,valorViejo,valorNuevo)->{
           if(valorNuevo!= null){
               if(valorNuevo.getText().equalsIgnoreCase("Leve"))
               {
                   cmbPtosDeducidos.getSelectionModel().select("8");
               }
               else if(valorNuevo.getText().equalsIgnoreCase("Grave"))
               {
                   cmbPtosDeducidos.getSelectionModel().select("10");
               }
               else if(valorNuevo.getText().equalsIgnoreCase("Muy grave"))
               {
                   cmbPtosDeducidos.getSelectionModel().select("12");
               }
               
           }
       });
    }
    
    
    @FXML private void botonRegistrar()
    {
        ValidacionCampoVacio validacionCampoVacio = new ValidacionCampoVacio();
        ValidacionSoloLetras validacionSoloLetras = new ValidacionSoloLetras();
        ValidacionSoloNumeros validacionSoloNumeros = new ValidacionSoloNumeros();
        ValidacionFecha validacionFecha = new ValidacionFecha();
        ValidacionCarnet validacionCarnet = new ValidacionCarnet();
        ValidacionCantidadCaracteresExacta validacionCantidadCaracteresExacta = new ValidacionCantidadCaracteresExacta(11);
        ValidacionCantidadCaracteresMaxima validacionCantidadCaracteresMaxima= new ValidacionCantidadCaracteresMaxima(6, "Numero Licencia");
        ValidacionGrupoRadioButton validarGrupoBotones= new ValidacionGrupoRadioButton();
        
        ValidacionCompuesta campoNombre = new ValidacionCompuesta(validacionCampoVacio,validacionSoloLetras);
        ValidacionCompuesta campoCarnet = new ValidacionCompuesta(validacionCantidadCaracteresExacta,validacionSoloNumeros,validacionCarnet);
        ValidacionCompuesta campoNumeroLicencia= new ValidacionCompuesta(validacionSoloNumeros,validacionCampoVacio,validacionCantidadCaracteresMaxima);
        
        try {
            campoCarnet.validar(txfCi.getText(), "Carnet identidad");
            ValidacionConductorExiste.Validar(txfCi.getText());
            campoNumeroLicencia.validar(txfNumeroLicencia.getText(), "Numero de licencia");
            long idLicencia = Long.parseLong(txfNumeroLicencia.getText());
            ValidacionIdLicenciaRelacionCI.validarRelacionCiLicencia(txfCi.getText(),idLicencia);
            campoNombre.validar(txfLugarInfraccion.getText(), "Lugar infraccion");
            campoNombre.validar(txfNombreOficial.getText(), "Nombre oficial");
            validacionFecha.validar(dpFechaInfraccion.getValue(), "Fecha infraccion");
            campoNombre.validar(txaDescripcion.getText(), "Descripcion");
            validarGrupoBotones.validar(grupoGravedadrdbt, "gravedad de la infraccion");
            
            boolean pagado = rdbtnBotonPagado.isSelected();
            java.sql.Date fecha = java.sql.Date.valueOf(dpFechaInfraccion.getValue());
            int puntos = Integer.parseInt((String) cmbPtosDeducidos.getValue());
            RadioButton seleccionado = (RadioButton) grupoGravedadrdbt.getSelectedToggle();
            String gravedad = seleccionado.getText();
            
            Licencia licencia = ServicioLicencia.ObtenerLicenciaPorId(idLicencia);
            if(licencia.getCantPuntos()>=36)
                throw new Exception("Esa licencia ya alcanzo el maximo de puntos");
            if(!licencia.getEstado().equals("Vigente"))
                throw new Exception("El estado de la licencia no permite asignarle una infraccion");
            
            
            Infraccion infraccion = new Infraccion(
                    fecha,
                    txfLugarInfraccion.getText(),
                    txaDescripcion.getText(),
                    puntos,
                    pagado,
                    idLicencia,
                    gravedad,
                    txfNombreOficial.getText());
            
            ServicioInfraccion.crearInfraccionBaseDatos(infraccion);
            ServicioLicencia.actualizarPuntosLicencia(idLicencia, puntos);
            
            GestorEscenas.cargarConfirmacion(btnRegistrar.getScene().getWindow(), "Se ha registrado con éxito");
            GestorEscenas.cerrar(btnRegistrar);

        } catch (Exception e) {
            GestorEscenas.cargarError(btnAtras.getScene().getWindow(), e);
        }

    }
}
