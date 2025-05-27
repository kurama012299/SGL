/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.usuario.implementaciones;

import java.util.ArrayList;
import logica.usuario.consultas.ConsultasUsuario;
import logica.usuario.modelos.Usuario;

/**
 *
 * @author Angel Hernandez
 */
public class ServicioUsuario {
    
    public static Usuario obtenerUsuarioPorNombre(String nombre) throws Exception
    {
        return ConsultasUsuario.obtenerUsuarioPorNombreConsulta(nombre);
    }
    
    public static ArrayList<Usuario> obtenerCorreosUsuario() throws Exception
    {
        return ConsultasUsuario.obtenerCorreosUsuariosConsulta();
    }
    
    public static Long crearUsuarioBd(Usuario usuario,Long idRol,String clave) throws Exception
    {
        return ConsultasUsuario.crearUsuarioConsulta(usuario, idRol, clave);
    }
    
    public static ArrayList<Usuario> obtenerUsuariosExamenesConduccion() throws Exception
    {
        return ConsultasUsuario.obtenerUsuariosExamenesConduccionConsulta();
    }
    
    public static ArrayList<Usuario> obtenerUsuariosExamenesMedicos() throws Exception
    {
        return ConsultasUsuario.obtenerUsuariosExamenesMedicoConsulta();
    }
}
