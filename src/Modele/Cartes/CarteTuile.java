/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Cartes;

import Modele.*;
import Utils.Utils.*;

/**
 *
 * @author dussaulp
 */
public class CarteTuile {
    
    private String nom;
    private Pion pion;
    
    
    public CarteTuile(String nom){
        this.setNom(nom);
    }
    
    public CarteTuile(String nom, Pion pion) {
        this.setNom(nom);
        this.setPion(pion);
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
   
    public boolean isDepart() {
        return this.getPion() != null;
    }
    
}
