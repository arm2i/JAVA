/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modele.Categorie;
import modele.CategorieDao;
import vue.CategorieVue;

/**
 *
 * @author Formation
 */
public class ControleurCategorie implements ActionListener{
    private CategorieDao catDao;
    private CategorieVue catVue;
    private DefaultTableModel modelCat;

    public ControleurCategorie() {
        catVue = new CategorieVue();
        catDao = new CategorieDao();
        init();
        catVue.getBtnAjouter().addActionListener(this);
        catVue.setVisible(true);
    }

    public ControleurCategorie(CategorieDao catDao, CategorieVue catVue) {
        this.catDao = catDao;
        this.catVue = catVue;

    }

    public ControleurCategorie(CategorieVue catVue) {
        this.catVue = catVue;
        this.catDao = new CategorieDao();
    }
    
   
    public void init(){
           // création du modele cat
        modelCat = new DefaultTableModel();
        // Ajout de colonnes du model Cat
        modelCat.addColumn("ID Catégorie");
        modelCat.addColumn("Libellé");
        
        
        //inserer les lignes dans le model cat
        List<Categorie> allCat = this.catDao.getAllCategorie();
        
        for(Categorie cat : allCat){
            modelCat.addRow(new Object[]{cat.getIdCat(),cat.getLibelle()});
        }
        catVue.getListeTableau().setModel(modelCat);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    
        if(ae.getSource().equals(this.catVue.getBtnAjouter())){
            Categorie cat = new Categorie();
            cat.setLibelle(this.catVue.getTxtLibelle().getText());
            catDao.addCategorie(cat);
            
            JOptionPane.showMessageDialog(null,"Enregistrement effectué avec succès");
            // vider le champs libelle
            this.catVue.getTxtLibelle().setText("");
            init();
            
        }



    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
