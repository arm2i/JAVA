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
public class Puissance4 {
    private Partie puissance4;
    
    
    public Puissance4(){
        
    }

    public Puissance4(Partie puissance4) {
        this.puissance4 = puissance4;
    }
    
   public boolean gameIsOver(){
       boolean gameIsOver=false;
     if(this.puissance4.isPartieFinie()==true){
         gameIsOver=true;
     }
     return gameIsOver;
   }
   
   public void jouer(int numColonne){
   
         while (numColonne>0&&numColonne<Grille.NB_COLONNES&&puissance4.isPartieFinie()!=true){
             
              
               
               Jeton jeton = new Jeton(this.puissance4.getJoueurCourant().getNom());
               this.puissance4.getGrille().insererJeton(jeton, numColonne);
               
             
            // if(this.puissance4.getGrille().isFullColonne(numColonne)==false){
             //this.puissance4.getGrille().insererJeton(jeton, numColonne);
             
             //}
             }  
   }

    @Override
    public String toString() {
        return "Puissance4{" + "puissance4=" + puissance4 + '}';
    }
   
    
}
