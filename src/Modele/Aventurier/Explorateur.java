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
public class Explorateur extends Aventurier {
    
    public Explorateur(String nom, Couleur couleur) {
        super(nom);
        this.pionAventurier = Pion.VERT;
    }
    
    @Override
    public Message deplacer(Tuile cible) {
        int xAventurier = getTuile().getCoords().getX();
        int yAventurier = getTuile().getCoords().getY();
        int xCible = cible.getCoords().getX();
        int yCible = cible.getCoords().getY();
        Message message;
        boolean valide = false;
        
        if(cible.getEtatTuile() != Etat_Tuile.COULEE){
            if(((xCible == xAventurier && (yCible == yAventurier + 1 || yCible == yAventurier -1)) || (yCible == yAventurier && (xCible == xAventurier + 1 || xCible == xAventurier -1)) || (xCible == xAventurier - 1 && (yCible == yAventurier - 1 || yCible == yAventurier + 1)) || (xCible == xAventurier + 1 && (yCible == yAventurier - 1 || yCible == yAventurier + 1))) && (xCible != xAventurier && yCible != yAventurier)                              ){

                tuile.enleverAventurier(this);
                cible.ajouterAventurier(this);
                this.setTuile(cible);
                actions = actions -1;
                message = new Message(TypeMessage.DEPLACEMENT_OK);
            }else{
                message = new Message(TypeMessage.DEPLACEMENT_TUILE_NON_ATTEIGNABLE);
            }
        }else{
            message = new Message(TypeMessage.DEPLACEMENT_SOMBREE);
        }
        return message;
    }
    
    @Override
    public Message assecher(Tuile cible){
        int xAventurier = getTuile().getCoords().getX();
        int yAventurier = getTuile().getCoords().getY();
        int xCible = cible.getCoords().getX();
        int yCible = cible.getCoords().getY();
        Message message;
        boolean valide = false; 

        if(cible.getEtatTuile() == Etat_Tuile.COULEE){
            message = new Message(TypeMessage.ASSECHEMENT_SOMBREE);
        }else{
            if(cible.getEtatTuile() != Etat_Tuile.INONDEE){
                if(((xCible == xAventurier-1 || xCible == xAventurier+1) && (yCible == yAventurier-1 || yCible == yAventurier+1)) || (xCible == xAventurier && yCible == yAventurier)){
                    cible.assecher();
                    actions = actions -1;
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
