/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Cartes;

import Modele.Etat_Tuile;
import Modele.Tuile;
import Observateur.Message;
import Observateur.TypeMessage;

/**
 *
 * @author lebouchn
 */
public class CarteSacSable  extends CarteTresor{
    
    public CarteSacSable(String nom) {
        super(nom);
    }
    public Message assecher(Tuile tuile){
        Message message;
        if (tuile.getEtatTuile() == Etat_Tuile.INONDEE){
            
            message = new Message(TypeMessage.ASSECHEMENT_OK);
            
        }else if (tuile.getEtatTuile() == Etat_Tuile.COULEE){
            
            message = new Message(TypeMessage.ASSECHEMENT_SOMBREE);
            
        }else {
            
            message = new Message(TypeMessage.ASSECHEE);
            
        }
        return message;
    }
}
