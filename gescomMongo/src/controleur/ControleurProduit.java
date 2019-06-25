/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modele.Categorie;
import modele.CategorieDao;
import modele.Produit;
import modele.ProduitDao;
import vue.ProduitVue;

/**
 *
 * @author Formation
 */
public class ControleurProduit implements ActionListener{
    private ProduitDao prodDao;
    private ProduitVue prodVue;
    private CategorieDao catDao;
    private DefaultTableModel modelProd;

    public ControleurProduit() {
        prodDao = new ProduitDao();
        prodVue = new ProduitVue();
        catDao = new CategorieDao();
        modelProd = new DefaultTableModel();   
        
        ajoutCategorie();
        initModelProd();
        addListerner();
        prodVue.setVisible(true);
    }
    
    public void addListerner(){
        this.prodVue.getBtnAjouterProd().addActionListener(this);
    }
    /**
     * Cette Méthode permet de charger le combobox avec la liste des 
     * catégories de produit
     */
    public void ajoutCategorie(){
        List<Categorie> listeCat = this.catDao.getAllCategorie();      
        for(Categorie cat : listeCat){
            this.prodVue.getComboCat().addItem(cat.getIdCat() +" "+ cat.getLibelle());
        }
    }
    /**
     * Cette méthode récupère l'idCat dans la chaine de caractère formée de 
     * idcat et du libelle
     * @param chaine
     * @return 
     */
    public int findIdCat(String chaine){
        String [] tabIdCat = chaine.split(" ");  
        return Integer.parseInt(tabIdCat[0]);
    }
    /**
     * 
     */
    public void initModelProd(){
          //création du modele catégorie
        //Ajout des Colonnes du dodele Catégorie
        modelProd.addColumn("ID Prod");
        modelProd.addColumn("Nom");
        modelProd.addColumn("Description");
        modelProd.addColumn("Prix");
        modelProd.addColumn("Qte");
        modelProd.addColumn("Cat");
        //inserer les lignes dans le medele cat
        List<Produit> allProd = new ArrayList<>();
        allProd = this.prodDao.getAllProduit();
        
        for (Produit prod : allProd) {
            modelProd.addRow(new Object[]{prod.getIdProd(),
                prod.getNomProd(),prod.getDescriptionProd(),prod.getPrixProd(),
            prod.getQteProd(),prod.getCatProd().getIdCat()});
        }
        this.prodVue.getTableListeProd().setModel(modelProd);     
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(this.prodVue.getBtnAjouterProd())){
            Produit prod = new Produit();
            prod.setNomProd(this.prodVue.getTxtNomProd().getText());
            prod.setDescriptionProd(this.prodVue.getTxtDescriptionProd().getText());
            prod.setPrixProd(Double.parseDouble(this.prodVue.getTxtPrixProd().getText()));           
            prod.setQteProd(Integer.parseInt(this.prodVue.getTxtPrixProd().getText())); 
            
            String chaine = this.prodVue.getComboCat().getSelectedItem().toString();
            prod.setCatProd(new Categorie(findIdCat(chaine)));
            
            this.prodDao.addProduit(prod);
        }
        
           }
    
}
