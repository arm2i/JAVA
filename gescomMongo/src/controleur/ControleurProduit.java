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
import java.util.Collections;
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
public class ControleurProduit implements ActionListener, MouseListener{
    private ProduitDao prodDao;
    private ProduitVue prodVue;
    private CategorieDao catDao;
    private DefaultTableModel modelProd;

    public ControleurProduit() {
        prodDao = new ProduitDao();
        prodVue = new ProduitVue();
        catDao = new CategorieDao();
        //modelProd = new DefaultTableModel();   
        
        ajoutCategorie();
        initModelProd();
        addListerner();
        nextId();
        prodVue.setVisible(true);
    }
    
    public void addListerner(){
        this.prodVue.getBtnAjouterProd().addActionListener(this);
        this.prodVue.getBtnSupprimerProd().addActionListener(this);
        this.prodVue.getBtnModifierProd().addActionListener(this);
        this.prodVue.getBtnResetProd().addActionListener(this);
        this.prodVue.getTableListeProd().addMouseListener(this);
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
        
        modelProd = new DefaultTableModel();
        
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
            
            // ici on recupere ID 
            prod.setIdProd(Integer.parseInt(this.prodVue.getTxtIdProd().getText()));
            
            prod.setNomProd(this.prodVue.getTxtNomProd().getText());
            prod.setDescriptionProd(this.prodVue.getTxtDescriptionProd().getText());
            prod.setPrixProd(Double.parseDouble(this.prodVue.getTxtPrixProd().getText()));         
            prod.setQteProd(Integer.parseInt(this.prodVue.getTxtPrixProd().getText())); 
            
            String chaine = this.prodVue.getComboCat().getSelectedItem().toString();
            
            // cree un objet pour recuperer id + libelle grace a la metode getOneCategorie
            Categorie cat = this.catDao.getOneCategorie(findIdCat(chaine));
            
            prod.setCatProd(cat);
            
            this.prodDao.addProduit(prod);
            initModelProd();
        }
        if(e.getSource().equals(this.prodVue.getBtnSupprimerProd())){
            Produit prod = new Produit();
            // ici on recupere ID 
            prod.setIdProd(Integer.parseInt(this.prodVue.getTxtIdProd().getText()));
            this.prodDao.deleteProduit(prod);
            initModelProd();
        
        
           }
        
        if(e.getSource().equals(this.prodVue.getBtnModifierProd())){
            Produit prod = new Produit();

            // ici on recupere ID 
            prod.setIdProd(Integer.parseInt(this.prodVue.getTxtIdProd().getText()));

            prod.setNomProd(this.prodVue.getTxtNomProd().getText());
            prod.setDescriptionProd(this.prodVue.getTxtDescriptionProd().getText());
            prod.setPrixProd(Double.parseDouble(this.prodVue.getTxtPrixProd().getText()));
            prod.setQteProd(Integer.parseInt(this.prodVue.getTxtQteProd().getText()));

            String chaine = this.prodVue.getComboCat().getSelectedItem().toString();

            // cree un objet pour recuperer id + libelle grace a la metode getOneCategorie
            Categorie cat = this.catDao.getOneCategorie(findIdCat(chaine));

            prod.setCatProd(cat);

            this.prodDao.updateProduit(prod);
            initModelProd();
        }
        
        if (e.getSource().equals(this.prodVue.getBtnResetProd())) {
            prodVue.getBtnAjouterProd().setEnabled(true);
            prodVue.getBtnModifierProd().setEnabled(false);
            prodVue.getBtnSupprimerProd().setEnabled(false);
            prodVue.getTxtIdProd().setText("");
            prodVue.getTxtNomProd().setText("");
            prodVue.getTxtPrixProd().setText("");
            prodVue.getTxtDescriptionProd().setText("");
            prodVue.getTxtQteProd().setText("");
            nextId();
        }


    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int ligne = this.prodVue.getTableListeProd().getSelectedRow();

     this.prodVue.getTxtIdProd().setText((modelProd.getValueAt(ligne, 0).toString()));
      this.prodVue.getTxtNomProd().setText(modelProd.getValueAt(ligne, 1).toString());
       this.prodVue.getTxtDescriptionProd().setText(modelProd.getValueAt(ligne, 2).toString()); 
       this.prodVue.getTxtPrixProd().setText(modelProd.getValueAt(ligne, 3).toString());
       this.prodVue.getTxtQteProd().setText(modelProd.getValueAt(ligne, 4).toString());
       
       prodVue.getBtnAjouterProd().setEnabled(false);
       prodVue.getBtnModifierProd().setEnabled(true);
       prodVue.getBtnSupprimerProd().setEnabled(true);

        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * cette méthode retourne l'id max de la collection Produit
     * @return 
     */
    public int maxId() {
        List<Produit> listeProd = this.prodDao.getAllProduit();
        List<Integer> listeId = new ArrayList<>();

        for (Produit prod : listeProd) {
            listeId.add(prod.getIdProd());
        }
        if (listeId.isEmpty()) {
            listeId.add(0);
        }
        return Collections.max(listeId);
    }
/**
     * cette méthode met à jour le champs idProd avec l'id max +1 de la base
     */
    public void nextId(){
         prodVue.getTxtIdProd().setText(Integer.toString(maxId()+1));
    }
    
}
