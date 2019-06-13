/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupuissance4;

import business.Puissance4;
import business.Puissance4Exception;
import java.util.Scanner;

/**
 * Jeu puissance4 jouable à 2 dans la console.
 *
 * @author arhgi128
 * 
 * 
 */
public class JeuPuissance4 {

    /**
     * lancement du menu principal du jeu.
     *@param args : aucun de gérés.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String answer;
        boolean quitGame = false;
        String errMsg = "";
        clearConsole();
        displayWelcome();
        // tant que l'utilisateur ne veut pas quitter
        while (!quitGame){
            if (errMsg != ""){
                displayError(errMsg);
                errMsg = "";
            }
            displayFirstMenu();
            answer = sc.nextLine().substring(0, 1).toUpperCase();
            System.out.println(answer);
            switch (answer){
                case "N":
                    playGame();
                    break;
                case "Q":
                    quitGame = true;
                default:
                    errMsg = "Commande inconnue";
            }
        }
    }
    /**
     * lancement et gestion d'une partie.
     */
    static void playGame(){
        Scanner sc = new Scanner(System.in);
        String answer;
        String errMsg = "";
        boolean quitGame = false;
        //Créer une instance de Puissance4
        Puissance4 puissance4 = new Puissance4();
        //Tant que la partie du jeu n'est pas finie
        while (!puissance4.gameIsOver()){
            //Afficher la partie du jeu puissance4
            clearConsole();
            displayGrille(puissance4);
            if (errMsg != ""){
                displayError(errMsg);
                errMsg = "";
            }
            displayCmdes(puissance4);
            //Demander au joueur courant l'action qu'il propose
            try {
                answer = sc.nextLine().substring(0, 1).toUpperCase();
                //Effectuer cette action
                switch (answer){
                    case "A":
                        puissance4.abandonner();
                        break;
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6": 
                    case "7":
                        puissance4.jouer(Integer.parseInt(answer) - 1);
                        break;
                    default:
                        errMsg = "Commande inconnue";
                }
            }
            /*catch(IllegalArgumentException e){
                errMsg = e.getMessage();
            }*/
            catch(Puissance4Exception e){
                errMsg = e.getMessage();
            }
        }//Fin tant que
        //Afficher le gagnant.
        clearConsole();
        displayGrille(puissance4);
        displayEnd(puissance4);
    }
    /**
     * Les méthodes d'affichage du jeu et des prompt.
     */
    public static void displayWelcome(){
        System.out.println("\n\033[37;44m Bienvenue dans le jeu puissance4 \033[0m\n");
    }
    
    public static void displayFirstMenu(){
        System.out.println("\n-----------------------------");
        System.out.println("N: lancer une partie");
        System.out.println("Q: Quitter le jeu");
    }
    
    public static void clearConsole(){
        for (int i = 0; i <= 50; i++)
            System.out.println();
    }
    
    public static void displayGrille(Puissance4 puissance4){
        System.out.println(puissance4.getPartie().getGrille().toString());
    }
    
    private static void displayCmdes(Puissance4 puissance4){
        System.out.println("");
        System.out.println("A: Abandonner; \n0-6 : jouer un jeton dans la colonne");
        String jc = puissance4.getPartie().getJoueurCourant().getNom().toString();
        if(jc.equalsIgnoreCase("jaune"))
            jc = "\033[43m " + jc + " \033[0m";
        else 
            jc = "\033[37;41m " + jc + " \033[0m";
        System.out.println("Tour du joueur : " + jc);
        System.out.println("Veuillez saisir votre action");
    }
    
    private static void displayError(String msg){
        System.out.println("");
        System.out.println("\033[31;1m " + msg + " \033[0m");
    }
    
    private static void displayEnd(Puissance4 puissance4){
        System.out.println("");
        System.out.println("\033[37;44m           GAME OVER           \033[0m\n");
        if(puissance4.getPartie().getGagnant() != null){
            String gagnant = puissance4.getPartie().getGagnant().getNom().toString();
            if(gagnant.equalsIgnoreCase("jaune"))
                gagnant = "\033[43m " + gagnant + " \033[0m";
            else 
                gagnant = "\033[37;41m " + gagnant + " \033[0m";
            System.out.print("GAGNANT : " + gagnant);
            if (puissance4.getPartie().isParAbandon())
                System.out.println(" par abandon");
            else 
                System.out.println("");
        } else 
            System.out.println("\033[37;41m        PAS DE GAGNANT        \033[0m");
    }
}
