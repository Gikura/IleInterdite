/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Observateur.*;

/**
 *
 * @author dussaulp
 */
public class Explorateur extends Aventurier {
    
    public Explorateur(String nom, Couleur couleur) {
        super(nom, couleur);
    }
    
    public Message déplacer(Tuile cible) {
        int xAventurier = getTuile().getCoords().getX();
        int yAventurier = getTuile().getCoords().getY();
        int xCible = cible.getCoords().getX();
        int yCible = cible.getCoords().getY();
        Message message;
        boolean valide = false;
        
        if(cible.isSombree()){
            if((xCible == xAventurier && (yCible == yAventurier + 1 || yCible == yAventurier -1)) || (yCible == yAventurier && (xCible == xAventurier + 1 || xCible == xAventurier -1)) || (xCible == xAventurier - 1 && (yCible == yAventurier - 1 || yCible == yAventurier + 1)) || (xCible == xAventurier + 1 && (yCible == yAventurier - 1 || yCible == yAventurier + 1))){

                tuile.enleverAventurier(this);
                cible.ajouterAventurier(this);
                this.setTuile(cible);
                message = new Message(TypeMessage.DEPLACEMENT_OK);
            }else{
                message = new Message(TypeMessage.DEPLACEMENT_TUILE_NON_ATTEIGNABLE);
            }
        }else{
            message = new Message(TypeMessage.DEPLACEMENT_SOMBREE);
        }
        return message;
    }

    
    
    
    
    
    
}
