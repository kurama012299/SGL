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
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String nombreDirector;
    private String tipoEntidad;

    public void setId(Long Id) {
        this.id = Id;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
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

    public void setNombreDirector(String NombreDirector) {
        this.nombreDirector = NombreDirector;
    }

    public void setTipoEntidad(String TipoEntidad) {
        this.tipoEntidad = TipoEntidad;
    }

    public EntidadRelacionada(Long Id, String Nombre, String Direccion, String Telefono, String Correo, String NombreDirector, String TipoEntidad) {
        this.id = Id;
        this.nombre = Nombre;
        this.direccion = Direccion;
        this.telefono = Telefono;
        this.correo = Correo;
        this.nombreDirector = NombreDirector;
        this.tipoEntidad = TipoEntidad;
    }
    public EntidadRelacionada(String nombre)
    {
        this.nombre = nombre;
    }
    
    public EntidadRelacionada(String Nombre, String Direccion, String Telefono, String Correo, String NombreDirector, String TipoEntidad) {
        this.nombre = Nombre;
        this.direccion = Direccion;
        this.telefono = Telefono;
        this.correo = Correo;
        this.nombreDirector = NombreDirector;
        this.tipoEntidad = TipoEntidad;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
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

    public String getNombreDirector() {
        return nombreDirector;
    }

    public String getTipoEntidad() {
        return tipoEntidad;
    }
    
    @Override
    public String toString()
    {
        return  "Id: "+ id +"\n"+
                "Nombre: "+ nombre +"\n"+
                "Direccion: "+ direccion +"\n"+
                "Telefono: " + telefono +"\n"+
                "Correo: " + correo +"\n"+
                "NombreDirector: " + nombreDirector+"\n"+
                "TipoEntidad: " + tipoEntidad +"\n" ;
    }
}
