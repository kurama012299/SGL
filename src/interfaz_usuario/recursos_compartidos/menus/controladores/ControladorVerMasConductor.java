/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import logica.licencia.modelos.Licencia;
import logica.persona.modelos.Conductor;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorVerMasConductor {
    
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
    ImageView CategoriaCarro;
    
    @FXML
    ImageView CategoriaMoto;
    
    @FXML
    ImageView CategoriaOmnibus;
    
    @FXML
    ImageView CategoriaCamion;


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
        TextFieldEstadoLicencia.setText(Licencia.GetEstado());
        TextFieldFechaEmision.setText(Licencia.GetFechaEmision().toString());
        TextFieldFechaVencimiento.setText(Licencia.GetFechaVencimiento().toString());
        LabelTipoLicencia.setText(Licencia.GetTipo());
        TextFieldIdLicencia.setText(Licencia.GetId().toString());
    }
}
