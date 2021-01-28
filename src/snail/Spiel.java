package snail;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Spiel extends JPanel implements Runnable, ActionListener {

    private Thread thread;
    private boolean running = false;

    public static final int WIDTH = 720, HEIGHT = 440; //Feldgroessee
    //private Image feld;


    SpielLogik spielLogik;

    SpielDaten spieldaten;

    MovementHandler movementHandler;


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

        spielLogik = new SpielLogik();

        spieldaten = new SpielDaten(spielLogik);

        this.setFocusable(true);


        setPreferredSize(new Dimension(WIDTH, HEIGHT));


        //for the keyevents
        addKeyListener(new SnailListener(spieldaten.getSchnecke(0),spieldaten.getSchnecke(1)));
        movementHandler = new MovementHandler();

        start();

    }


    @Override
    public void actionPerformed(ActionEvent k) {

        running = spielLogik.checkifWinner(spieldaten.getSchneckenFeld());
        if (running) {
            stop();
        }

        spielLogik.checkifWinner(
                spieldaten.getSchneckenFeld()
        );

        spielLogik.checkObjectCollision(
                spieldaten.getErdbeerenFeld(),
                spieldaten.getPilzefeld(),
                spieldaten.getBlumenFeld(),
                spieldaten.getSchneckenFeld()
        );

        /* Bewegt die Schnecke */


        movementHandler.canMove(spieldaten.getSchneckenFeld());


        if(spieldaten.getSchneckenFeld().size() > 2) {
            spieldaten.removeFromSchneckenFeld(0);
            spieldaten.removeFromSchneckenFeld(1);
        }
    }

    public void paint(Graphics g) {

        if (spieldaten.getSchneckenFeld().size() == 0) {
            Schnecke schnecke1 = new Schnecke(5, 2, 40,"taps");
            spieldaten.addToSchneckenFeld(0,schnecke1);
            Schnecke schnecke2 = new Schnecke(5, 5, 40,"pips");
            spieldaten.addToSchneckenFeld(1,schnecke2);

        }

        g.drawImage(spieldaten.getImageFeld(), 0, 0, WIDTH, HEIGHT, this);

        spieldaten.getSchnecke(0).draw(g);
        spieldaten.getSchnecke(1).draw2(g);

        for (Blume blume : spieldaten.getBlumenFeld()) {
            blume.draw(g);
        }

        for (Pilz pilz : spieldaten.getPilzefeld()) {
            pilz.draw(g);
        }

        for (Erdbeere erdbeere : spieldaten.getErdbeerenFeld()) {
            erdbeere.draw(g);
        }
    }
}
