/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.styrox.pharma_care.main_components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;

/**
 *
 * @author dnana
 */
public class roundButton extends JButton{

    public roundButton() {
        init();
    }
    
    public void init(){
        this.putClientProperty(FlatClientProperties.STYLE, "arc:20" );
    }
    
}
