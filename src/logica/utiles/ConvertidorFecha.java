/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.utiles;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Angel Hernandez
 */
public class ConvertidorFecha {
    public static Date convertirFecha(String fechaString) throws Exception {
        // Verificar que el String tenga exactamente 6 caracteres numéricos
        if (fechaString == null || fechaString.length() != 6 || !fechaString.matches("\\d+")) {
            throw new IllegalArgumentException("El formato debe ser DDMMAA (6 dígitos numéricos)");
        }

        // Separar día, mes y año
        String dia = fechaString.substring(4, 6);
        String mes = fechaString.substring(2, 4);
        String anio = fechaString.substring(0, 2);

        // Determinar el siglo (asumir que años 00-20 son 2000s y 21-99 son 1900s)
        int anioNum = Integer.parseInt(anio);
        String siglo = (anioNum <= 20) ? "20" : "19";
        String anioCompleto = siglo + anio;

        // Formatear como yyyy-MM-dd
        String fechaFormateada = anioCompleto + "-" + mes + "-" + dia;

        // Convertir a Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Validación estricta de fechas

        try {
            return sdf.parse(fechaFormateada);
        } catch (Exception e) {
            throw new Exception("Fecha inválida: " + fechaString
                    + ". Formato esperado: DDMMAA (ej: 100489 para 10-04-1989). Error: " + e.getMessage());
        }
    }
}
