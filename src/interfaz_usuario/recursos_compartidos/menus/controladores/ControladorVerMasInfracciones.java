/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logica.infraccion.modelos.Infraccion;
import logica.licencia.modelos.Licencia;
import logica.persona.implementaciones.ServicioConductor;
import logica.persona.modelos.Conductor;

/**
 *
 * @author Adrian
 */
public class ControladorVerMasInfracciones {
    
    private Infraccion Infraccion;
    private Licencia Licencia;
    
    @FXML
    TextField TextFieldPuntos;
    
    @FXML
    TextField TextFieldGravedad;
    
    @FXML
    TextField TextFieldPagado;
    
    @FXML
    TextField TextFieldNombre;
    
    @FXML
    TextField TextFieldLugar;
    
    @FXML
    TextField TextFieldIdLicencia;
    
    @FXML
    TextField TextFieldOficial;
    
    @FXML
    TextArea TextFieldDescripcion;
    
    @FXML
    TextField TextFieldFecha;
    
    
    @FXML
    public void initialize()
    {
        System.out.println("Controlador ver mas infraccion iniciado");
    }
    
    public void SetDatos(Infraccion Infraccion, Licencia Licencia) throws Exception
    {
        this.Infraccion = Infraccion;
        this.Licencia = Licencia;
        Iniciar();
    }
    
    public void Iniciar() throws Exception
    {
        System.out.println("Iniciar llamado");
        String puntosStr = Integer.toString(Licencia.getCantPuntos());
        Conductor Conductor = ServicioConductor.ObtenerConductorPorIdLicencia(Licencia.getId());
        TextFieldPuntos.setText(puntosStr);
        ColorGravedad(Infraccion.getGravedad(), TextFieldGravedad);
        TextFieldPagado.setText(Pagado(Infraccion.isPagada()));
        TextFieldLugar.setText(Infraccion.getLugar());
        TextFieldNombre.setText(Conductor.getNombre()+" "+ Conductor.getApellidos());
        TextFieldOficial.setText(Infraccion.getNombreOficial());
        TextFieldDescripcion.setText(Infraccion.getDescripcion());
        TextFieldFecha.setText(Infraccion.getFecha().toString());
        TextFieldIdLicencia.setText(Licencia.getId().toString());
    }
    
    private String Pagado(boolean Pagado){
        if(Pagado){
            return "Si";
        }else
            return "No";
    }
    
    private void ColorGravedad(String Gravedad, TextField TextField){
        switch (Gravedad) {
            case "Grave":
                TextFieldGravedad.setText(Gravedad);
                TextField.setStyle("-fx-text-fill: #FFA500; -fx-background-color: transparent;");
                              
                break;
            case "Muy grave":
                TextFieldGravedad.setText(Gravedad);
                TextField.setStyle("-fx-text-fill: #ff0000; -fx-background-color: transparent;");
                
                break;
            case "Leve":
                TextFieldGravedad.setText(Gravedad);
                TextField.setStyle("-fx-text-fill: #FFFF00; -fx-background-color: transparent;");
                
                break;
            default:
               
        }
    }

}
