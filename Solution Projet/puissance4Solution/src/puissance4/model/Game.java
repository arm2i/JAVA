package puissance4.model;

import java.util.Observable;

/**
 *
 * @author G37269
 */
public class Game extends Observable {

    private Partie puissance4;

    public Game() {
        this.puissance4 = new Partie();
    }

    public Game(Partie puissance4) {
        this.puissance4 = puissance4;
    }

    public Partie getPuissance4() {
        return this.puissance4;
    }

    public boolean gameIsOver() {

        if (puissance4.getGrille().isFullGrille() == true) {
            return true;
        } else {
            return puissance4.isFinishGame();
        }
    }

    /**
     *
     * @param numColonne
     * @throws Puissance4Exception
     */
    public void jouer(int numColonne) throws Puissance4Exception /*throws Puissance4Exception*/ {
        int line;
        boolean align = false;

        //création d'un jeton ayant la couleur du joueur courant

        Token jeton = new Token(puissance4.getCurrentPlayer().getNameColor());

        if (puissance4.getGrille().isFullGrille() == true) {
            puissance4.setFinishGame(true);
            puissance4.setWinner(null);
            setChanged();
            notifyObservers();
        } else {
            //on teste si la partie est terminée
            if (puissance4.isFinishGame() == false) {
                //if(puissance4.getGrille().isFullGrille()==false){
                //on insere le jeton a la colonne indiquée et récupération de la lige

                if (puissance4.getGrille().isFullColonne(numColonne) == false) {

                    line = puissance4.getGrille().insererJeton(jeton, numColonne);

                    //création d'une nouvelle position  
                    Position p = new Position(line, numColonne);

                    //on teste les alignement du jeton à cette position
                    align = puissance4.getGrille().alignementRealise(p);

                    if (align == true) {
                        //on modifie la valeur du gagnant	
                        puissance4.setWinner(puissance4.getCurrentPlayer());
                        //on modifie la valeur de PartieFinie
                        puissance4.setFinishGame(true);
                        setChanged();
                        notifyObservers();
                    }
                    Player[] tabPlayer1;
                    tabPlayer1 = new Player[2];
                    tabPlayer1 = puissance4.getPayers();
                    if (puissance4.getCurrentPlayer().getNameColor() == tabPlayer1[0].getNameColor()) {
                        puissance4.setCurrentPlayer(tabPlayer1[1]);
                        setChanged();
                        notifyObservers();
                    } else {
                        puissance4.setCurrentPlayer(tabPlayer1[0]);
                        setChanged();
                        notifyObservers();
                    }

                }
                //	}else{
                //		puissance4.setFinishGame(true);

            }
        }
    }

  /*  public void abandonner() {
        Joueur[] tabPlayer;
        tabPlayer = new Joueur[2];
        tabPlayer = puissance4.getPayers();
        puissance4.setToLeave(true);
        if (puissance4.getCurrentPlayer().getNameColor() == tabPlayer[0].getNameColor()) {
            puissance4.setWinner(tabPlayer[1]);
        } else {
            puissance4.setWinner(tabPlayer[0]);
        }
        puissance4.setFinishGame(true);

    }*/
}
