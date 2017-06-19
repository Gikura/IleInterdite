/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Romain
 */
public class Overlay extends JPanel{

    private static final int FULL = 0;          //#
    private static final int CENTER = 1;        //#
    private static final int FULL_HEIGHT = 2;   //# Différents profiles d'images
    private static final int FULL_WIDTH = 3;    //#
    private static final int CENTERX = 4;       //#
    private static final int CENTERY = 5;       //#
    private static final int NORMAL = 6;        //#

    /**
     * @return the FULL
     */
    public static int getFULL() {
        return FULL;
    }

    /**
     * @return the CENTER
     */
    public static int getCENTER() {
        return CENTER;
    }

    /**
     * @return the FULL_HEIGHT
     */
    public static int getFULL_HEIGHT() {
        return FULL_HEIGHT;
    }

    /**
     * @return the FULL_WIDTH
     */
    public static int getFULL_WIDTH() {
        return FULL_WIDTH;
    }

    /**
     * @return the CENTERX
     */
    public static int getCENTERX() {
        return CENTERX;
    }

    /**
     * @return the CENTERY
     */
    public static int getCENTERY() {
        return CENTERY;
    }

    /**
     * @return the NORMAL
     */
    public static int getNORMAL() {
        return NORMAL;
    }
    
    private int marginXOver, marginYOver; //# Valeur a ajouter a la marge
    private int marginXFond, marginYFond; //#
    private int xOver, yOver; // Marge en X et/ou en Y de l'overlay
    private int xFond, yFond; // Marge en X et/ou en Y du fond
    private BufferedImage over, fond; // Images pré-chargée
    private int profileOver, profileFond; // Profils des images
    private Dimension dimOver, dimFond; // Dimenssions des images
    private double scaleOver, scaleFond; // Facteur de taille des images
     
        private void Overlay(BufferedImage over, BufferedImage fond, int marginXOver, int marginYOver, int marginXFond, int marginYFond){
            this.setOver(over);
            this.setFond(fond);
            this.setMarginXOver(marginXFond);
            this.setMarginYOver(marginYOver);
            this.setMarginXFond(marginXFond);
            this.setMarginYFond(marginYFond);
        }

        public Overlay(BufferedImage over, BufferedImage fond, int profileOver, int marginXOver, int marginYOver, int profileFond, int marginXFond, int marginYFond, double scaleOver, double scaleFond ) { // Taille relative a la taille du fichier image
            Overlay(over, fond, marginXOver, marginYOver, marginXFond,marginYFond);
            this.profileOver = profileOver;
            this.profileFond = profileFond; 
            this.scaleOver = scaleOver;
            this.scaleFond = scaleFond;
        }
        
        public Overlay(BufferedImage over, BufferedImage fond, Dimension dimOver, int marginXOver, int marginYOver, Dimension dimFond, int marginXFond, int marginYFond ) { // Taille définie en px
            Overlay(over, fond, marginXOver, marginYOver, marginXFond,marginYFond);
            this.dimOver = dimOver;
            this.dimFond = dimFond;
        }
        
