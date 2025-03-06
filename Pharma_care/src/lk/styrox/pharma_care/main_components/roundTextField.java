/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.styrox.pharma_care.main_components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JTextField;


/**
 *
 * @author dnana
 */
public class roundTextField extends JTextField{

    public roundTextField() {
        init();
    }
    
    private void init(){
        this.putClientProperty("JComponent.roundRect", true); // margin is padding the format: Top,Left,Bottom,Right
    }
    
}
