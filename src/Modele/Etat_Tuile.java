/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author dussaulp
 */
public enum Etat_Tuile {
    ASSECHEE("Asséchée"), 
    INONDEE("Inondée"),
    COULEE("Coulée");

    String libelle ;

    Etat_Tuile(String libelle) {
        this.libelle = libelle ;
    }

    @Override
    public String toString() {
        return this.libelle;
    }
}
