/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Formation
 */
public class Produit {
    private int idProd;
    private String nomProd;
    private String descriptionProd;
    private double prixProd;
    private int qteProd;
    private Categorie catProd;

    @Override
    public String toString() {
        return "Produit{" + "idProd=" + idProd + ", nomProd=" + nomProd + ", descriptionProd=" + descriptionProd + ", prixProd=" + prixProd + ", qteProd=" + qteProd + ", catProd=" + catProd + '}';
    }

    public Produit(int idProd, String nomProd, String descriptionProd, double prixProd, int qteProd, Categorie catProd) {
        this.idProd = idProd;
        this.nomProd = nomProd;
        this.descriptionProd = descriptionProd;
        this.prixProd = prixProd;
        this.qteProd = qteProd;
        this.catProd = catProd;
    }

    public Produit() {
        catProd = new Categorie();
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getDescriptionProd() {
        return descriptionProd;
    }

    public void setDescriptionProd(String descriptionProd) {
        this.descriptionProd = descriptionProd;
    }

    public double getPrixProd() {
        return prixProd;
    }

    public void setPrixProd(double prixProd) {
        this.prixProd = prixProd;
    }

    public int getQteProd() {
        return qteProd;
    }

    public void setQteProd(int qteProd) {
        this.qteProd = qteProd;
    }

    public Categorie getCatProd() {
        return catProd;
    }

    public void setCatProd(Categorie catProd) {
        this.catProd = catProd;
    }
    
    
    
}
