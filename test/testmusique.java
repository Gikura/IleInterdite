
import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Romain
 */
public class testmusique {
    
    public static void main(String args[]){
        new JFXPanel();
        MediaPlayer musique = new MediaPlayer(new Media(new File("src/Musiques/menu.mp3").toURI().toString()));
        musique.play();
    }
}
