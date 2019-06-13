package business;


import business.Joueur;
import business.Grille;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Classe gérant la partie (grille, joueurs, gagnant, fin de partie...).
 * 
 * @author arhgi128.
 */
public class Partie {
    private Grille grille;
    private Joueur[] joueurs;
    private Joueur joueurCourant;
    private boolean partieFinie;
    private Joueur gagnant;
    private boolean parAbandon;

    // constructeur
    /**
     * Création d'une partie, génère les éléments nécessaire.
     */
    public Partie() {
        this.grille = new Grille();
        this.joueurs = new Joueur[2];
        joueurs[0] = new Joueur(Couleur.JAUNE);
        joueurs[1] = new Joueur(Couleur.ROUGE);
        joueurCourant = new Joueur();
        joueurCourant = joueurs[(int)((Math.random()*2)%2)];
        gagnant = null;
        partieFinie = false;
        parAbandon = false;
    }
    
    // getters
    /**
     * 
     * @return la grille de jeu et ses jetons.
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * 
     * @return la liste des joueurs.
     */
    public Joueur[] getJoueurs() {
        return joueurs;
    }

    /**
     * 
     * @return le joueur dont c'est le tour.
     */
    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    /**
     * 
     * @return true si la partie est finie, sinon false.
     */
    public boolean isPartieFinie() {
        return partieFinie;
    }

    /**
     * 
     * @return le joueur gagnant, sinon null.
     */
    public Joueur getGagnant() {
        return gagnant;
    }

    /** 
     * 
     * @return true si le perdant a abandonné, sinon false.
     */
    public boolean isParAbandon() {
        return parAbandon;
    }

    // setters
    /**
     * permet d'assigner le tour à un joueur.
     * @param joueurCourant : le joueur dont c'est le tour.
     */
    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    /** 
     * permet de passer la partie à l'état terminé.
     * @param partieFinie .
     */
    public void setPartieFinie(boolean partieFinie) {
        this.partieFinie = partieFinie;
    }

    /**
     * permet de définir le gagnant de la partie.
     * @param gagnant : le joueur gagnant.
     */
    public void setGagnant(Joueur gagnant) {
        this.gagnant = gagnant;
    }

    /**
     * permet de définir si la partie a été terminée par abandon d'un joueur.
     * @param parAbandon .
     */
    public void setParAbandon(boolean parAbandon) {
        this.parAbandon = parAbandon;
    }
    
}
