/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import sun.java2d.pipe.AlphaColorPipe;

/**
 *
 * @author Romain
 */
public class JeuFenetre extends JFrame{
    private int stateFrame; // Mode d'affichage (fullscreen, fenêtré)
    private boolean undecorated; // Afficher les style par defaut
    private StartMenu startMenu;
    private HashMap<String, BufferedImage> images;
    private HashMap<String, MediaPlayer> musiques;
    
    public JeuFenetre(HashMap<String, BufferedImage> images, HashMap<String, MediaPlayer> musiques) throws IOException{
        super();
        this.stateFrame = JFrame.NORMAL;
        this.undecorated = false;
        this.images = images;
        this.musiques = musiques;
        
        this.setTitle("Ile Interdite");
        this.setSize(1080,720);
        this.setLocationRelativeTo(null); // Permet de centrer la fenêtre sur l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setExtendedState(stateFrame); // On définit le mode d'affichage
        this.setUndecorated(undecorated); // On choisi d'afficher ou non les style par défaut
        
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        
        this.setMinimumSize(new Dimension(1080, 720));
        
        this.startMenu = new StartMenu(images, musiques); 
        startMenu.setSize(this.getSize());
        this.add(startMenu, BorderLayout.CENTER);
        
        this.setVisible(true);
        this.setAlwaysOnTop(true); //# Permet de redonner le focus a la fenêtre s'il a été perdu
        this.setAlwaysOnTop(false);//#
    }
    
    public void paint(Graphics g){
        double scale;
        if(getWidth() <= getHeight()){
            scale = (double)getWidth()/2000*2;
        }else{
            scale = (double)getHeight()/2000*2;
        }
        startMenu.getBackgroundOverlay().setScaleOver(scale);
        startMenu.repaint();
    }
}
