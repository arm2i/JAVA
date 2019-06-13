/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupuissance4;

/**
 *
 * @author Administrateur
 */
public class Partie {
    private Grille grille;
    private Joueur[] joueurs;
    private Joueur joueurCourant;
    private boolean PartieFinie;
    private Joueur gagnant;
    private boolean ParAbandon;
    
    public Partie(){
        this.grille = new Grille(); //creation nouvelle grille
        this.joueurs = new Joueur[2];// creation 2 joueurs
       Joueur joueurJaune = new Joueur(Couleur.JAUNE);
       this.joueurs[0]=joueurJaune;
              Joueur joueurRouge = new Joueur(Couleur.ROUGE);
       this.joueurs[1]=joueurRouge;
       int n = (int)(Math.random() * 2);
       this.joueurCourant = this.joueurs[n];
       
       this.PartieFinie=false;
       this.gagnant=null;
       this.ParAbandon=false;
    }

    public Grille getGrille() {
        return grille;
    }

    public Joueur[] getJoueurs() {
        
        return joueurs;
    }

    public Joueur getJoueurCourant() {
                        
        return joueurCourant;
    }

    public boolean isPartieFinie() {
        
        if(this.gagnant!=null|this.ParAbandon==true|grille.isFullGrille()==true){
            this.PartieFinie=true;
        }
        
        return PartieFinie;
    }

    public Joueur getGagnant() {
        
        return gagnant;
    }

    @Override
    public String toString() {
        return "Partie{" + "grille=" + grille + ", joueurs=" + joueurs + ", joueurCourant=" + joueurCourant + ", PartieFinie=" + PartieFinie + ", gagnant=" + gagnant + ", ParAbandon=" + ParAbandon + '}';
    }
    
    
    
}
