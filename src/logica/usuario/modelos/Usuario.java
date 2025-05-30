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
    
    private String nombre;
    private String correo;
    private String rol;
    private String foto;
    private Long entidadPerteneciente;
    private Long id;
    
    public Usuario(String Nombre, String Correo, String Rol,String Foto ,Long EntidadPerteneciente, Long Id)
    {
        this.nombre=Nombre;
        this.correo = Correo;
        this.foto = Foto;
        this.rol = Rol;
        this.entidadPerteneciente = EntidadPerteneciente;
        this.id=Id;
    }
    
    public Usuario(String Nombre)
    {
        this.nombre=Nombre;
    }
    
    public Usuario(String correo,String nombre)
    {
        this.correo=correo;
        this.nombre=nombre;
    }
    
    public Usuario(String Nombre, String Correo, String Rol,String Foto ,Long EntidadPerteneciente)
    {
        this.nombre=Nombre;
        this.correo = Correo;
        this.foto = Foto;
        this.rol = Rol;
        this.entidadPerteneciente = EntidadPerteneciente;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRol() {
        return rol;
    }

    public String getFoto() {
        return foto;
    }

    public Long getEntidadPerteneciente() {
        return entidadPerteneciente;
    }
    
    public Long getId()
    {
        return id;
    }
    
    @Override
    public String toString()
    {
        return  "Id: " + id +"\n"+
                "Nombre: "+ nombre +"\n"+
                "Correo: "+ correo +"\n"+
                "Foto: " + foto +"\n"+
                "Rol: " + rol +"\n"+
                "Entidad Perteneciente: "+ entidadPerteneciente;
    }
}
