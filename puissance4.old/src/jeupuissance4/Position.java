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
public class Position {
    private int ligne;
    private int colonne;

    public Position(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }
    @Override
    public String toString() {
        return "Position{" + "ligne=" + ligne + ", colonne=" + colonne + '}';
    }   
}
