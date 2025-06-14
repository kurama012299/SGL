/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;


import gestor_interfaces.GestorEscenas;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logica.licencia.modelos.Licencia;
import logica.persona.implementaciones.ServicioConductor;
import logica.persona.modelos.Conductor;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorVerMasConductor{
    
    private Conductor Conductor;
    private Licencia Licencia;
    
    @FXML
    TextField TextFieldCI;
    
    @FXML
    TextField TextFieldTelefono;
    
    @FXML
    TextField TextFieldCorreo;
    
    @FXML
    TextField TextFieldNombre;
    
    @FXML
    TextField TextFieldDireccion;
    
    @FXML
    TextField TextFieldIdLicencia;
    
    @FXML
    TextField TextFieldEstadoLicencia;
    
    @FXML
    TextField TextFieldFechaEmision;
    
    @FXML
    TextField TextFieldFechaVencimiento;
    
    @FXML
    Label LabelTipoLicencia;
    
    @FXML
    Pane CategoriaCarro;
    
    @FXML
    Pane CategoriaMoto;
    
    @FXML
    Pane CategoriaOmnibus;
    
    @FXML
    Pane CategoriaCamion;
    
    @FXML Button btnAtras;
    
    @FXML Button btnEliminar;


    @FXML
    public void initialize()
    {
        System.out.println("Controlador ver mas conductor iniciado");
    }
    
    public void SetDatos(Conductor Conductor,Licencia Licencia)
    {
        this.Conductor=Conductor;
        this.Licencia = Licencia;
        Iniciar();
    }
    
    public void Iniciar()
    {
        System.out.println("Iniciar llamado");
        TextFieldCI.setText(Conductor.getCI());
        TextFieldTelefono.setText(Conductor.getTelefono());
        TextFieldCorreo.setText(Conductor.getCorreo());
        TextFieldDireccion.setText(Conductor.getDireccion());
        TextFieldNombre.setText(Conductor.getNombre()+" "+ Conductor.getApellidos());
        TextFieldEstadoLicencia.setText(Licencia.getEstado());
        TextFieldFechaEmision.setText(Licencia.getFechaEmision().toString());
        TextFieldFechaVencimiento.setText(Licencia.getFechaVencimiento().toString());
        LabelTipoLicencia.setText(Licencia.getTipo());
        TextFieldIdLicencia.setText(Licencia.getId().toString());
        CargarCategorias();
        btnAtras.setOnAction(e ->{
            GestorEscenas.cerrar(btnAtras);
        });
    }
    
    private void CargarCategorias()
    {
        for(String categoria:Licencia.getCategorias())
        {
            switch(categoria)
            {
                case "Moto":
                    CategoriaMoto.setStyle("-fx-background-color: green;");
                    break;
                case "Automovil":
                    CategoriaCarro.setStyle("-fx-background-color: green;");
                    break;
                case "Autobus":
                    CategoriaOmnibus.setStyle("-fx-background-color: green;");
                    break;
                case "Camion":
                    CategoriaCamion.setStyle("-fx-background-color: green;");
                    break;
            }
        }
    }

        
    @FXML public void eliminar()
    {
        try {
            ServicioConductor.eliminarConductor(Conductor.getId());
            GestorEscenas.cerrar(btnAtras);
        } catch (Exception ex) {
            GestorEscenas.cargarError(btnEliminar.getScene().getWindow(), ex);
        }
    }
    

}
