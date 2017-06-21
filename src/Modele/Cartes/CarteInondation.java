/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Cartes;

import Modele.Etat_Tuile;
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
        if (this.getTuile().getEtatTuile() == Etat_Tuile.ASSECHEE){
        this.getTuile().setEtatTuile(Etat_Tuile.INONDEE);
        }
        else {
            this.getTuile().setEtatTuile(Etat_Tuile.COULEE);
        }
    }

    /**
     * @return the tuile
     */
    public Tuile getTuile() {
        return tuile;
    }
}