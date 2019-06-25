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
import java.util.ArrayList;
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
public class ControleurCategorie implements ActionListener, MouseListener {

    private CategorieDao catDao;
    private CategorieVue catVue;
    private PrincipaleVue principaleVue;
    private DefaultTableModel modelCat;

    public ControleurCategorie() {
        catVue = new CategorieVue();
        catDao = new CategorieDao();
        principaleVue = new PrincipaleVue();

        init();
        
        catVue.getBtnAjouter().setEnabled(true);
        catVue.getBtnModifier().setEnabled(false);
        catVue.getBtnSupprimer().setEnabled(false);
                
        addListener();
        
        principaleVue.setVisible(true);
        //catVue.setVisible(true);

    }
    public void addListener(){
        catVue.getBtnAjouter().addActionListener(this);
        catVue.getBtnSupprimer().addActionListener(this);
        catVue.getBtnModifier().addActionListener(this);
        catVue.getBtnReset().addActionListener(this);
        catVue.getjTable1().addMouseListener(this); 
        principaleVue.getMenuCat().addActionListener(this);
    }

    public ControleurCategorie(CategorieDao catDao, CategorieVue catVue) {
        this.catDao = catDao;
        this.catVue = catVue;

    }

    public ControleurCategorie(CategorieVue catVue) {
        this.catVue = catVue;
        this.catDao = new CategorieDao();
    }

    public void init() {
        //création du modele catégorie
        modelCat = new DefaultTableModel();
        //Ajout des Colonnes du dodele Catégorie
        modelCat.addColumn("ID Catégorie");
        modelCat.addColumn("Libelle");
        //inserer les lignes dans le medele cat
        List<Categorie> allCat = new ArrayList<>();

        allCat = this.catDao.getAllCategorie();

        for (Categorie cat : allCat) {
            modelCat.addRow(new Object[]{cat.getIdCat(), cat.getLibelle()});
        }
        catVue.getjTable1().setModel(modelCat);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(this.catVue.getBtnAjouter())) {
            Categorie cat = new Categorie();
            cat.setLibelle(this.catVue.getTxtLibelle().getText());

            catDao.addCategorie(cat);

            JOptionPane.showMessageDialog(null, "Enregistrement effectué avec succès");
            //vider le champs libelle
            this.catVue.getTxtLibelle().setText("");
            init();
        }
        if (e.getSource().equals(this.catVue.getBtnSupprimer())) {
            Categorie cat = new Categorie();
            cat.setIdCat(Integer.parseInt(this.catVue.getTxtIdCat().getText()));
            catDao.deleteCategorie(cat);
            JOptionPane.showMessageDialog(null, "opération effectuée avec succès");
            //vider le champs libelle
            this.catVue.getTxtLibelle().setText("");
            init();
        }
        if (e.getSource().equals(this.catVue.getBtnModifier())) {
            Categorie cat = new Categorie();
            cat.setIdCat(Integer.parseInt(this.catVue.getTxtIdCat().getText()));
            cat.setLibelle(this.catVue.getTxtLibelle().getText());
            catDao.updateCategorie(cat);
            JOptionPane.showMessageDialog(null, "opération effectuée avec succès");
            //vider le champs libelle
            this.catVue.getTxtLibelle().setText("");
            init();
        }
        if (e.getSource().equals(this.catVue.getBtnReset())) {
            catVue.getBtnAjouter().setEnabled(true);
            catVue.getBtnModifier().setEnabled(false);
            catVue.getBtnSupprimer().setEnabled(false);
            catVue.getTxtIdCat().setText("");
            catVue.getTxtLibelle().setText("");
        }
        if(e.getSource().equals(this.principaleVue.getMenuCat())){
            this.catVue.setVisible(true);
        }

// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int ligne = this.catVue.getjTable1().getSelectedRow();

        this.catVue.getTxtIdCat().setText(modelCat.getValueAt(ligne, 0).toString());
        this.catVue.getTxtLibelle().setText(modelCat.getValueAt(ligne, 1).toString());
        
        catVue.getBtnAjouter().setEnabled(false);
        catVue.getBtnModifier().setEnabled(true);
        catVue.getBtnSupprimer().setEnabled(true);

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
