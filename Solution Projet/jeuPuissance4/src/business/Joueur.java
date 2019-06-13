package business;


import business.Couleur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * gestion des joueurs et de leur couleur (selon la liste dans couleur.java).
 *
 * @author arhgi 128
 */
public class Joueur {
    Couleur nom;
    
    /**
     * création d'un joueur.
     */
    public Joueur(){
        this.nom = null;
    }
    
    /**
     * Création d'un joueur d'une couleur donnée.
     * @param nom : la Couleur du joueur.
     */
    public Joueur(Couleur nom) {
        this.nom = nom;
    }

    /**
     * 
     * @return la Couleur du joueur.
     */
    public Couleur getNom() {
        return nom;
    }
    
}
