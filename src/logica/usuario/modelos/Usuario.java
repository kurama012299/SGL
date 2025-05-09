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
    private Long Id;
    
    public Usuario (String Nombre, String Correo, String Rol,String Foto ,Long EntidadPerteneciente, Long Id)
    {
        this.Nombre=Nombre;
        this.Correo = Correo;
        this.Foto = Foto;
        this.Rol = Rol;
        this.EntidadPerteneciente = EntidadPerteneciente;
        this.Id=Id;
    }
    
    public Usuario(String Nombre)
    {
        this.Nombre=Nombre;
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
    
    public Long getId()
    {
        return Id;
    }
    
    @Override
    public String toString()
    {
        return  "Id: " + Id +"\n"+
                "Nombre: "+ Nombre +"\n"+
                "Correo: "+ Correo +"\n"+
                "Foto: " + Foto +"\n"+
                "Rol: " + Rol +"\n"+
                "Entidad Perteneciente: "+ EntidadPerteneciente;
    }
}
