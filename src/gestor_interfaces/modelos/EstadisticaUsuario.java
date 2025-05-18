/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_interfaces.modelos;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 * @author Angel Hernandez
 */
public class EstadisticaUsuario {
    
    private String UltimoInicioSesion;
    private long CantidadIniciosSesion;

    public String GetUltimoInicioSesion() {
        return UltimoInicioSesion;
    }

    public void SetUltimoInicioSesion(Date UltimoInicioSesion) {

        if (UltimoInicioSesion == null) {
            this.UltimoInicioSesion = "ahora";
            return;
        }

        // Convertir java.sql.Date a LocalDateTime
        LocalDateTime UltimoInicio;
        if (UltimoInicioSesion instanceof java.sql.Date) {
            UltimoInicio = ((java.sql.Date) UltimoInicioSesion).toLocalDate()
                    .atStartOfDay();
        } else {
            // Para java.util.Date normal
            UltimoInicio = UltimoInicioSesion.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }

        LocalDateTime Ahora = LocalDateTime.now();
        long Minutos = ChronoUnit.MINUTES.between(UltimoInicio, Ahora);
        long Horas = ChronoUnit.HOURS.between(UltimoInicio, Ahora);
        long Dias = ChronoUnit.DAYS.between(UltimoInicio, Ahora);

        if (Minutos < 1) {
            this.UltimoInicioSesion = " menos de 1 minuto";
        } else if (Minutos < 60) {
            this.UltimoInicioSesion = String.format(" %d minuto%s",
                    Minutos, (Minutos != 1 ? "s" : ""));
        } else if (Horas < 24) {
            this.UltimoInicioSesion = String.format(" %d hora%s",
                    Horas, (Horas != 1 ? "s" : ""));
        } else if (Dias < 30) {
            this.UltimoInicioSesion = String.format(" %d día%s",
                    Dias, (Dias != 1 ? "s" : ""));
        } else {
            this.UltimoInicioSesion = " mucho tiempo";
        }
    }
    
    public long GetCantidadIniciosSesion() {
        return CantidadIniciosSesion;
    }

    public void SetCantidadIniciosSesion(long CantidadIniciosSesion) {
        this.CantidadIniciosSesion = CantidadIniciosSesion;
    }
    
    @Override
    public String toString()
    {
        return "Inicios Sesion: "+ CantidadIniciosSesion+"\n"+
               "Ultimo inicio: " + UltimoInicioSesion;
    }
    
}
