package snail;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public abstract class Objekt implements ImageObserver {

    protected int x,y,width,height;
    protected Image image;


    public Objekt(int x, int y, int groesse,String filename)
    {
        this.x = x;
        this.y = y;
        width = groesse;
        height = groesse;
        ImageIcon icon_erdbeere = new ImageIcon(filename);
        image = icon_erdbeere.getImage();

    }

    public Objekt(int x, int y, int groesse)
    {
        this.x = x;
        this.y = y;
        width = groesse;
        height = groesse;

    }

    public void draw(Graphics g) {
        g.drawImage(image, x * width, y * height, width, height, this);
    }

    public int getx() {
        return x;
    }
    public void setx(int x) {
        this.x = x;
    }
    public int gety() {
        return y;
    }
    public void sety(int y) {
        this.y = y;
    }



    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        // TODO Auto-generated method stub
        return false;
    }

}

