/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.autentificacion;

import logica.inicios_sesion.consultas.ConsultasInicioSesion;
import logica.usuario.consultas.ConsultasUsuario;
import logica.usuario.modelos.Usuario;



/**
 *
 * @author Angel Hernandez
 */
public class Autentificador {
    
    public static Usuario Usuario;
    public static String ExisteUsuario(String Correo,String Clave) throws Exception
    {
        Usuario = ConsultasUsuario.ObtenerUsuario(Correo, Clave);
        System.out.println(Usuario);
        ConsultasInicioSesion.CrearInicioSesion(Usuario.getId());
        return Usuario.getRol();
    }
}
