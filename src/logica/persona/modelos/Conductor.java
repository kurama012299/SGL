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
public class Conductor extends Persona{
    private Long idLicencia;

    public Conductor(Long Id, String Nombre, String Apellidos, String CI, Date FechaNacimiento, String Direccion, String Telefono, String Correo, String Foto,Long IdLicencia) {
        super(Id, Nombre, Apellidos, CI, FechaNacimiento, Direccion, Telefono, Correo, Foto);
        this.idLicencia=IdLicencia;
    }
    
     public Conductor(String Nombre, String Apellidos, Long IdLicencia, String CI, String Foto){
         super(Nombre,Apellidos,CI,Foto);
        this.idLicencia=IdLicencia;
    }
     
     public Conductor(String CI){
         super(CI);
     }
     
     public Conductor(long id,String nombre, String apellidos, String CI, long idLicencia){
         super(id,nombre,apellidos,CI);
         this.idLicencia=idLicencia;
     }
    

    public Long getIdLicencia() {
        return idLicencia;
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
                "Foto: "+ foto + "\n"+
                "IdLicencia: "+ idLicencia + "\n";
    }
}
