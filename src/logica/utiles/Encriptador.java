/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.utiles;


/**
 *
 * @author Angel Hernandez
 */
public class Encriptador {
      // Caracteres válidos (personaliza este string según necesites)
    private static final String CARACTERES = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=[]{}|;:',.<>?/\" ";

    // Encriptar
    public static String encriptar(String texto, int clave) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            int posicion = CARACTERES.indexOf(c);
            if (posicion != -1) { // Si el carácter está en nuestra lista
                int nuevaPosicion = (posicion + clave) % CARACTERES.length();
                if (nuevaPosicion < 0) nuevaPosicion += CARACTERES.length(); // Manejar desplazamientos negativos
                resultado.append(CARACTERES.charAt(nuevaPosicion));
            } else {
                resultado.append(c); // Mantener caracteres no reconocidos
            }
        }
        return resultado.toString();
    }

    // Desencriptar
    public static String desencriptar(String texto, int clave) {
        return encriptar(texto, -clave); // Invertir el desplazamiento
    }

}
