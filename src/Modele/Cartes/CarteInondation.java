/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Cartes;

import Modele.Tuile;

/**
 *
 * @author lebouchn
 */
public class CarteInondation {
    
    private Tuile tuile;
    
    public CarteInondation(Tuile tuile){
        this.tuile=tuile;
    }
    

    public void inondation(){
        if (!this.getTuile().isInondee()){
        this.getTuile().setInondee(true);
        }
        else {
            this.getTuile().setSombree(true);
        }
    }

    /**
     * @return the tuile
     */
    public Tuile getTuile() {
        return tuile;
    }
}