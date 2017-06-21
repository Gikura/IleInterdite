/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Modele.Aventurier.Aventurier;
import Utils.Utils.*;
import java.util.ArrayList;

/**
 *
 * @author Romain
 */
public class Tuile {
    private String nom;
    private Coordonnees coords;
    private Pion pion;
    private ArrayList<Aventurier> aventuriers;
    private boolean inondee;
    private boolean sombree;
    
    public Tuile(String nom){
        this.setNom(nom);
        this.inondee = this.sombree = false;
    }
    
    public Tuile(String nom, Coordonnees coords){
        this.setNom(nom);
        this.setCoords(coords);
        this.inondee = this.sombree = false;
    }
    
    public Tuile(String nom, Coordonnees coords, Pion pion) {
        this.setNom(nom);
        this.setCoords(coords);
        this.inondee = this.sombree = false;
        this.setPion(pion);
    }

    
    public void ajouterAventurier(Aventurier aventurier){
        aventuriers.add(aventurier);
    }
    
    public void enleverAventurier(Aventurier aventurier){
        aventuriers.remove(aventurier);
    }
    
    public void assecher() {
        this.inondee = false;
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
     * @return the inondee
     */
    public boolean isInondee() {
        return inondee;
    }

    /**
     * @param inondee the inondee to set
     */
    public void setInondee(boolean inondee) {
        this.inondee = inondee;
    }

    /**
     * @return the sombree
     */
    public boolean isSombree() {
        return sombree;
    }

    /**
     * @param sombree the sombree to set
     */
    public void setSombree(boolean sombree) {
        this.sombree = sombree;
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
    
    
}
