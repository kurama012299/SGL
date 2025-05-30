/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.pdf_gestion;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Platform;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import logica.examen.modelos.Examen;
import logica.infraccion.modelos.Infraccion;
import logica.licencia.implementaciones.ServicioLicencia;
import logica.licencia.modelos.Licencia;
import logica.persona.implementaciones.ServicioConductor;
import logica.persona.modelos.Conductor;



/**
 *
 * @author Angel Hernandez
 */
public class GestorPDF {
    
    //Licencia
    public static void generarReporteLicenciasEmitidas(ObservableList<Licencia> licencias, String tituloReporte) {
        try {
            // 1. Configuración inicial del documento
            File pdfFile = File.createTempFile("reporte_licencias_emitidas_", ".pdf");
            pdfFile.deleteOnExit();

            Document document = new Document(PageSize.A4.rotate()); // Horizontal para más columnas
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();

            // 2. Estilos de fuentes
            Map<String, Font> estilos = crearEstilosFuentes();

            // 3. Agregar encabezado
            agregarEncabezado(document, tituloReporte, "Reporte de licencias emitidas", estilos);

            // 4. Agregar tabla con datos de licencias
            agregarTablaLicencias(document, licencias, estilos);

            // 5. Agregar pie de página
            agregarPiePagina(document, estilos.get("footer"));

            document.close();

            // 6. Abrir el PDF automáticamente
            abrirDocumento(pdfFile);

        } catch (Exception e) {
            manejarErrorGeneracionReporte(e);
        }
    }

    
    private static void agregarTablaLicencias(Document document, ObservableList<Licencia> licencias, Map<String, Font> estilos)
            throws DocumentException, Exception {

        // Columnas: Número, Tipo, Fecha Emisión, Fecha Vencimiento, Restricciones, Carnet Identidad
        PdfPTable tabla = new PdfPTable(7);
        tabla.setWidthPercentage(100);
        tabla.setSplitLate(false); // Evita que las celdas se dividan en páginas

        // Configurar anchos relativos de columnas
        float[] anchosColumnas = {1.5f, 1f, 1.5f, 1.5f, 2f, 1.5f, 1.5f};
        tabla.setWidths(anchosColumnas);

        // Configuración común para todas las celdas
        tabla.setExtendLastRow(false);
        tabla.setHeaderRows(1); // La primera fila es cabecera

        // Cabecera de la tabla (con alineación vertical superior)
        agregarCeldaCabecera(tabla, "Número Licencia", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Tipo", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Fecha Emisión", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Fecha Vencimiento", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Restricciones", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Carnet Identidad", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Categoria", estilos.get("cabecera"));

        // Datos de las licencias
        boolean fondoGris = false;
        for (Licencia licencia : licencias) {
            BaseColor colorFondo = fondoGris ? BaseColor.LIGHT_GRAY : BaseColor.WHITE;

            // Celda con texto que se ajusta (wrap) y alineación superior
            agregarCeldaDatosAjustable(tabla, licencia.getId().toString(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, licencia.getTipo(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, licencia.getFechaEmision().toString(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, licencia.getFechaVencimiento().toString(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, obtenerRestriccionesFormateadas(licencia.getRestricciones()), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, ServicioConductor.ObtenerConductorPorIdLicencia(licencia.getId()).getCI(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, obtenerCategoriasFormateadas(licencia.getCategorias()), estilos.get("datos"), colorFondo);

            fondoGris = !fondoGris;
        }

        document.add(tabla);
    }

    
    private static String obtenerRestriccionesFormateadas(List<String> restricciones) {
        if (restricciones == null || restricciones.isEmpty()) {
            return "Ninguna";
        }
        return String.join(", ", restricciones);
    }

    
    private static String obtenerCategoriasFormateadas(List<String> categorias) {
        if (categorias == null || categorias.isEmpty()) {
            return "Ninguna";
        }
        return String.join(", ", categorias);
    }

    
    //Infracciones
    public static void generarReporteInfracciones(ObservableList<Infraccion> infracciones, String tituloReporte) {
        try {
            // 1. Configuración inicial del documento
            File pdfFile = File.createTempFile("reporte_infracciones_", ".pdf");
            pdfFile.deleteOnExit();

            Document document = new Document(PageSize.A4.rotate()); // Horizontal para más columnas
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();

            // 2. Estilos de fuentes
            Map<String, Font> estilos = crearEstilosFuentes();

            // 3. Agregar encabezado
            agregarEncabezado(document, tituloReporte, "Reporte detallado de infracciones", estilos);

            // 4. Agregar tabla con datos de infracciones
            agregarTablaInfracciones(document, infracciones, estilos);

            // 5. Agregar pie de página
            agregarPiePagina(document, estilos.get("footer"));

            document.close();

            // 6. Abrir el PDF automáticamente
            abrirDocumento(pdfFile);

        } catch (Exception e) {
            manejarErrorGeneracionReporte(e);
        }
    }

    
    private static void agregarTablaInfracciones(Document document, ObservableList<Infraccion> infracciones, Map<String, Font> estilos)
            throws DocumentException {

        // Columnas: ID, Fecha, Lugar, Descripción, Puntos, Pagada, Gravedad, Oficial
        PdfPTable tabla = new PdfPTable(8);
        tabla.setWidthPercentage(100);
        tabla.setSplitLate(false);

        // Configurar anchos relativos de columnas
        float[] anchosColumnas = {1f, 1.5f, 1.5f, 3f, 1f, 1f, 1.5f, 2f};
        tabla.setWidths(anchosColumnas);

        // Configuración común para todas las celdas
        tabla.setExtendLastRow(false);
        tabla.setHeaderRows(1);

        // Cabecera de la tabla
        agregarCeldaCabecera(tabla, "ID", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Fecha", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Lugar", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Descripción", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Puntos", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Pagada", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Gravedad", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Oficial", estilos.get("cabecera"));

        // Formateador de fecha
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Datos de las infracciones
        boolean fondoGris = false;
        for (Infraccion infraccion : infracciones) {
            BaseColor colorFondo = fondoGris ? BaseColor.LIGHT_GRAY : BaseColor.WHITE;

            // Convertir Date a LocalDateTime si es necesario
            Date fechaInfraccion = infraccion.getFecha();

            agregarCeldaDatosAjustable(tabla, infraccion.getId().toString(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, fechaInfraccion.toString(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, infraccion.getLugar(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, infraccion.getDescripcion(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, String.valueOf(infraccion.getPuntosDeducidos()), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, infraccion.isPagada() ? "Sí" : "No", estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, infraccion.getGravedad(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, infraccion.getNombreOficial(), estilos.get("datos"), colorFondo);

            fondoGris = !fondoGris;
        }

        document.add(tabla);
    }

    //Utiles
    private static void agregarCeldaDatosAjustable(PdfPTable tabla, String texto, Font font, BaseColor bgColor) {
        PdfPCell celda = new PdfPCell(new Phrase(texto != null ? texto : "", font));
        celda.setBackgroundColor(bgColor);
        celda.setPadding(5);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_TOP); // Alineación vertical superior
        celda.setNoWrap(false); // Permite que el texto se ajuste
        celda.setFixedHeight(0); // Altura flexible según contenido
        tabla.addCell(celda);
    }

    
    private static void agregarCeldaCabecera(PdfPTable tabla, String texto, Font font) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, font));
        celda.setBackgroundColor(BaseColor.GRAY);
        celda.setPadding(5);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE); // Centrado vertical en cabeceras
        celda.setNoWrap(false); // Permite ajuste de texto
        tabla.addCell(celda);
    }

    
    private static void agregarPiePagina(Document document, Font fontFooter) throws DocumentException {
        Paragraph footer = new Paragraph(
                "\n\nGenerado automáticamente el " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                fontFooter
        );
        footer.setAlignment(Element.ALIGN_RIGHT);
        document.add(footer);
    }

    
    private static void abrirDocumento(File pdfFile) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(pdfFile);
        }
    }

    
    private static void manejarErrorGeneracionReporte(Exception e) {
        e.printStackTrace();
        // Aquí podrías lanzar una excepción personalizada o mostrar un diálogo de error
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al generar reporte");
            alert.setHeaderText("Ocurrió un error al generar el reporte");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        });
    }

    
    private static Map<String, Font> crearEstilosFuentes() {
        Map<String, Font> estilos = new HashMap<>();
        estilos.put("titulo", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE));
        estilos.put("subtitulo", new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.DARK_GRAY));
        estilos.put("cabecera", new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE));
        estilos.put("datos", new Font(Font.FontFamily.HELVETICA, 10));
        estilos.put("footer", new Font(Font.FontFamily.HELVETICA, 8));
        return estilos;
    }

    
    private static void agregarEncabezado(Document document, String titulo, String subtitulo, Map<String, Font> estilos) throws DocumentException {
        Paragraph tituloPdf = new Paragraph(titulo, estilos.get("titulo"));
        tituloPdf.setAlignment(Element.ALIGN_CENTER);
        tituloPdf.setSpacingAfter(20f);
        document.add(tituloPdf);

        Paragraph subtituloPdf = new Paragraph(subtitulo, estilos.get("subtitulo"));
        subtituloPdf.setAlignment(Element.ALIGN_CENTER);
        subtituloPdf.setSpacingAfter(15f);
        document.add(subtituloPdf);
    }

    
    private static void agregarTablaExamenes(Document document, ObservableList<Examen> examenes, Map<String, Font> estilos)
            throws DocumentException, Exception {

        // Columnas: Codigo, Nombre Persona, Tipo, Fecha, Resultado(Aprobado-Suspenso),Entidad
        PdfPTable tabla = new PdfPTable(6);
        tabla.setWidthPercentage(100);
        tabla.setSplitLate(false); // Evita que las celdas se dividan en páginas

        // Configurar anchos relativos de columnas
        float[] anchosColumnas = {1f, 2f, 1.5f, 1.5f, 1.5f, 1.5f};
        tabla.setWidths(anchosColumnas);

        // Configuración común para todas las celdas
        tabla.setExtendLastRow(false);
        tabla.setHeaderRows(1); // La primera fila es cabecera

        // Cabecera de la tabla (con alineación vertical superior)
        agregarCeldaCabecera(tabla, "Codigo", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Nombre", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Tipo", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Fecha", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Resultado", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Entidad", estilos.get("cabecera"));

        // Datos de los examenes
        boolean fondoGris = false;
        for (Examen examen : examenes) {
            BaseColor colorFondo = fondoGris ? BaseColor.LIGHT_GRAY : BaseColor.WHITE;

            // Celda con texto que se ajusta (wrap) y alineación superior
            agregarCeldaDatosAjustable(tabla, examen.getId().toString(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, examen.getPersona().getNombre() + examen.getPersona().getApellidos(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, examen.getTipo(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, examen.getFecha().toString(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, (examen.isAprobado()) ? "Aprobado" : "Reprobado", estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, examen.getEntidad().getNombre(), estilos.get("datos"), colorFondo);

            fondoGris = !fondoGris;
        }

        document.add(tabla);
    }

    
    public static void generarReporteExamenes(ObservableList<Examen> examenes, String tituloReporte) {
        try {
            // 1. Configuración inicial del documento
            File pdfFile = File.createTempFile("reporte_examenes_", ".pdf");
            pdfFile.deleteOnExit();

            Document document = new Document(PageSize.A4.rotate()); // Horizontal para más columnas
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();

            // 2. Estilos de fuentes
            Map<String, Font> estilos = crearEstilosFuentes();

            // 3. Agregar encabezado
            agregarEncabezado(document, tituloReporte, "Reporte detallado de examenes", estilos);

            // 4. Agregar tabla con datos de infracciones
            agregarTablaExamenes(document, examenes, estilos);

            // 5. Agregar pie de página
            agregarPiePagina(document, estilos.get("footer"));

            document.close();

            // 6. Abrir el PDF automáticamente
            abrirDocumento(pdfFile);

        } catch (Exception e) {
            manejarErrorGeneracionReporte(e);
        }
    }

    
    public static void generarReporteInfraccionesPorTipo(ObservableList<Infraccion> infracciones, String tituloReporte, String tipo) {
        try {
            // 1. Configuración inicial del documento
            File pdfFile = File.createTempFile("reporte_infracciones_por_tipo", ".pdf");
            pdfFile.deleteOnExit();

            Document document = new Document(PageSize.A4.rotate()); // Horizontal para más columnas
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();

            // 2. Estilos de fuentes
            Map<String, Font> estilos = crearEstilosFuentes();

            // 3. Agregar encabezado
            agregarEncabezado(document, tituloReporte, "Reporte detallado de infracciones " + tipo + ":", estilos);

            // 4. Agregar tabla con datos de infracciones
            agregarTablaInfracciones(document, infracciones, estilos);

            // 5. Agregar pie de página
            agregarPiePagina(document, estilos.get("footer"));

            document.close();

            // 6. Abrir el PDF automáticamente
            abrirDocumento(pdfFile);

        } catch (Exception e) {
            manejarErrorGeneracionReporte(e);
        }
    }

    
    public static void generarReporteConductoresLicenciasVencidas(ObservableList<Conductor> conductores, String tituloReporte) {
        try {
            // 1. Configuración inicial del documento
            File pdfFile = File.createTempFile("reporte_conductores_licencias_vencidas_", ".pdf");
            pdfFile.deleteOnExit();

            Document document = new Document(PageSize.A4.rotate()); // Horizontal para más columnas
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();

            // 2. Estilos de fuentes
            Map<String, Font> estilos = crearEstilosFuentes();

            // 3. Agregar encabezado
            agregarEncabezado(document, tituloReporte, "Reporte de conductores con licencias vencidas", estilos);

            // 4. Agregar tabla con datos de licencias
            agregarTablaConductores(document, conductores, estilos);

            // 5. Agregar pie de página
            agregarPiePagina(document, estilos.get("footer"));

            document.close();

            // 6. Abrir el PDF automáticamente
            abrirDocumento(pdfFile);

        } catch (Exception e) {
            manejarErrorGeneracionReporte(e);
        }
    }

    
    private static void agregarTablaConductores(Document document, ObservableList<Conductor> conductores, Map<String, Font> estilos)
            throws DocumentException, Exception {

        // Columnas: Nombre, CI, Tipo, Fecha Vencimiento, Estado
        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(100);
        tabla.setSplitLate(false); // Evita que las celdas se dividan en páginas

        // Configurar anchos relativos de columnas
        float[] anchosColumnas = {2f, 2f, 1.5f, 1.5f, 1.5f};
        tabla.setWidths(anchosColumnas);

        // Configuración común para todas las celdas
        tabla.setExtendLastRow(false);
        tabla.setHeaderRows(1);

        // Cabecera de la tabla (con alineación vertical superior)
        agregarCeldaCabecera(tabla, "Nombre Conductor", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Carnet Identidad", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Tipo Licencia", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Fecha Vencimiento", estilos.get("cabecera"));
        agregarCeldaCabecera(tabla, "Estado", estilos.get("cabecera"));

        // Datos de los conductores
        boolean fondoGris = false;
        for (Conductor conductor : conductores) {
            Licencia licencia = ServicioLicencia.ObtenerLicenciaPorId(conductor.getIdLicencia());
            BaseColor colorFondo = fondoGris ? BaseColor.LIGHT_GRAY : BaseColor.WHITE;

            // Celda con texto que se ajusta (wrap) y alineación superior
            agregarCeldaDatosAjustable(tabla, conductor.getNombre() + conductor.getApellidos(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, conductor.getCI(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, licencia.getTipo(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, licencia.getFechaVencimiento().toString(), estilos.get("datos"), colorFondo);
            agregarCeldaDatosAjustable(tabla, licencia.getEstado(), estilos.get("datos"), colorFondo);

            fondoGris = !fondoGris;
        }

        document.add(tabla);
    }
}
