package snail;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Spiel extends JPanel implements Runnable, ActionListener {

    private Thread thread;
    private boolean running = false;

    public static final int WIDTH = 720, HEIGHT = 440; //Feldgroessee
    private Image feld;

    private Schnecke schnecke1;
    private Schnecke schnecke2;


    private final ArrayList<Schnecke> schneckenFeld;
    private final ArrayList<Blume> blumenFeld;
    private ArrayList<Pilz> pilzeFeld;
    private ArrayList<Erdbeere> erdbeerenFeld;


    /* Startet das Spiel
     * @author malte*/

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
        Timer t = new Timer(350, this);
        t.start();
    }

    /* beendet das Spiel
     * @author malte*/

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /* Refresht das Spiel
     * @author malte*/

    public void run() {
        while (running) {
            repaint();
        }
    }

    public Spiel() {
        this.setFocusable(true);


        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        //for the random spots
        Random r = new Random();

        schneckenFeld = new ArrayList<Schnecke>();
        erdbeerenFeld = new ArrayList<Erdbeere>();
        pilzeFeld = new ArrayList<Pilz>();
        blumenFeld = new ArrayList<Blume>();

        schnecke1 = new Schnecke(15, 2, 40,"taps");
        schneckenFeld.add(schnecke1);
        schnecke2 = new Schnecke(5, 5, 40, "pips");
        schneckenFeld.add(schnecke2);

        //for the keyevents
        addKeyListener(new SnailListener(schnecke1,schnecke2));

        start();

            while (blumenFeld.size() < 5) {
                int x = r.nextInt(18);
                int y = r.nextInt(11);

                Blume blume = new Blume(x, y, 40,"blume_rot.png");
                blumenFeld.add(blume);

                ImageIcon icon_feld = new ImageIcon("feld.jpg");
                feld = icon_feld.getImage();

            }

        /*erzeugt Schnecke */

        if (schneckenFeld.size() == 0) {
            schnecke1 = new Schnecke(5, 2, 40,"taps");
            schneckenFeld.add(schnecke1);
            schnecke2 = new Schnecke(5, 5, 40, "pips");
            schneckenFeld.add(schnecke2);
        }

    }

    private void checkifWinner(Schnecke snail) {
        if (snail.getBlumenAnzahl() == 3) {
            System.out.println(snail.getName() + " hat gewonnen");
            stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent k) {

    checkifWinner(schnecke1);
    checkifWinner(schnecke2);

        /* Erzeugt Erdbeeren*/

        while(erdbeerenFeld.size() < 2) {
            Random r = new Random();
            int x = r.nextInt(18);
            int y = r.nextInt(11);

            Erdbeere erdbeere = new Erdbeere(x, y, 40,"erdbeere.png");
            erdbeerenFeld.add(erdbeere);
        }

        /* Erzeugt Pilze*/

        while(pilzeFeld.size() < 2) {
            Random r = new Random();
            int x = r.nextInt(18);
            int y = r.nextInt(11);

            Pilz pilz = new Pilz(x, y, 40,"pilz.png");
            pilzeFeld.add(pilz);
        }


        checkObjectCollision(schnecke1);
        checkObjectCollision(schnecke2);


        /* Bewegt die Schnecke */
        canMove(schnecke1.getDirection(),schnecke1.getSpeed(),schnecke1.getSteps(),schnecke1);
        canMove(schnecke2.getDirection(),schnecke2.getSpeed(),schnecke2.getSteps(),schnecke2);

        if(schneckenFeld.size() > 2) {
            schneckenFeld.remove(0);
            schneckenFeld.remove(1);
        }


    }

    /**
     * Bewegt die Schnecke auf dem Spielfeld
     * Pr�ft die Geschwindigkeit
     * @author malte
     */


    private void checkObjectCollision(Schnecke snail) {

        //Erdbeere

        for(int i = 0; i < erdbeerenFeld.size(); i++)
        {
            if(snail.getx() == erdbeerenFeld.get(i).getx() && snail.gety() == erdbeerenFeld.get(i).gety())
            {
                erdbeerenFeld.remove(i);
                System.out.println("schneller Speed");
                snail.resetSteps();
                snail.setSpeed(2);
                // t.setDelay(100);

            }
        }

        //Pilze

        for(int i = 0; i < pilzeFeld.size(); i++)
        {
            if(snail.getx() == pilzeFeld.get(i).getx() && snail.gety() == pilzeFeld.get(i).gety())
            {
                pilzeFeld.remove(i);
                System.out.println("Langsamer Speed");
                snail.resetSteps();
                snail.setSpeed(0);
                // t.setDelay(450);

            }
        }

        //Blumen

        for(int i = 0; i <= blumenFeld.size() - 1; i++)
        {
            if(snail.getx() == blumenFeld.get(i).getx() && snail.gety() == blumenFeld.get(i).gety())
            {
                blumenFeld.remove(i);
                snail.erhoeheBlumen();
            }
        }
    }

    private void canMove(int direction,int speed, int steps,Schnecke snail) {

        manageSteps(speed,steps,snail);

        if(hasNoWallCollision(direction,snail)) {




            if ((speed == 1) && (steps % 2 == 0)) {        //normal
                moveSnail(direction, snail);
            } else if (speed == 2) {                         //schnell
                moveSnail(direction, snail);
            } else if ((speed == 0) && (steps % 3 == 0)) {   //langsam
                moveSnail(direction, snail);
            }
        }

    }

    private void manageSteps(int speed, int steps,Schnecke snail) {

        /* speed : 0 = langsam; 1 = normal; 2 = schnell */

        if ((speed == 0 && steps == 8) ||(speed == 2 && steps == 14) ) {
            snail.resetSteps();
            speed = 1;
            System.out.println("normaler Speed");
        }
        if (speed == 1 && steps > 29) {
            snail.resetSteps();
        }
            snail.erhoeheSteps();


//        moveSnail(schnecke1.getDirection(),schnecke1);
//        moveSnail(schnecke2.getDirection(),schnecke2);
    }

    private void moveSnail(int direction, Schnecke snail ) {



        /* direction :  0 = left; 1 = right; 2 = up; 3 = down */

        switch(direction)
        {
            case 0: //links
                    snail.setx(snail.getx() -1);
            case 1: //rechts
                    snail.setx(snail.getx() + 1);
            case 2: //unten
                    snail.sety(snail.gety()-1);
            case 3: //hoch
                    snail.sety(snail.gety()+1);

        }

    }

    /* direction :  0 = left; 1 = right; 2 = up; 3 = down */
    /* Checkt, ob Die Schnecke am Spielfeldrand steht
     * @author malte*/

    private boolean hasNoWallCollision(int direction, Schnecke snail) {

        switch(direction) {

            case 0 :
                if (snail.getx() == 0) {
                    return false;
                }
                
            case 1 :

                if (snail.getx() == 17) {
                    return false;
                }

            case 2 :
                if (snail.gety() == 0) {
                    return false;
                }

            case 3 :
                if (snail.gety() == 10) {
                    return false;
                }
                break;

        }
        return true;
    }

    /** Erzeugt das Spielfeld mit Raster
     * Width = 18
     * Height = 11
     * und zeigt die erzeugtem Objekte auf dem Feld an
     * @author malte */


    public void paint(Graphics g) {

        if (schneckenFeld.size() == 0) {
            schnecke1 = new Schnecke(5, 2, 40,"taps");
            schneckenFeld.add(schnecke1);
            schnecke2 = new Schnecke(5, 5, 40,"pips");
            schneckenFeld.add(schnecke2);

        }

        g.drawImage(feld, 0, 0, WIDTH, HEIGHT, this);

        schnecke1.draw(g);
        schnecke2.draw2(g);

        for (Blume blume : blumenFeld) {
            blume.draw(g);
        }

        for (Pilz pilz : pilzeFeld) {
            pilz.draw(g);
        }

        for (Erdbeere erdbeere : erdbeerenFeld) {
            erdbeere.draw(g);
        }
    }


}
