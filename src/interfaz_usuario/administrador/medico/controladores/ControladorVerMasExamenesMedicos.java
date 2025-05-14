/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.medico.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logica.examen_medico.modelos.ExamenMedico;

/**
 *
 * @author Kris
 */
public class ControladorVerMasExamenesMedicos {
    
    private ExamenMedico ExamenMedico;
    
    @FXML
    private TextField TextFieldExaminado;
    
    @FXML
    private TextField TextFieldExaminador;
    
    @FXML
    private TextField TextFieldId;
    
    @FXML
    private TextField TextFieldResultado;
    
    @FXML
    private TextField TextFieldClinica;
    
    @FXML
    private TextField TextFieldFecha;
    
    @FXML
    private TextArea TextFieldRestricciones;
    
    @FXML
    public void initialize()
    {
        System.out.println("Controlador ver mas examenes medicos iniciado");
    }
    
    @FXML
    public void setDatos(ExamenMedico ExamenMedico)
    {
        this.ExamenMedico=ExamenMedico;
        Iniciar();
    }
    
    @FXML
    public void Iniciar()
    {
        TextFieldExaminado.setText(ExamenMedico.getPersona().getNombre()+ExamenMedico.getPersona().getApellidos());
        TextFieldExaminador.setText(ExamenMedico.getExaminador().getNombre());
        TextFieldId.setText(ExamenMedico.getPersona().getCI());
        if(ExamenMedico.isAprobado() && ExamenMedico.getRestricciones().size()==0)
        {
            TextFieldResultado.setText("Aprobado");
            TextFieldRestricciones.setText("Ninguna");
        }
        else if(ExamenMedico.isAprobado() && ExamenMedico.getRestricciones().size()!=0)
        {
            TextFieldResultado.setText("Aprobado condicional");
            TextFieldRestricciones.setText(ExamenMedico.getRestricciones().toString());
        }
        else
        {
            TextFieldResultado.setText("Reprobado");
            TextFieldRestricciones.setText("Restricciones que impiden "+"\n"+"conducir "+"\n"+"Pendiente");
        }
        TextFieldClinica.setText(ExamenMedico.getEntidad().getNombre());
        TextFieldFecha.setText(ExamenMedico.getFecha().toString());
    }
}
