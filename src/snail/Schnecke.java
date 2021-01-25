package snail;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 * Die Klasse der Schnecke
 * @author mark
 */

public class Schnecke extends Objekt implements ImageObserver  {

    private final Image schnecke_links, schnecke_rechts, schnecke_vorne, schnecke_hinten, schnecke2_links, schnecke2_rechts, schnecke2_vorne, schnecke2_hinten;
    private int blumenanzahl,steps;
    private final String name;
    private Direction direction;
    private Speed speed;

    public Schnecke(int x, int y, int groesse,String name)
    {
        super(x,y,groesse);
        blumenanzahl = 0;
        speed = Speed.NORMAL;
        steps = 0;
        this.name = name;
        direction = Direction.LEFT;

        ImageIcon icon_schnecke_links = new ImageIcon("pips_left.png");
        schnecke_links = icon_schnecke_links.getImage();

        ImageIcon icon_schnecke_rechts = new ImageIcon("pips_right.png");
        schnecke_rechts = icon_schnecke_rechts.getImage();

        ImageIcon icon_schnecke_vorne = new ImageIcon("pips_front.png");
        schnecke_vorne = icon_schnecke_vorne.getImage();

        ImageIcon icon_schnecke_hinten = new ImageIcon("pips_back.png");
        schnecke_hinten = icon_schnecke_hinten.getImage();

        ImageIcon icon_schnecke2_links = new ImageIcon("taps_left.png");
        schnecke2_links = icon_schnecke2_links.getImage();

        ImageIcon icon_schnecke2_rechts = new ImageIcon("taps_right.png");
        schnecke2_rechts = icon_schnecke2_rechts.getImage();

        ImageIcon icon_schnecke2_vorne = new ImageIcon("taps_front.png");
        schnecke2_vorne = icon_schnecke2_vorne.getImage();

        ImageIcon icon_schnecke2_hinten = new ImageIcon("taps_back.png");
        schnecke2_hinten = icon_schnecke2_hinten.getImage();
    }

    public void draw(Graphics g) {
        //ImageIcon icon_schnecke = new ImageIcon("pips_left.png");
        //schnecke = icon_schnecke.getImage();
        /* direction :  0 = left; 1 = right; 2 = up; 3 = down */

        //Schnecke Pfeiltasten

        if(direction == Direction.LEFT) {
            g.drawImage(schnecke_links, x * width, y * height, width, height, this);
        }
        if(direction == Direction.RIGHT) {
            g.drawImage(schnecke_rechts, x * width, y * height, width, height, this);
        }
        if(direction == Direction.UP) {
            g.drawImage(schnecke_hinten, x * width, y * height, width, height, this);
        }
        if(direction == Direction.DOWN) {
            g.drawImage(schnecke_vorne, x * width, y * height, width, height, this);
        }
    }
    public void draw2(Graphics g) {

        //Schnecke WASD

        if(direction == Direction.LEFT) {
            g.drawImage(schnecke2_links, x * width, y * height, width, height, this);
        }
        if(direction == Direction.RIGHT) {
            g.drawImage(schnecke2_rechts, x * width, y * height, width, height, this);
        }
        if(direction == Direction.UP) {
            g.drawImage(schnecke2_hinten, x * width, y * height, width, height, this);
        }
        if(direction == Direction.DOWN) {
            g.drawImage(schnecke2_vorne, x * width, y * height, width, height, this);
        }
    }

    public int getBlumenAnzahl() {
        return blumenanzahl;
    }
    public int getSteps() { return steps; }
    public Speed getSpeed() { return speed; }
    public Direction getDirection() { return direction; }
    public String getName() { return name; }


    public void erhoeheBlumen() {
        blumenanzahl++;
    }
    public void erhoeheSteps() {
        steps++;
    }
    public void resetSteps() {
        steps=0;
    }

    public void setSpeed(Speed speed) { this.speed = speed; }

    public void setDirection(Direction direction) { this.direction = direction; }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {

        return false;
    }

}
