/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.starfield.yasoja.connection;

/**
 *
 * @author dnana
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Mysql {
    
    private static Connection connection;
    private static final String USER="root";
    private static final String PASSWORD="colombo123";
    private static final  String DATABASE_NAME="starfield";
    
    
    public static Connection GetConnection(){
      
            try {
                
                if(connection == null){
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DATABASE_NAME,USER,PASSWORD);
                }
                
                return connection;
                
            } catch (SQLException | ClassNotFoundException ex) {
                throw new ExceptionInInitializerError("Oops! Mysql Connection Failed....! ");
            }
        
       
    }
    
    public static void iud(String query){
        try {
            GetConnection().createStatement().executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    public static ResultSet search(String query) throws SQLException{
          return GetConnection().createStatement().executeQuery(query);
    }
}