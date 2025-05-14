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
    protected Long Id;
    protected String Nombre;
    protected String Apellidos;
    protected String CI;
    protected Date FechaNacimiento;
    protected String Direccion;
    protected String Telefono;
    protected String Correo;
    protected String Foto;

    public Persona(Long Id, String Nombre, String Apellidos, String CI, Date FechaNacimiento, String Direccion, String Telefono, String Correo, String Foto) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.CI = CI;
        this.FechaNacimiento = FechaNacimiento;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Correo = Correo;
        this.Foto = Foto;
    }

    public Persona(String nombre,String apellidos,String CI)
    {
        this.Nombre = nombre;
        this.Apellidos= apellidos;
        this.CI=CI;
    }
    
     public Persona(String Nombre,String Apellidos, String CI, String Foto)
    {
        this.Nombre = Nombre;
        this.Apellidos= Apellidos;
        this.CI = CI;
        this.Foto = Foto;
    }
    
    
    public Long getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getCI() {
        return CI;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getFoto() {
        return Foto;
    }
    
    
    
}
