/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Formation
 */
public class ProduitDao implements ProduitInterface{
    Connection conn = Connexion.getConnexion();
    @Override
    public List<Produit> getAllProduit() {
           List<Produit> listeProd = new ArrayList<>();           
           try{
               PreparedStatement ps = conn.prepareStatement("SELECT * FROM produit");
               ResultSet rs = ps.executeQuery();
               while(rs.next()){            
                   Produit prod = new Produit();
                  //modification de l'état de l'objet prod 
                   prod.setIdProd(rs.getInt("idProd"));
                   prod.setNomProd(rs.getString("nomProd"));
                   prod.setDescriptionProd(rs.getString("descriptionProd"));
                   prod.setPrixProd(rs.getDouble("prixProd"));
                   prod.setQteProd(rs.getInt("qteProd"));
                   prod.setCatProd(new Categorie(rs.getInt("idCat")));
                //Ajout de l'objet produit à la liste   
                   listeProd.add(prod);
               }
           }catch(SQLException ex){ System.out.println(ex.getMessage());}       
           return listeProd;
    }

    @Override
    public void addProduit(Produit prod) {       
        try{
           PreparedStatement ps = conn.prepareStatement("INSERT INTO "
                   + "produit(nomProd,descriptionProd,prixProd,qteProd,idCat) "
                   + " values(?,?,?,?,?)");
           //passage de paramètre à la requête préparée
           ps.setString(1,prod.getNomProd());
           ps.setString(2,prod.getDescriptionProd());
           ps.setDouble(3,prod.getPrixProd());
           ps.setInt(4,prod.getQteProd());
           ps.setInt(5, prod.getCatProd().getIdCat());
           
           System.out.println(ps);
           ps.executeUpdate();
           
           JOptionPane.showMessageDialog(null,"Enregistrement effectué avec succès");
        }catch(SQLException e){
            
        }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProduit(Produit prod) {    
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM produit "
                    + "where idProd = ?");
            //passage de paramètre à la requête préparée
            ps.setInt(1, prod.getIdProd());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "opération effectuée avec succès");
        } catch (SQLException e) {
    }
    }
    @Override
    public void updateProduit(Produit prod) {
  try{
           PreparedStatement ps = conn.prepareStatement("UPDATE produit"
                   + "set nomProd=?,descriptionProd=?,prixProd=?,"
                   + "qteProd = ?,idCat=? where idProd=? )");
           //passage de paramètre à la requête préparée
           ps.setString(1,prod.getNomProd());
           ps.setString(2,prod.getDescriptionProd());
           ps.setDouble(3,prod.getPrixProd());
           ps.setInt(4,prod.getQteProd());
           ps.setInt(5, prod.getCatProd().getIdCat());
           ps.setInt(6, prod.getIdProd());
           
           ps.executeUpdate();
           
           JOptionPane.showMessageDialog(null,"Enregistrement effectué avec succès");
        }catch(SQLException e){
            
        }
        
    }

    @Override
    public Produit getOneProduit(int idProd) {
        Produit prod = new Produit();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM produit"
                    + "where idProd = ? ");
            ps.setInt(1, idProd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                prod.setIdProd(rs.getInt("idProd"));
                prod.setNomProd(rs.getString("nomProd"));
                prod.setDescriptionProd(rs.getString("descriptionProd"));
                prod.setPrixProd(rs.getDouble("prixProd"));
                prod.setQteProd(rs.getInt("qteProd"));
                prod.setCatProd(new Categorie(rs.getInt("idCat")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return prod;
    }

}
