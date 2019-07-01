/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 *
 * @author Formation
 */
public class Connexion {    
    static DB db;
    static {      
        try{
            //classe de connexion à la base mongo
            MongoClient mongo = new MongoClient("localHost",27017);
            //sélection de la base de données nommée boutique
            db = mongo.getDB("boutique");
            //selection de toutes les bases de l'instance du port 27101
            //System.out.println(mongo.getDatabaseNames());         
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Cette méthode retourne l'objet de connexion à mongo
     * @return 
     */
    public static DB getConnexion(){
        return db;
    }   
}
