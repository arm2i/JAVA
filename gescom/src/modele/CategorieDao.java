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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Formation
 */
public class CategorieDao implements CategorieInterface {

    Connection conn = Connexion.getConnexion();

    @Override
    public List<Categorie> getAllCategorie() {
        //création d'une liste de catégorie pour stocker les enregistremens de la table
        //catégorie
        List<Categorie> listeCat = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            String requete = "SELECT * FROM categorie";
            ResultSet rs = stmt.executeQuery(requete);

            while (rs.next()) {
                //création d'un objet catégorie 
                Categorie cat = new Categorie();
                cat.setIdCat(rs.getInt("idCat"));
                cat.setLibelle(rs.getString("libelle"));
                //ajout de l'objet catégorie à la liste
                listeCat.add(cat);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listeCat;
    }

    @Override
    public Categorie getOneCategorie(int idCat) {
        Categorie cat = null;//new Categorie();
        try{
            PreparedStatement ps = conn.prepareStatement(""
            +"select * from categorie where idCat=? ");
            ps.setInt(1, idCat);
            ResultSet rs = ps.executeQuery();
            rs.first();
            cat = new Categorie(rs.getInt("idCat"),rs.getString("libelle"));
           //cat.setIdCat(rs.getInt("idCat"));
          // cat.setLibelle(rs.getString("libelle"));
          
          ps.close();
        }catch(SQLException e){
            
        }  
        return cat;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCategorie(Categorie cat) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT "
                    + "INTO categorie(libelle) VALUES(?)");
            ps.setString(1, cat.getLibelle());
            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
        }

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCategorie(Categorie cat) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM "
                    + "`categorie` WHERE idCat = ?");
            ps.setInt(1, cat.getIdCat());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
        }

// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCategorie(Categorie cat) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
          + "UPDATE categorie SET libelle = ? WHERE idCat=?");
            ps.setString(1, cat.getLibelle());
            ps.setInt(2, cat.getIdCat());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
        }
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
