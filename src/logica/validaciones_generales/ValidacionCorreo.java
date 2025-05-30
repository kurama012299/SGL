/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.validaciones_generales;

import java.util.regex.Pattern;

/**
 *
 * @author Angel Hernandez
 */
public class ValidacionCorreo implements IValidacion {

    @Override
    public void validar(Object Texto,String NombreCampo) throws Exception {

        String correo = Texto.toString().trim();
        if (!correo.endsWith("@gmail.com")) {
            throw new Exception("El correo no debe estar vacio y debe terminar con el dominio correcto");
        }
        String parteLocal=correo.split("@")[0];
        if (parteLocal.length() > 20) {
            throw new Exception("El nombre del correo no debe exceder los 12 caracteres");
        }
        long cantidadNumeros = parteLocal.chars().filter(Character::isDigit).count();
        
        // Validar máximo 2 números
        if (cantidadNumeros > 2) {
            throw new Exception("El nombre del correo no debe exceder de dos numeros");
        }

    }

}
