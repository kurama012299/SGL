/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.trabajador_autoescuela.controladores;

import gestor_interfaces.GestorEscenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logica.examen_conduccion.modelos.ExamenConduccion;

/**
 *
 * @author Kris
 */
public class ControladorVerMasExamenesTeoricosTrabajador {
    
    private ExamenConduccion ExamenTeorico;
    
    @FXML
    private TextField TextFieldExaminado;
    
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
        System.out.println("Controlador ver mas examenes teoricos iniciado");
    }
    
    @FXML
    public void setDatos(ExamenConduccion ExamenTeorico)
    {
        this.ExamenTeorico=ExamenTeorico;
        Iniciar();
    }
    
    @FXML
    private void Iniciar()
    {
        btnAtras.setOnAction(e -> {
            GestorEscenas.cerrar(btnAtras);
        });
        TextFieldExaminado.setText(ExamenTeorico.getPersona().getNombre()+ExamenTeorico.getPersona().getApellidos());
        TextFieldId.setText(ExamenTeorico.getPersona().getCI());
        TextFieldFecha.setText(ExamenTeorico.getFecha().toString());
        TextFieldEntidad.setText(ExamenTeorico.getEntidad().getNombre());
        if(ExamenTeorico.isAprobado())
        {
            TextFieldResultado.setText("Aprobado");
        }
        else
        {
            TextFieldResultado.setText("Reprobado");
        }
    }
    
}
