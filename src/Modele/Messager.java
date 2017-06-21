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
public class Messager extends Aventurier {
    
    public Messager(String nom, Couleur couleur) {
        super(nom, couleur);
    }
    
    @Override
    public void donnerCarte(CarteTresor carte,Aventurier aventurier){
        if (this.getCartes().contains(carte)){
            this.getCartes().remove(carte);
            aventurier.ajouterCarte(carte);
        }
    }
}
