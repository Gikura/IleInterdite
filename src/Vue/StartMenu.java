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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Romain
 */
public class StartMenu extends JPanel{
    private Overlay background;
    private Runnable r;
    private HashMap<String, BufferedImage> images;
    private HashMap<String, MediaPlayer> musiques;
    
    
    public StartMenu(HashMap<String, BufferedImage> images, HashMap<String, MediaPlayer> musiques) throws IOException{
        super(new BorderLayout());
        this.images = images;
        this.musiques = musiques;

        this.background = new Overlay(images.get("logo"), images.get("menuBg5"), Overlay.getCENTERX(), 0, 50, Overlay.getFULL_HEIGHT(), 0, 0, 2, 1);
        background.setBackground(Color.BLACK);
       
        this.add(background, BorderLayout.CENTER);
        
        MediaPlayer musique = this.musiques.get("menu");
        musique.setStartTime(new Duration(0));
        musique.setStopTime(musique.getMedia().getDuration());
        musique.setCycleCount(MediaPlayer.INDEFINITE);
        musique.setAutoPlay(true);
        
        randomBackground(true);
    }
    
    public void randomBackground(boolean b){
        r = new Runnable() {
            public void run() {
                int i = 5;
                while(b){
                    BufferedImage img;
                    try {
                        img = ImageIO.read(new File("src/Images/MenuBackgrounds/" + i + ".jpg"));
                        background.setFond(img);
                        background.repaint();
                    } catch (IOException ex) {
                        Logger.getLogger(JeuFenetre.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JeuFenetre.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    i = (i%10)+1;
                }
            }
        };
        new Thread(r).start();
    }
    
    public Overlay getBackgroundOverlay(){
        return this.background;
    }
    
}
