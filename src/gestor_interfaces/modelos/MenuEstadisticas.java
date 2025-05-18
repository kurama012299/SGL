/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_interfaces.modelos;

import java.util.ArrayList;

/**
 *
 * @author Angel Hernandez
 */
public class MenuEstadisticas {
    
    private ArrayList<Estadistica> Estadisticas;
    private EstadisticaUsuario EstadisticaUsuario;

    public ArrayList<Estadistica> getEstadisticas() {
        return Estadisticas;
    }

    public void SetEstadisticas(ArrayList<Estadistica> Estadisticas) {
        this.Estadisticas = Estadisticas;
    }

    public EstadisticaUsuario GetEstadisticaUsuario() {
        return EstadisticaUsuario;
    }

    public void SetEstadisticaUsuario(EstadisticaUsuario EstadisticaUsuario) {
        this.EstadisticaUsuario = EstadisticaUsuario;
    }
    
    @Override
    public String toString()
    {
        return Estadisticas + "\n"+
               EstadisticaUsuario;
    }
}
