/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.pdf_gestion;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;


/**
 *
 * @author Angel Hernandez
 */
public class GestorPDF {
    
    public static void GenerarMostrarPDF(String titulo, String subtitulo) {
        try {
            // Crear archivo temporal
            File pdfFile = File.createTempFile("reporte_"+titulo, ".pdf");
            pdfFile.deleteOnExit();
            
            // Configurar documento PDF
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
            
            // Fuentes personalizadas
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
            Font fontSubtitulo = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.DARK_GRAY);
            Font fontCabecera = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);
            Font fontDatos = new Font(Font.FontFamily.HELVETICA, 10);
            
            // 1. Añadir título
            Paragraph tituloPdf = new Paragraph(titulo, fontTitulo);
            tituloPdf.setAlignment(Element.ALIGN_CENTER);
            tituloPdf.setSpacingAfter(20f);
            document.add(tituloPdf);
            
            // 2. Añadir subtítulo
            Paragraph subtituloPdf = new Paragraph(subtitulo, fontSubtitulo);
            subtituloPdf.setAlignment(Element.ALIGN_CENTER);
            subtituloPdf.setSpacingAfter(15f);
            document.add(subtituloPdf);
            
            // 3. Crear tabla con datos de ejemplo
            PdfPTable tabla = new PdfPTable(3); // 3 columnas
            tabla.setWidthPercentage(100); // Ancho completo
            
            // Cabecera de la tabla
            agregarCelda(tabla, "Producto", fontCabecera, BaseColor.GRAY);
            agregarCelda(tabla, "Cantidad", fontCabecera, BaseColor.GRAY);
            agregarCelda(tabla, "Precio", fontCabecera, BaseColor.GRAY);
            
            // Datos de ejemplo
            agregarCelda(tabla, "Laptop HP", fontDatos, BaseColor.LIGHT_GRAY);
            agregarCelda(tabla, "15", fontDatos, BaseColor.LIGHT_GRAY);
            agregarCelda(tabla, "$1200.00", fontDatos, BaseColor.LIGHT_GRAY);
            
            agregarCelda(tabla, "Mouse Inalámbrico", fontDatos, BaseColor.WHITE);
            agregarCelda(tabla, "42", fontDatos, BaseColor.WHITE);
            agregarCelda(tabla, "$25.99", fontDatos, BaseColor.WHITE);
            
            agregarCelda(tabla, "Teclado Mecánico", fontDatos, BaseColor.LIGHT_GRAY);
            agregarCelda(tabla, "8", fontDatos, BaseColor.LIGHT_GRAY);
            agregarCelda(tabla, "$89.50", fontDatos, BaseColor.LIGHT_GRAY);
            
            document.add(tabla);
            
            // 4. Añadir pie de página
            Paragraph footer = new Paragraph("\n\nGenerado automáticamente el " + new java.util.Date(), 
                                          new Font(Font.FontFamily.HELVETICA, 8));
            document.add(footer);
            
            document.close();
            
            // Abrir el PDF
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void agregarCelda(PdfPTable tabla, String texto, Font font, BaseColor bgColor) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, font));
        celda.setBackgroundColor(bgColor);
        celda.setPadding(5);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);
    }
    
    
}
