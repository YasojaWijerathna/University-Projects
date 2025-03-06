/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.starfield.yasoja.logger;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author dnana
 */
public class Logger {
    
    private static final String FILE_NAME="testLog.log";
    
    public static void log(String msg){
    
        try (FileWriter fw=new FileWriter(FILE_NAME,true); PrintWriter pw=new PrintWriter(fw,true) ){  
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String timeStamp=sdf.format(new Date());
            pw.printf("[%s] %s%n",timeStamp,msg);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        
    }
    
    
}
