/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hadik;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class Had {

    private int x = 0;
    private int y = 0;
    private double cas = 0;
    private String smer = "";
    private BufferedImage mlok = null;
    private int Score = 0;
    private LinkedList<String> cesta = new LinkedList<String>();
    private HighScore HS = new HighScore();
    private String Meno = "";

    Had() {
//        try {
//            mlok = ImageIO.read(new File("C:\\Users\\MSI\\OneDrive\\Počítač\\mlok.png"));
//        } catch (IOException ex) {
//            Logger.getLogger(Had.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Meno = (String) JOptionPane.showInputDialog(
                null,
                "MENO:\n",
                 "Zadaj meno hráča",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        if (Meno == null || Meno.isEmpty()) System.exit(0);
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

    public void update() {
        int rychlost = 200;
        if (this.getScore() >= 10) {
            rychlost -= 70;
        }
        if (this.getScore() >= 20) {
            rychlost -= 30;
        }
        if (this.getScore() >= 30) {
            rychlost -= 15;
        }
        if (this.getScore() >= 40) {
            rychlost -= 17;
        }
        if (this.getScore() >= 50) {
            rychlost -= 15;
        }
        if (this.getScore() >= 60) {
            rychlost -= 10;
        }

        if (System.currentTimeMillis() - cas > rychlost) {
            cas = System.currentTimeMillis();
            if ("doprava".equals(smer)) {
                x++;
                if (x >= 35) {
                    x = 0;
                }
            }
            if ("dolava".equals(smer)) {
                x--;
                if (x <= -1) {
                    x = 34;
                }
            }
            if ("dole".equals(smer)) {
                y++;
                if (y >= 30) {
                    y = 0;
                }
            }
            if ("hore".equals(smer)) {
                y--;
                if (y <= -1) {
                    y = 29;
                }
            }
            cesta.add(smer);
            cesta.removeFirst();
            skontroluj();
        }
    }

    public String getSmer() {
        return smer;
    }

    public void setSmer(String smer) {
        this.smer = smer;
    }

    public BufferedImage getMlok() {
        return mlok;
    }

    public void setMlok(BufferedImage mlok) {
        this.mlok = mlok;
    }

    public void vykresliHad(Graphics2D grfka) {
        // grfka.drawImage(this.getMlok(), this.getX(), this.getY(), null);
        grfka.setColor(Color.red);
        zmenFarbu(grfka);
        grfka.fillRect(this.getX() * 20, this.getY() * 20, 20, 20);
        if (!cesta.isEmpty()) {
            int xxx = this.x;
            int yyy = this.y;
            for (int i = cesta.size() - 1; i >= 0; i--) {
                String direksn = cesta.get(i);
                if ("doprava".equals(direksn)) {
                    xxx--;
                    if (xxx <= -1) {
                        xxx = 34;
                    }
                }
                if ("dolava".equals(direksn)) {
                    xxx++;
                    if (xxx >= 35) {
                        xxx = 0;
                    }
                }
                if ("dole".equals(direksn)) {
                    yyy--;
                    if (yyy <= -1) {
                        yyy = 29;
                    }
                }
                if ("hore".equals(direksn)) {
                    yyy++;
                    if (yyy >= 30) {
                        yyy = 0;
                    }
                }
                grfka.fillRect(xxx * 20, yyy * 20, 20, 20);
            }
        }

    }

    public void spapaj(Jedlo M) {
        if (this.getX() == M.getX() && this.getY() == M.getY()) {
            M.nahodnaPozicia();
            Score += 1;
            if (cesta.isEmpty()) {
                cesta.addFirst(this.getSmer());
            } else {
                cesta.addFirst(cesta.getFirst());
            }
        }
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public void skontroluj() {
        int xxx = this.x;
        int yyy = this.y;
        if (!cesta.isEmpty()) {
            for (int i = cesta.size() - 1; i >= 0; i--) {
                String direksn = cesta.get(i);
                if ("doprava".equals(direksn)) {
                    xxx--;
                    if (xxx <= -1) {
                        xxx = 34;
                    }
                }
                if ("dolava".equals(direksn)) {
                    xxx++;
                    if (xxx >= 35) {
                        xxx = 0;
                    }
                }
                if ("dole".equals(direksn)) {
                    yyy--;
                    if (yyy <= -1) {
                        yyy = 29;
                    }
                }
                if ("hore".equals(direksn)) {
                    yyy++;
                    if (yyy >= 30) {
                        yyy = 0;
                    }
                }
                if (xxx == this.getX() && yyy == this.getY()) {
                    reset();
                    break;
                }
            }
        }
    }

    public void reset() {
        HS.ulozDoZoznamu(this.getMeno()+ ":" + this.getScore());
        HS.uloz();
        this.setScore(0);
        this.setX(0);
        this.setY(0);
        cesta.clear();
    }

    public void zmenFarbu(Graphics2D grfka) {

        if (this.getScore() >= 10) {
            grfka.setColor(Color.pink);
        }
        if (this.getScore() >= 20) {
            grfka.setColor(Color.blue);
        }
        if (this.getScore() >= 30) {
            grfka.setColor(Color.cyan);
        }
        if (this.getScore() >= 40) {
            grfka.setColor(Color.WHITE);
        }
        if (this.getScore() >= 50) {
            grfka.setColor(Color.GREEN);
        }
        if (this.getScore() >= 60) {
            grfka.setColor(Color.YELLOW);
        }

    }

    public String getMeno() {
        return Meno;
    }

    public void setMeno(String Meno) {
        this.Meno = Meno;
    }
    

}
