/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Formation
 */
public class ProduitDao implements ProduitInterface{
    private DB db;
    private DBCollection collectionProd;

    public ProduitDao() {
         this.db = Connexion.getConnexion();
        collectionProd = this.db.getCollection("produit");
    }
    
    
    
    
    @Override
    public List<Produit> getAllProduit() {
         List<Produit> listeProd = new ArrayList<>();
       //stockage de toutes les catégories dans l'objet DBCusor
        DBCursor cursor = this.collectionProd.find();      
        //parcours du cursor      
        while(cursor.hasNext()){ //hasNext permet de tester la fin du curseur
            DBObject objprod = cursor.next(); //next permet de récupérer le document
                                           //dans le curseur àprès chaque itération
        
        Produit prod = new Produit();
        prod.setIdProd((int)objprod.get("_id"));
        prod.setNomProd(objprod.get("nom").toString());
        prod.setPrixProd((double)objprod.get("prix"));
        prod.setDescriptionProd(objprod.get("description").toString());
        prod.setQteProd((int)objprod.get("quantite"));
        
        //creation du document pour récuperer le cat ds le document Prod
        DBObject objCat =(DBObject)objprod.get("idCat"); // on caste on veutr qui retourne un DBObject
               
        
        //mise à jour de la cat dans l'objet produit
        prod.setCatProd(new Categorie((int)objCat.get("_id"), objCat.get("libelle").toString()));
       
        //ajout a la liste
        listeProd.add(prod);
            
        }  
       return listeProd;
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addProduit(Produit prod) {
        //création d'un document 
        BasicDBObject docProd = new BasicDBObject();
        docProd.append("_id",prod.getIdProd());
        docProd.append("nom", prod.getNomProd());
        docProd.append("prix", prod.getPrixProd());
        docProd.append("description", prod.getDescriptionProd());
        docProd.append("quantite", prod.getQteProd());
        //creation d'un sous document
        docProd.append("idCat",new BasicDBObject ("_id",prod.getCatProd().getIdCat())
                                                    .append("libelle", prod.getCatProd().getLibelle()));
        //ajout du document dans la collection catégorie
        this.collectionProd.insert(docProd);
        
        JOptionPane.showMessageDialog(null,"Opération Ajout Produit effectuée avec succes");
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProduit(Produit prod) {
        
          BasicDBObject docProd = new BasicDBObject();
        docProd.append("_id",prod.getIdProd());
        this.collectionProd.remove(docProd);            
        JOptionPane.showMessageDialog(null,"Opération suppression effectuée avec succes");
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateProduit(Produit prod) {
          //création du document à l'id de la cat qui permet de rechercher dans la collection
        BasicDBObject docProdOld = new BasicDBObject();
        docProdOld.append("_id",prod.getIdProd());
        //création du document avec les valeur à mettre à jour
        BasicDBObject docProdNew = new BasicDBObject();
         docProdNew.append("nom", prod.getNomProd());
        docProdNew.append("prix", prod.getPrixProd());
        docProdNew.append("description", prod.getDescriptionProd());
        docProdNew.append("quantite", prod.getQteProd());
        docProdNew.append("idCat",new BasicDBObject ("_id",prod.getCatProd().getIdCat())
                                                    .append("libelle", prod.getCatProd().getLibelle()));
        
        this.collectionProd.update(docProdOld, docProdNew);
     
        JOptionPane.showMessageDialog(null,"Opération mise à jour produit effectuée avec succes");
        
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produit getOneProduit(int idProd) {
        
        Produit prod = new Produit();
        //creation d'un document avec l'id passé en paramètre
        BasicDBObject docProd = new BasicDBObject("_id",idProd);
       
        DBObject objprod =this.collectionProd.findOne(docProd);
        
        prod.setIdProd((int)objprod.get("_id"));
        prod.setNomProd(objprod.get("nom").toString());
        prod.setPrixProd((double)objprod.get("prix"));
        prod.setDescriptionProd(objprod.get("description").toString());
        prod.setQteProd((int)objprod.get("quantite"));
        
         //creation du document pour récuperer le cat ds le document Prod
        DBObject objCat =(DBObject)objprod.get("idCat"); // on caste on veutr qui retourne un DBObject
               
        
        //mise à jour de la cat dans l'objet produit
        prod.setCatProd(new Categorie((int)objCat.get("_id"), objCat.get("libelle").toString()));
         
        
        
        return prod;
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
