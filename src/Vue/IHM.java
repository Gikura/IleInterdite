/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;

/**
 *
 * @author Romain
 */
public class IHM {
    private HashMap<String,BufferedImage> images;
    private HashMap<String,MediaPlayer> musiques;
    
    public IHM() throws IOException, InterruptedException{
        new JFXPanel();
        this.images = new HashMap<>();
        this.musiques = new HashMap<>();
        start();
    }
    
    public  void start() throws IOException, InterruptedException{
        Launcher launcher = new Launcher();        
        launcher.setVisible(true);
        loadImages();
        loadMusiques();
        for(String m : musiques.keySet()){
            musiques.get(m).setOnReady(new Runnable() {
                @Override
                public void run() { //#TODO Trouver un moyen pour le faire pour toutes les musiques
                                    //# mettre dans un thread launchervisble, ect.... et faire une boucle récursive
                    launcher.setVisible(false);
                    try {
                        JeuFenetre fenetre = new JeuFenetre(images, musiques);
                    } catch (IOException ex) {
                        Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
        
    }
    
    private void loadImages() throws IOException{
        images.put("logo", ImageIO.read(new File("src/Images/LogoEffects.png")));
        for(int i = 1; i <= 10; i++){
            images.put("menuBg" + i, ImageIO.read(new File("src/Images/MenuBackgrounds/"+ i +".jpg")));
        }
    }
    
    private void loadMusiques(){
        musiques.put("menu", new MediaPlayer(new Media(new File("src/Musiques/menu.mp3").toURI().toString())));
    }
}
