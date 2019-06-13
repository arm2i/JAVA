/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupuissance4;

import java.util.Scanner;

/**
 *
 * @author Formation
 */
public class JeuPuissance4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grille grille = new Grille();
        System.out.println(grille.toString());
        
/*
        //Test inserer jeton
        Jeton jeton = new Jeton(Couleur.ROUGE);
        Jeton jeton2 = new Jeton(Couleur.JAUNE);
        grille.insererJeton(jeton, 1);
        
        System.out.println("Colonne : "+grille.isFullColonne(1));
        System.out.println(" Grille : " +grille.isFullGrille());
        System.out.println(grille.toString());
        grille.insererJeton(jeton, 1);
        System.out.println(grille.toString());        
        grille.insererJeton(jeton, 2);
        System.out.println(grille.toString());
        grille.insererJeton(jeton,3);
        System.out.println(grille.toString());
        grille.insererJeton(jeton, 4);
        System.out.println(grille.toString());        
        grille.insererJeton(jeton2, 1);
        System.out.println(grille.toString());
        
        grille.insererJeton(jeton2, 6);
        System.out.println(grille.toString());
        
        System.out.println("Colonne : "+grille.isFullColonne(1));
        System.out.println(" Grille : " +grille.isFullGrille()); 
        */

        Partie jeu1 = new Partie();
       Puissance4 game = new Puissance4(jeu1);
        game.jouer(4);
      
        /* while(jeu1.isPartieFinie()==false){
           Joueur gamer = jeu1.getJoueurCourant();
        System.out.println(gamer  +" donner un num. colonne:");
        Scanner clavier = new Scanner(System.in);
        int valeurColonne;
        valeurColonne = clavier.nextInt();
        
       game.jouer(valeurColonne);

        } */
    }

}

