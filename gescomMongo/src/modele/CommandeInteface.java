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
public interface CommandeInteface {
    
    public void ajouterCommande(Commande cmd);
    public void supprimerCommande(Commande cmd);
    
    
    public void supprimerProdCommande(Commande cmd, Produit prod);
    public void ajouterProdCommande(Commande cmd, Produit prod);
    public void modifierProdCommande(Commande cmd, Produit prod);
    
    public List<Commande> getAllCommande();
    public Commande getOneCommande(int idCmd);
    
    
    
}
