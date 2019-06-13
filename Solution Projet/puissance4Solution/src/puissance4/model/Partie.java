package puissance4.model;

/**
 *
 * @author G37269
 */
public class Partie {

    private Grid grid;
    private Player[] players;
    private Player currentPlayer;
    private boolean finishGame;
    //private boolean toLeave; //par Abandon
    private Player winner;

    /**
     * Crée une nouvelle partie du jeu an initialisant toutes les variables
     */
    public Partie() {

        grid = new Grid();
        players = new Player[2];
        players[0] = new Player(Color.RED);
        players[1] = new Player(Color.YELLOW);

        currentPlayer = players[(int) Math.ceil(Math.random())];

        finishGame = false;
       // toLeave = false;
        winner = null;
    }

    /**
     * Affiche la grid de jeton
     *
     * @return la grid
     */
    public Grid getGrille() {
        return grid;
    }

    /**
     * Donne le nom du joueur courant
     *
     * @return le jour courant
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Modifie le nom du joueur Couraant
     *
     * @param currentPlayer type Joueur
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Affiche le nom du winner
     *
     * @return le gagant
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Modifie le nom du gaganant
     *
     * @param player
     */
    public void setWinner(Player player) {
        winner = player;

    }
    /**
     * Modifie la variable finishGame
     *
     * @param finishGame booléen qui détermine si la partie est terminée ou pas
     */
    public void setFinishGame(boolean finishGame) {
        this.finishGame = finishGame;
    }

    /**
     * Informe sur l'état de la partie
     *
     * @return vrai si la partie est terminée et faux dans le cas contraire
     */
    public boolean isFinishGame() {

        return finishGame;
    }

    /**
     * Affiche le nom des players
     * @return le joueur
     */
    public Player[] getPayers() {
        return players;
    }

    /**
     * Termine une partie du jeu
     */
    public void setFinishGame() {
        finishGame = true;
    }

    /**
     * Affiche de manière lisible le nom du gagannt
     *
     * @return le nom du winner
     */
    @Override
    public String toString() {
        String chain;
        chain = winner.toString();
        return chain;
    }
}
