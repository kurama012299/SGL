/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.validaciones_generales;

import javafx.scene.control.ToggleGroup;

/**
 *
 * @author Kris
 */
public class ValidacionGrupoRadioButton implements IValidacion{

    @Override
    public void validar(Object Entrada, String nombreCampo) throws Exception {
        ToggleGroup grupoBotones=(ToggleGroup)Entrada;
        if(grupoBotones.getSelectedToggle()==null)
            throw new Exception("Debe seleccionar una de las "+nombreCampo);
    }
    
}
