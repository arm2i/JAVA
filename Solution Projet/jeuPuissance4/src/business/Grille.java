package business;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Gestion de la grille de jeu.
 *
 * @author stagiaire
 * 
 */
public class Grille {

    public int NB_LIGNES =Config.NB_LIGNES ;
    public int NB_COLONNES =Config.NB_COLONNES;
    private Jeton[][] plateauJetons;

    // Constructeurs
    /**
     * construit une nouvelle grille de jeu.
     */
    public Grille() {
        this.plateauJetons = new Jeton[NB_LIGNES][NB_COLONNES];
    }

    /**
     * constructeur prévu pour charger une grille avec une partie sauvegardée (non implémenté pour le moment).
     * @param plateauJetons  : le plateau et ses jetons.
     */
    public Grille(Jeton[][] plateauJetons) {
        this.plateauJetons = plateauJetons;
    }

    //getters
    /**
     * 
     * @return le plateau avec ses jetons.
     */
    public Jeton[][] getPlateauJetons() {
        return plateauJetons;
    }

    /**
     * renvoie le jeton à une position (ord, abs) donnée.
     * @param position : la position à laquelle on cherche le jeton.
     * @return le jeton trouvé à la position sinon null.
     */
    public Jeton getJeton(Position position) {
        Jeton jeton = this.plateauJetons[position.getAbs()][position.getOrd()];
        return jeton;
    }

    /**
     * Ajout d'un jeton dans une colonne.
     * @param jeton : le jeton insérer avec sa couleur.
     * @param numColonne : la colonne dans laquelle le jeton est inséré.
     * @return la ligne à laquelle le jeton est inséré (attention la gestion des lignes dans le code est inversée par rapport à l'affichage.
     * @throws Puissance4Exception : erreur si la colonne est pleine.
     */
    public int insererJeton(Jeton jeton, int numColonne) throws Puissance4Exception/*, IllegalArgumentException*/ {
        int numLigne = -1, i = 0;
        // on vérifie la validité de la colonne
        // supprimé on le gère dans les appels de commandes
        /*if (numColonne < 0 || numColonne > NB_COLONNES - 1) {
            throw new IllegalArgumentException("Le nombre de colonne doit être compris entre 0 et " + (NB_COLONNES - 1) 
                    + ". Veuillez rejouer.");
        }*/

        // on vérifie si la colonne n'est pas pleine
        if (isFullColonne(numColonne)) {
            throw new Puissance4Exception("La colonne " + (numColonne + 1) + " est pleine. Veuillez rejouer.");
        }
        // on cherche la première position libre
        while (numLigne == -1) {
            Position pos = new Position(i, numColonne);
            if (getJeton(pos) == null) {
                numLigne = i;
                plateauJetons[i][numColonne] = jeton;
            }
            i++;
        }
        // on renvoit le numéro de ligne
        return numLigne;
    }

    // méthode vérifiant si la colonne est pleine
    private boolean isFullColonne(int numColonne) {
        boolean isFull = true;
        // si la dernière ligne est occupée alors la colonne est pleine
        Position pos = new Position(NB_LIGNES - 1, numColonne);
        if (getJeton(pos) == null) {
            isFull = false;
        }
        return isFull;
    }

    /**
     * Vérifie si la grille est pleine (fin du jeu, partie nulle).
     * @return vrai si pleine, faux sinon.
     */
    public boolean isFullGrille() {
        boolean isFull = true;
        int numCol = 0;
        // on vérifie si toutes les colonnes sont pleines
        while (isFull && numCol < NB_COLONNES) {
            if (isFullColonne(numCol)) {
                numCol++;
            } else {
                isFull = false;
            }
        }
        return isFull;
    }

    /**
     * méthode permettant de vérifier si le joueur vient de réaliser un alignement de 4 jetons.
     * @param position : la position du jeton qui vient d'être inséré.
     * @return true si un alignement a été réalisé, sinon false.
     */
    public boolean alignementRealise(Position position) {
        boolean alignement;
        // alignement ligne
        alignement = aligntVert(position);
        // alignement colonne
        if (!alignement) {
            alignement = aligntHz(position);
        }
        // alignement diagonale 1
        if (!alignement) {
            alignement = aligntDiag1(position);
        }
        // alignement diagonale 2
        if (!alignement) {
            alignement = aligntDiag2(position);
        }
        return alignement;
    }

    // recherche alignement vertical
    private boolean aligntVert(Position pos) {
        boolean vertAlign, nextLine = true;
        // line : ligne en dessous du jeton actuel, le nombre de jeton est à 1 (celui à la position donné
        int line = pos.getAbs() - 1, col = pos.getOrd(), nbJeton = 1;
        // couleur du jeton
        Couleur couleur = getJeton(pos).getCouleur();
        // on parcourt vers le bas
        while (nextLine && line >= 0) {
            Position nPos = new Position(line, col);
            if (getJeton(nPos) != null && getJeton(nPos).getCouleur() == couleur) {
                // jeton de même couleur, on incrémente
                nbJeton++;
                // on passe à la ligne du dessous
                line--;
            } else {
                // pas de la même couleur, on arrête
                nextLine = false;
            }
        }
        vertAlign = (nbJeton >= 4);
        return vertAlign;
    }

