/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hadik;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author MSI
 */
public class Jedlo {

    private int x = 0;
    private int y = 0;
    private int a = 0;
    private int b = 0;
    private BufferedImage mafin = null;

    Jedlo() {
        try {
            File mufin = new File ("src\\hadik\\pics\\mafin.png");
            System.out.println(mufin.getAbsolutePath());     
            mafin = ImageIO.read(mufin);
        } catch (IOException ex) {
            Logger.getLogger(Had.class.getName()).log(Level.SEVERE, null, ex);
        }
        nahodnaPozicia();    
    }

    public void vykresliJedlo(Graphics2D grfka) {
            grfka.drawImage(this.mafin, this.x * 20, this.y * 20, null);
    }

    public void nahodnaPozicia() {
        Random pozicia = new Random();
        
        this.x = pozicia.nextInt(0, 35);
        this.y = pozicia.nextInt(0, 30);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
