/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modele.Commande;
import modele.CommandeDao;
import modele.Produit;
import modele.ProduitDao;
import vue.CommandeVue;
import vue.LigneCommande;

/**
 *
 * @author Formation
 */
public class ControleurCommande implements ActionListener, MouseListener {

    private CommandeVue cmdVue;
    private CommandeDao cmdDao;
    private DefaultTableModel modelCmd, modelLigneCmd;
    private LigneCommande ligneVue;
    private ProduitDao prodDao;
    

    public ControleurCommande() {
       
        this.cmdVue = new CommandeVue();
        this.cmdDao = new CommandeDao();
        this.ligneVue = new LigneCommande();
        this.prodDao = new ProduitDao();
        
        this.cmdVue.getTxtDateCommande().setText(getDateJour());
        nextId();
        initModelCmd();
        addListerner();
        initBtnDefaut();
        ajoutProduit();
        this.cmdVue.setVisible(true);
    }

    public String getDateJour() {
        Date aujourdhui = new Date();
        SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("dd/MM/Y");
        return formater.format(aujourdhui);
    }

    public void addListerner() {
        this.cmdVue.getBtnAjouterProdCmd().addActionListener(this);
        this.cmdVue.getBtnSupCmd().addActionListener(this);
        this.cmdVue.getBtnCreerCmd().addActionListener(this);
        this.cmdVue.getBtnResetCmd().addActionListener(this);
        this.cmdVue.getTableListeCmd().addMouseListener(this);

        this.ligneVue.getComboxProduit().addActionListener(this);
        this.ligneVue.getBtnAjoutProduit().addActionListener(this);
        this.ligneVue.getBtnModifierProduit().addActionListener(this);
        this.ligneVue.getBtnSupprimerProduit().addActionListener(this);
        this.ligneVue.getBtnResetProduit().addActionListener(this);

    }

    public void initBtnDefaut() {
        cmdVue.getBtnAjouterProdCmd().setEnabled(false);
        cmdVue.getBtnSupCmd().setEnabled(false);
        cmdVue.getBtnCreerCmd().setEnabled(true);
    }

    public void initBtnModif() {
        cmdVue.getBtnAjouterProdCmd().setEnabled(true);
        cmdVue.getBtnSupCmd().setEnabled(true);
        cmdVue.getBtnCreerCmd().setEnabled(false);
    }

    public void initModelCmd() {
        modelCmd = new DefaultTableModel();
        //création du modele catégorie
        //Ajout des Colonnes du dodele Catégorie
        modelCmd.addColumn("ID Cmd");
        modelCmd.addColumn("Nom Client");
        modelCmd.addColumn("Date Commande");
        modelCmd.addColumn("Nb de produits");

        //inserer les lignes dans le medele cat
        List<Commande> allCmd = new ArrayList<>();
        allCmd = this.cmdDao.getAllCommande();

        for (Commande cmd : allCmd) {
            modelCmd.addRow(new Object[]{cmd.getIdCmd(),
                cmd.getNomClient(), cmd.getDate(), cmd.getListProd().size()});
        }
        this.cmdVue.getTableListeCmd().setModel(modelCmd);

    }

