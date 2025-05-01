/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.entidad.modelos;

/**
 *
 * @author Angel Hernandez
 */
public class EntidadRelacionada {
    private Long Id;
    private String Nombre;
    private String Direccion;
    private String Telefono;
    private String Correo;
    private String NombreDirector;
    private String TipoEntidad;

    public EntidadRelacionada(Long Id, String Nombre, String Direccion, String Telefono, String Correo, String NombreDirector, String TipoEntidad) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Correo = Correo;
        this.NombreDirector = NombreDirector;
        this.TipoEntidad = TipoEntidad;
    }

    public Long getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
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

    public String getNombreDirector() {
        return NombreDirector;
    }

    public String getTipoEntidad() {
        return TipoEntidad;
    }
    
    @Override
    public String toString()
    {
        return  "Id: "+ Id +"\n"+
                "Nombre: "+ Nombre +"\n"+
                "Direccion: "+ Direccion +"\n"+
                "Telefono: " + Telefono +"\n"+
                "Correo: " + Correo +"\n"+
                "NombreDirector: " + NombreDirector+"\n"+
                "TipoEntidad: " + TipoEntidad +"\n" ;
    }
}
