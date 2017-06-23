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
import Modele.Cartes.*;

/**
 *
 * @author dussaulp
 */
public class Grille {
    
    private Tuile[][] plateauJeu;
    
    public Grille() {
        this.plateauJeu = new Tuile[6][6];
    }
    
    public void installerGrille (ArrayList<CarteTuile> pileTuiles, HashMap<Pion, Aventurier> joueurs) {
        int i = 0;
        CarteTuile carte;
        Aventurier joueur;
        
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                if (!( (x == 0 || x == 5 && (y == 0 || y == 1 || y == 4 || y ==5)) || (x == 1 || x == 4 && (y == 0 || y == 5)) )) {
                    carte = pileTuiles.get(i);
                    getMap()[x][y] = new Tuile (carte, new Coordonnees(x,y));  
                
                    joueur = joueurs.get(carte.getPion());
                    if (carte.isDepart()  && joueur != null ) {
                        getMap()[x][y].ajouterAventurier(joueur);
                    }
                    i++;
                }
            }
        }  
    }
    
    public ArrayList<Tuile> getTuilesGrille() {
        ArrayList<Tuile> listeCases = new ArrayList<>();
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y <= 5; y++) {
                if (!( (x == 0 || x == 5 && (y == 0 || y == 1 || y == 4 || y ==5)) || (x == 1 || x == 4 && (y == 0 || y == 5)) )) {
                    listeCases.add(getTuile(x,y));
                }
            }
        }
        return listeCases;
    }
    
    public ArrayList<Tuile> getTuilesAdjacentes(Tuile tuile) {

        ArrayList<Tuile> listeAdj = new ArrayList<>();

        if (getTuile((tuile.getCoords().getX()+1),(tuile.getCoords().getY())) != null) {       
            listeAdj.add(getTuile((tuile.getCoords().getX()+1),(tuile.getCoords().getY())));
        }
        if (getTuile((tuile.getCoords().getX()-1),(tuile.getCoords().getY())) != null) {
            listeAdj.add(getTuile((tuile.getCoords().getX()-1),(tuile.getCoords().getY())));
        }    
        if (getTuile((tuile.getCoords().getX()),(tuile.getCoords().getY()+1)) != null) {
            listeAdj.add(getTuile((tuile.getCoords().getX()),(tuile.getCoords().getY()+1)));
        }
        if (getTuile((tuile.getCoords().getX()),(tuile.getCoords().getY()-1)) != null) {
            listeAdj.add(getTuile((tuile.getCoords().getX()),(tuile.getCoords().getY()-1)));
        }
        return listeAdj;
    }
    
    public ArrayList<Tuile> getTuilesDiagonales(Tuile tuile) {
        
        ArrayList<Tuile> listeDiagonale = new ArrayList<>();
        
        if (getTuile(tuile.getCoords().getX() + 1, tuile.getCoords().getY() + 1) != null) {
            listeDiagonale.add(getTuile(tuile.getCoords().getX() + 1, tuile.getCoords().getY() + 1));
        }
        if (getTuile(tuile.getCoords().getX() + 1, tuile.getCoords().getY() - 1) != null) {
            listeDiagonale.add(getTuile(tuile.getCoords().getX() + 1, tuile.getCoords().getY() - 1));
        }
        if (getTuile(tuile.getCoords().getX() - 1, tuile.getCoords().getY() - 1) != null) {
            listeDiagonale.add(getTuile(tuile.getCoords().getX() - 1, tuile.getCoords().getY() - 1));
        }
        if (getTuile(tuile.getCoords().getX() - 1, tuile.getCoords().getY() + 1) != null) {
            listeDiagonale.add(getTuile(tuile.getCoords().getX() - 1, tuile.getCoords().getY() + 1));
        }
        return listeDiagonale;
    }

    public Tuile getTuile(int x, int y) {
        return ((x<6 && x>=0)&&(y<6 && y>=0)) ? getMap()[y][x] : null;
    }
    
    public Tuile getTuile(String nom) {
        Tuile tuile = null;
        for (Tuile t : this.getTuilesGrille()) {
            if (t.getCarteTuile().getNom().equals(nom)) {
                tuile = t;
            }
        }
        return tuile;
    }
    
    public Tuile[][] getMap() {
        return this.plateauJeu;
    }
 
}
