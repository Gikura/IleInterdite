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
        ROUGE("Rouge", Color.RED),
        VERT("Vert", Color.GREEN),
        BLEU("Bleu", Color.BLUE ),
        NOIR("Noir", Color.BLACK),
        GRIS("Gris", Color.GRAY),
        JAUNE("Jaune", Color.YELLOW);

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
    
    public static enum Tresor{
        
        Calice("Calice de l'onde"),
        Pierre("Pierre Sacree"),
        Cristal("Cristal Ardent"),
        Statue("Statue du  zéphyr");
            
           private String libelle;
           
           Tresor(String libelle){
               this.libelle=libelle;
           }
           
           @Override
            public String toString() {
            return this.libelle;
        }
    }
}