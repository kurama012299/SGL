/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import gestor_interfaces.GestorEscenas;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import logica.examen_medico.implementaciones.ServiciosExamenesMedicos;

/**
 *
 * @author Kris
 */
public class ControladorMenuReportesTipo {
    
    private String reporte;
    private LocalDate fechaActual=LocalDate.now();
    @FXML private Button btnMostrarReporte;
    @FXML private Button btnCerrar;
    @FXML private ComboBox cmbTipoInfraccion;
    @FXML private ComboBox cmbFechaEscogidaAnno;
    
    public void initialize() throws Exception {
        System.out.println("Controlador Menu Reportes Tipo Iniciado"); 
        llenarComboBox();
        cmbFechaEscogidaAnno.setValue(String.valueOf(fechaActual.getYear()));
    }
    
    public void setDatos(String reporte) {
        this.reporte = reporte;
    }
    
    
    private void llenarComboBox() throws Exception
    {
        cmbTipoInfraccion.setItems(FXCollections.observableArrayList(
                "Leve","Grave","Muy grave"
        ));
        ObservableList<Integer>annos = FXCollections.observableArrayList();
        for(int anno=ServiciosExamenesMedicos.obtenerFechaMasViejaExamen().getYear();anno<=fechaActual.getYear();anno++)
        {
            annos.add(anno);
        }
        cmbFechaEscogidaAnno.setItems(annos);
        
    }
    @FXML
    public void mostrarReporte() {
        
        try {
            switch (reporte) {
                case "Infracciones por tipo":
                    break;
            }
            
            GestorEscenas.cargarConfirmacion(btnMostrarReporte.getScene().getWindow(), "Reporte generado correctamente");
            cerrar();
            
        } catch (Exception ex) {
            GestorEscenas.cargarError(btnMostrarReporte.getScene().getWindow(), ex);
        }
    }
    
    @FXML
    public void cerrar() {
        System.out.println("Cerrando ventana...");
        GestorEscenas.cerrar(btnCerrar);
    }
}
