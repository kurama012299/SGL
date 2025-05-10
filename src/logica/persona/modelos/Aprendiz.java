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
        return "Id: "+ Id + "\n"+
                "Nombre: "+ Nombre + "\n"+
                "Apellidos: "+ Apellidos + "\n"+
                "CI: "+ CI + "\n"+
                "FechaNacimiento: "+ FechaNacimiento + "\n"+
                "Direccion: "+ Direccion + "\n"+
                "Telefono: "+ Telefono + "\n"+
                "Correo: "+ Correo + "\n"+
                "Foto: "+ Foto + "\n";
    }
    
    public Aprendiz(String nombre,String apellido,String Ci)
    {
        super(nombre,apellido,Ci);
    }
}
