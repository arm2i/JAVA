package business;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * définit une position dans la grille avec son ordonnée et son absisse.
 *
 * @author arhgi128.
 */
public class Position {
    private int ligne;
    private int colonne;

    /**
     * création d'une position.
     * @param ligne (ou absisse).
     * @param colonne (ou ordonnée).
     */
    public Position(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     * 
     * @return le numéro de ligne (absisse) de la position.
     */
    public int getAbs() {
        return ligne;
    }

    /**
     * 
     * @return le numéro de colonne (ou ordonnée) de la position.
     */
    public int getOrd() {
        return colonne;
    }

    @Override
    public String toString() {
        return "Position{" + "ligne=" + ligne + ", colonne=" + colonne + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.ligne != other.ligne) {
            return false;
        }
        if (this.colonne != other.colonne) {
            return false;
        }
        return true;
    }
    
}
