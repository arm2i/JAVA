/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicLabelUI;

/**
 *
 * @author Formation
 */
public class CommandeDao implements CommandeInteface {

    private DB db;
    private DBCollection collectionCmd;

    public CommandeDao() {
        this.db = Connexion.getConnexion();
        this.collectionCmd = this.db.getCollection("commande");
    }

    @Override
    public void ajouterCommande(Commande cmd) {
        //List<BasicDBObject> listProCmd = new ArrayList<>();
        BasicDBObject docCmd = new BasicDBObject("_id", cmd.getIdCmd())
                .append("nomClient", cmd.getNomClient())
                .append("dateCmd", cmd.getDate())
                .append("contenir", cmd.getListProd());

        this.collectionCmd.insert(docCmd);

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerProdCommande(Commande cmd, Produit prod) {

        // creation de l'ancienne liste des produits de commande  
        BasicDBObject docCmdold = new BasicDBObject("_id", cmd.getIdCmd());
        //recherche de la commande
        DBObject obj = this.collectionCmd.findOne(docCmdold);
        //recuperation de la liste des produits de la commande
        BasicDBList listProd = (BasicDBList) obj.get("contenir");

        for (int i = 0; i < listProd.size(); i++) {
            DBObject objProd = (DBObject) listProd.get(i);
            if (objProd.get("_id").equals(prod.getIdProd())) {
                listProd.remove(i);
            }

        }
        //creation de la nouvelle commande
        BasicDBObject docCmdnew = new BasicDBObject("_id", cmd.getIdCmd())
                .append("nomClient", cmd.getNomClient())
                .append("dateCmd", cmd.getDate())
                .append("contenir", listProd);

        //mise à jour de la nouvelle commande
        this.collectionCmd.update(docCmdold, docCmdnew);
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterProdCommande(Commande cmd, Produit prod) {

        // creation de l'ancienne liste des produits de commande  
        BasicDBObject docCmdold = new BasicDBObject("_id", cmd.getIdCmd());
        //recherche de la commande
        DBObject obj = this.collectionCmd.findOne(docCmdold);
        //recuperation de la liste des produits de la commande
        BasicDBList listProd = (BasicDBList) obj.get("contenir");

        //System.out.println(listProd);
        //ajout du nouveau produit à la listProdCmd
        BasicDBObject newProd = new BasicDBObject("_id", prod.getIdProd())
                .append("nomProd", prod.getNomProd())
                .append("qteCmd", prod.getQteProd())
                .append("catégorie", new BasicDBObject("_id", prod.getCatProd().getIdCat())
                        .append("libelle", prod.getCatProd().getLibelle()));

        listProd.add(newProd);

        // System.out.println(listProd);
        //creation de la nouvelle commande
        BasicDBObject docCmdnew = new BasicDBObject("_id", cmd.getIdCmd())
                .append("nomClient", cmd.getNomClient())
                .append("dateCmd", cmd.getDate())
                .append("contenir", listProd);

        //mise à jour de la nouvelle commande
        this.collectionCmd.update(docCmdold, docCmdnew);

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commande> getAllCommande() {
        List<Commande> listeCmd = new ArrayList<>();
        //stockage de toutes les catégories dans l'objet DBCusor
        DBCursor cursor = this.collectionCmd.find();
        //parcours du cursor      
        while (cursor.hasNext()) { //hasNext permet de tester la fin du curseur
            DBObject objCmd = cursor.next(); //next permet de récupérer le document
            //dans le curseur àprès chaque itération
            Commande cmd = new Commande();
            cmd.setIdCmd((int) objCmd.get("_id"));
            cmd.setNomClient(objCmd.get("nomClient").toString());
            cmd.setDate(objCmd.get("dateCmd").toString());
            cmd.setListProd((List) objCmd.get("contenir"));

            listeCmd.add(cmd);
        }
        return listeCmd;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public Commande getOneCommande(int idCmd) {

        //creation d'un document avec l'id passé en paramètre
        BasicDBObject docCmd = new BasicDBObject("_id", idCmd);
        //find avec id comme filtre
        DBObject objcmd = this.collectionCmd.findOne(docCmd);

        Commande cmd = new Commande();
        cmd.setIdCmd((int) objcmd.get("_id"));
        cmd.setNomClient(objcmd.get("nomClient").toString());
        cmd.setDate(objcmd.get("dateCmd").toString());
        cmd.setListProd((List) objcmd.get("contenir"));

        return cmd;
    }

    @Override
    public void supprimerCommande(Commande cmd) {
        // creation de l'ancienne liste des produits de commande  
        BasicDBObject docCmdold = new BasicDBObject("_id", cmd.getIdCmd());
        //recherche de la commande
        DBObject obj = this.collectionCmd.findOne(docCmdold);

        //recuperation de la liste des produits de la commande
        BasicDBList listProd = (BasicDBList) obj.get("contenir");

        //ici nous vérifions si la commande ne contient pas de produits -- controle presence de produits ds la commande
        if (listProd.isEmpty()) {
            this.collectionCmd.remove(obj);
            JOptionPane.showMessageDialog(null, "Opération de suppression OK");
        } else {
            JOptionPane.showMessageDialog(null, "la commande N°: " + cmd.getIdCmd() + "contient des produits !");
        }

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierProdCommande(Commande cmd, Produit prod) {

        // creation de l'ancienne liste des produits de commande  
        BasicDBObject docCmdold = new BasicDBObject("_id", cmd.getIdCmd());
        //recherche de la commande
        DBObject obj = this.collectionCmd.findOne(docCmdold);
        //recuperation de la liste des produits de la commande
        BasicDBList listProd = (BasicDBList) obj.get("contenir");

        for (int i = 0; i < listProd.size(); i++) {
            DBObject objProd = (DBObject) listProd.get(i);
            if (objProd.get("_id").equals(prod.getIdProd())) {
                //supprimer l'objet de la liste
                listProd.remove(i);
                //creation de l'objet avec nouvelle quantite et ajout nouveau produit à la listeProdCmd        
                BasicDBObject newProd = new BasicDBObject("_id", prod.getIdProd())
                        .append("nomProd", prod.getNomProd())
                        .append("qteCmd", prod.getQteProd())
                        .append("catégorie", new BasicDBObject("_id", prod.getCatProd().getIdCat())
                                .append("libelle", prod.getCatProd().getLibelle()));
                //ajout de l'objet liste
                listProd.add(newProd);
            }

        }
        //System.out.println(listProd);

        //creation de la nouvelle commande
        BasicDBObject docCmdnew = new BasicDBObject("_id", cmd.getIdCmd())
                .append("nomClient", cmd.getNomClient())
                .append("dateCmd", cmd.getDate())
                .append("contenir", listProd);

        //mise à jour de la nouvelle commande
        this.collectionCmd.update(docCmdold, docCmdnew);

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