    // recherche alignement horizontal
    private boolean aligntHz(Position pos) {
        boolean hzAlign, nextCol = true;
        // line : ligne du jeton actuel, colonne à gauche du jeton actuel, le nombre de jeton est à 1 (celui à la position donné
        int line = pos.getAbs(), col = pos.getOrd() - 1, nbJeton = 1;
        // couleur du jeton
        Couleur couleur = getJeton(pos).getCouleur();
        // on parcourt vers la gauche
        while (nextCol && col >= 0) {
            Position nPos = new Position(line, col);
            if (getJeton(nPos) != null && getJeton(nPos).getCouleur() == couleur) {
                // jeton de même couleur, on incrémente
                nbJeton++;
                // on passe à la ligne du dessous
                col--;
            } else {
                // pas de la même couleur, on arrête
                nextCol = false;
            }
        }
        // on parcourt vers la droite
        col = pos.getOrd() + 1;
        nextCol = true;
        while (nextCol && col < NB_COLONNES) {
            Position nPos = new Position(line, col);
            if (getJeton(nPos) != null && getJeton(nPos).getCouleur() == couleur) {
                // jeton de même couleur, on incrémente
                nbJeton++;
                // on passe à la ligne du dessous
                col++;
            } else {
                // pas de la même couleur, on arrête
                nextCol = false;
            }
        }
        hzAlign = (nbJeton >= 4);
        return hzAlign;
    }

    // recherche alignement Diagonal 1
    private boolean aligntDiag1(Position pos) {
        boolean dg1Align, nextPos = true;
        // line : ligne en dessous du jeton actuel, colonne à gauche du jeton actuel
        //le nombre de jeton est à 1 (celui à la position donné
        int line = pos.getAbs() - 1, col = pos.getOrd() - 1, nbJeton = 1;
        // couleur du jeton
        Couleur couleur = getJeton(pos).getCouleur();
        // on parcourt vers bas gauche
        while (nextPos && line >= 0 && col >= 0) {
            Position nPos = new Position(line, col);
            if (getJeton(nPos) != null && getJeton(nPos).getCouleur() == couleur) {
                // jeton de même couleur, on incrémente
                nbJeton++;
                // on va en bas gauche
                col--;
                line--;
            } else {
                // pas de la même couleur, on arrête
                nextPos = false;
            }
        }
        // on parcourt vers haut droite
        line = pos.getAbs() + 1;
        col = pos.getOrd() + 1;
        nextPos = true;
        while (nextPos && line < NB_LIGNES && col < NB_COLONNES) {
            Position nPos = new Position(line, col);
            if (getJeton(nPos) != null && getJeton(nPos).getCouleur() == couleur) {
                // jeton de même couleur, on incrémente
                nbJeton++;
                // on en haut droite
                col++;
                line++;
            } else {
                // pas de la même couleur, on arrête
                nextPos = false;
            }
        }
        dg1Align = (nbJeton >= 4);
        return dg1Align;
    }

    // recherche alignement Diagonal 2
    private boolean aligntDiag2(Position pos) {
        boolean dg1Align, nextPos = true;
        // line : ligne en dessous du jeton actuel, colonne à droite du jeton actuel
        //le nombre de jeton est à 1 (celui à la position donné
        int line = pos.getAbs() - 1, col = pos.getOrd() + 1, nbJeton = 1;
        // couleur du jeton
        Couleur couleur = getJeton(pos).getCouleur();
        // on parcourt vers bas gauche
        while (nextPos && line >= 0 && col < NB_COLONNES) {
            Position nPos = new Position(line, col);
            if (getJeton(nPos) != null && getJeton(nPos).getCouleur() == couleur) {
                // jeton de même couleur, on incrémente
                nbJeton++;
                // on va en bas droite
                col++;
                line--;
            } else {
                // pas de la même couleur, on arrête
                nextPos = false;
            }
        }
        // on parcourt vers haut gauche
        line = pos.getAbs() + 1;
        col = pos.getOrd() - 1;
        nextPos = true;
        while (nextPos && line < NB_LIGNES && col >= 0) {
            Position nPos = new Position(line, col);
            if (getJeton(nPos) != null && getJeton(nPos).getCouleur() == couleur) {
                // jeton de même couleur, on incrémente
                nbJeton++;
                // on en haut droite
                col--;
                line++;
            } else {
                // pas de la même couleur, on arrête
                nextPos = false;
            }
        }
        dg1Align = (nbJeton >= 4);
        return dg1Align;
    }

    /**
     * Override de toString permettant l'affichage de la grille de jeu et des jetons joués.
     * @return la grille sous forme de chaine de caractères.
     */
    @Override
    public String toString() {
        String print;
        // haut du plateau
        print = "----------------------------\n";
        // Parcours des lignes
        for (int i = plateauJetons.length - 1; i >= 0; i--) { // il faut afficher le tableau à l'envers car la ligne 0 est celle du bas !
            print += "|";
            // parcours des colonnes
            for (int j = 0, len = plateauJetons[i].length; j < len; j++) {
                // Affichage jeton
                if (plateauJetons[i][j] == null) {
                    // vide
                    print += "   ";
                } else {
                    // jeton dans sa couleur
                    if (plateauJetons[i][j].getCouleur().toString().equals("JAUNE")) {
                        print += "\033[43m J \033[0m";
                    } else {
                        print += "\033[37;41m R \033[0m";
                    }
                }
                print += "|";
            }
            print += "\n";
        }
        // fin du plateau
        print += "----------------------------\n";
        print += "  1   2   3   4   5   6   7  ";
        return print;
    }

}
