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
    
    private String Nombre;
    private String DireccionPostal;
    private String Telefono;
    private String NombreDirectorGeneral;
    private String NombreJefeDptoRH;
    private String NombreJefeDptoCont;
    private String NombreSecretarioGS;
    private String Logo;

    public Centro(String Nombre, String DireccionPostal, String Telefono, String NombreDirectorGeneral, String NombreJefeDptoRH, String NombreJefeDptoCont, String NombreSecretarioGS, String Logo) {
        this.Nombre = Nombre;
        this.DireccionPostal = DireccionPostal;
        this.Telefono = Telefono;
        this.NombreDirectorGeneral = NombreDirectorGeneral;
        this.NombreJefeDptoRH = NombreJefeDptoRH;
        this.NombreJefeDptoCont = NombreJefeDptoCont;
        this.NombreSecretarioGS = NombreSecretarioGS;
        this.Logo = Logo;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDireccionPostal() {
        return DireccionPostal;
    }

    public String getTelefono() {
        return Telefono;
    }

    public String getNombreDirectorGeneral() {
        return NombreDirectorGeneral;
    }

    public String getNombreJefeDptoRH() {
        return NombreJefeDptoRH;
    }

    public String getNombreJefeDptoCont() {
        return NombreJefeDptoCont;
    }

    public String getNombreSecretarioGS() {
        return NombreSecretarioGS;
    }

    public String getLogo() {
        return Logo;
    }
    
    @Override
    public String toString()
    {
        return  "Nombre: "+ Nombre +"\n"+
                "DireccionPostal: "+ DireccionPostal +"\n"+
                "Telefono: " + Telefono +"\n"+
                "NombreDirectorGeneral: " + NombreDirectorGeneral +"\n"+
                "NombreJefeDptoRH: " + NombreJefeDptoRH +"\n"+
                "NombreJefeDptoCont: " + NombreJefeDptoCont +"\n"+
                "NombreSecretarioGS: " + NombreSecretarioGS +"\n"+
                "Logo: " + Logo +"\n";
    }
    
}
