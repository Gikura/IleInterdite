/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Aventurier;

import Modele.Couleur;
import Modele.Tuile;
import Observateur.Message;
import Observateur.TypeMessage;
import java.util.ArrayList;

/**
 *
 * @author dussaulp
 */
public class Plongeur extends Aventurier {
    
    public Plongeur(String nom, Couleur couleur) {
        super(nom, couleur);
    }
    
    @Override
    public Message deplacer(Tuile cible){
        int xAventurier = getTuile().getCoords().getX();
        int yAventurier = getTuile().getCoords().getY();
        int xCible = cible.getCoords().getX();
        int yCible = cible.getCoords().getY();
        Message message;
        boolean valide = false;
        ArrayList<Tuile> tuilesPassees = new ArrayList<>();
        ArrayList<Tuile> tuilesPassables = new ArrayList<>();
            if(cible.isSombree()){
                if((xCible == xAventurier && (yCible == yAventurier + 1 || yCible == yAventurier -1)) || (yCible == yAventurier && (xCible == xAventurier + 1 || xCible == xAventurier -1))){
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
    
}
