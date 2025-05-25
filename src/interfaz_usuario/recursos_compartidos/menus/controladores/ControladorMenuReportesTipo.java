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
import logica.infraccion.implementaciones.ServicioInfraccion;
import logica.pdf_gestion.GestorPDF;


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
        
        String tipo = (String) cmbTipoInfraccion.getValue();
        int annio = 0;
        try{
            Object valor = cmbFechaEscogidaAnno.getValue();
            annio = Integer.parseInt(valor.toString());
        }catch(NumberFormatException | NullPointerException e){
            e.printStackTrace();
        }
        
       
        try {
            switch (reporte) {
                case "Infracciones por tipo":
                    GestorPDF.GenerarReporteInfraccionesPorTipo(
                            ServicioInfraccion.obtenerInfraccionesPorAnioYTipo(annio, tipo),
                            "Reporte de infracciones emitidas en el  "
                            + annio,
                            tipo
                    );
                                 
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
