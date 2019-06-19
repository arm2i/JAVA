/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import com.sun.javafx.beans.IDProperty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Formation
 */
public class ProduitDao implements ProduitInterface{ // creation d'un liste de catégorie pour stocker les enregistrement  de la table catégorie
        Connection conn = Connexion.getConnexion();
  

    
    
    
    @Override
    public List<Produit> getAllProduit() {
        
         // creation d'un liste de catégorie pour stocker les enregistrement  de la table catégorie
         
    List<Produit> listeProd = new ArrayList<>();
   // List<Categorie> listeCat = new ArrayList<>();
     Categorie cat = null;
        
       try{
           Statement stmt = conn.createStatement();
           String requete = "SELECT * FROM produit";
           ResultSet rs = stmt.executeQuery(requete);
           
           while(rs.next()){
               // création d'un objet catégorie
              
               cat = new Categorie(rs.getInt("idCat"));
               
               Produit prod = new Produit();
               prod.setIdProd(rs.getInt("idProd"));
               prod.setNomProd(rs.getString("nomProd"));
               prod.setDescriptionProd(rs.getString("DescriptionProd"));
               prod.setPrixProd(rs.getFloat("PrixProd"));
               prod.setQteProd(rs.getInt("qteProd"));
               
               
               prod.setCatProd(cat);
               
               
               
               
               // ajout de l'objet à la liste
               listeProd.add(prod);
               
           }
           
       }catch(SQLException e){
         System.out.println(e.getMessage());
       }
    
    return listeProd;
    
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produit getOneProduit(int IdProd) {
        
               Produit prod = new Produit();
          try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `produit` WHERE  idProd = ?");
            ps.setInt(1,IdProd);
            ResultSet rs = ps.executeQuery();
            rs.first();
            
            
              prod.setIdProd(rs.getInt("idProd"));
              prod.setNomProd(rs.getString("nomProd"));
              prod.setDescriptionProd(rs.getString("DescriptionProd"));
              prod.setPrixProd(rs.getFloat("PrixProd"));
              prod.setQteProd(rs.getInt("qteProd"));
              prod.setCatProd(new Categorie(rs.getInt("idCat")));
              //prod = new Produit(rs.getInt("idProd"));
            ps.close();
            
                    
        }catch(SQLException e){
           System.out.println(e.getMessage());
       }
          return prod;
        
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addProduit(Produit prod) {
        
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO produit VALUES (?,?,?,?,?)");
            ps.setString(1, prod.getNomProd());
            ps.setString(2, prod.getDescriptionProd());
            ps.setFloat(3, prod.getPrixProd());
            ps.setInt(4, prod.getQteProd());
            ps.setInt(5, prod.getCatProd().getIdCat());

            ps.executeUpdate();
            ps.close();
                    
        }catch(SQLException e){
           System.out.println(e.getMessage());
       }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProduit(Produit prod) {
          
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM produit WHERE idProd = ?");
            ps.setInt(1, prod.getIdProd());
            ps.executeUpdate();
            ps.close();
                    
        }catch(SQLException e){
           System.out.println(e.getMessage());
       }
        
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateProduit(Produit prod) {
        
         try{
            PreparedStatement ps = conn.prepareStatement("UPDATE produit set nomProd = ?,descriptionProd = ?, prixProd = ?, qteProd = ?, idCat = ? WHERE idProd = ?)");
            ps.setString(1, prod.getNomProd());
            ps.setString(2, prod.getDescriptionProd());
            ps.setFloat(3, prod.getPrixProd());
            ps.setInt(4, prod.getQteProd());
            ps.setInt(5, prod.getCatProd().getIdCat());
            ps.setInt(6, prod.getIdProd());

            ps.executeUpdate();
            ps.close();
                    
        }catch(SQLException e){
           System.out.println(e.getMessage());
       }
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
