/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projettest;

import java.util.Objects;

/**
 *
 * @author Formation
 */
public class Etudiant extends Personne implements AjoutSup{

    private int anneeEtude;
    private boolean doubleur;
    private boolean ancien;

    public Etudiant(int anneeEtude, boolean doubleur, boolean ancien, int numero, String nom, Adresse adresse) {
         super(numero, nom, adresse);
        this.anneeEtude = anneeEtude;
        this.doubleur = doubleur;
        this.ancien = ancien;
       
    }
    public Etudiant(){

    }

    public int getAnneeEtude() {
        return anneeEtude;
    }

    public void setAnneeEtude(int anneeEtude) {
        this.anneeEtude = anneeEtude;
    }

    public boolean isDoubleur() {
        return doubleur;
    }

    public void setDoubleur(boolean doubleur) {
        this.doubleur = doubleur;
    }

    public boolean isAncien() {
        return ancien;
    }

    public void setAncien(boolean ancien) {
        this.ancien = ancien;
    }

    @Override
    public String toString() {
        return "Etudiant{"+"nom="+super.getNom() + " anneeEtude=" + anneeEtude + ", doubleur=" + doubleur + ", ancien=" + ancien + '}';
    }

    @Override
    public void ajouter() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   @Override
    public void afficher() {
        System.out.println("je suis l'étudiant");
    }



    
    }
    
    


