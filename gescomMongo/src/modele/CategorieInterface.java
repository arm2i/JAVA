/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.List;

/**
 *
 * @author Formation
 */
public interface CategorieInterface {
    public List<Categorie> getAllCategorie();
    public  Categorie getOneCategorie(int idCat);
    public void addCategorie(Categorie cat);
    public void deleteCategorie(Categorie cat);
    public void updateCategorie(Categorie cat);
    
    
}
