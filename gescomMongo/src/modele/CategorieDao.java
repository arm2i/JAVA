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
public class CategorieDao implements CategorieInterface{

    private DB db;
    private DBCollection collectionCat;

    public CategorieDao() {
        this.db = Connexion.getConnexion();
        collectionCat = this.db.getCollection("categorie");
    }
    
    @Override
    public List<Categorie> getAllCategorie() {
       List<Categorie> listeCat = new ArrayList<>();
       //stockage de toutes les catégories dans l'objet DBCusor
        DBCursor cursor = this.collectionCat.find();      
        //parcours du cursor      
        while(cursor.hasNext()){ //hasNext permet de tester la fin du curseur
            DBObject objCat = cursor.next(); //next permet de récupérer le document
                                           //dans le curseur àprès chaque itération
           Categorie cat = new Categorie();
           cat.setIdCat((int)objCat.get("_id"));
           cat.setLibelle(objCat.get("libelle").toString());
           
           listeCat.add(cat);
        }  
       return listeCat;
    }

    @Override
    public Categorie getOneCategorie(int idCat) {
        Categorie cat = new Categorie();
        //creation d'un document avec l'id passé en paramètre
        BasicDBObject docCat = new BasicDBObject("_id",idCat);
        //find avec id comme filtre
        //DBCursor cursor = this.collectionCat.find(docCat);
        //DBObject objcat = cursor.next();//this.collectionCat.findOne(docCat);
        DBObject objcat =this.collectionCat.findOne(docCat);
        cat.setIdCat((int)objcat.get("_id"));
        cat.setLibelle(objcat.get("libelle").toString());
        
        return cat;
    }

    @Override
    public void addCategorie(Categorie cat) {
        //création d'un document 
        BasicDBObject docCat = new BasicDBObject();
        docCat.append("_id",cat.getIdCat());
        docCat.append("libelle", cat.getLibelle());
        //ajout du document dans la collection catégorie
        this.collectionCat.insert(docCat);
        
        JOptionPane.showMessageDialog(null,"Opération effectuée avec succes");
    }

    @Override
    public void deleteCategorie(Categorie cat) {
       // création
        BasicDBObject docCat = new BasicDBObject();
        docCat.append("_id",cat.getIdCat());
        this.collectionCat.remove(docCat);            
        JOptionPane.showMessageDialog(null,"Opération effectuée avec succes");
    }

    @Override
    public void updateCategorie(Categorie cat) {
        //création du document à l'id de la cat qui permet de rechercher dans la collection
        BasicDBObject docCatOld = new BasicDBObject();
        docCatOld.append("_id",cat.getIdCat());
        //création du document avec les valeur à mettre à jour
        BasicDBObject docCatNew = new BasicDBObject();
        docCatNew.append("_id",cat.getIdCat());
        docCatNew.append("libelle", cat.getLibelle());
        
        this.collectionCat.update(docCatOld, docCatNew);
     
        JOptionPane.showMessageDialog(null,"Opération effectuée avec succes");
    }
    
}
