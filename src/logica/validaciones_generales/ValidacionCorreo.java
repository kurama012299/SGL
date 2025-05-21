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

    private final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public void Validar(Object Texto,String NombreCampo) throws Exception {

        String Correo = Texto.toString().trim();

        if (Texto.toString() == null || Correo.isEmpty()) throw new Exception("El correo es obligatorio");


        if (!EMAIL_PATTERN.matcher(Correo).matches()) throw new Exception("El correo no es valido");

    }

}
