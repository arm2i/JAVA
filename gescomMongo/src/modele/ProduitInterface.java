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
public interface ProduitInterface {
    public List<Produit> getAllProduit();
    public void addProduit(Produit prod);
    public void deleteProduit(Produit prod);
    public void updateProduit(Produit prod);
    public Produit getOneProduit(int idProd);
    
}
