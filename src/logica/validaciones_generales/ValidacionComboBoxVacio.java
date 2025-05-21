/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.validaciones_generales;

import javafx.scene.control.ComboBox;

/**
 *
 * @author Kris
 */
public class ValidacionComboBoxVacio implements IValidacion{

    @Override
    public void Validar(Object Entrada, String nombreCampo) throws Exception {
        ComboBox comboBox=(ComboBox)Entrada;
        if(comboBox.getSelectionModel().getSelectedItem()== null)
            throw new Exception("El "+nombreCampo+" no puede estar vacio");
        
    }
    
}