    /**
     * Cette Méthode permet de charger le combobox avec la liste des catégories
     * de produit
     */
    public void ajoutProduit() {
        List<Produit> listeProd = this.prodDao.getAllProduit();
        for (Produit prod : listeProd) {
            this.ligneVue.getComboxProduit().addItem(prod.getIdProd() + " " + prod.getNomProd());

        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource().equals(this.cmdVue.getBtnSupCmd())) {
            this.cmdDao.supprimerCommande(new Commande(Integer.parseInt(this.cmdVue.getTxtIdCmd().getText())));
            initModelCmd();
        }

        if (ae.getSource().equals(this.cmdVue.getBtnCreerCmd())) {
            Commande cmd = new Commande();
            cmd.setIdCmd(Integer.parseInt(this.cmdVue.getTxtIdCmd().getText()));
            cmd.setNomClient(this.cmdVue.getTxtNomClient().getText());
            cmd.setDate(this.cmdVue.getTxtDateCommande().getText());
            this.cmdDao.ajouterCommande(cmd);
            this.ligneVue.setVisible(true);

            nextId();
            initModelCmd();
            initBtnModif();
        }

        if (ae.getSource().equals(this.cmdVue.getBtnResetCmd())) {
            initBtnDefaut();
            this.cmdVue.getTxtDateCommande().setText(getDateJour());
            this.cmdVue.getTxtNomClient().setText("");
            nextId();
        }

        if (ae.getSource().equals(this.cmdVue.getBtnAjouterProdCmd())) {
            this.ligneVue.setVisible(true);

            nextId();
        }

        if (ae.getSource().equals(this.ligneVue.getComboxProduit())) {

            String chaine = this.ligneVue.getComboxProduit().getSelectedItem().toString();
            // cree un objet pour recuperer id + libelle grace a la methode getOneProduit
            Produit prod = this.prodDao.getOneProduit(findIdProd(chaine));
            this.ligneVue.getTxtPrixUnitaire().setText(Double.toString(prod.getPrixProd()));
            this.ligneVue.getTxtCatProd().setText(prod.getCatProd().getLibelle());
        }

        if (ae.getSource().equals(this.ligneVue.getBtnSupprimerProduit())) {
            System.out.println("id commande" + this.ligneVue.getTxtLigneIdCmd().getText());
            Commande cmd = this.cmdDao.getOneCommande(Integer.parseInt(this.ligneVue.getTxtLigneIdCmd().getText()));
            // ici on recupere ID 
            int ligneProduit = this.ligneVue.getListeLigneproduits().getSelectedRow();
            int idprod = (int) this.modelLigneCmd.getValueAt(ligneProduit, 0);
            Produit prod = new Produit();

            prod.setIdProd(idprod);

            System.out.println("ligne" + ligneProduit);
            System.out.println("id prod" + idprod);

            this.cmdDao.supprimerProdCommande(cmd, prod);

            initTableauProd(cmd.getIdCmd());
                      
          //  this.ligneVue.getListeLigneproduits().setModel(modelLigneCmd);
                       }
        
        if (ae.getSource().equals(this.ligneVue.getBtnAjoutProduit())) {

            Commande cmd = this.cmdDao.getOneCommande(Integer.parseInt(this.ligneVue.getTxtLigneIdCmd().getText()));

            this.ligneVue.getComboxProduit().getSelectedItem();
            String chaine = this.ligneVue.getComboxProduit().getSelectedItem().toString();
            // cree un objet pour recuperer id + libelle grace a la methode getOneProduit
            Produit prod = this.prodDao.getOneProduit(findIdProd(chaine));

            List<BasicDBObject> listedesprod = new ArrayList();

            // for (BasicDBObject p : cmd.getListProd()) {
            System.out.println("avant :" + cmd.getListProd());

            for (int i = 0; i < cmd.getListProd().size(); i++) {
                BasicDBObject p = cmd.getListProd().get(i);
                if (p.get("_id").equals(prod.getIdProd())) {

                    int a = (Integer) (p.get("qteCmd"));
                    int b = Integer.parseInt(this.ligneVue.getTxtQuantite().getText());
                    int c = a + b;

                    p.replace("qteCmd", c);

                    // Produit pNew = new Produit();
                    //pNew.setIdProd((int)p.get("_id"));
                    prod.setQteProd((int) p.get("qteCmd"));
                    // pNew.setQteProd((int)p.get("qteCmd"));

                    initTableauProd(cmd.getIdCmd());
                    System.out.println("saisie" + this.ligneVue.getTxtQuantite().getText());
                    System.out.println("ancien" + p.get("qteCmd"));
                    this.prodDao.updateProduit(prod);
                    //listedesprod.add(p);
                    this.cmdDao.modifierProdCommande(cmd, prod);
                    initTableauProd(cmd.getIdCmd());

                } else {
                    listedesprod.add(p);
                    cmd.setListProd(listedesprod);
                    this.cmdDao.ajouterProdCommande(cmd, prod);
                    initTableauProd(cmd.getIdCmd());
                }

                System.out.println("apres :" + listedesprod);

                
                

               

            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int ligne = this.cmdVue.getTableListeCmd().getSelectedRow();
        this.cmdVue.getTxtIdCmd().setText((modelCmd.getValueAt(ligne, 0).toString()));
        this.cmdVue.getTxtNomClient().setText(modelCmd.getValueAt(ligne, 1).toString());
        this.cmdVue.getTxtDateCommande().setText(modelCmd.getValueAt(ligne, 2).toString());

        initBtnModif();

        int ligneProduit = this.ligneVue.getListeLigneproduits().getSelectedRow();

        this.ligneVue.getTxtLigneIdCmd().setText((modelCmd.getValueAt(ligne, 0).toString()));
        this.ligneVue.getTxtLigneNomClient().setText(modelCmd.getValueAt(ligne, 1).toString());
        this.ligneVue.getTxtLigneDateCmd().setText(modelCmd.getValueAt(ligne, 2).toString());
        int idCmd = Integer.parseInt(modelCmd.getValueAt(ligne, 0).toString());

        initTableauProd(idCmd);

        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void initTableauProd(int idCmd){      
      /**
         * ******** modele ligne Commande *********
         */
        modelLigneCmd = new DefaultTableModel();

        //création du modele catégorie
        //Ajout des Colonnes du dodele Catégorie
        modelLigneCmd.addColumn("ID PROD");
        modelLigneCmd.addColumn("Nom PRODUIT");
        modelLigneCmd.addColumn("Qte Commandée");
        modelLigneCmd.addColumn("Catégorie");

        //id de la commande selectionnee
        //int idCmd = Integer.parseInt(modelCmd.getValueAt(ligne, 0).toString());
        Commande Cmd = this.cmdDao.getOneCommande(idCmd);
        for (BasicDBObject obj : Cmd.getListProd()) {
            DBObject objCat =(DBObject)obj.get("catégorie");
            modelLigneCmd.addRow(new Object[]{obj.get("_id"),
                obj.get("nomProd"), obj.get("qteCmd"), objCat.get("libelle")});
        }
        this.ligneVue.getListeLigneproduits().setModel(modelLigneCmd);
              
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
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * cette méthode retourne l'id max de la collection Produit
     *
     * @return
     */
    public int maxId() {
        List<Commande> listeCmd = this.cmdDao.getAllCommande();
        List<Integer> listeId = new ArrayList<>();

        for (Commande cmd : listeCmd) {
            listeId.add(cmd.getIdCmd());
        }
        if (listeId.isEmpty()) {
            listeId.add(0);
        }
        return Collections.max(listeId);
    }

     
  
    /**
     * cette méthode met à jour le champs idProd avec l'id max +1 de la base
     */
    public void nextId() {
        cmdVue.getTxtIdCmd().setText(Integer.toString(maxId() + 1));
    }
    
      /**
     * Cette méthode récupère l'idCat dans la chaine de caractère formée de 
     * idcat et du libelle
     * @param chaine
     * @return 
     */
    public int findIdProd(String chaine){
        String [] tabIdProd = chaine.split(" ");  
        return Integer.parseInt(tabIdProd[0]);
    }
}
