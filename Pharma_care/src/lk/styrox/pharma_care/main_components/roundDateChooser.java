/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.styrox.pharma_care.main_components;

import com.formdev.flatlaf.FlatClientProperties;
import com.toedter.calendar.JDateChooser;

/**
 *
 * @author dnana
 */
public class roundDateChooser extends JDateChooser{

    public roundDateChooser() {
        init();
    }
    
     private void init(){
//        this.putClientProperty(FlatClientProperties.STYLE, "arc:20;");
        this.putClientProperty("JComponent.roundRect", true);
        
    }
}
