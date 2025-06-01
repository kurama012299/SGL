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
public class Persona {
    protected Long id;
    protected String nombre;
    protected String apellidos;
    protected String ci;
    protected Date fechaNacimiento;
    protected String direccion;
    protected String telefono;
    protected String correo;
    protected String foto;

    public Persona(Long Id, String Nombre, String Apellidos, String CI, Date FechaNacimiento, String Direccion, String Telefono, String Correo, String Foto) {
        this.id = Id;
        this.nombre = Nombre;
        this.apellidos = Apellidos;
        this.ci = CI;
        this.fechaNacimiento = FechaNacimiento;
        this.direccion = Direccion;
        this.telefono = Telefono;
        this.correo = Correo;
        this.foto = Foto;
    }
    
    public Persona(String Nombre, String Apellidos, String CI, Date FechaNacimiento, String Direccion, String Telefono, String Correo, String Foto) {
        this.nombre = Nombre;
        this.apellidos = Apellidos;
        this.ci = CI;
        this.fechaNacimiento = FechaNacimiento;
        this.direccion = Direccion;
        this.telefono = Telefono;
        this.correo = Correo;
        this.foto = Foto;
    }

    public Persona(long id,String nombre,String apellidos,String CI)
    {
        this.id=id;
        this.nombre = nombre;
        this.apellidos= apellidos;
        this.ci=CI;
    }
    
    public Persona(String nombre,String apellidos,String CI)
    {
        this.nombre = nombre;
        this.apellidos= apellidos;
        this.ci=CI;
    }
    
     public Persona(String Nombre,String Apellidos, String CI, String Foto)
    {
        this.nombre = Nombre;
        this.apellidos= Apellidos;
        this.ci = CI;
        this.foto = Foto;
    }
    
     public Persona(String CI){
         
         this.ci = CI;
     }
    
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreApellidos()
    {
        return nombre+"  "+apellidos;
    }
    public String getApellidos() {
        return apellidos;
    }

    public String getCI() {
        return ci;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.fechaNacimiento = FechaNacimiento;
    }

    public void setDireccion(String Direccion) {
        this.direccion = Direccion;
    }

    public void setTelefono(String Telefono) {
        this.telefono = Telefono;
    }

    public void setCorreo(String Correo) {
        this.correo = Correo;
    }

    public void setFoto(String Foto) {
        this.foto = Foto;
    }

    public void setId(long id) {
        this.id=id;
    }
    
    
    
}