        public void setProfiles(){
            switch(getProfileOver()){ // Définition des différents profiles
                case 0 :    // Cas FULL
                    this.setDimOver(new Dimension(getWidth(), getHeight()));                    
                    break;
                case 1 :    // Cas CENTER
                    this.setDimOver(new Dimension((int)(((double)getOver().getWidth())*getScaleOver()), (int)(((double)getOver().getHeight())*getScaleOver())));
                    this.setxOver((int) (((double)getWidth()/2) - ((double) getDimOver().getWidth() / 2)));
                    this.setyOver((int) (((double)getHeight()/2) - ((double) getDimOver().getHeight() / 2)));
                    break;
                case 2 :    // Cas FULL_HEIGHT
                    int width = (int)((((double)getHeight()/(double)getOver().getHeight()))*((double)getOver().getWidth()));
                    this.setDimOver(new Dimension(width, getHeight()));
                    this.setxOver((int) (((double)getWidth()/2) - ((double) getDimOver().getWidth() / 2)));
                    break;
                case 3 :    // Cas FULL_WIDTH
                    int height = (int)((((double)getWidth()/(double)getOver().getWidth()))*((double)getOver().getHeight()));
                    this.setDimOver(new Dimension(getWidth(), height));
                    this.setyOver((int) (((double)getHeight()/2) - ((double) getDimOver().getHeight() / 2)));
                    break;
                case 4 :    // Cas CENTERX
                    this.setDimOver(new Dimension((int)(((double)getOver().getWidth())*getScaleOver()), (int)(((double)getOver().getHeight())*getScaleOver())));
                    this.setxOver((int) (((double)getWidth()/2) - ((double) getDimOver().getWidth() / 2)));
                    break;
                case 5 :    //Cas CENTERY
                    this.setDimOver(new Dimension((int)(((double)getOver().getWidth())*getScaleOver()), (int)(((double)getOver().getHeight())*getScaleOver())));
                    this.setyOver((int) (((double)getHeight()/2) - ((double) getDimOver().getHeight() / 2)));
                    break;
                case 6 :   // Cas NORMAL
                    this.setDimOver(new Dimension(getOver().getWidth(), getOver().getHeight()));
                    break;
                default :
                    break;
            }
            switch(getProfileFond()){ // Définition des différents profiles
                case 0 :    // Cas FULL
                    this.setDimFond(new Dimension(getWidth(), getHeight()));                    
                    break;
                case 1 :    // Cas CENTER
                    this.setDimFond(new Dimension((int)(((double)getFond().getWidth())*getScaleFond()), (int)(((double)getFond().getHeight())*getScaleFond())));
                    this.setxFond((int) (((double)getWidth()/2) - ((double) getDimFond().getWidth() / 2)));
                    this.setyFond((int) (((double)getHeight()/2) - ((double) getDimFond().getHeight() / 2)));
                    break;
                case 2 :    // Cas FULL_HEIGHT
                    int width = (int)((((double)getHeight()/(double)getFond().getHeight()))*((double)getFond().getWidth()));
                    this.setDimFond(new Dimension(width, getHeight()));
                    this.setxFond((int) (((double)getWidth()/2) - ((double) getDimFond().getWidth() / 2)));
                    break;
                case 3 :    // Cas FULL_WIDTH
                    int height = (int)((((double)getWidth()/(double)getFond().getWidth()))*((double)getFond().getHeight()));
                    this.setDimFond(new Dimension(getWidth(), height));
                    this.setyFond((int) (((double)getHeight()/2) - ((double) getDimFond().getHeight() / 2)));
                    break;
                case 4 :    // Cas CENTERX
                    this.setDimFond(new Dimension((int)(((double)getFond().getWidth())*getScaleFond()), (int)(((double)getFond().getHeight())*getScaleFond())));
                    this.setxFond((int) (((double)getWidth()/2) - ((double) getDimFond().getWidth() / 2)));
                    break;
                case 5 :    //Cas CENTERY
                    this.setDimFond(new Dimension((int)(((double)getFond().getWidth())*getScaleFond()), (int)(((double)getFond().getHeight())*getScaleFond())));
                    this.setyFond((int) (((double)getHeight()/2) - ((double) getDimFond().getHeight() / 2)));
                    break;
                case 6 :   // Cas NORMAL
                    this.setDimFond(new Dimension(getFond().getWidth(), getFond().getHeight()));
                    break;
                default :
                    break;
            }
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            setProfiles();
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g.drawImage(getFond(), getxFond() + getMarginXFond(), getyFond() + getMarginYFond(), getDimFond().width, getDimFond().height, null);            
            if(getOver() == null){
                System.out.println("t1");
            }

            if(getDimOver() == null){
                System.out.println("1t");
            }
            g2d.drawImage(getOver(), getxOver() + getMarginXOver(), getyOver() + getMarginYOver(), getDimOver().width, getDimOver().height, null);
            g2d.dispose();
        }

