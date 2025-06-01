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
import jxl.Cell;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import logica.entidad.modelos.EntidadRelacionada;
import logica.examen_conduccion.modelos.ExamenConduccion;
import logica.examen_medico.implementaciones.ServiciosExamenesMedicos;
import logica.examen_medico.modelos.ExamenMedico;
import logica.infraccion.modelos.Infraccion;
import logica.licencia.modelos.Licencia;
import logica.persona.implementaciones.ServicioConductor;
import logica.persona.modelos.Conductor;




/**
 *
 * @author Angel Hernandez
 */
public class GestorExcel {

    public static void exportarConductoresExcel(ObservableList<Conductor> listaConductores, String NombreBaseArchivo, Stage ventanaPadre) throws Exception {
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
            for (Conductor conductor : listaConductores) {
                hoja.addCell(new Label(0, fila, String.valueOf(conductor.getId())));
                hoja.addCell(new Label(1, fila, conductor.getNombre() + " " + conductor.getApellidos()));
                hoja.addCell(new Label(2, fila, conductor.getCI()));
                hoja.addCell(new Label(3, fila, conductor.getCorreo()));
                hoja.addCell(new Label(4, fila, conductor.getDireccion()));
                fila++;
            }

            for (int i = 0; i < encabezados.length; i++) {
            autoSizeColumn(hoja, i);
        }
            // Guardar y cerrar el libro
            libro.write();
            libro.close();

            System.out.println("Exportado correctamente a: " + archivoFinal.getAbsolutePath());

        } catch (Exception e) {
            throw new Exception("Error al exportar los datos");
        }
    }
    
    
    public static void exportarEntidadesTipoExcel(ObservableList<EntidadRelacionada> listaEntidades, String nombreBaseArchivo, Stage ventanaPadre) throws Exception {
        try {
            // Configurar el FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar carpeta para guardar el archivo");

            // Establecer el nombre inicial del archivo
            String nombreArchivo = nombreBaseArchivo + "-"
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
            WritableSheet hoja= null;
            if(listaEntidades.get(0).getTipoEntidad().equalsIgnoreCase("Clínica"))
                 hoja = libro.createSheet("Clínica", 0);
            else
                hoja = libro.createSheet("Autoescuela", 0);
                
           

            // Escribir encabezados
            String[] encabezados = {"Director", "Nombre", "Dirección", "Telefono", "Correo"};
            for (int col = 0; col < encabezados.length; col++) {
                hoja.addCell(new Label(col, 0, encabezados[col]));
            }

            // Escribir datos de las entidades
            int fila = 1;
            for (EntidadRelacionada entidades : listaEntidades) {
                hoja.addCell(new Label(0, fila, entidades.getNombreDirector()));
                hoja.addCell(new Label(1, fila, entidades.getNombre()));
                hoja.addCell(new Label(2, fila, entidades.getDireccion()));
                hoja.addCell(new Label(3, fila, entidades.getTelefono().toString()));
                hoja.addCell(new Label(4, fila, entidades.getCorreo()));
                fila++;
            }

            for (int i = 0; i < encabezados.length; i++) {
                autoSizeColumn(hoja, i);
        }
            // Guardar y cerrar el libro
            libro.write();
            libro.close();

            System.out.println("Exportado correctamente a: " + archivoFinal.getAbsolutePath());

        } catch (Exception e) {
            throw new Exception("Error al exportar los datos");
        }
    }
    
    
    public static void exportarEntidadesGeneralesExcel(ObservableList<EntidadRelacionada> listaEntidades, String nombreBaseArchivo, Stage ventanaPadre) throws Exception {
        try {
            // Configurar el FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar carpeta para guardar el archivo");

            // Establecer el nombre inicial del archivo
            String nombreArchivo = nombreBaseArchivo + "-"
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
            WritableSheet hoja= libro.createSheet("Entidades", 0);

            // Escribir encabezados
            String[] encabezados = {"Director", "Nombre","Tipo", "Dirección", "Telefono", "Correo"};
            for (int col = 0; col < encabezados.length; col++) {
                hoja.addCell(new Label(col, 0, encabezados[col]));
            }

            // Escribir datos de las entidades
            int fila = 1;
            for (EntidadRelacionada entidades : listaEntidades) {
                hoja.addCell(new Label(0, fila, entidades.getNombreDirector()));
                hoja.addCell(new Label(1, fila, entidades.getNombre()));
                hoja.addCell(new Label(2, fila, entidades.getTipoEntidad()));
                hoja.addCell(new Label(3, fila, entidades.getDireccion()));
                hoja.addCell(new Label(4, fila, entidades.getTelefono().toString()));
                hoja.addCell(new Label(5, fila, entidades.getCorreo()));
                fila++;
            }

            for (int i = 0; i < encabezados.length; i++) {
                autoSizeColumn(hoja, i);
        }
            // Guardar y cerrar el libro
            libro.write();
            libro.close();

            System.out.println("Exportado correctamente a: " + archivoFinal.getAbsolutePath());

        } catch (Exception e) {
            throw new Exception("Error al exportar los datos");
        }
    }
    
    
    public static void exportarInfraccionesExcel(ObservableList<Infraccion> listaInfracciones, String nombreBaseArchivo, Stage ventanaPadre) throws Exception {
        try {
            // Configurar el FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar carpeta para guardar el archivo");

            // Establecer el nombre inicial del archivo
            String nombreArchivo = nombreBaseArchivo + "-"
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
            WritableSheet hoja= libro.createSheet("Infracciones", 0);

            // Escribir encabezados
            String[] encabezados = {"Nombre", "Tipo","Fecha", "Lugar", "Licencia", "Puntos"};
            for (int col = 0; col < encabezados.length; col++) {
                hoja.addCell(new Label(col, 0, encabezados[col]));
            }

            // Escribir datos de las infracciones
            int fila = 1;
            for (Infraccion infracciones : listaInfracciones) {
                hoja.addCell(new Label(0, fila, (ServicioConductor.ObtenerConductorPorId(infracciones.getIdLicencia())).getNombreApellidos()));
                hoja.addCell(new Label(1, fila, infracciones.getGravedad()));
                hoja.addCell(new Label(2, fila, infracciones.getFecha().toString()));
                hoja.addCell(new Label(3, fila, infracciones.getLugar()));
                hoja.addCell(new Label(4, fila, String.valueOf(infracciones.getIdLicencia())));
                hoja.addCell(new Label(5, fila, String.valueOf(infracciones.getPuntosDeducidos())));
                fila++;
            }

            for (int i = 0; i < encabezados.length; i++) {
                autoSizeColumn(hoja, i);
        }
            // Guardar y cerrar el libro
            libro.write();
            libro.close();

            System.out.println("Exportado correctamente a: " + archivoFinal.getAbsolutePath());

        } catch (Exception e) {
            throw new Exception("Error al exportar los datos");
        }
    }
    
    
    public static void exportarExamenesExcel(ObservableList<ExamenConduccion> listaExamenes, String nombreBaseArchivo, Stage ventanaPadre) throws Exception {
        try {
            // Configurar el FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar carpeta para guardar el archivo");

            // Establecer el nombre inicial del archivo
            String nombreArchivo = nombreBaseArchivo + "-"
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
            WritableSheet hoja= libro.createSheet("Examenes", 0);

            // Escribir encabezados
            String[] encabezados = {"Examinado", "Tipo","Fecha", "Examinador", "Resultado"};
            for (int col = 0; col < encabezados.length; col++) {
                hoja.addCell(new Label(col, 0, encabezados[col]));
            }

            ExamenMedico examenMedico=null;
            
            // Escribir datos de las examenes
            int fila = 1;
            for (ExamenConduccion examenes : listaExamenes) {
                hoja.addCell(new Label(0, fila, examenes.getPersona().getNombreApellidos()));
                hoja.addCell(new Label(1, fila, examenes.getTipo()));
                hoja.addCell(new Label(2, fila, examenes.getFecha().toString()));
                hoja.addCell(new Label(3, fila, examenes.getExaminador().getNombre()));
                if(examenes.isAprobado() && !(examenes.getTipo().equalsIgnoreCase("Médico")))
                {
                    hoja.addCell(new Label(4, fila, "Aprobado"));
                }
                else if(!examenes.isAprobado())
                {
                    hoja.addCell(new Label(4, fila, "Reprobado"));
                }
                else if(examenes.isAprobado() && examenes.getTipo().equalsIgnoreCase("Médico"))
                {
                    examenMedico=ServiciosExamenesMedicos.obtenerExamenesMedicoPorId(examenes.getId());
                    if(examenMedico.getRestricciones().size()==0)
                    {
                        hoja.addCell(new Label(4, fila, "Aprobado condicional"));
                    }
                    else if(examenMedico.getRestricciones().size()!=0)
                    {
                        hoja.addCell(new Label(4, fila, "Reprobado"));
                    }
                }
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
    
    
    public static void exportarLicenciasExcel(ObservableList<Licencia> listaLicencias, String nombreBaseArchivo, Stage ventanaPadre) throws Exception {
        try {
            // Configurar el FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar carpeta para guardar el archivo");

            // Establecer el nombre inicial del archivo
            String nombreArchivo = nombreBaseArchivo + "-"
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
            WritableSheet hoja= libro.createSheet("Licencias", 0);

            // Escribir encabezados
            String[] encabezados = {"Nombre", "Tipo","Emision", "Vencimiento", "Puntos"};
            for (int col = 0; col < encabezados.length; col++) {
                hoja.addCell(new Label(col, 0, encabezados[col]));
            }

            // Escribir datos de las licencias
            int fila = 1;
            for (Licencia licencia : listaLicencias) {
                hoja.addCell(new Label(0, fila, ServicioConductor.ObtenerConductorPorIdLicencia(licencia.getId()).getNombreApellidos()));
                hoja.addCell(new Label(1, fila, licencia.getTipo()));
                hoja.addCell(new Label(2, fila, licencia.getFechaEmision().toString()));
                hoja.addCell(new Label(3, fila, licencia.getFechaVencimiento().toString()));
                hoja.addCell(new Label(4, fila, String.valueOf(licencia.getCantPuntos())));
                fila++;
            }

            for (int i = 0; i < encabezados.length; i++) {
                autoSizeColumn(hoja, i);
        }
            // Guardar y cerrar el libro
            libro.write();
            libro.close();

            System.out.println("Exportado correctamente a: " + archivoFinal.getAbsolutePath());

        } catch (Exception e) {
            throw new Exception("Error al exportar los datos");
        }
    }
    
    
    private static void autoSizeColumn(WritableSheet hoja, int indiceColumna) {
        try {
            int maximoAncho = 0;
            
            // Analizar las primeras 100 filas por rendimiento
            int filasARevisar = Math.min(hoja.getRows(), 100);
            
            for (int fila = 0; fila < filasARevisar; fila++) {
                Cell celda = hoja.getCell(indiceColumna, fila);
                String contenido = celda.getContents();
                
                // Calcular ancho aproximado (1 carácter ≈ 256 unidades)
                int ancho = contenido.length() * 256;
                
                // Aumentar un 20% para encabezados
                if (fila == 0) ancho = (int)(ancho * 1.2);
                
                if (ancho > maximoAncho) {
                    maximoAncho = ancho;
                }
            }
            
            // Limitar al máximo permitido por Excel (255 caracteres)
            int anchoColumna = Math.min(maximoAncho, 255 * 256);
            
            // Aplicar el ancho (convertir a unidades de JXL)
            hoja.setColumnView(indiceColumna, anchoColumna / 256);
            
        } catch (Exception e) {
            System.err.println("Error al autoajustar columna: " + e.getMessage());
        }
    }
}
      
    

      
    

