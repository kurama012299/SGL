/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.usuario.modelos;

/**
 *
 * @author Angel Hernandez
 */
public class Usuario {
    
    private String Nombre;
    private String Correo;
    private String Rol;
    private String Foto;
    private Long EntidadPerteneciente;
    
    public Usuario (String Nombre, String Correo, String Rol,String Foto ,Long EntidadPerteneciente)
    {
        this.Nombre=Nombre;
        this.Correo = Correo;
        this.Foto = Foto;
        this.Rol = Rol;
        this.EntidadPerteneciente = EntidadPerteneciente;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getRol() {
        return Rol;
    }

    public String getFoto() {
        return Foto;
    }

    public Long getEntidadPerteneciente() {
        return EntidadPerteneciente;
    }
    
    @Override
    public String toString()
    {
        return "Nombre: "+ Nombre +"\n"+
                "Correo: "+ Correo +"\n"+
                "Foto: " + Foto +"\n"+
                "Rol: " + Rol +"\n"+
                "Entidad Perteneciente: "+ EntidadPerteneciente;
    }
}
