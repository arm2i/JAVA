package business;


import business.Partie;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
* Classe principale de gestion d'une partie de puissance 4. Fait la jonction entre le main et les autres classes.
*
* @author arhgi128
* 
*/
public class Puissance4 {
    Partie partie;

    /**
     * générateur d'une nouvelle partie.
     */
    public Puissance4() {
        this.partie = new Partie();
    }

    /**
     * 
     * @return la partie en cours.
     */
    public Partie getPartie() {
        return partie;
    }

    /**
     * générateur prévu pour charger une partie sauvegardée (non implémenté pour l'instant).
     * @param partie .
     */
    public Puissance4(Partie partie) {
        this.partie = partie;
    }
    
    /**
     * 
     * @return true si la partie est terminée sinon false.
     */
    public boolean gameIsOver(){
        return this.partie.isPartieFinie();
    }
    
    /**
     * Méthode principale du déroulement du jeu.
     * <ul>
     * <li> insertion d'un jeton dans une colonne</li>
     * <li> vérifie si le joueur a réalisé un alignement</li>
     * <li> vérifie si la grille n'est pas pleine</li>
     * <li> donne la main au joueur suivant</li>
     * </ul>
     * @param numColonne : la colonne dans laquelle le jeton est inséré
     * @throws Puissance4Exception  : exception si la colonne jouée est pleine
     */
    public void jouer(int numColonne) throws Puissance4Exception/*, IllegalArgumentException*/ {
        if(!gameIsOver()){
            Jeton jeton = new Jeton(this.partie.getJoueurCourant().getNom());
                System.out.println("Inserer jeton");
            int line = this.partie.getGrille().insererJeton(jeton, numColonne);
                System.out.println("jeton inséré");
            if (line >= 0 && line <= this.partie.getGrille().NB_LIGNES -1){
                Position pos = new Position(line, numColonne);
                System.out.println("position " + pos);
                // on regarde si le joueur n'a pas gagné
                if (this.partie.getGrille().alignementRealise(pos)){
                System.out.println("alignement");
                    // on définit le gagnant (joueur en cours)
                    this.partie.setGagnant(this.partie.getJoueurCourant());
                    // on flag partie finie
                    this.partie.setPartieFinie(true);
                } 
                // on regarde si la grille n'est pas pleine
                if(this.partie.getGrille().isFullGrille()){
                System.out.println("grille pleine");
                    // la partie est terminée
                    this.partie.setPartieFinie(true);
                } 
                // on définit le joueur suivant
                if(this.partie.getJoueurs()[0].equals(this.partie.getJoueurCourant())){
                    this.partie.setJoueurCourant(this.partie.getJoueurs()[1]);
                } else {
                    this.partie.setJoueurCourant(this.partie.getJoueurs()[0]);
                }
                System.out.println("fin");
            }
        }
    }
    
    /**
     * Permet à un joueur d'abandonner.
     */
    public void abandonner(){
        // on définit le gagnant (pas le joueur courant)
        if(this.partie.getJoueurs()[0].equals(this.partie.getJoueurCourant())){
            this.partie.setGagnant(this.partie.getJoueurs()[1]);
        } else {
            this.partie.setGagnant(this.partie.getJoueurs()[0]);
        }
        // on flag parAbandon
        this.partie.setParAbandon(true);
        // on flag partie finie
        this.partie.setPartieFinie(true);
    }
}
