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
import javafx.stage.Stage;
import logica.examen_conduccion.modelos.ExamenConduccion;
import logica.examen_medico.modelos.ExamenMedico;

/**
 *
 * @author Kris
 */
public class ControladorVerMasExamenes {
    
    private ExamenConduccion ExamenConduccion=null;
    private ExamenMedico ExamenMedico=null;
    
    @FXML
    private TextField TextFieldExaminado;
    
    @FXML
    private TextField TextFieldExaminador;
    
    @FXML
    private TextField TextFieldId;
    
    @FXML
    private TextArea TextFieldRestricciones;
    
    @FXML
    private TextField TextFieldTipo;
    
    @FXML
    private TextField TextFieldResultado;
    
    @FXML
    private TextField TextFieldFecha;
    
    @FXML
    private TextField TextFieldEntidad;
    
    @FXML
    private Label LabelRestriccion;
    
    @FXML
    private Label LabelFecha;
    
    @FXML private Button btnAtras;
    
    @FXML
    public void initialize()
    {
        System.out.println("Controlador ver mas examenes iniciado");
        TextFieldRestricciones.setVisible(false);
        TextFieldRestricciones.setManaged(false);
        LabelRestriccion.setVisible(false);
        LabelRestriccion.setManaged(false);
    }
    
    public void SetDatos(ExamenConduccion examenConduccion,ExamenMedico examenMedico)
    {
        this.ExamenConduccion=examenConduccion;
        this.ExamenMedico = examenMedico;
        if(examenConduccion == null && examenMedico != null)
        {
            TextFieldRestricciones.setVisible(true);
            TextFieldRestricciones.setManaged(true);
            LabelRestriccion.setVisible(true);
            LabelRestriccion.setManaged(true);
            IniciarMedico();
        }
        else if(examenConduccion != null)
        {
            LabelFecha.setLayoutY(LabelFecha.getLayoutY()-50);
            TextFieldFecha.setLayoutY(TextFieldFecha.getLayoutY()-50);
            TextFieldRestricciones.setVisible(false);
            TextFieldRestricciones.setManaged(false);
            LabelRestriccion.setVisible(false);
            LabelRestriccion.setManaged(false);
            IniciarConduccion();
        }
    }
    
    
    public void IniciarConduccion()
    {
        System.out.println("Iniciar llamado");
        TextFieldId.setText(ExamenConduccion.getPersona().getCI());
        TextFieldExaminado.setText(ExamenConduccion.getPersona().getNombre()+ExamenConduccion.getPersona().getApellidos());
        TextFieldExaminador.setText(ExamenConduccion.getExaminador().getNombre());
        TextFieldTipo.setText(ExamenConduccion.getTipo());
        if(ExamenConduccion.isAprobado())
        {
            TextFieldResultado.setText("Aprobado");
        }
        else
        {
            TextFieldResultado.setText("Reprobado");
        }
        TextFieldEntidad.setText(ExamenConduccion.getEntidad().getNombre());
        TextFieldFecha.setText(ExamenConduccion.getFecha().toString());
       
    }
    
    public void IniciarMedico()
    {
        System.out.println("Iniciar llamado");
        
        TextFieldId.setText(ExamenMedico.getPersona().getCI());
        TextFieldExaminado.setText(ExamenMedico.getPersona().getNombre()+ExamenMedico.getPersona().getApellidos());
        TextFieldExaminador.setText(ExamenMedico.getExaminador().getNombre());
        TextFieldTipo.setText(ExamenMedico.getTipo());
        if(ExamenMedico.isAprobado() && ExamenMedico.getRestricciones().size()==0)
        {
            TextFieldResultado.setText("Aprobado");
            TextFieldRestricciones.setText("Ninguna");
        }
        else if(ExamenMedico.isAprobado() && ExamenMedico.getRestricciones().size()!=0)
        {
            TextFieldResultado.setText("Aprobado condicional");
            TextFieldRestricciones.setText(GestorEscenas.mostrarRestricciones(ExamenMedico.getRestricciones()).toString());
        }
        else
        {
            TextFieldResultado.setText("Reprobado");
            TextFieldRestricciones.setText("Restricciones que impiden "+"\n"+"conducir "+"\n"+"Pendiente");
        }
        TextFieldEntidad.setText(ExamenMedico.getEntidad().getNombre());
        TextFieldFecha.setText(ExamenMedico.getFecha().toString());
        
    }
    
     
    @FXML private void cerrar() {
        Stage ventana = (Stage) btnAtras.getScene().getWindow();
        ventana.close();
    }
}
