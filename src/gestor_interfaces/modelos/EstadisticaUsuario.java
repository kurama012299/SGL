/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_interfaces.modelos;

import java.util.Date;

/**
 *
 * @author Angel Hernandez
 */
public class EstadisticaUsuario {
    private String UltimoInicioSesion;
    private long CantidadIniciosSesion;

    public String getUltimoInicioSesion() {
        return UltimoInicioSesion;
    }

    public void setUltimoInicioSesion(Date UltimoInicioSesion) {
        this.UltimoInicioSesion = UltimoInicioSesion.toString();
    }

    public long getCantidadIniciosSesion() {
        return CantidadIniciosSesion;
    }

    public void setCantidadIniciosSesion(long CantidadIniciosSesion) {
        this.CantidadIniciosSesion = CantidadIniciosSesion;
    }
    
    @Override
    public String toString()
    {
        return "Inicios Sesion: "+ CantidadIniciosSesion+"\n"+
               "Ultimo inicio: " + UltimoInicioSesion;
    }
    
}
