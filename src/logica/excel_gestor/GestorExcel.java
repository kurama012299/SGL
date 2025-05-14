/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.excel_gestor;

import java.io.File;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import logica.persona.modelos.Conductor;



/**
 *
 * @author Angel Hernandez
 */
public class GestorExcel {

    public static void ExportarConductoresExcel(ObservableList<Conductor> ListaConductores, String NombreBaseArchivo, Stage ventanaPadre) throws Exception {
        try {
            // Configurar el FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar carpeta para guardar el archivo");

            // Establecer el nombre inicial del archivo
            String nombreArchivo = NombreBaseArchivo + "-"
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm")) + ".xls";
            fileChooser.setInitialFileName(nombreArchivo);

            // Mostrar diálogo para seleccionar ubicación
            File archivoDestino = fileChooser.showSaveDialog(ventanaPadre);

            if (archivoDestino == null) {
                System.out.println("Exportación cancelada por el usuario");
                return;
            }

            // Forzar el nombre del archivo
            String rutaCompleta = archivoDestino.getParent() + File.separator + nombreArchivo;
            File archivoFinal = new File(rutaCompleta);

            // Crear el libro de Excel
            WritableWorkbook libro = Workbook.createWorkbook(archivoFinal);
            WritableSheet hoja = libro.createSheet("Conductores", 0);

            // Escribir encabezados
            String[] encabezados = {"ID", "Nombre Completo", "Carnet identidad", "Correo electronico", "Dirección residencia"};
            for (int col = 0; col < encabezados.length; col++) {
                hoja.addCell(new Label(col, 0, encabezados[col]));
            }

            // Escribir datos de los conductores
            int fila = 1;
            for (Conductor conductor : ListaConductores) {
                hoja.addCell(new Label(0, fila, String.valueOf(conductor.getId())));
                hoja.addCell(new Label(1, fila, conductor.getNombre() + " " + conductor.getApellidos()));
                hoja.addCell(new Label(2, fila, conductor.getCI()));
                hoja.addCell(new Label(3, fila, conductor.getCorreo()));
                hoja.addCell(new Label(4, fila, conductor.getDireccion()));
                fila++;
            }

            for (int i = 0; i < encabezados.length; i++) {
            hoja.setColumnView(i, encabezados[i].length()*2);
        }
            // Guardar y cerrar el libro
            libro.write();
            libro.close();

            System.out.println("Exportado correctamente a: " + archivoFinal.getAbsolutePath());

        } catch (Exception e) {
            throw new Exception("Error al exportar los datos");
        }
    }
}
      
    

      
    

