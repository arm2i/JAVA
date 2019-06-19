/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modele.Categorie;
import modele.CategorieDao;
import vue.CategorieVue;
import vue.PrincipaleVue;


/**
 *
 * @author Formation
 */
public class ControleurCategorie implements ActionListener,MouseListener{
    private CategorieDao catDao;
    private CategorieVue catVue;
    private DefaultTableModel modelCat;
     private PrincipaleVue principaleVue;

    public ControleurCategorie() {
        catVue = new CategorieVue();
        catDao = new CategorieDao();
        principaleVue = new PrincipaleVue();
        init();
        catVue.getBtnAjouter().addActionListener(this);
        catVue.getBtnSupprimer().addActionListener(this);
        catVue.getBtnModifier().addActionListener(this);
        // ecouteur sur le tableau        
        catVue.getListeTableau().addMouseListener(this);
       
        
        principaleVue.setVisible(true);
    
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
    
        // BOUTON AJOUTER
        if(ae.getSource().equals(this.catVue.getBtnAjouter())){
            Categorie cat = new Categorie();
            String champ = this.catVue.getTxtLibelle().getText();
            if(champ.equals("")){JOptionPane.showMessageDialog(null,"Labelle vide");}
            else{cat.setLibelle(this.catVue.getTxtLibelle().getText());
            catDao.addCategorie(cat);
            
            JOptionPane.showMessageDialog(null,"Enregistrement effectué avec succès");
            // vider le champs libelle
            this.catVue.getTxtLibelle().setText("");}
            init();
          }
        
        // BOUTON SUPPRIMER
         if(ae.getSource().equals(this.catVue.getBtnSupprimer())){
             //ecoute sur le tableau
            catVue.getListeTableau().addMouseListener(this);
            
            Categorie cat = new Categorie();
            int ligne=this.catVue.getListeTableau().getSelectedRow();
            this.catVue.getTxtIdCat().setText(modelCat.getValueAt(ligne, 0).toString());
            this.catVue.getTxtLibelle().setText(modelCat.getValueAt(ligne, 1).toString());
            int idSelect = Integer.parseInt(modelCat.getValueAt(ligne, 0).toString());
            cat.setIdCat(idSelect);
            catDao.deleteCategorie(cat);
            JOptionPane.showMessageDialog(null,"Catégorie id= "+idSelect+ "Supprimé!");
                      
            // vider le champs libelle
            this.catVue.getTxtLibelle().setText("");
            init();
          }
         
         
          // BOUTON MODIFIER
         if(ae.getSource().equals(this.catVue.getBtnModifier())){
             //ecoute sur le tableau
                      
            Categorie cat = new Categorie();
            cat.setLibelle(this.catVue.getTxtLibelle().getText());
            cat.setIdCat(Integer.parseInt(this.catVue.getTxtIdCat().getText()));
            String champ = this.catVue.getTxtLibelle().getText();
            if(champ.equals("")){
                JOptionPane.showMessageDialog(null,"Labelle vide");
                champ="";
            }
            else{catDao.updateCategorie(cat);
            JOptionPane.showMessageDialog(null,"Labelle modifié ");}
            
            // vider le champs libelle
            this.catVue.getTxtLibelle().setText("");
            init();
          }

    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        
        int ligne = this.catVue.getListeTableau().getSelectedRow();
        this.catVue.getTxtIdCat().setText(modelCat.getValueAt(ligne, 0).toString());
        this.catVue.getTxtLibelle().setText(modelCat.getValueAt(ligne, 1).toString());
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
