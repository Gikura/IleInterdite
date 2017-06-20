/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Observateur.Message;

/**
 *
 * @author dussaulp
 */
public class Ingenieur extends Aventurier {
    
    public Ingenieur(String nom, Couleur couleur) {
        super(nom, couleur);
    }

    @Override
    public Message deplacer(Tuile cible) {
        return super.deplacer(cible); //To change body of generated methods, choose Tools | Templates.
    }    
}
