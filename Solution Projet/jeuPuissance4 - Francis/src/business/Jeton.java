package business;


import business.Couleur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * gestion d'un jeton et de sa couleur (selon la liste dans couleur.java).
 *
 * @author arhgi128 
 * 
 */
public class Jeton {
    public Couleur couleur;
    
    /**
     * Création d'un jeton.
     * @param couleur (selon la liste dans couleur.java).
     */
    public Jeton(Couleur couleur){
        this.couleur = couleur;
    }
    
    /**
     * 
     * @return la couleur du jeton.
     */
    public Couleur getCouleur(){
        return this.couleur;
    }
}
