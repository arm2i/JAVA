package business;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * Définit les exceptions de non respect des règles du jeu.
 *
 * @author arhgi128
 */
public class Puissance4Exception extends Exception {
    //String message;
    public Puissance4Exception(String message){
        //this.message = message;
        super(message);
    }
    /*@Override
    public String getMessage(){
        return this.message;
    }*/
}
