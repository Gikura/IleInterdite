/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;


import java.util.ArrayList;
import java.util.HashMap;
import Utils.Utils.*;
import Modele.Aventurier.*;

/**
 *
 * @author dussaulp
 */
public class Grille {
    
    private Tuile[][] plateauJeu;
    
    public Grille() {
        this.plateauJeu = new Tuile[6][6];
    }
    
    public void installer_grille (ArrayList<Tuile> pileTuile, HashMap<Pion, Aventurier> Joueurs) {
        int numTuile = 0;
        Tuile tuile;
        Aventurier joueur;
        
        for (int x = 1; x <= 6; x++) {
            for (int y = 1; y <= 6; y++) {
                if (()) {
                    
                }
            }
        }
        
        
    }
    
    
    
    
    private Tuile[][] getmap() {
        return this.plateauJeu;
    }
    
    
    
    
    
}
