/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private TextField TextFieldPuntos;
    
    @FXML
    private TextField TextFieldGravedad;
    
    @FXML
    private TextField TextFieldPagado;
    
    @FXML
    private TextField TextFieldNombre;
    
    @FXML
    private TextField TextFieldLugar;
    
    @FXML
    private TextField TextFieldIdLicencia;
    
    @FXML
    private TextField TextFieldOficial;
    
    @FXML
    private TextArea TextFieldDescripcion;
    
    @FXML
    private TextField TextFieldFecha;
    
    @FXML private Button btnAtras;
    
    
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
        Conductor Conductor = ServicioConductor.obtenerConductorPorIdLicencia(Licencia.getId());
        TextFieldPuntos.setText(puntosStr);
        ColorGravedad(Infraccion.getGravedad(), TextFieldGravedad);
        TextFieldPagado.setText(Pagado(Infraccion.isPagada()));
        TextFieldLugar.setText(Infraccion.getLugar());
        TextFieldNombre.setText(Conductor.getNombre()+" "+ Conductor.getApellidos());
        TextFieldOficial.setText(Infraccion.getNombreOficial());
        TextFieldDescripcion.setText(Infraccion.getDescripcion());
        TextFieldFecha.setText(Infraccion.getFecha().toString());
        TextFieldIdLicencia.setText(Licencia.getId().toString());
        btnAtras.setOnAction(e ->{
            GestorEscenas.cerrar(btnAtras);
        });
    }
    
    public static String Pagado(boolean Pagado){
        if(Pagado){
            return "Si";
        }else
            return "No";
    }
    
    private void ColorGravedad(String Gravedad, TextField TextField){
        switch (Gravedad) {
            case "Grave":
                TextFieldGravedad.setText(Gravedad);
                TextField.setStyle("-fx-text-fill: #FF5722; -fx-text-weight: bold; -fx-background-color: transparent;");
                              
                break;
            case "Muy grave":
                TextFieldGravedad.setText(Gravedad);
                TextField.setStyle("-fx-text-fill: red; -fx-background-color: transparent;");
                
                break;
            case "Leve":
                TextFieldGravedad.setText(Gravedad);
                TextField.setStyle("-fx-text-fill: #FFD700; -fx-background-color: transparent;");
                
                break;
            default:
               
        }
    }
    

}
