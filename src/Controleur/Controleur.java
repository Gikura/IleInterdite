/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.Cartes.*;
import Modele.Aventurier.*;
import Modele.*;
import Observateur.Observateur;
import Utils.Utils.Pion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author Romain
 */
public class Controleur {
    
    private Grille grille;
    
    private ArrayList<CarteTuile> pileTuile;
    private ArrayList<CarteTresor> pileTresor;    
    private ArrayList<CarteTresor> pileDefausseTresor;
    private ArrayList<CarteInondation> pileInondation;
    private ArrayList<CarteInondation> pileDefausseInondation;
    private HashMap<Pion, Aventurier> listeJoueurs;
    private Pion[] ordreJoueurs;
    private int ordreJeu;
    private Observateur observateur;
    private int INDICE_MONTEE_DES_EAUX = 0;
    
    public Controleur() {
        pileTuile = new ArrayList<>();
        pileInondation = new ArrayList<>();
        pileTresor = new ArrayList<>();
        creerCarte();
        this.grille = new Grille();
    }
    
    public void creerCarte() {
        
        String nomCarte;    
        // Cartes Trésor
        for (int i = 0; i < 4; i++) {
            pileTresor.add(new CarteTresor("Calice de l'onde"));
            pileTresor.add(new CarteTresor("Pierre sacrée"));
            pileTresor.add(new CarteTresor("Statue du zéphyr"));
            pileTresor.add(new CarteTresor("Cristal ardent"));               
            if (i < 2) {
            pileTresor.add(new CarteTresor("Hélicoptère"));
            }
            if (i < 1) {
            pileTresor.add(new CarteTresor("Montée des eaux"));
            pileTresor.add(new CarteTresor("Sac de sable"));
            }
        }
        
        // Cartes Tuiles
        String[] nomTuiles = {"La Porte de Bronze", "La Porte de Fer", "La Porte d'Or", "La Porte d'Argent", "Heliport", 
        "La Porte de Cuivre", "Le Pont des Abîmes","Les Falaises de l'Oubli", "La Caverne Des Ombres", "Le Palais de Corail",
        "Les Dunes de l'Illusion", "Le Jardin Des Hurlements", "La Foret Pourpre","Le Lagon Perdu", "Le Marais Brumeux", 
        "Observatoire","Le Rocher Fantôme", "La Caverne du Brasier", "Le Temple du Soleil","Le Temple de la Lune", 
        "Le Palais des Marées", "Le Val du Crépuscule","La Tour du Guet", "Le Jardin des Murmures"};
        
        Pion[] pions = {Pion.BLEU, Pion.GRIS, Pion.JAUNE, Pion.NOIR, Pion.ROUGE, Pion.VERT};
        
        for (int i = 0; i <= 24; i++) {
            if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
                pileTuile.add(new CarteTuile(nomTuiles[i], pions[i]));
            }else{
                pileTuile.add(new CarteTuile(nomTuiles[i]));
            }
        }   
        
        //Cartes Inondation
        for (int i = 0; i <= 24; i++) {
            pileInondation.add(new CarteInondation(nomTuiles[i]));
        }
        
        melangerTresor();
        melangerInondation();
        melangerTuile();
        
    }
    
    public void melangerTresor() {
        Collections.shuffle(this.pileTresor);
    }
    
    public void melangerInondation() {
        Collections.shuffle(this.pileInondation);    
    }
    
    public void melangerTuile() {
        Collections.shuffle(this.pileTuile);
    }
    
    public void piocherCarteTresor(Aventurier joueur) {
        CarteTresor cartesPiochées[] = new CarteTresor[2];
        
        if (this.pileTresor.isEmpty()) {
            remplirPileTresor();
            cartesPiochées[0] = pileTresor.get(0);
            cartesPiochées[1] = pileTresor.get(1);
            pileTresor.remove(0);
            pileTresor.remove(1);
        }else if (this.pileTresor.size() == 1) {
            cartesPiochées[0] = pileTresor.get(0);
            pileTresor.remove(0);
            remplirPileTresor();
            cartesPiochées[1] = pileTresor.get(0);
        }else if (this.pileTresor.size() >= 2) {
            cartesPiochées[0] = pileTresor.get(0);
            cartesPiochées[1] = pileTresor.get(1);
            pileTresor.remove(0);
            pileTresor.remove(1);
        }
        
        if (joueur.getCartes().size() <= 4) {
            joueur.ajouterCarte(cartesPiochées[0]);
            joueur.ajouterCarte(cartesPiochées[1]);
        }else{
            joueur.ajouterCarte(cartesPiochées[0]);
            joueur.ajouterCarte(cartesPiochées[1]);
            gererCartes();
        }
        
        
        
    }
    
    public void viderDefausseTresor() {
        this.pileDefausseTresor.clear();
    }
    
    public void viderDefausseInondation() {
        this.pileDefausseInondation.clear();
    }
    
    public void remplirPileTresor() {
        for (CarteTresor ct : pileDefausseTresor) {
            pileTresor.add(ct);
            pileDefausseTresor.remove(ct);
        }
        melangerTresor();
    }
    
    public void remplirPileInondation() {
        for (CarteInondation ci : pileDefausseInondation) {
            pileInondation.add(ci);
            pileDefausseInondation.remove(ci);
        }
        melangerInondation();
    }
    
    public void gererCartes() {
        
        // ICI SYSTEME DE DEFAUSSE DE CARTES QUAND PLUS DE 4 CARTES POSSEDEES
        
    }
//
//        CarteMonteeEau montee1 = new CarteMonteeEau("Montée des eaux");
//        CarteMonteeEau montee2 = new CarteMonteeEau("Montée des eaux");    
//
//        CarteHelicoptere helico1 = new CarteHelicoptere("Hélicoptère");
//        CarteHelicoptere helico2 = new CarteHelicoptere("Hélicoptère");
//        CarteHelicoptere helico3 = new CarteHelicoptere("Hélicoptère");
//
//        CarteSacSable sac1 = new CarteSacSable("Sac de sable");
//        CarteSacSable sac2 = new CarteSacSable("Sac de sable");
}
