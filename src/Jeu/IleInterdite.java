/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import Controleur.Controleur;
import Vue.IHM;
import Vue.JeuFenetre;
import Vue.Launcher;
import java.awt.Frame;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Romain
 */
public class IleInterdite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
//        IHM ihm = new IHM();
        
        //   T E S T   L I S T E   C A R T E S   T R E S O R S   //
        Controleur controleur = new Controleur();
        controleur.creerCarte();
        
        System.out.println("Taile pileTuile : " + controleur.getPileTuile().size());
        System.out.println("Taile pileInondation : " + controleur.getPileInondation().size());
        System.out.println("Taile pileTresor : " + controleur.getPileTresor().size());
        
        for (int i = 0; i < controleur.getPileTresor().size(); i++) {
            System.out.println("// CARTE NUMERO " + (i+1) + " //");
            System.out.println(controleur.getPileTresor().get(i).getNom());
        }


    }
    
}
