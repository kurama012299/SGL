/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import com.jfoenix.controls.JFXButton;
import gestor_interfaces.GestorEscenas;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import logica.examen.implementaciones.ServicioExamenesGenerales;
import logica.infraccion.implementaciones.ServicioInfraccion;
import logica.licencia.implementaciones.ServicioLicencia;
import logica.pdf_gestion.GestorPDF;
import logica.persona.implementaciones.ServicioConductor;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorMenuReportesPeriodo {

    private String reporte;

    @FXML
    private JFXButton btnMostrarReporte;
    @FXML
    private DatePicker dtFechaInicio;
    @FXML
    private DatePicker dtFechaFin;
    @FXML
    private JFXButton btnCerrar;

    public void initialize() {
        System.out.println("Controlador Menu Reportes Periodo Iniciado");

        dtFechaInicio.setValue(LocalDate.now().minusYears(1));
        dtFechaFin.setValue(LocalDate.now());
    }

    public void setDatos(String reporte) {
        this.reporte = reporte;
    }

    @FXML
    public void mostrarReporte() {

        try {
            switch (reporte) {
                case "Licencias emitidas":
                    GestorPDF.GenerarReporteLicenciasEmitidas(
                            ServicioLicencia.obtenerLicenciasPorPeriodo(
                                    dtFechaInicio.getValue(),
                                    dtFechaFin.getValue()
                            ),
                            "Reporte de licencias emitidas desde "
                            + dtFechaInicio.getValue() + " hasta " + dtFechaFin.getValue()
                    );
                    break;
                case "Infracciones emitidas":
                    GestorPDF.GenerarReporteInfracciones(
                            ServicioInfraccion.obtenerInfraccionesPorPeriodo(
                                    dtFechaInicio.getValue(),
                                    dtFechaFin.getValue()
                            ),
                            "Reporte de infracciones emitidas desde "
                            + dtFechaInicio.getValue() + " hasta " + dtFechaFin.getValue()
                    );
                    break;
                case "Examenes emitidos":
                    GestorPDF.GenerarReporteExamenes(
                            ServicioExamenesGenerales.obtenerExamenesPorPeriodo(
                                    dtFechaInicio.getValue(),
                                    dtFechaFin.getValue()
                            ),
                            "Reporte de examenes emitidos desde "
                            + dtFechaInicio.getValue() + " hasta " + dtFechaFin.getValue()
                    );
                    break;
                case "Licencias vencidas":
                    GestorPDF.GenerarReporteConductoresLicenciasVencidas(
                            ServicioConductor.obtenerConductoresConLicenciaVencida(
                                    dtFechaInicio.getValue(),
                                    dtFechaFin.getValue()),
                            "Reporte de conductores con licencias vencidas desde "
                            + dtFechaInicio.getValue() + " hasta " + dtFechaFin.getValue()
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
