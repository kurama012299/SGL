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

    public void setId(Long Id) {
        this.Id = Id;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public void setNombreDirector(String NombreDirector) {
        this.NombreDirector = NombreDirector;
    }

    public void setTipoEntidad(String TipoEntidad) {
        this.TipoEntidad = TipoEntidad;
    }

    public EntidadRelacionada(Long Id, String Nombre, String Direccion, String Telefono, String Correo, String NombreDirector, String TipoEntidad) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Correo = Correo;
        this.NombreDirector = NombreDirector;
        this.TipoEntidad = TipoEntidad;
    }
    public EntidadRelacionada(String nombre)
    {
        this.Nombre = nombre;
    }
    
    public EntidadRelacionada(String Nombre, String Direccion, String Telefono, String Correo, String NombreDirector, String TipoEntidad) {
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
