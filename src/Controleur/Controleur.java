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
        
        Pion[] pions = {Pion.ROUGE, Pion.NOIR, Pion.JAUNE, Pion.GRIS, Pion.BLEU, Pion.VERT};
        
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
            pileTresor.remove(0);
        }else if (this.pileTresor.size() >= 2) {
            cartesPiochées[0] = pileTresor.get(0);
            cartesPiochées[1] = pileTresor.get(1);
            pileTresor.remove(0);
            pileTresor.remove(1);
        }
        
        if (joueur.getCartes().size() <= 3) { // Cas où le joueur n'a pas besoin de défausser une carte
            if (cartesPiochées[0].getNom() == "Montée des eaux") {
                INDICE_MONTEE_DES_EAUX ++;
                pileDefausseTresor.add(cartesPiochées[0]);
            }else{
                joueur.ajouterCarte(cartesPiochées[0]);
            }    
            if (cartesPiochées[1].getNom() == "Montée des eaux") {
                INDICE_MONTEE_DES_EAUX ++;
                pileDefausseTresor.add(cartesPiochées[1]);
            }else{
                joueur.ajouterCarte(cartesPiochées[1]);
            }    
        }else{ // Cas où on gère la défausse après avoir pioché
            if (cartesPiochées[0].getNom() == "Montée des eaux") {
                INDICE_MONTEE_DES_EAUX ++;
                pileDefausseTresor.add(cartesPiochées[0]);
            }else{
                joueur.ajouterCarte(cartesPiochées[0]);
            }    
            if (cartesPiochées[1].getNom() == "Montée des eaux") {
                INDICE_MONTEE_DES_EAUX ++;
                pileDefausseTresor.add(cartesPiochées[1]);
            }else{
                joueur.ajouterCarte(cartesPiochées[1]);
            }
                joueur.ajouterCarte(cartesPiochées[0]);
                joueur.ajouterCarte(cartesPiochées[1]);
                
            gererCartes();
        }
    }
    
    public void piocherCarteInondation(int nb) {
        
        CarteInondation cartesPiochées[] = new CarteInondation[nb];
        
        if (this.pileInondation.isEmpty()) {
            remplirPileInondation();
            for (int i = 0; i < nb; i++) {
                cartesPiochées[i] = pileInondation.get(i);
                pileInondation.remove(i);
            }
        }else if (this.pileInondation.size() == 1) {
            cartesPiochées[0] = pileInondation.get(0);
            pileInondation.remove(0);
            remplirPileTresor();
            for (int i = 1; i < nb; i++) {
            cartesPiochées[i] = pileInondation.get(i);
            pileInondation.remove(i);
            }
        }else if (this.pileInondation.size() >= nb) {
            for (int i = 0; i < nb; i++) {
                cartesPiochées[i] = pileInondation.get(i);
                pileInondation.remove(i);
            }
        }
        
        Tuile tuile0 = grille.getTuile(cartesPiochées[0].getNom());
        Tuile tuile1 = grille.getTuile(cartesPiochées[1].getNom());
        
        
        if (tuile0.getEtatTuile() == Etat_Tuile.ASSECHEE) {
            tuile0.setEtatTuile(Etat_Tuile.INONDEE);
            pileDefausseInondation.add(cartesPiochées[0]);
        }else if (tuile0.getEtatTuile() == Etat_Tuile.INONDEE) {
            tuile0.setEtatTuile(Etat_Tuile.COULEE);
            // on ne l'ajoute pas à la pile de défausse elle sera donc supprimée puisque la tuile est coulée
        }
        if (tuile1.getEtatTuile() == Etat_Tuile.ASSECHEE) {
            tuile1.setEtatTuile(Etat_Tuile.INONDEE);
            pileDefausseInondation.add(cartesPiochées[1]);
        }else if (tuile1.getEtatTuile() == Etat_Tuile.INONDEE) {
            tuile1.setEtatTuile(Etat_Tuile.COULEE);
            // on ne l'ajoute pas à la pile de défausse elle sera donc supprimée puisque la tuile est coulée
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
    
}
