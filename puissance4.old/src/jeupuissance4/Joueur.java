/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupuissance4;

/**
 *
 * @author Formation
 */
public class Joueur {
    private Couleur nom;

    public Couleur getNom() {
        return nom;
    }

    public Joueur(Couleur nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Joueur: " + this.nom ;
    }
    
    
}
