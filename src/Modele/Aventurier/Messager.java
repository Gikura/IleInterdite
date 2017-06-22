/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Aventurier;

import Modele.Cartes.CarteTresor;
import Modele.Couleur;
import Observateur.*;
import Utils.Utils;
import Utils.Utils.Pion;

/**
 *
 * @author dussaulp
 */
public class Messager extends Aventurier {
    
    public Messager(String nom, Couleur couleur) {
        super(nom);
        this.pionAventurier = Pion.GRIS;
    }
    
    @Override
    public Message donnerCarte(CarteTresor carte,Aventurier aventurier){
        Message message;
        if (this.getCartes().contains(carte)){
            this.getCartes().remove(carte);
            aventurier.ajouterCarte(carte);
            actions = actions +1;
            message = new Message(TypeMessage.DONNER_CARTE); 
        }
        else{
            message = new Message(TypeMessage.DONNER_CARTE_IMPOSSIBLE);
        }
        return message;
    }
}
