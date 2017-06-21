/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import Modele.Aventurier.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author dussaulp
 */
public class Utils {
    public static enum Pion {
        ROUGE("Rouge", new Color(255, 0, 0)),
        VERT("Vert", new Color(0, 195, 0)),
        BLEU("Bleu", new Color(55,194,198)),
        NOIR("Noir", new Color(48, 48, 48)),
        GRIS("Gris", new Color(158, 158, 158)),
        JAUNE("Jaune", new Color(255, 255, 0));

        private final String libelle ;
        private final Color couleur ;

        Pion (String libelle, Color couleur) {
            this.libelle = libelle ;
            this.couleur = couleur ;
        }
        
        @Override
        public String toString() {
            return this.libelle;
        }

        public Color getCouleur() {
            return this.couleur;
        }

        static Pion trouverPion(String name) {
            if (ROUGE.name().equals(name)) return ROUGE ;
            if (VERT.name().equals(name)) return VERT ;
            if (BLEU.name().equals(name)) return BLEU ;
            if (NOIR.name().equals(name)) return NOIR ;
            if (GRIS.name().equals(name)) return GRIS ;
            if (JAUNE.name().equals(name)) return JAUNE ;
            return null ;
        }
    }
    
    public ArrayList<Aventurier> mélangerJoueurs(ArrayList<Aventurier> aventuriers) {
        Collections.shuffle(aventuriers);
        return aventuriers;
    } 
}
