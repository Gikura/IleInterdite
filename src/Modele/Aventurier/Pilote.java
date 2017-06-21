/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Aventurier;

import Modele.Couleur;
import Modele.Etat_Tuile;
import Modele.Tuile;
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
    
    @Override
    public Message deplacer(Tuile cible) {
        int xAventurier = getTuile().getCoords().getX();
        int yAventurier = getTuile().getCoords().getY();
        int xCible = cible.getCoords().getX();
        int yCible = cible.getCoords().getY();
        Message message;
        boolean valide = false;
        
        if (cible.getEtatTuile() != Etat_Tuile.COULEE) {
            if (xCible != xAventurier && yCible != yAventurier) {      
                tuile.enleverAventurier(this);
                cible.ajouterAventurier(this);
                this.setTuile(cible);
                actions = actions -1;
                message = new Message(TypeMessage.DEPLACEMENT_OK);
            }else{
                message = new Message(TypeMessage.DEPLACEMENT_MEME_TUILE);
            }
                
        }else{
            message = new Message(TypeMessage.DEPLACEMENT_SOMBREE);
        }
        
        return message;
    }
    
    
    
    
}
