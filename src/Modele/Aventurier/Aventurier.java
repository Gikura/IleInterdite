/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Aventurier;

import Modele.Cartes.CarteTresor;
import Modele.*;
import java.util.ArrayList;
import Observateur.*;
import Utils.Utils.Pion;

/**
 *
 * @author Romain
 */
public abstract class Aventurier {
    private String nom;
    protected int actions;
    private ArrayList<CarteTresor> cartes;
    protected Tuile tuile;
    protected Pion pionAventurier;
    
    public Aventurier(String nom){
        this.nom = nom;
        this.actions = 3;
        this.cartes = new ArrayList<>();
        this.tuile = null;
    }

    public Message deplacer(Tuile cible){
        int xAventurier = getTuile().getCoords().getX();
        int yAventurier = getTuile().getCoords().getY();
        int xCible = cible.getCoords().getX();
        int yCible = cible.getCoords().getY();
        Message message;
        boolean valide = false;
        
        if(cible.getEtatTuile() != Etat_Tuile.COULEE){
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
    
    public Message assecher(Tuile cible){
        int xAventurier = getTuile().getCoords().getX();
        int yAventurier = getTuile().getCoords().getY();
        int xCible = cible.getCoords().getX();
        int yCible = cible.getCoords().getY();
        Message message;
        
        if (cible.getEtatTuile() == Etat_Tuile.COULEE){
            message = new Message(TypeMessage.ASSECHEMENT_SOMBREE);
        }else{
            if (cible.getEtatTuile() == Etat_Tuile.INONDEE){
                if((xCible == xAventurier && (yCible == yAventurier + 1 || yCible == yAventurier -1)) || (yCible == yAventurier && (xCible == xAventurier + 1 || xCible == xAventurier -1))){
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
    
    public Message donnerCarte (CarteTresor carte, Aventurier aventurier){
        int xAventurier1 = aventurier.getTuile().getCoords().getX();
        int yAventurier1 = aventurier.getTuile().getCoords().getY();
        int xAventurier2 = aventurier.getTuile().getCoords().getX();
        int yAventurier2 = aventurier.getTuile().getCoords().getY();
        Message message = null;
        
        if (xAventurier1 == xAventurier2 && yAventurier1 == yAventurier2){
            if (this.getCartes().contains(carte)) {
                this.enleverCarte(carte);
                aventurier.ajouterCarte(carte);
                actions = actions -1;
                message = new Message(TypeMessage.DONNER_CARTE);
            }else{
                message = new Message(TypeMessage.CARTE_NON_POSSEDEE);
            }
        }else{
            message = new Message(TypeMessage.DONNER_CARTE_IMPOSSIBLE);
        } 
        return message;  
    }
        
//    public ArrayList<Tuile> getTuilesAccessibles(Grille grille) {
//        return  
//    }
    
    public void ajouterCarte(CarteTresor carte){
        cartes.add(carte);
    }
    
    public void enleverCarte(CarteTresor carte){
        cartes.remove(carte);
    }
    
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the actions
     */
    public int getActions() {
        return actions;
    }

    /**
     * @param actions the actions to set
     */
    public void setActions(int actions) {
        this.actions = actions;
    }

    /**
     * @return the cartes
     */
    public ArrayList<CarteTresor> getCartes() {
        return cartes;
    }

    /**
     * @param cartes the cartes to set
     */
    public void setCartes(ArrayList<CarteTresor> cartes) {
        this.cartes = cartes;
    }

    /**
     * @return the tuile
     */
    public Tuile getTuile() {
        return tuile;
    }

    /**
     * @param tuile the tuile to set
     */
    public void setTuile(Tuile tuile) {
        this.tuile = tuile;
    }

    /**
     * @return the pionAventurier
     */
    public Pion getPionAventurier() {
        return pionAventurier;
    }

    /**
     * @param pionAventurier the pionAventurier to set
     */
    public void setPionAventurier(Pion pionAventurier) {
        this.pionAventurier = pionAventurier;
    }
    
    
    
}
