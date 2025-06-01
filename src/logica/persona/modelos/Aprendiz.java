/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.persona.modelos;

import java.util.Date;

/**
 *
 * @author Angel Hernandez
 */
public class Aprendiz extends Persona{
    
    public Aprendiz(Long Id, String Nombre, String Apellidos, String CI, Date FechaNacimiento, String Direccion, String Telefono, String Correo, String Foto) {
        super(Id, Nombre, Apellidos, CI, FechaNacimiento, Direccion, Telefono, Correo, Foto);
    }
    
    @Override
    public String toString()
    {
        return "Id: "+ id + "\n"+
                "Nombre: "+ nombre + "\n"+
                "Apellidos: "+ apellidos + "\n"+
                "CI: "+ ci + "\n"+
                "FechaNacimiento: "+ fechaNacimiento + "\n"+
                "Direccion: "+ direccion + "\n"+
                "Telefono: "+ telefono + "\n"+
                "Correo: "+ correo + "\n"+
                "Foto: "+ foto + "\n";
    }
    
    public Aprendiz(long id,String nombre,String apellido,String Ci)
    {
        super(id,nombre,apellido,Ci);
    }
}
