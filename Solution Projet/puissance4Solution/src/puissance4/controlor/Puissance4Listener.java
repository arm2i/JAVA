/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4.controlor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import puissance4.view.Puissance4Vue;

/**
 *
 * @author Francis
 */
public class Puissance4Listener implements ActionListener {

    Puissance4Vue puissance4Vue;
    //Puissance4 puissance4;

    /**
     *
     * @param puissance4Vue
     */
    public Puissance4Listener(Puissance4Vue puissance4Vue) {
        this.puissance4Vue = puissance4Vue;
       // this.puissance4 = puissance4;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String composant = e.getActionCommand();
            this.puissance4Vue.getPuissance4().jouer(Integer.parseInt(composant));
            
           // if (puissance4.getPuissance4().getGrille().isFullColonne(Integer.parseInt(composant)) == false) {
            //if(composant!=null){
           // if (puissance4.getPuissance4().getGrille().isFullColonne(Integer.parseInt(composant)) != false){
               /* switch (composant) {
                    case "0":
                        //System.out.println("vous avez cliquez sur le bouton:" + composant);
                        this.puissance4Vue.getPuissance4().jouer(Integer.parseInt(composant));
                        break;
                    case "1":
                        //System.out.println("vous avez cliquez sur le bouton:" + composant);
                        this.puissance4Vue.getPuissance4().jouer(Integer.parseInt(composant));
                        break;
                    case "2":
                        //System.out.println("vous avez cliquez sur le bouton:" + composant);
                        this.puissance4Vue.getPuissance4().jouer(Integer.parseInt(composant));
                        break;
                    case "3":
                        //System.out.println("vous avez cliquez sur le bouton:" + composant);
                        this.puissance4Vue.getPuissance4().jouer(Integer.parseInt(composant));
                        break;
                    case "4":
                        //System.out.println("vous avez cliquez sur le bouton:" + composant);
                        this.puissance4Vue.getPuissance4().jouer(Integer.parseInt(composant));
                        break;
                    case "5":
                        //System.out.println("vous avez cliquez sur le bouton:" + composant);
                        this.puissance4Vue.getPuissance4().jouer(Integer.parseInt(composant));
                        break;
                    case "6":
                        //System.out.println("vous avez cliquez sur le bouton:" + composant);
                        this.puissance4Vue.getPuissance4().jouer(Integer.parseInt(composant));
                        break;
                    case "Nouveau":
                        //System.out.println("vous avez cliquez sur le bouton:" + composant);
                        //this.puissance4Vue.getPuissance4().jouer(7);
                        break;
                    case "Fermer":
                        //System.out.println("vous avez cliquez sur le bouton:" + composant);
                        System.exit(0);
                        break;
                    default:
                        break;
                }*/
           // }
            //}
        } catch (Exception ex) {
            //this.puissance4.
            System.out.println(ex.getMessage());
        }
    


    }
}
