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
import Utils.Utils;
import Utils.Utils.Pion;

/**
 *
 * @author dussaulp
 */
public class Ingenieur extends Aventurier {
    
    public Ingenieur(String nom, Couleur couleur) {
    super(nom);
    this.pionAventurier = Pion.ROUGE;
    }
    
    @Override
    public Message assecher(Tuile cible){
        int xAventurier = getTuile().getCoords().getX();
        int yAventurier = getTuile().getCoords().getY();
        int xCible = cible.getCoords().getX();
        int yCible = cible.getCoords().getY();
        Message message;
        
        if(cible.getEtatTuile() != Etat_Tuile.COULEE){
            message = new Message(TypeMessage.ASSECHEMENT_SOMBREE);
        }else{
            if(cible.getEtatTuile() == Etat_Tuile.INONDEE){
                if((xCible == xAventurier && (yCible == yAventurier + 1 || yCible == yAventurier -1)) || (yCible == yAventurier && (xCible == xAventurier + 1 || xCible == xAventurier -1))){
                    cible.assecher();
                    actions = actions - (int) 0.5;
                    message = new Message(TypeMessage.ASSECHEMENT_OK);
                }else{
                    message = new Message(TypeMessage.ASSECHEMENT_TUILE_NON_ATTEIGNABLE);
                }
            }else{
                message = new Message(TypeMessage.ASSECHEE);
            }
        }
        return message;        
    }
}
