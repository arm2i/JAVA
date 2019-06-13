package puissance4.model;

import java.util.Observable;

/**
 *
 * @author G37269
 */
public class Grid extends Observable {

    public int LIGN_NUMBER = Config.LIGN_NUMBER;
    public int COLUMN_NUMBER = Config.COLUMN_NUMBER;
    private Token[][] plateauToken;

    /**
     * crée le tableau de Jeton contenant 6 lignes et 7 colonne
     */
    public Grid() {
        plateauToken = new Token[LIGN_NUMBER][COLUMN_NUMBER];

        for (Token[] plateauToken1 : plateauToken) {
            for (int j = 0; j < plateauToken1.length; j++) {
                plateauToken1[j] = null;
            }
        }

    }

    /**
     * Affiche de manière lisible la grille
     *
     * @return la grille
     */
    @Override
    public String toString() {
        String chain = "";

        for (Token[] plateauToken1 : plateauToken) {
            for (Token plateauToken11 : plateauToken1) {
                if (plateauToken11 == null) {
                    //System.out.println(" 0 ");
                    chain += " 0 \n";
                    chain += "| ";
                    //System.out.print("| ");
                } else {
                    switch (plateauToken11.getColor()) {
                        //case null: System.out.print(" ");break;
                        case RED:
                            chain += "   R   ";
                            chain += "| ";
                            //System.out.print("   R   ");
                            //System.out.print("| ");
                            break;
                        case YELLOW:
                            chain += "   J   ";
                            chain += "| ";
                            // System.out.print("   J   ");
                            //System.out.print("| ");
                            break;
                        default:
                            chain += "   -   ";
                            //es=chain +"| ";
                            // System.out.print("   -   ");
                            break;
                    }
                }
            }
            //System.out.println(" ");
            chain += "  \n";
        }
        //es = plateauToken.toString();

        return chain;

    }

    /**
     * Affiche le tableau de jetons
     *
     * @return le tableau de Jeton
     */
    public Token[][] getPlateauToken() {
        return plateauToken;
    }

    /**
     * Initialise la grille avec un tableau de jeton
     *
     * @param plateauToken type Jeton
     */
    public Grid(Token[][] plateauToken) {
        this.plateauToken = plateauToken;

    }

    /**
     * Retourne le jetons à la position correspondante sur la grille
     *
     * @param position position du jeton sur la grille
     * @return null si la position est libre
     * @throws IllegalArgumentException si la position est invalide
     */
    public Token getToken(Position position) {
        if (position.getLine() < 0 || position.getLine() >= LIGN_NUMBER || position.getColumn() < 0 || position.getColumn() >= COLUMN_NUMBER) {
            throw new IllegalArgumentException("position invalide");
        }
        return plateauToken[position.getLine()][position.getColumn()];

    }

    /**
     * Teste si une colonne de la grille est vide
     *
     * @param numColonne indice de la colonne qu'on veut tester sur la grille
     * @return vrai si la grille n'est pas pleine et faux si la grille est
     * pleine
     * @throws IllegalArgumentException si l'indice de la colonne est invalide
     */
    public boolean isFullColonne(int numColonne) {

        if (numColonne < 0 || numColonne >= COLUMN_NUMBER) {
            throw new IllegalArgumentException("invalide column");
        }
        return plateauToken[0][numColonne] != null;
    }

    /**
     * Teste si la grille est pleine
     *
     * @return vrai si la grille est pleine et faux dans le cas contraire
     */
    public boolean isFullGrille() {
        int j=0;
       // System.out.println(j);
        while(plateauToken[0][j] != null && j <= COLUMN_NUMBER){
            j+=1;
        }
        if(j==COLUMN_NUMBER){
            //System.out.println("in true" +j);
            return true;
        }else{
            //System.out.println("in false" +j);
        return false;
        }
    }

    /**
     * Insere un jetons dans la grille à une position précise
     *
     * @param jeton type Jeton qui pricise la color1 du jeton
     * @param numColonne entier indice de la colonne ou on veut inserer le jeton
     * @return retourne l'indice de la line ou le jeton a été inséré
     * @throws Puissance4Exception si la colonne est déja remplie
     * @throws IllegalArgumentException si l'indice de la colonne est invalide
     */
    public int insererJeton(Token jeton, int numColonne) throws Puissance4Exception /*throws Puissance4Exception*/ {
        if (numColonne < 0 || numColonne >= COLUMN_NUMBER) {
            throw new IllegalArgumentException("colonne invalide");
        }
        if (isFullColonne(numColonne) == true) {
            throw new Puissance4Exception("Colonne remplie");
        }
        int j = LIGN_NUMBER - 1;
        boolean isEmpty = false;

        while (j >= 0 && isEmpty == false) {
            if (plateauToken[j][numColonne] == null) {
                isEmpty = true;
                plateauToken[j][numColonne] = jeton;
                //	j=j-1;
            } else {
                isEmpty = false;
                //j=j-1;
            }
            j -= 1;
        }
        setChanged();
        notifyObservers();
        return j + 1;
    }

    /**
     * Teste si il existe un alignement valide de jetons
     *
     * @param position type Position
     * @return vrai si il y a un alignement de 4 jetons sur la vertical ou sur
     * l'horizontal ou sur les diagonales
     *
     */
    public boolean alignementRealise(Position position) {
        boolean isAlign = false;
        if (alignementHorizontal(position) >= Config.CaseAlign || alignementVertical(position) >= Config.CaseAlign || alignementDiagonal1(position) >= Config.CaseAlign || alignementDiagonal2(position) >= Config.CaseAlign) {
            isAlign = true;
        }
        return isAlign;
    }

