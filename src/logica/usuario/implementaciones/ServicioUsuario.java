/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.usuario.implementaciones;

import logica.usuario.consultas.ConsultasUsuario;
import logica.usuario.modelos.Usuario;

/**
 *
 * @author Angel Hernandez
 */
public class ServicioUsuario {
    
    public static Usuario obtenerUsuarioPorId(long id) throws Exception
    {
        return ConsultasUsuario.obtenerUsuarioPorIdConsulta(id);
    }
    
}