    /**
     * @return the marginXOver
     */
    public int getMarginXOver() {
        return marginXOver;
    }

    /**
     * @param marginXOver the marginXOver to set
     */
    public void setMarginXOver(int marginXOver) {
        this.marginXOver = marginXOver;
    }

    /**
     * @return the marginYOver
     */
    public int getMarginYOver() {
        return marginYOver;
    }

    /**
     * @param marginYOver the marginYOver to set
     */
    public void setMarginYOver(int marginYOver) {
        this.marginYOver = marginYOver;
    }

    /**
     * @return the marginXFond
     */
    public int getMarginXFond() {
        return marginXFond;
    }

    /**
     * @param marginXFond the marginXFond to set
     */
    public void setMarginXFond(int marginXFond) {
        this.marginXFond = marginXFond;
    }

    /**
     * @return the marginYFond
     */
    public int getMarginYFond() {
        return marginYFond;
    }

    /**
     * @param marginYFond the marginYFond to set
     */
    public void setMarginYFond(int marginYFond) {
        this.marginYFond = marginYFond;
    }

    /**
     * @return the xOver
     */
    public int getxOver() {
        return xOver;
    }

    /**
     * @param xOver the xOver to set
     */
    public void setxOver(int xOver) {
        this.xOver = xOver;
    }

    /**
     * @return the yOver
     */
    public int getyOver() {
        return yOver;
    }

    /**
     * @param yOver the yOver to set
     */
    public void setyOver(int yOver) {
        this.yOver = yOver;
    }

    /**
     * @return the xFond
     */
    public int getxFond() {
        return xFond;
    }

    /**
     * @param xFond the xFond to set
     */
    public void setxFond(int xFond) {
        this.xFond = xFond;
    }

    /**
     * @return the yFond
     */
    public int getyFond() {
        return yFond;
    }

    /**
     * @param yFond the yFond to set
     */
    public void setyFond(int yFond) {
        this.yFond = yFond;
    }

    /**
     * @return the over
     */
    public BufferedImage getOver() {
        return over;
    }

    /**
     * @param over the over to set
     */
    public void setOver(BufferedImage over) {
        this.over = over;
    }

    /**
     * @return the fond
     */
    public BufferedImage getFond() {
        return fond;
    }

    /**
     * @param fond the fond to set
     */
    public void setFond(BufferedImage fond) {
        this.fond = fond;
    }

    /**
     * @return the profileOver
     */
    public int getProfileOver() {
        return profileOver;
    }

    /**
     * @param profileOver the profileOver to set
     */
    public void setProfileOver(int profileOver) {
        this.profileOver = profileOver;
    }

    /**
     * @return the profileFond
     */
    public int getProfileFond() {
        return profileFond;
    }

    /**
     * @param profileFond the profileFond to set
     */
    public void setProfileFond(int profileFond) {
        this.profileFond = profileFond;
    }

    /**
     * @return the dimOver
     */
    public Dimension getDimOver() {
        return dimOver;
    }

    /**
     * @param dimOver the dimOver to set
     */
    public void setDimOver(Dimension dimOver) {
        this.dimOver = dimOver;
    }

    /**
     * @return the dimFond
     */
    public Dimension getDimFond() {
        return dimFond;
    }

    /**
     * @param dimFond the dimFond to set
     */
    public void setDimFond(Dimension dimFond) {
        this.dimFond = dimFond;
    }

    /**
     * @return the scaleOver
     */
    public double getScaleOver() {
        return scaleOver;
    }

    /**
     * @param scaleOver the scaleOver to set
     */
    public void setScaleOver(double scaleOver) {
        this.scaleOver = scaleOver;
    }

    /**
     * @return the scaleFond
     */
    public double getScaleFond() {
        return scaleFond;
    }

    /**
     * @param scaleFond the scaleFond to set
     */
    public void setScaleFond(double scaleFond) {
        this.scaleFond = scaleFond;
    }
    }