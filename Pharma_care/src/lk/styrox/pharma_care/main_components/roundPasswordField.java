/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.styrox.pharma_care.main_components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JPasswordField;

/**
 *
 * @author dnana
 */
public class roundPasswordField extends JPasswordField{

    public roundPasswordField() {
        init();
    }
    
    private void init(){
        this.putClientProperty(FlatClientProperties.STYLE, "arc:20; "
                + "margin:0,10,0,10;");
    }
    
}
