/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import logica.infraccion.implementaciones.ServicioInfraccion;
import logica.licencia.modelos.Licencia;
import logica.persona.modelos.Conductor;

/**
 *
 * @author Adrian
 */
public class ControladorVerMasLicencias {
    
     private Conductor Conductor;
    private Licencia Licencia;
    
    @FXML
    TextField TextFieldCI;
    
    @FXML
    Label LabelTipo;
    
    @FXML
    TextField TextFieldCantidadInfracciones;
    
    @FXML
    TextField TextFieldNombre;
    
    @FXML
    TextField TextFieldPuntos;
    
    @FXML
    TextField TextFieldId;
    
    @FXML
    TextField TextFieldEstado;
    
    @FXML
    TextField TextFieldEmision;
    
    @FXML
    TextField TextFieldVencimiento;
    
    @FXML
    TextArea TextFieldRestricciones;
    
    @FXML
    TextField TextFieldRenovada;
    
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
        System.out.println("Controlador ver mas Licencias iniciado");
    }
    
    public void SetDatos(Conductor Conductor,Licencia Licencia) throws Exception
    {
        this.Conductor=Conductor;
        this.Licencia = Licencia;
        Iniciar();
    }
    
    public void Iniciar() throws Exception
    {
        System.out.println("Iniciar llamado");
        TextFieldCI.setText(Conductor.getCI());
        String CantInfraccionesStr = Integer.toString(ServicioInfraccion.ObtenerCantidadInfraccionesPorId(Licencia.getId()));
        TextFieldCantidadInfracciones.setText(CantInfraccionesStr);
        TextFieldRenovada.setText(ControladorVerMasInfracciones.Pagado(Licencia.isRenovada()));
        String CantPuntosStr = Integer.toString(Licencia.getCantPuntos());
        TextFieldPuntos.setText(CantPuntosStr);
        TextFieldNombre.setText(Conductor.getNombre()+" "+ Conductor.getApellidos());
        TextFieldEstado.setText(Licencia.getEstado());
        TextFieldEmision.setText(Licencia.getFechaEmision().toString());
        TextFieldVencimiento.setText(Licencia.getFechaVencimiento().toString());
        LabelTipo.setText(Licencia.getTipo());
        TextFieldId.setText(Licencia.getId().toString());
        if(Licencia.getRestricciones().isEmpty())
            TextFieldRestricciones.setText("Ninguna");
        else
            TextFieldRestricciones.setText(Licencia.getRestricciones().toString());
    }
    
}
