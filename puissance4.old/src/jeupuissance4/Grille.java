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
public class Grille {

    public static final int NB_LIGNES = Config.NB_LIGNES;
    public static final int NB_COLONNES = Config.NB_COLONNES;
    private Jeton[][] plateauJetons;

    public Grille() {
        this.plateauJetons = new Jeton[NB_LIGNES][NB_COLONNES];
    }

    public Grille(Jeton[][] plateauJetons) {
        this.plateauJetons = plateauJetons;
    }

    /**
     * Cette méthode retourne le jeton à une position
     *
     * @param position
     * @return
     */
    public Jeton getJeton(Position position) {
        return this.plateauJetons[position.getLigne()][position.getColonne()];
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String chaine = "\n";
        chaine += "    0    1    2    3    4    5    6\n";
        chaine += "   ---------------------------------\n";
        for (int i = 0; i < plateauJetons.length; i++) {
            for (int j = 0; j < plateauJetons[i].length; j++) {
                //chaine= chaine+"----";          
                if (this.plateauJetons[i][j] == null) {
                    chaine = chaine + "  |  ";
                } else if (this.plateauJetons[i][j].getCouleur() == Couleur.JAUNE) {
                    chaine = chaine + " |\033[43m J\033[0m ";
                } else {
                    chaine = chaine + " |\033[37;41m R\033[0m ";
                }
            }
            chaine = chaine + " | " + (i) + "\n";
            chaine += "   ---------------------------------\n";
        }
        //chaine+="  -----------------------------------\n";
        chaine += "    0    1    2    3    4    5    6";
        return chaine;
    }

    /**
     * cette méthode permet d'inserer un jeton
     *
     * @param jeton
     * @param numColonne
     * @return
     */
    public int insererJeton(Jeton jeton, int numColonne) {
        int ligne = -1;
        int i = NB_LIGNES - 1;
        while (ligne == -1) {
            //création de la position d'insertion
            Position pos = new Position(i, numColonne);
            //test du jeton à la postion
            if (getJeton(pos) == null) {
                ligne = i;
                //insertion du jeton dans la grille qui est un tableau à deux dim
                this.plateauJetons[ligne][numColonne] = jeton;
               // System.out.println("AligVert? :" + alignementHorizontale(new Position(ligne, numColonne)));
            }
            //décrémentation de la ligne après insertion pour éviter la boucle infinie
            i--;
        }
        //retourner la ligne d'insertion du jeton    
        return ligne;
    }
    // cette methode teste si une colonne est full

    public boolean isFullColonne(int numColonne) {
        Position pos = new Position(0, numColonne);
        /*boolean fullColonne = false;
        if(getJeton(pos)!=null){
            fullColonne=true;
        }
        return fullColonne;*/
        return getJeton(pos) != null;

    }

    public boolean isFullGrille() {
        boolean isFull = true;
        /**
         * variable permettant de tester la grille*
         */
        int numColonne = 0;
        /**
         * variable de colonne initialisée à la colonne0*
         */

        while (numColonne < NB_COLONNES && isFull == true) {
            if (isFullColonne(numColonne)) {
                numColonne++;
            } else {
                isFull = false;
            }
        }

        return isFull;
    }

   public boolean alignementHorizontale(Position position) {
        int ligne = position.getLigne();
        int colonne = position.getColonne() - 1;
        Couleur couleur = getJeton(position).getCouleur();
        int compteur = 1;
        boolean memeCouleur = true;
        /**
         * *********** parcours vers la gauche**************
         */
        while (memeCouleur && colonne > 0 && compteur < 4) {
            Position pos = new Position(ligne, colonne);
            if (getJeton(pos) != null && couleur == getJeton(pos).getCouleur()) {
                compteur++;
                colonne--;
            } else {
                memeCouleur = false;
            }
        }
        memeCouleur = true;
        colonne = position.getColonne() + 1;
        /**
         * *********parcours vers la droite *****************
         */
        while (memeCouleur && colonne < NB_COLONNES) {
            Position pos = new Position(ligne, colonne);
            if (getJeton(pos) != null && couleur == getJeton(pos).getCouleur()) {
                compteur++;
                colonne++;
            } else {
                memeCouleur = false;
            }
        }
        return compteur >= 4;
    }

    public boolean alignementVerticale(Position position) {
        int ligne = position.getLigne() + 1;
        int colonne = position.getColonne();
        Couleur couleur = getJeton(position).getCouleur();
        int compteur = 1;
        boolean memeCouleur = true;
        /**
         * *********** parcours vers la gauche**************
         */
        while (memeCouleur && ligne < NB_LIGNES && compteur < 4) {
            //System.out.println("ligne : " + ligne);
            Position pos = new Position(ligne, colonne);
            if (getJeton(pos) != null && couleur == getJeton(pos).getCouleur()) {
                compteur++;
                ligne++;
            } else {
                memeCouleur = false;
            }
        }
        return compteur >= 4;
    }

    public boolean alignementDiagonaleGauche(Position position) {
        int ligne = position.getLigne() + 1;
        int colonne = position.getColonne() + 1;
        Couleur couleur = getJeton(position).getCouleur();
        int compteur = 1;
        boolean memeCouleur = true;
        /**
         * *********** parcours vers la droite**************
         */
        while (memeCouleur && colonne < NB_COLONNES && ligne < NB_LIGNES && compteur < 4) {

            Position pos = new Position(ligne, colonne);
            if (getJeton(pos) != null && couleur == getJeton(pos).getCouleur()) {
                compteur++;
                colonne++;
                ligne++;
            } else {
                memeCouleur = false;
            }
        }
        memeCouleur = true;
        colonne = position.getColonne() - 1;
        ligne = position.getLigne() - 1;
        /**
         * *********parcours vers la gauche  *****************
         */
        while (memeCouleur && colonne > 0 && ligne > 0 && compteur < 4) {

            Position pos = new Position(ligne, colonne);
            if (getJeton(pos) != null && couleur == getJeton(pos).getCouleur()) {
                compteur++;
                colonne--;
                ligne--;
            } else {
                memeCouleur = false;
            }
        }
        return compteur >= 4;
    }

    public boolean alignementDiagonaleDroite(Position position) {
        int ligne = position.getLigne() - 1;
        int colonne = position.getColonne() + 1;
        Couleur couleur = getJeton(position).getCouleur();
        int compteur = 1;
        boolean memeCouleur = true;
        /**
         * *********** parcours vers la droite**************
         */
        while (memeCouleur && colonne < NB_COLONNES && ligne > 0 && compteur < 4) {
            Position pos = new Position(ligne, colonne);
            if (getJeton(pos) != null && couleur == getJeton(pos).getCouleur()) {
                compteur++;
                colonne++;
                ligne--;
            } else {
                memeCouleur = false;
            }
        }
        memeCouleur = true;
        colonne = position.getColonne() - 1;
        ligne = position.getLigne() + 1;
        /**
         * *********parcours vers la gauche  *****************
         */
        while (memeCouleur && colonne > 0 && ligne < NB_LIGNES && compteur < 4) {

            Position pos = new Position(ligne, colonne);
            if (getJeton(pos) != null && couleur == getJeton(pos).getCouleur()) {
                compteur++;
                colonne--;
                ligne++;
            } else {
                memeCouleur = false;
            }
        }
        return compteur >= 4;
    }
   public boolean alignementRealise(Position position){
       return alignementVerticale(position) ||
               alignementHorizontale(position) ||
               alignementDiagonaleDroite(position)||
               alignementDiagonaleGauche(position);
   }
   
}
