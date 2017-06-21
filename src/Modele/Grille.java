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
        
        for (int x = 1; x <= 6; x++) {
            for (int y = 1; y <= 6; y++) {
                if (!( (x == 1 || x == 6 && (y == 1 || y == 2 || y == 5 || y ==6)) || (x == 2 || x == 5 && (y == 1 || y == 6)) )) {
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
    
    private ArrayList<Tuile> getTuilesGrille() {
        ArrayList<Tuile> listeCases = new ArrayList<>();
        for (int x = 1; x <= 6; x++) {
            for (int y = 1; y <= 6; y++) {
                if (!( (x == 1 || x == 6 && (y == 1 || y == 2 || y == 5 || y ==6)) || (x == 2 || x == 5 && (y == 1 || y == 6)) )) {
                    listeCases.add(getTuile(x,y));
                }
            }
        }
        return listeCases;
    }
    
    public ArrayList<Tuile> getTuilesAdjacentes(Tuile tuile) {

        ArrayList<Tuile> listeAdj = new ArrayList<>();

        if (getTuile((tuile.getCoords().getX()+1),(tuile.getCoords().getY())) != null) {
            Tuile tuileD = getTuile((tuile.getCoords().getX()+1),(tuile.getCoords().getY()));
            listeAdj.add(tuile);
        }
        if (getTuile((tuile.getCoords().getX()-1),(tuile.getCoords().getY())) != null) {
            Tuile tuileG = getTuile((tuile.getCoords().getX()-1),(tuile.getCoords().getY()));
            listeAdj.add(tuile);
        }    
        if (getTuile((tuile.getCoords().getX()),(tuile.getCoords().getY()+1)) != null) {
            Tuile tuileH = getTuile((tuile.getCoords().getX()),(tuile.getCoords().getY()+1));
            listeAdj.add(tuile);
        }
        if (getTuile((tuile.getCoords().getX()),(tuile.getCoords().getY()-1)) != null) {
            Tuile tuileB = getTuile((tuile.getCoords().getX()),(tuile.getCoords().getY()-1));
            listeAdj.add(tuile);
        }
        return listeAdj;
    }

    private Tuile getTuile(int x, int y) {
        return ((x<6 && x>=0)&&(y<6 && y>=0)) ? getMap()[y][x] : null;
    }
    
    
    private Tuile[][] getMap() {
        return this.plateauJeu;
    }
    
    
    
    
    
}
