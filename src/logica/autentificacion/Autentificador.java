/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.autentificacion;

/**
 *
 * @author Angel Hernandez
 */
public class Autentificador {
    
    public static String ExisteUsuario(String Correo,String Clave) throws Exception
    {
        String Rol = "";
        switch (Correo) {
                case "Administrador":
                    Rol="Administrador";
                    break;
                case "AdministradorAutoescuela":
                    Rol="AdministradorAutoescuela";
                    break;
                case "AdministradorMedico":
                    Rol="AdministradorMedico";
                    break;
                case "TrabajadorAutoescuela":
                    Rol="TrabajadorAutoescuela";
                    break;
                case "TrabajadorCentro":
                    Rol="TrabajadorCentro";
                    break;
                case "Medico":
                    Rol="Medico";
                    break;
            }
        
        if(Rol.isEmpty())
            throw new Exception("Usuario o clave incorrectos");
        return Rol;
    }
}
