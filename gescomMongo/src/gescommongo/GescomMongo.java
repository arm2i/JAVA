/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gescommongo;

import com.mongodb.BasicDBObject;
import controleur.ControleurCategorie;
import controleur.ControleurCommande;
import controleur.ControleurProduit;
import java.util.ArrayList;
import java.util.List;
import modele.Categorie;
import modele.CategorieDao;
import modele.Commande;
import modele.CommandeDao;
import modele.Connexion;
import modele.Produit;
import modele.ProduitDao;
import vue.CategorieVue;
import vue.ProduitVue;

/**
 *
 * @author Formation
 */
public class GescomMongo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   //     Connexion.getConnexion();
      //  Categorie cat = new Categorie(2,"CD"); //création de l'objet catégorie
        //CategorieDao catDao = new CategorieDao();//création de l'objet DaoCat 
        
        //insertion du l'objet cat à la collection categorie;
  //      catDao.addCategorie(cat);
        ///catDao.deleteCategorie(cat);
        //System.out.println(catDao.getOneCategorie(2));
        
        //CategorieVue catVue = new CategorieVue();
       // catVue.setVisible(true);
       
       // ControleurCategorie controlCat = new ControleurCategorie();
       
       //Produit prodold = new Produit(4,"PC5","Un truc cool5", 650.8, 1, cat);
  /* Produit prodnew = new Produit(2,"PCnumero1","Un truc cool5", 655.8, 1, cat);
       
      ProduitDao prodDao = new ProduitDao();
       
      prodDao.addProduit(prodnew);
       
       //prodDao.updateProduit(prodnew);
      // prodDao.deleteProduit(prodnew);
       
       //System.out.println(prodDao.getAllProduit());
     
       
       List<Produit> listeProd = new ArrayList<>();
       listeProd = prodDao.getAllProduit();
          
        for(Produit prod : listeProd){
               System.out.println(prod);
         }
      
       
      }
*/
  
      //  ControleurProduit produit = new ControleurProduit();
      
      // Commande cmd = new Commande(0, "Francis", "25/06/2019");
       // Commande cmd1 = new Commande(1, "Simpson", "24/06/2019");
       //Commande cmd2 = new Commande(2, "Bart", "23/06/2019");
       // Commande cmd3 = new Commande(3, "Lisa", "20/06/2019");
        
        //cmd1.getListProd().add(new BasicDBObject("_id",1).append("nomProd","NoSQL pour les NULS").append("qteCmd",5));
       // cmd3.getListProd().add(new BasicDBObject("_id",2).append("nomProd","trois petits").append("qteCmd",10)
     //                               .append("catégorie", new BasicDBObject("_id",1).append("libelle","Livre")));
            
     //CommandeDao Cdao = new CommandeDao();
       //Produit prodnew = new Produit(2,"PCnumero1","Un truc cool5", 655.8, 1, cat);
       //Cdao.ajouterCommande(Cdao);
       
      // cmd.getListProd().add(new BasicDBObject("_id",0).append("nomProd","NoSQL pour les NULS").append("qteCmd",5));
       
      
      //Cdao.ajouterProdCommande(cmd, prodnew);
       //Cdao.supprimerProdCommande(cmd, prodnew);
       
        // test du getAllCommande
       
         //System.out.println(Cdao.getAllCommande());

         // List<Commande> listcmd= Cdao.getAllCommande();
        // for(Commande cmd1 : listcmd){System.out.println(cmd1);}
       
      // System.out.println(Cdao.getOneCommande(0));
      
     // ControleurCategorie cat = new ControleurCategorie();
      
    // ControleurProduit prod = new ControleurProduit();
       
         ControleurCommande Ccmd = new ControleurCommande();
        
    }
    
}
