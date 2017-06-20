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
public class Navigateur extends Aventurier {
    
    public Navigateur(String nom, Couleur couleur) {
        super(nom, couleur);
    }
    
    @Override
    public Message deplacer(Tuile cible) {
        return super.deplacer(cible);
    }
    
    public Message déplacerJoueur (Aventurier Aventurier, Tuile cible) {
        int xAventurier = Aventurier.getTuile().getCoords().getX();
        int yAventurier = Aventurier.getTuile().getCoords().getY();
        int xCible = cible.getCoords().getX();
        int yCible = cible.getCoords().getY();
        Message message = null;
        boolean valide = false;
        
        if(cible.isSombree() != true){
            if(xCible != xAventurier && yCible != yAventurier) {
                if(((xCible == xAventurier && (yCible == yAventurier +1 || yCible == yAventurier +2 || yCible == yAventurier -1 || yCible == yAventurier -2)) || (yCible == yAventurier && (xCible == yAventurier +1 || xCible == yAventurier +2 || xCible == yAventurier -1 || xCible == yAventurier -2)) || (xCible == xAventurier +1 && (yCible == yAventurier + 1 || yCible == yAventurier -1)) || (xCible == xAventurier -1 && (yCible == yAventurier +1 || yCible == yAventurier -1))) || (xCible != xAventurier && yCible != yAventurier)){
                    tuile.enleverAventurier(Aventurier);
                    cible.ajouterAventurier(Aventurier);
                    this.setTuile(cible);
                    
                }else{
                    message = new Message(TypeMessage.DEPLACEMENT_TUILE_NON_ATTEIGNABLE);
                }
            }else{
                message = new Message(TypeMessage.DEPLACEMENT_MEME_TUILE);
            }
        }else{
            message = new Message(TypeMessage.DEPLACEMENT_SOMBREE);
        }
        return message;
    }
    
    
    
}
