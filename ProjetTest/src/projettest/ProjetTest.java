/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projettest;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Formation
 */
public class ProjetTest{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       // 1 création de l'interface principale
       JFrame fenetreP = new JFrame("Principale");
       JFrame fenetreS = new JFrame("Secondaire");
       
       JPanel pane = new JPanel();
       pane.setLayout(new GridLayout(3, 2));
       pane.add(new JButton("cli cli 1"));
       pane.add(new JButton("cli cli 2"));
       pane.add(new JButton("cli cli 3"));
       pane.add(new JButton("cli cli 4"));
       pane.add(new JButton("cli cli 5"));
       pane.add(new JButton("cli cli 6"));
       
       fenetreS.setContentPane(pane);
       
       //nos objets
       JButton btnEnvoyer = new JButton("Envoyer");
       JTextField txtMessage = new JTextField("votre message ! ");
       JTextArea txtHistorique = new JTextArea("historique message", 10,50);
       
      //les différents Jpanel
      JPanel panelBtn = new JPanel();
      panelBtn.setLayout(new FlowLayout());
      panelBtn.add(btnEnvoyer);
      panelBtn.add(txtMessage);
      
      JPanel panelMessage = new JPanel();
      panelMessage.setLayout(new BorderLayout());
      
      panelMessage.add(txtHistorique,BorderLayout.CENTER);
      panelMessage.add(panelBtn,BorderLayout.SOUTH);
      
      fenetreP.setContentPane(panelMessage);
  
       
       
       
       
       
       
       
       
       
       fenetreP.setTitle("Mon interface Principale");
       
       fenetreS.setSize(500, 400);
       fenetreP.setSize(500, 400);
       //les objets
       JPanel panel = new JPanel();
       JButton btnValider = new JButton("Valider");
       JLabel txtAfficher = new JLabel("Un petit texte");
       //ajout des composants dans le jpanel
       panel.add(btnValider);
       panel.add(txtAfficher);
       //panel.setLayout(new BorderLayout());
      // fenetreP.getContentPane().add(panel,BorderLayout.SOUTH);
       
       //fenetreP.setContentPane(panel);
       
       fenetreP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       fenetreS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
       fenetreP.setLocationRelativeTo(null);
       
       fenetreP.setVisible(true);
       fenetreS.setVisible(true);
       
       Principale principale = new Principale();
       principale.setVisible(true);
        
        
    }

    public static boolean tousPositif(int[][] tab) {
        boolean positif = true;

        for (int i = 0; i < tab.length && positif; i++) {
            for (int j = 0; j < tab[i].length && positif; j++) {
                if (tab[i][j] < 0) {
                    positif = false;
                }
            }
        }
        return positif;
    }

    public static void proportionEltNuls(int[][] tab) {
        int nombreEltNuls = 0;
        for (int[] sousTab : tab) {
            for (int val : sousTab) {
                if (val < 0) {
                    nombreEltNuls++;
                }
            }
        }
        System.out.println("la proportion d'éléments nuls : "
                + (nombreEltNuls / nombreEltTableau(tab)) * 100);
    }

    public static int nombreEltTableau(int[][] tab) {
        int nombreElt = 0;
        for (int[] sousTab : tab) {
            for (int val : sousTab) {
                nombreElt++;
            }
        }
        return nombreElt;
    }

    public static int sommeEltImpaireSurBord(int[][] tab) {
        int somme = 0;
        //premiere ligne
        for (int i = 0; i < tab[0].length; i++) {
            if (tab[0][i] % 2 != 0) {
                somme = somme + tab[0][i];
            }
        }
        //dernière ligne  
        int derniereLigne = (tab[tab.length - 1].length) - 1;
        for (int i = 0; i < tab[derniereLigne - 1].length; i++) {
            //System.out.println(i);
            if (tab[derniereLigne - 1][i] % 2 != 0) {
                // System.out.println(i);
                somme = somme + tab[derniereLigne - 1][i];
            }
        }
        //première colonne
        for (int j = 1; j < tab.length - 1; j++) {
            if (tab[j][0] % 2 != 0) {
                somme = somme + tab[j][0];
            }
        }
        //dernière colonne
        int derniereColonne = tab[tab.length - 1].length - 1;

        for (int j = 1; j < tab.length - 1; j++) {
            if (tab[j][derniereColonne] % 2 != 0) {
                somme = somme + tab[j][derniereColonne];
            }
        }
        return somme;
    }

    public static int sommeEltListe(ArrayList<Integer> maListe) {
        int somme = 0;
        for (int valeur : maListe) {
            if (valeur % 2 == 0) {
                somme = somme + valeur;
            }
        }
        return somme;
    }

    public static void supMinMaxListe(ArrayList<Integer> liste) {
        int mix, max, posMax, posMix;
        mix = liste.get(0);
        max = liste.get(0);
        posMax = 0;
        posMix = 0;
        for (int i = 1; i < liste.size(); i++) {
            if (liste.get(i) > max) {
                max = liste.get(i);
                posMax = i;
            } else if (liste.get(i) < mix) {
                mix = liste.get(i);
                posMix = i;
            }
        }
        liste.remove(posMax);
        liste.remove(posMix);

    }

    //exercice 4 : concaténation de deux listes
    public static void concatenListe(ArrayList<Integer> liste1,
            ArrayList<Integer> liste2) {
        for (int valeur : liste2) {
            liste1.add(valeur);
        }
    }

    public static ArrayList<Integer> elimineDoublons(
            ArrayList<Integer> liste) {
        ArrayList<Integer> listeResult = new ArrayList<>();
        listeResult.add(liste.get(0));
        for (int i = 1; i < liste.size(); i++) {
            {
                if (liste.get(i) != liste.get(i - 1)) {
                    listeResult.add(liste.get(i));
                }
            }
        }

        return listeResult;
    }

}
