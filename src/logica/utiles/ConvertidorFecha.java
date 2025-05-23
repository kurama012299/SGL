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
        // Verificar que el String tenga exactamente 6 caracteres
        if (fechaString == null || fechaString.length() != 6) {
            throw new IllegalArgumentException("El formato debe ser DDMMAA (6 dígitos)");
        }

        // Separar día, mes y año
        String dia = fechaString.substring(0, 2);
        String mes = fechaString.substring(2, 4);
        String anio = "20" + fechaString.substring(4, 6); // Asumimos siglo 21 (2000s)

        // Formatear como yyyy-MM-dd
        String fechaFormateada = anio + "-" + mes + "-" + dia;

        // Convertir a Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Validación estricta de fechas
        
        try {
            return sdf.parse(fechaFormateada);
        } catch (Exception e) {
            throw new Exception("Fecha inválida: " + fechaString + 
                             ". Error: " + e.getMessage());
        }
    }
}
