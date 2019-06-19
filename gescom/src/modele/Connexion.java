/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Formation
 */
public class Connexion {
    static Connection conex;    
    static {
         try{ 
         Class.forName("com.mysql.jdbc.Driver");
         String url = "jdbc:mysql://localhost:3306/gescom";
         String user = "root";
         String password="";
         conex =DriverManager.getConnection(url,user, password);
           System.out.println("Connexion ok");
         
       }catch(ClassNotFoundException | SQLException e){
           System.out.println(e.getMessage());
       }
    }
    public static Connection getConnexion(){
        return conex;
    }
    
}
