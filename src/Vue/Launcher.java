/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author Romain
 */
public class Launcher extends JFrame {
    public Launcher() throws IOException{
        this.setTitle("Ile Interdite");
        this.setSize(600,250);
        this.setLocationRelativeTo(null); // Permet de centrer la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setUndecorated(true); // Désactive les styles par défaut, notamment la toolbar.
        this.setAlwaysOnTop(true); // Donne a la fenêtre une "priorité maximale". 
        
        ImagePanel background = new ImagePanel("src/Images/Launcher.jpg"); // Panel servant de background
        this.add(background);
       
    }
}
