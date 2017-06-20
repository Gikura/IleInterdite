/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Observateur.Message;
import Observateur.TypeMessage;

/**
 *
 * @author dussaulp
 */
public class Pilote extends Aventurier {
    
    public Pilote(String nom, Couleur couleur) {
        super(nom, couleur);
    }
    
    public Message déplacer(Tuile cible) {
        int xAventurier = getTuile().getCoords().getX();
        int yAventurier = getTuile().getCoords().getY();
        int xCible = cible.getCoords().getX();
        int yCible = cible.getCoords().getY();
        Message message;
        boolean valide = false;
        
        if ((cible.isSombree() != false) && (xCible != xAventurier && yCible != yAventurier)) {      
            
            tuile.enleverAventurier(this);
            cible.ajouterAventurier(this);
            this.setTuile(cible);
            message = new Message(TypeMessage.DEPLACEMENT_OK);
        }else{
            message = new Message(TypeMessage.DEPLACEMENT_SOMBREE);
        }
        
        return message;
    }
    
    
    
    
}
