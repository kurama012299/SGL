/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_interfaces.modelos;

import java.time.Duration;
import java.time.Instant;
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

    public String getUltimoInicioSesion() {
        return UltimoInicioSesion;
    }

    public void setUltimoInicioSesion(Date UltimoInicioSesion) {
        System.out.println(UltimoInicioSesion.toString());
        if (UltimoInicioSesion == null) {
            this.UltimoInicioSesion = "ahora";
            return;
        }

        // Convertir java.sql.Date a LocalDateTime
        LocalDateTime ultimoInicio;
        if (UltimoInicioSesion instanceof java.sql.Date) {
            // Para java.sql.Date
            ultimoInicio = ((java.sql.Date) UltimoInicioSesion).toLocalDate()
                    .atStartOfDay();
        } else {
            // Para java.util.Date normal
            ultimoInicio = UltimoInicioSesion.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }

        LocalDateTime ahora = LocalDateTime.now();
        long minutos = ChronoUnit.MINUTES.between(ultimoInicio, ahora);
        long horas = ChronoUnit.HOURS.between(ultimoInicio, ahora);
        long dias = ChronoUnit.DAYS.between(ultimoInicio, ahora);

        if (minutos < 1) {
            this.UltimoInicioSesion = "Hace menos de 1 minuto";
        } else if (minutos < 60) {
            this.UltimoInicioSesion = String.format("Hace %d minuto%s",
                    minutos, (minutos != 1 ? "s" : ""));
        } else if (horas < 24) {
            this.UltimoInicioSesion = String.format("Hace %d hora%s",
                    horas, (horas != 1 ? "s" : ""));
        } else if (dias < 30) {
            this.UltimoInicioSesion = String.format("Hace %d día%s",
                    dias, (dias != 1 ? "s" : ""));
        } else {
            this.UltimoInicioSesion = "Hace mucho tiempo";
        }
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
