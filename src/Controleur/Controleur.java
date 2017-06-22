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
import Utils.*;
import Utils.Utils.*;

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
    private int numJoueurCourant;
    private Observateur observateur;
    private int INDICE_MONTEE_DES_EAUX = 0;
    
    public Controleur() {
        grille = new Grille();
        pileTuile = new ArrayList<>();
        pileInondation = new ArrayList<>();
        pileDefausseInondation = new ArrayList<>();
        pileTresor = new ArrayList<>();
        pileDefausseTresor = new ArrayList<>();
        listeJoueurs = new HashMap<>();
        creerCarte();
    }
    
    public void creerCarte() {
        
        toutVider();
        
        // Cartes Trésor
        for (int i = 0; i < 5; i++) {
            getPileTresor().add(new CarteTresor("Calice de l'onde"));
            getPileTresor().add(new CarteTresor("Pierre sacrée"));
            getPileTresor().add(new CarteTresor("Statue du zéphyr"));
            getPileTresor().add(new CarteTresor("Cristal ardent"));                   
            if (i < 3) {
            getPileTresor().add(new CarteTresor("Hélicoptère"));
            getPileTresor().add(new CarteTresor("Sac de sable"));
            }
            if (i < 2) {
            getPileTresor().add(new CarteTresor("Montée des eaux"));
            }
        }

        // Cartes Tuiles
        String[] nomTuiles = {"La Porte de Bronze", "La Porte de Fer", "La Porte d'Or", "La Porte d'Argent", "Heliport", 
        "La Porte de Cuivre", "Le Pont des Abîmes","Les Falaises de l'Oubli", "La Caverne Des Ombres", "Le Palais de Corail",
        "Les Dunes de l'Illusion", "Le Jardin Des Hurlements", "La Foret Pourpre","Le Lagon Perdu", "Le Marais Brumeux", 
        "Observatoire","Le Rocher Fantôme", "La Caverne du Brasier", "Le Temple du Soleil","Le Temple de la Lune", 
        "Le Palais des Marées", "Le Val du Crépuscule","La Tour du Guet", "Le Jardin des Murmures"};
        
        Pion[] pions = {Pion.ROUGE, Pion.NOIR, Pion.JAUNE, Pion.GRIS, Pion.BLEU, Pion.VERT};
        
        for (int i = 0; i < nomTuiles.length; i++) {
            if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
                pileTuile.add(new CarteTuile(nomTuiles[i], pions[i]));
            }else{
                pileTuile.add(new CarteTuile(nomTuiles[i]));
            }
        }   
        
        //Cartes Inondation
        for (int i = 0; i < 24; i++) {
            getPileInondation().add(new CarteInondation(nomTuiles[i]));
        }
        
        melangerTresor();
        melangerInondation();
        melangerTuile();
        
    }
    
    public void melangerTresor() {
        Collections.shuffle(this.getPileTresor());
    }
    
    public void melangerInondation() {
        Collections.shuffle(this.getPileInondation());    
    }
    
    public void melangerTuile() {
        Collections.shuffle(this.getPileTuile());
    }
    
    public void piocherCarteTresor(Aventurier joueur) {
        CarteTresor cartesPiochées[] = new CarteTresor[2];
        
        if (this.getPileTresor().isEmpty()) {
            remplirPileTresor();
            cartesPiochées[0] = getPileTresor().get(0);
            cartesPiochées[1] = getPileTresor().get(1);
            getPileTresor().remove(0);
            getPileTresor().remove(1);
        }else if (this.getPileTresor().size() == 1) {
            cartesPiochées[0] = getPileTresor().get(0);
            getPileTresor().remove(0);
            remplirPileTresor();
            cartesPiochées[1] = getPileTresor().get(0);
            getPileTresor().remove(0);
        }else if (this.getPileTresor().size() >= 2) {
            cartesPiochées[0] = getPileTresor().get(0);
            cartesPiochées[1] = getPileTresor().get(1);
            getPileTresor().remove(0);
            getPileTresor().remove(1);
        }
        
        if (joueur.getCartes().size() <= 3) { // Cas où le joueur n'a pas besoin de défausser une carte
            if (cartesPiochées[0].getNom() == "Montée des eaux") {
                setINDICE_MONTEE_DES_EAUX(getINDICE_MONTEE_DES_EAUX() + 1);
                getPileDefausseTresor().add(cartesPiochées[0]);
            }else{
                joueur.ajouterCarte(cartesPiochées[0]);
            }    
            if (cartesPiochées[1].getNom() == "Montée des eaux") {
                setINDICE_MONTEE_DES_EAUX(getINDICE_MONTEE_DES_EAUX() + 1);
                getPileDefausseTresor().add(cartesPiochées[1]);
            }else{
                joueur.ajouterCarte(cartesPiochées[1]);
            }    
        }else{ // Cas où on gère la défausse après avoir pioché
            if (cartesPiochées[0].getNom() == "Montée des eaux") {
                setINDICE_MONTEE_DES_EAUX(getINDICE_MONTEE_DES_EAUX() + 1);
                getPileDefausseTresor().add(cartesPiochées[0]);
            }else{
                joueur.ajouterCarte(cartesPiochées[0]);
            }    
            if (cartesPiochées[1].getNom() == "Montée des eaux") {
                setINDICE_MONTEE_DES_EAUX(getINDICE_MONTEE_DES_EAUX() + 1);
                getPileDefausseTresor().add(cartesPiochées[1]);
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
        
        if (this.getPileInondation().isEmpty()) {
            remplirPileInondation();
            for (int i = 0; i < nb; i++) {
                cartesPiochées[i] = getPileInondation().get(i);
                getPileInondation().remove(i);
            }
        }else if (this.getPileInondation().size() == 1) {
            cartesPiochées[0] = getPileInondation().get(0);
            getPileInondation().remove(0);
            remplirPileTresor();
            for (int i = 1; i < nb; i++) {
            cartesPiochées[i] = getPileInondation().get(i);
                getPileInondation().remove(i);
            }
        }else if (this.getPileInondation().size() >= nb) {
            for (int i = 0; i < nb; i++) {
                cartesPiochées[i] = getPileInondation().get(i);
                getPileInondation().remove(i);
            }
        }
        
        for (int i = 0; i < nb; i++) {
            if (getGrille().getTuile(cartesPiochées[i].getNom()).getEtatTuile() == Etat_Tuile.ASSECHEE) {
                this.getGrille().getTuile(cartesPiochées[i].getNom()).setEtatTuile(Etat_Tuile.INONDEE);
                getPileDefausseInondation().add(cartesPiochées[i]);
            }else if (getGrille().getTuile(cartesPiochées[i].getNom()).getEtatTuile() == Etat_Tuile.INONDEE) {
                this.getGrille().getTuile(cartesPiochées[i].getNom()).setEtatTuile(Etat_Tuile.COULEE);
                // on ne l'ajoute pas à la pile de défausse elle sera donc supprimée puisque la tuile est coulée
            }       
        }  
    } 
    
    public void toutVider() {
        pileTuile.clear();
        pileTresor.clear();
        viderDefausseTresor();
        pileInondation.clear();
        viderDefausseInondation();
    }
     
    public void viderDefausseTresor() {
        this.getPileDefausseTresor().clear();
    }
    
    public void viderDefausseInondation() {
        this.getPileDefausseInondation().clear();
    }
    
    public void remplirPileTresor() {
        for (CarteTresor ct : getPileDefausseTresor()) {
            getPileTresor().add(ct);
            getPileDefausseTresor().remove(ct);
        }
        melangerTresor();
    }
    
    public void remplirPileInondation() {
        for (CarteInondation ci : getPileDefausseInondation()) {
            getPileInondation().add(ci);
            getPileDefausseInondation().remove(ci);
        }
        melangerInondation();
    }
    
    public void gererCartes() {
        
        // ICI SYSTEME DE DEFAUSSE DE CARTES QUAND PLUS DE 4 CARTES POSSEDEES
        
    }

    public void ajouterJoueur (ArrayList<Aventurier> listeJ) {
        int nbJoueurs = listeJ.size();
        
        this.ordreJoueurs = new Pion[nbJoueurs];
        for (int i = 0; i < nbJoueurs; i++) {
            this.ordreJoueurs[i] = listeJ.get(i).getPionAventurier();
        }
        
        this.numJoueurCourant = -1;
        
        HashMap<Pion, Aventurier> joueursPartie = new HashMap<>();
        for (Aventurier a : listeJ) {
            joueursPartie.put(a.getPionAventurier(), a);
        }
        
        this.setListeJoueurs(joueursPartie);
        
        getGrille().installerGrille(pileTuile, listeJoueurs);
    }
    
    /**
     * @return the grille
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * @param grille the grille to set
     */
    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    /**
     * @return the pileTuile
     */
    public ArrayList<CarteTuile> getPileTuile() {
        return pileTuile;
    }

    /**
     * @param pileTuile the pileTuile to set
     */
    public void setPileTuile(ArrayList<CarteTuile> pileTuile) {
        this.pileTuile = pileTuile;
    }

    /**
     * @return the pileTresor
     */
    public ArrayList<CarteTresor> getPileTresor() {
        return pileTresor;
    }

    /**
     * @param pileTresor the pileTresor to set
     */
    public void setPileTresor(ArrayList<CarteTresor> pileTresor) {
        this.pileTresor = pileTresor;
    }

    /**
     * @return the pileDefausseTresor
     */
    public ArrayList<CarteTresor> getPileDefausseTresor() {
        return pileDefausseTresor;
    }

    /**
     * @param pileDefausseTresor the pileDefausseTresor to set
     */
    public void setPileDefausseTresor(ArrayList<CarteTresor> pileDefausseTresor) {
        this.pileDefausseTresor = pileDefausseTresor;
    }

    /**
     * @return the pileInondation
     */
    public ArrayList<CarteInondation> getPileInondation() {
        return pileInondation;
    }

    /**
     * @param pileInondation the pileInondation to set
     */
    public void setPileInondation(ArrayList<CarteInondation> pileInondation) {
        this.pileInondation = pileInondation;
    }

    /**
     * @return the pileDefausseInondation
     */
    public ArrayList<CarteInondation> getPileDefausseInondation() {
        return pileDefausseInondation;
    }

    /**
     * @param pileDefausseInondation the pileDefausseInondation to set
     */
    public void setPileDefausseInondation(ArrayList<CarteInondation> pileDefausseInondation) {
        this.pileDefausseInondation = pileDefausseInondation;
    }

    /**
     * @return the listeJoueurs
     */
    public HashMap<Pion, Aventurier> getListeJoueurs() {
        return listeJoueurs;
    }

    /**
     * @param listeJoueurs the listeJoueurs to set
     */
    public void setListeJoueurs(HashMap<Pion, Aventurier> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }

    /**
     * @return the ordreJoueurs
     */
    public Pion[] getOrdreJoueurs() {
        return ordreJoueurs;
    }

    /**
     * @param ordreJoueurs the ordreJoueurs to set
     */
    public void setOrdreJoueurs(Pion[] ordreJoueurs) {
        this.ordreJoueurs = ordreJoueurs;
    }

    /**
     * @return the observateur
     */
    public Observateur getObservateur() {
        return observateur;
    }

    /**
     * @param observateur the observateur to set
     */
    public void setObservateur(Observateur observateur) {
        this.observateur = observateur;
    }

    /**
     * @return the INDICE_MONTEE_DES_EAUX
     */
    public int getINDICE_MONTEE_DES_EAUX() {
        return INDICE_MONTEE_DES_EAUX;
    }

    /**
     * @param INDICE_MONTEE_DES_EAUX the INDICE_MONTEE_DES_EAUX to set
     */
    public void setINDICE_MONTEE_DES_EAUX(int INDICE_MONTEE_DES_EAUX) {
        this.INDICE_MONTEE_DES_EAUX = INDICE_MONTEE_DES_EAUX;
    }

    /**
     * @return the numJoueurCourant
     */
    public int getNumJoueurCourant() {
        return numJoueurCourant;
    }

    /**
     * @param numJoueurCourant the numJoueurCourant to set
     */
    public void setNumJoueurCourant(int numJoueurCourant) {
        this.numJoueurCourant = numJoueurCourant;
    }
    
}
