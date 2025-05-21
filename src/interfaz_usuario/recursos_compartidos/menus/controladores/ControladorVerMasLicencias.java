/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
    private TextField TextFieldCI;
    
    @FXML
    private Label LabelTipo;
    
    @FXML
    private TextField TextFieldCantidadInfracciones;
    
    @FXML
    private TextField TextFieldNombre;
    
    @FXML
    private TextField TextFieldPuntos;
    
    @FXML
    private TextField TextFieldId;
    
    @FXML
    private TextField TextFieldEstado;
    
    @FXML
    private TextField TextFieldEmision;
    
    @FXML
    private TextField TextFieldVencimiento;
    
    @FXML
    private TextArea TextFieldRestricciones;
    
    @FXML
    private TextField TextFieldRenovada;
    
    @FXML private Button btnAtras;
    
 
    
    @FXML
    Pane paneCarro;
    
    @FXML
    Pane paneMoto;
    
    @FXML
    Pane paneOmnibus;
    
    @FXML
    Pane paneCamion;
    
    
    
    @FXML
    private ImageView imgCarro;
    
    @FXML
    private ImageView imgMoto;
    
    @FXML
    private ImageView imgOmnibus;
    
    @FXML
    private ImageView imgCamion;
    
    


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
        
        btnAtras.setOnAction(e ->{
            GestorEscenas.cerrar(btnAtras);
        });
        TextFieldCI.setText(Conductor.getCI());
        String CantInfraccionesStr = Integer.toString(ServicioInfraccion.ObtenerCantidadInfraccionesPorId(Licencia.getId()));
        TextFieldCantidadInfracciones.setText(CantInfraccionesStr);
        TextFieldRenovada.setText(ControladorVerMasInfracciones.Pagado(Licencia.EstaRenovada()));
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
        cargarCategorias();
    }
    
    private void cargarCategorias()
    {
        for(String categoria:Licencia.getCategorias())
        {
            switch(categoria)
            {
                case "Moto":
                    paneMoto.setStyle("-fx-background-color: green;");
                    break;
                case "Automovil":
                    paneCarro.setStyle("-fx-background-color: green;");
                    break;
                case "Autobus":
                    paneOmnibus.setStyle("-fx-background-color: green;");
                    break;
                case "Camion":
                    paneCamion.setStyle("-fx-background-color: green;");
                    break;
            }
        }
    }
    
    
}