    //  Cette méthode vérifie si 4 jetons de même color1 sont alignés dans le sens de l'horizontal
    private int alignementHorizontal(Position position) {
        Color color1;
        int line, colonne, nbAlignment;
        boolean sameColor;
        line = position.getLine();
        colonne = position.getColumn();
        color1 = plateauToken[line][colonne].getColor();
        nbAlignment = 1;
        sameColor = true;

        while (sameColor == true && colonne - 1 >= 0) {
            colonne -= 1;
            sameColor = (plateauToken[position.getLine()][colonne] != null && plateauToken[position.getLine()][colonne].getColor() == color1);
            if (sameColor == true) {
                nbAlignment += 1;
            }
        }

        colonne = position.getColumn();
        sameColor = true;

        while (sameColor == true && colonne + 1 < COLUMN_NUMBER) {
            colonne += 1;
            sameColor = (plateauToken[position.getLine()][colonne] != null && plateauToken[position.getLine()][colonne].getColor() == color1);
            if (sameColor == true) {
                nbAlignment += 1;
            }
        }
        return nbAlignment;
    }

    //  Cette méthode vérifie si 4 jetons de même color1 sont alignés dans le sens de la vertical
    private int alignementVertical(Position position) {
        Color color2;
        int line1, colonne, nbAlignement1;
        boolean sameColor2;
        line1 = position.getLine();
        colonne = position.getColumn();
        color2 = plateauToken[line1][colonne].getColor();
        nbAlignement1 = 1;
        sameColor2 = true;

        while (sameColor2 == true && line1 + 1 < LIGN_NUMBER) {
            line1 += 1;
            // System.out.println(line);
            sameColor2 = (plateauToken[line1][position.getColumn()] != null && plateauToken[line1][position.getColumn()].getColor() == color2);
            if (sameColor2 == true) {
                nbAlignement1 += 1;
            }
        }
        line1 = position.getLine();
        //colonne = position.getColumn();
        sameColor2 = true;

        while (sameColor2 == true && line1 - 1 >= 0) {
            line1 -= 1;
            sameColor2 = (plateauToken[line1][position.getColumn()] != null && plateauToken[line1][position.getColumn()].getColor() == color2);
            if (sameColor2 == true) {
                nbAlignement1 += 1;
            }
        }
        return nbAlignement1;
    }

    //  Cette méthode vérifie si 4 jetons de même color1 sont alignés dans le sens de la diagonal de gauche
    private int alignementDiagonal1(Position position) {
        Color color3;
        int line3, colonne, nbAlignement3;
        boolean sameColor3;
        line3 = position.getLine();
        colonne = position.getColumn();
        color3 = plateauToken[line3][colonne].getColor();
        nbAlignement3 = 1;
        sameColor3 = true;

        //Regardons vers le haut vers la gauche dans le sensi de la diagonale
        while (sameColor3 == true && line3 - 1 >= 0 && colonne - 1 >= 0) {
            line3 -= 1;
            colonne -= 1;
            sameColor3 = (plateauToken[line3][colonne] != null && plateauToken[line3][colonne].getColor() == color3);
            if (sameColor3 == true) {
                nbAlignement3 += 1;
            }
        }
        line3 = position.getLine();
        colonne = position.getColumn();
        sameColor3 = true;

        while (sameColor3 == true && line3 + 1 < LIGN_NUMBER && colonne + 1 < COLUMN_NUMBER) {
            line3 += 1;
            colonne += 1;
            sameColor3 = (plateauToken[line3][colonne] != null && plateauToken[line3][colonne].getColor() == color3);
            if (sameColor3 == true) {
                nbAlignement3 += 1;
            }
        }
        return nbAlignement3;
    }

    //  Cette méthode vérifie si 4 jetons de même color1 sont alignés dans le sens de la diagonal de droite
    private int alignementDiagonal2(Position position) {
        Color color4;
        int line4, colonne, nbAlignement4;
        boolean sameColor4;
        line4 = position.getLine();
        colonne = position.getColumn();
        color4 = plateauToken[line4][colonne].getColor();
        nbAlignement4 = 1;
        sameColor4 = true;

        //Regardons vers le haut vers la gauche dans le sens de la diagonale
        while (sameColor4 == true && line4 - 1 >= 0 && colonne + 1 < COLUMN_NUMBER) {
            line4 -= 1;
            colonne += 1;
            sameColor4 = (plateauToken[line4][colonne] != null && plateauToken[line4][colonne].getColor() == color4);
            if (sameColor4 == true) {
                nbAlignement4 += 1;
            }
        }
        line4 = position.getLine();
        colonne = position.getColumn();
        sameColor4 = true;

        //Regardons vers le bas dans le sens de la diagonale
        while (sameColor4 == true && line4 + 1 < LIGN_NUMBER && colonne - 1 >= 0) {
            line4 += 1;
            colonne -= 1;
            sameColor4 = (plateauToken[line4][colonne] != null && plateauToken[line4][colonne].getColor() == color4);
            if (sameColor4 == true) {
                nbAlignement4 += 1;
            }
        }
        return nbAlignement4;
    }
}
