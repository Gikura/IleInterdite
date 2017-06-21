/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Modele.Etat_Tuile.*;
import Modele.Aventurier.Aventurier;
import Modele.Cartes.*;
import Utils.Utils.*;
import java.util.ArrayList;

/**
 *
 * @author Romain
 */
public class Tuile {
    private CarteTuile carteTuile;
    private Coordonnees coords;
    private Pion pion;
    private ArrayList<Aventurier> aventuriers;
    private Etat_Tuile etatTuile;
    
    public Tuile(CarteTuile carteTuile){
        this.setCarteTuile(carteTuile);
        this.etatTuile = Etat_Tuile.ASSECHEE;
    }
    
    public Tuile(CarteTuile carteTuile, Coordonnees coords){
        this.setCarteTuile(carteTuile);
        this.setCoords(coords);
        this.etatTuile = Etat_Tuile.ASSECHEE;
    }
    
    public Tuile(CarteTuile carteTuile, Coordonnees coords, Pion pion) {
        this.setCarteTuile(carteTuile);
        this.setCoords(coords);
        this.etatTuile = Etat_Tuile.ASSECHEE;
        this.setPion(pion);
    }

    
    public void ajouterAventurier(Aventurier aventurier){
        aventuriers.add(aventurier);
    }
    
    public void enleverAventurier(Aventurier aventurier){
        aventuriers.remove(aventurier);
    }
    
    public void assecher() {
        this.setEtatTuile(Etat_Tuile.ASSECHEE);
    }

    /**
     * @return the coords
     */
    public Coordonnees getCoords() {
        return coords;
    }

    /**
     * @param coords the coords to set
     */
    public void setCoords(Coordonnees coords) {
        this.coords = coords;
    }

    /**
     * @return the aventuriers
     */
    public ArrayList<Aventurier> getAventuriers() {
        return aventuriers;
    }

    /**
     * @param aventuriers the aventuriers to set
     */
    public void setAventuriers(ArrayList<Aventurier> aventuriers) {
        this.aventuriers = aventuriers;
    }

    /**
     * @return the pion
     */
    public Pion getPion() {
        return pion;
    }

    /**
     * @param pion the pion to set
     */
    public void setPion(Pion pion) {
        this.pion = pion;
    }

    /**
     * @return the etatTuile
     */
    public Etat_Tuile getEtatTuile() {
        return etatTuile;
    }

    /**
     * @param etatTuile the etatTuile to set
     */
    public void setEtatTuile(Etat_Tuile etatTuile) {
        this.etatTuile = etatTuile;
    }

    /**
     * @return the carteTuile
     */
    public CarteTuile getCarteTuile() {
        return carteTuile;
    }

    /**
     * @param carteTuile the carteTuile to set
     */
    public void setCarteTuile(CarteTuile carteTuile) {
        this.carteTuile = carteTuile;
    }
    
    
}
