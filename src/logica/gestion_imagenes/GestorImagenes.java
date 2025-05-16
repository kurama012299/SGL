/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.gestion_imagenes;

import javafx.scene.image.Image;
import java.io.InputStream;

/**
 *
 * @author Angel Hernandez
 */
public class GestorImagenes {

    public static String IMAGENES_URL = "/imagenes/";

    public static Image CargarImagen(String nombreArchivo) throws Exception {
        try {
            InputStream is = GestorImagenes.class.getResourceAsStream(IMAGENES_URL + nombreArchivo);
            if (is != null) {
                return new Image(is);
            }
        } catch (Exception e) {
            throw new Exception("Error cargando imagen");
        }
        return null;//AQUI DEBERIA DEVOLVER LA IMAGEN POR DEFECTO SINO SE ENCUENTRA LA IMAGEN NORMAL
    }
}
