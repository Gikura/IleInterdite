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
public class Ingenieur extends Aventurier {
    
    public Ingenieur(String nom, Couleur couleur) {
    super(nom, couleur);
    }
    
    
    @Override
        public Message assecher(Tuile cible){
            
            int xAventurier = getTuile().getCoords().getX();
            int yAventurier = getTuile().getCoords().getY();
            int xCible1 = cible.getCoords().getX();
            int yCible1 = cible.getCoords().getY();
            int xCible2 = cible.getCoords().getX();
            int yCible2 = cible.getCoords().getY();
            Message message;
            boolean valide = false;  
        
        
            if (cible.isSombree() == true ){
                message = new Message(TypeMessage.ASSECHEMENT_SOMBREE);

            }
        
            else {
                if (cible.isInondee() == true){
                
                    if((xCible1 == xAventurier && (yCible1 <= yAventurier + 1 || yCible1 >= yAventurier -1)) || (yCible1 == yAventurier && (xCible1 <= xAventurier + 1 || xCible1 >= xAventurier -1))){
                        cible.setInondee(false);
                        message = new Message(TypeMessage.ASSECHEMENT_OK);
                    }
                    else{
                        message = new Message(TypeMessage.ASSECHEMENT_TUILE_NON_ATTEIGNABLE);
                    }
                
                    if((xCible2 == xAventurier && (yCible2 <= yAventurier + 1 || yCible2 >= yAventurier -1)) || (yCible2 == yAventurier && (xCible2 <= xAventurier + 1 || xCible1 >= xAventurier -1))){
                        cible.setInondee(false);
                        message = new Message(TypeMessage.ASSECHEMENT_OK);
                    }
                     else{
                    message = new Message(TypeMessage.ASSECHEMENT_TUILE_NON_ATTEIGNABLE);
                    }

                }
                else{
                    message = new Message(TypeMessage.ASSECHEE);

                }
            }

        return message;        
        
   }
        super(nom, couleur);
    }

    @Override
    public Message deplacer(Tuile cible) {
        return super.deplacer(cible); //To change body of generated methods, choose Tools | Templates.
    }    
}
