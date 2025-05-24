/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import gestor_interfaces.GestorEscenas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Kris
 */
public class ControladorMenuReportesTipo {
    
    private String reporte;
    @FXML private Button btnMostrarReporte;
    @FXML private Button btnCerrar;
    @FXML private ComboBox cmbTipoInfraccion;
    @FXML private ComboBox cmbFechaEscogidaAnno;
    
    public void initialize() {
        System.out.println("Controlador Menu Reportes Tipo Iniciado"); 
        llenarComboBox();
        cmbFechaEscogidaAnno.setValue(2025);
    }
    
    public void setDatos(String reporte) {
        this.reporte = reporte;
    }
    
    
    private void llenarComboBox()
    {
        cmbTipoInfraccion.setItems(FXCollections.observableArrayList(
                "Leve","Grave","Muy grave"
        ));
        ObservableList<Integer>annos = FXCollections.observableArrayList();
        for(int anno=2000;anno<=2025;anno++)
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
