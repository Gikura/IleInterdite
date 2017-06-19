/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Romain
 */
public class ImagePanel extends JPanel {
    private Image image;
    public ImagePanel(String imageSRC) throws IOException{
        this.image = ImageIO.read(new File(imageSRC)); // Creation d'une image depuis un chemin
    }
    
    public void paint(Graphics g){
        int height = getHeight();
        int width = (int)((((double)getHeight()/(double)image.getHeight(this)))*((double)image.getWidth(this)));
        int x = (getWidth()/2)-(width/2);  // Coordonnée pour que l'image soit centrée.
        g.drawImage(image, x, 0, width, height, null);// On dessine l'image sur notre pannel
    }
}
