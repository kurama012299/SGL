/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.autoescuela.controladores;

import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logica.examen_conduccion.modelos.ExamenConduccion;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorVerMasExamenesPracticos {
    
    private ExamenConduccion ExamenPractico;
    
    @FXML
    private TextField TextFieldExaminado;
    
    @FXML
    private TextField TextFieldExaminador;
    
    @FXML
    private TextField TextFieldId;
    
    @FXML
    private TextField TextFieldFecha;
    
    @FXML
    private TextField TextFieldEntidad;
    
    @FXML
    private TextField TextFieldResultado;
    
    @FXML private Button btnAtras;
    
    @FXML
    public void initialize()
    {
        System.out.println("Controlador ver mas examenes practicos iniciado");
    }
    
    @FXML
    public void setDatos(ExamenConduccion ExamenPractico)
    {
        this.ExamenPractico=ExamenPractico;
        Iniciar();
    }
    
    @FXML
    private void Iniciar()
    {
        btnAtras.setOnAction(e -> {
            GestorEscenas.cerrar(btnAtras);
        });
        TextFieldExaminado.setText(ExamenPractico.getPersona().getNombre()+ExamenPractico.getPersona().getApellidos());
        TextFieldExaminador.setText(ExamenPractico.getExaminador().getNombre());
        TextFieldId.setText(ExamenPractico.getPersona().getCI());
        TextFieldFecha.setText(ExamenPractico.getFecha().toString());
        TextFieldEntidad.setText(ExamenPractico.getEntidad().getNombre());
        if(ExamenPractico.isAprobado())
        {
            TextFieldResultado.setText("Aprobado");
        }
        else
        {
            TextFieldResultado.setText("Reprobado");
        }
    }
    
    
}
