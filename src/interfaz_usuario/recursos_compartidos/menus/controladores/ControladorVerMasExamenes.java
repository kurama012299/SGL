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
    
    private ExamenConduccion examenConduccion=null;
    private ExamenMedico examenMedico=null;
    
    @FXML private TextField txfExaminado;
    
    @FXML private TextField txfExaminador;
    
    @FXML private TextField txfId;
    
    @FXML private TextArea txfRestricciones;
    
    @FXML private TextField txfTipo;
    
    @FXML private TextField txfResultado;
    
    @FXML private TextField txfFecha;
    
    @FXML private TextField txfEntidad;
    
    @FXML private Label lblRestriccion;
    
    @FXML private Label lblFecha;
    
    @FXML private Button btnAtras;
    
    @FXML
    public void initialize()
    {
        System.out.println("Controlador ver mas examenes iniciado");
        
        visibilidadRestricciones(false);
        
        btnAtras.setOnAction(e ->{
            GestorEscenas.cerrar(btnAtras);
        });
    }
    
    public void SetDatos(ExamenConduccion examenConduccion,ExamenMedico examenMedico)
    {
        this.examenConduccion=examenConduccion;
        this.examenMedico = examenMedico;
        if(examenConduccion == null && examenMedico != null)
        {
            visibilidadRestricciones(true);
            iniciarMedico();
        }
        else if(examenConduccion != null)
        {
            lblFecha.setLayoutY(lblFecha.getLayoutY()-50);
            txfFecha.setLayoutY(txfFecha.getLayoutY()-50);
            visibilidadRestricciones(false);
            iniciarConduccion();
        }
    }
    
    private void visibilidadRestricciones(boolean esVisible)
    {
        txfRestricciones.setVisible(esVisible);
        txfRestricciones.setManaged(esVisible);
        lblRestriccion.setVisible(esVisible);
        lblRestriccion.setManaged(esVisible);
    }
    public void iniciarConduccion()
    {
        System.out.println("Iniciar llamado");
        txfId.setText(examenConduccion.getPersona().getCI());
        txfExaminado.setText(examenConduccion.getPersona().getNombre()+examenConduccion.getPersona().getApellidos());
        txfExaminador.setText(examenConduccion.getExaminador().getNombre());
        txfTipo.setText(examenConduccion.getTipo());
        if(examenConduccion.isAprobado())
        {
            txfResultado.setText("Aprobado");
        }
        else
        {
            txfResultado.setText("Reprobado");
        }
        txfEntidad.setText(examenConduccion.getEntidad().getNombre());
        txfFecha.setText(examenConduccion.getFecha().toString());
       
    }
    
    public void iniciarMedico()
    {
        System.out.println("Iniciar llamado");
        
        txfId.setText(examenMedico.getPersona().getCI());
        txfExaminado.setText(examenMedico.getPersona().getNombre()+examenMedico.getPersona().getApellidos());
        txfExaminador.setText(examenMedico.getExaminador().getNombre());
        txfTipo.setText(examenMedico.getTipo());
        if(examenMedico.isAprobado() && examenMedico.getRestricciones().size()==0)
        {
            txfResultado.setText("Aprobado");
            txfRestricciones.setText("Ninguna");
        }
        else if(examenMedico.isAprobado() && examenMedico.getRestricciones().size()!=0)
        {
            txfResultado.setText("Aprobado condicional");
            txfRestricciones.setText(GestorEscenas.convertirArrayRestricciones(examenMedico.getRestricciones()).toString());
        }
        else
        {
            txfResultado.setText("Reprobado");
            txfRestricciones.setText(GestorEscenas.convertirArrayRestricciones(examenMedico.getRestricciones()).toString());
        }
        txfEntidad.setText(examenMedico.getEntidad().getNombre());
        txfFecha.setText(examenMedico.getFecha().toString());
        
    }
    
     
}
