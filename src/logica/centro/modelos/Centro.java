/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.centro.modelos;

/**
 *
 * @author Angel Hernandez
 */
public class Centro {
    
    private String nombre;
    private String direccionPostal;
    private String telefono;
    private String nombreDirectorGeneral;
    private String nombreJefeDptoRH;
    private String nombreJefeDptoCont;
    private String nombreSecretarioGS;
    private String logo;

    public Centro(String nombre, String direccionPostal, String telefono, String nombreDirectorGeneral, String nombreJefeDptoRH, String nombreJefeDptoCont, String nombreSecretarioGS, String logo) {
        this.nombre = nombre;
        this.direccionPostal = direccionPostal;
        this.telefono = telefono;
        this.nombreDirectorGeneral = nombreDirectorGeneral;
        this.nombreJefeDptoRH = nombreJefeDptoRH;
        this.nombreJefeDptoCont = nombreJefeDptoCont;
        this.nombreSecretarioGS = nombreSecretarioGS;
        this.logo = logo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccionPostal() {
        return direccionPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombreDirectorGeneral() {
        return nombreDirectorGeneral;
    }

    public String getNombreJefeDptoRH() {
        return nombreJefeDptoRH;
    }

    public String getNombreJefeDptoCont() {
        return nombreJefeDptoCont;
    }

    public String getNombreSecretarioGS() {
        return nombreSecretarioGS;
    }

    public String getLogo() {
        return logo;
    }
    
    @Override
    public String toString()
    {
        return  "Nombre: "+ nombre +"\n"+
                "DireccionPostal: "+ direccionPostal +"\n"+
                "Telefono: " + telefono +"\n"+
                "NombreDirectorGeneral: " + nombreDirectorGeneral +"\n"+
                "NombreJefeDptoRH: " + nombreJefeDptoRH +"\n"+
                "NombreJefeDptoCont: " + nombreJefeDptoCont +"\n"+
                "NombreSecretarioGS: " + nombreSecretarioGS +"\n"+
                "Logo: " + logo +"\n";
    }
    
}
