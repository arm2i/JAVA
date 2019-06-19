/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gescom;

import controleur.ControleurCategorie;
import controleur.ControleurProduit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import modele.Categorie;
import modele.CategorieDao;
import modele.Produit;
import modele.ProduitDao;

/**
 *
 * @author Formation
 */
public class Gescom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
      
        //ControleurCategorie controleur = new ControleurCategorie();
        
        ControleurProduit controlProd = new ControleurProduit();
        
        
        //ProduitDao prodDao = new ProduitDao();
         
        //Produit prod = new Produit(0,"Trois petits cochons","Livre Test",15.0,250,
        //new Categorie(8));
        
        //prodDao.addProduit(prod);

       // System.out.println(prodDao.getAllProduit());
        // TODO code application logic here
       /* CategorieDao catDao = new CategorieDao();
        List<Categorie> list = catDao.getAllCategorie();
        
        for(Categorie cat : list){
            System.out.println("IdCat : " + cat.getIdCat() + " libelle : " 
                    + cat.getLibelle());
        }
        catDao.updateCategorie(new Categorie(3,"Vêtement"));
        System.out.println("Après Update");
        list = catDao.getAllCategorie();
        
        for(Categorie cat : list){
            System.out.println("IdCat : " + cat.getIdCat() + " libelle : " 
                    + cat.getLibelle());
        }
        
        System.out.println(catDao.getOneCategorie(2));*/
        //catDao.addCategorie(new Categorie(0,"LIVRES"));
        
        //System.out.println(catDao.getAllCategorie());
    }
    
}
