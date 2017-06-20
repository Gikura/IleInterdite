/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import Observateur.*;

/**
 *
 * @author Romain
 */
public abstract class Aventurier {
    private String nom;
    private Couleur couleur;
    private int actions;
    private ArrayList<CarteTresor> cartes;
    protected Tuile tuile;
    
    public Aventurier(String nom, Couleur couleur){
        this.nom = nom;
        this.couleur = couleur;
        this.actions = 3;
        this.cartes = new ArrayList<>();
        this.tuile = null;
    }
    
    public void ajouterCarte(CarteTresor carte){
        cartes.add(carte);
    }
    
    public Message deplacer(Tuile cible){
        int xAventurier = getTuile().getCoords().getX();
        int yAventurier = getTuile().getCoords().getY();
        int xCible = cible.getCoords().getX();
        int yCible = cible.getCoords().getY();
        Message message;
        boolean valide = false;
        
        if(cible.isSombree()){
            if((xCible == xAventurier && (yCible == yAventurier + 1 || yCible == yAventurier -1)) || (yCible == yAventurier && (xCible == xAventurier + 1 || xCible == xAventurier -1))){
                tuile.enleverAventurier(this);
                cible.ajouterAventurier(this);
                this.setTuile(cible);
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
        boolean valide = false;   
        
        if (cible.isSombree() == true ){
            message = new Message(TypeMessage.ASSECHEMENT_SOMBREE);

        }
        
        else {
            if (cible.isInondee() == true){
                if((xCible == xAventurier && (yCible <= yAventurier + 1 || yCible >= yAventurier -1)) || (yCible == yAventurier && (xCible <= xAventurier + 1 || xCible >= xAventurier -1))){
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
     * @return the couleur
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * @param couleur the couleur to set
     */
    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
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
    
    
    
}
