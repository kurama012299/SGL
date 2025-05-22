/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.licencia.implementaciones;

import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.licencia.consultas.ConsultasLicencia;
import logica.licencia.modelos.Licencia;


/**
 *
 * @author Adrian
 */
public class ServicioLicencia {
 
    public static ObservableList<Licencia> ObtenerLicencias() throws Exception {
        return ConsultasLicencia.ObtenerLicenciasConsulta();
    }

    public static ObservableList<Licencia> ObtenerLicenciasAnual() throws Exception {
        ObservableList<Licencia> licencias = ConsultasLicencia.ObtenerLicenciasConsulta();

        // Filtrar por año actual
        int añoActual = Year.now().getValue();

        // Filtrar y ordenar
        List<Licencia> licenciasFiltradas = licencias.stream()
                .filter(licencia -> licencia.getFechaEmision() != null)
                .filter(licencia -> licencia.getFechaEmision().getYear() == añoActual)
                .sorted(Comparator.comparing(Licencia::getFechaEmision).reversed()) // Orden descendente (más reciente primero)
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(licenciasFiltradas);
    }

    public static Licencia ObtenerLicenciaPorId(long Id) throws Exception {
        return ConsultasLicencia.ObtenerLicenciaPorIdConsulta(Id);
    }

    public static int actualizarPuntosLicencia(long idLicencia, int puntosASumar) throws Exception{
        return ConsultasLicencia.actualizarPuntosLicenciaConsulta(idLicencia, puntosASumar);
    }
}
