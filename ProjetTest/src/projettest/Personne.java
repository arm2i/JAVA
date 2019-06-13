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
public class Personne extends AfficheAjoutSup{
    protected int numero;
    private String nom;
    protected Adresse adresse;

    public Personne(int numero, String nom, Adresse adresse) {
        this.numero = numero;
        this.nom = nom;
        this.adresse = adresse;
    }

    public Personne(){
        
    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) throws Puissance4Exception{
        if(numero<0) 
            throw new Puissance4Exception("valeur négative");
        
        
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Personne{" + "numero=" + numero + ", nom=" + nom + ", adresse=" + adresse + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
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
        final Personne other = (Personne) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        return true;
    }

    @Override
    public void afficher() {
        System.out.println("Je suis la classe mère");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
