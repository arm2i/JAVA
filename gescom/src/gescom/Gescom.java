/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gescom;

import com.mysql.jdbc.Connection;
import controleur.ControleurCategorie;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Categorie;
import modele.CategorieDao;

/**
 *
 * @author Formation
 */
public class Gescom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
       
        /*   CategorieDao catDao = new CategorieDao();
        
        List<Categorie> list = catDao.getAllCategorie();
        
        for(Categorie cat : list) {
            System.out.println("IdCat : " + cat.getIdCat() + " libella : " + cat.getLibelle());
        }
        
        catDao.addCategorie(new Categorie(0,"CAT 1"));
        
        //System.out.println(catDao.getAllCategorie());
        
        //catDao.deleteCategorie(new Categorie(12));
       //  System.out.println(catDao.getAllCategorie());
         
        catDao.updateCategorie(new Categorie(3,"vetement"));
         System.out.println(catDao.getAllCategorie());
        
         System.out.println(catDao.getOneCategorie(2));
*/
        
        ControleurCategorie controleur = new ControleurCategorie();
    }

   
}
