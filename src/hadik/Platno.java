/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hadik;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author MSI
 */
public class Platno extends JPanel implements KeyListener{
    
    private Had H;
    private BufferedImage jpg = new BufferedImage(700, 600, BufferedImage.TYPE_INT_RGB);
    private Graphics2D grfka;
    private Jedlo M;
    
    Platno (){
        H = new Had ();
        grfka = (Graphics2D)jpg.getGraphics();
        this.grfka.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        this.grfka.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        this.grfka.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        this.addKeyListener(this);
        setFocusable(true);
        
        M = new Jedlo ();
        
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(jpg, 0, 0, this);
       
        
        
    }
    public void run (){
        for (; ;){
            update();
            vykresliPozadie();
            paintComponent(getGraphics()); 
        }
    }
    public void update (){
        H.update();
        H.spapaj(M);
        
    }
    public void vykresliPozadie (){
        grfka.setColor(Color.black);
        grfka.fillRect(0, 0, getWidth(), getHeight());
        H.vykresliHad(grfka);
        M.vykresliJedlo(grfka);
        //M.vykresliDruheJedlo(grfka);
        grfka.setColor(Color.YELLOW);
        grfka.drawString("SCORE:"+ H.getScore(), 600,15 );
        
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        
                
       
        }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar()== 'a' || e.getKeyCode()== KeyEvent.VK_LEFT){
            H.setSmer("dolava");
        }
        if (e.getKeyChar()== 's' || e.getKeyCode()== KeyEvent.VK_DOWN){
            H.setSmer("dole");
        }
        if (e.getKeyChar()== 'd' || e.getKeyCode()== KeyEvent.VK_RIGHT){
            H.setSmer("doprava");
        }
        if (e.getKeyChar()== 'w' || e.getKeyCode()== KeyEvent.VK_UP){
            H.setSmer("hore");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
    
    
    
    
    
}
