/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author Romain
 */
public enum Couleur {
    ROUGE ("rouge"),
    JAUNE ("jaune"),
    GRIS ("gris"),
    VERT ("vert"),
    BLEU ("bleu"),
    NOIR ("noir");
    
    private String nom;
    
    Couleur(String nom){
        this.nom = nom;
    }
    
    @Override
    public String toString(){
        return nom;
    }
}
