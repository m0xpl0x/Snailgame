package snail;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SpielDaten {


    //constructor / init

    public SpielDaten(SpielLogik spiellogik) {
        schneckenFeld = new ArrayList<Schnecke>();
        erdbeerenFeld = new ArrayList<Erdbeere>();
        pilzeFeld = new ArrayList<Pilz>();
        blumenFeld = new ArrayList<Blume>();

        Schnecke schnecke1 = new Schnecke(15, 2, 40,"taps");
        schneckenFeld.add(schnecke1);
        Schnecke schnecke2 = new Schnecke(5, 5, 40, "pips");
        schneckenFeld.add(schnecke2);

        while (blumenFeld.size() < 5) {
            Random r = new Random();
            int x = r.nextInt(18);
            int y = r.nextInt(11);

            Blume blume = new Blume(x, y, 40,"blume_rot.png");
            blumenFeld.add(blume);



        }
        spiellogik.erzeugeObjekte(erdbeerenFeld,pilzeFeld);



    }





    //data

    private ArrayList<Erdbeere> erdbeerenFeld;
    private ArrayList<Pilz> pilzeFeld;
    private ArrayList<Blume> blumenFeld;
    private ArrayList<Schnecke> schneckenFeld;
    private Image feld;





    //methods

    public void addToErdbeerenfeldFeld(int index,Erdbeere erdbeere) {
        erdbeerenFeld.add(erdbeere);
    }
    public ArrayList<Erdbeere> getErdbeerenFeld() {
        return erdbeerenFeld;
    }

    public void removeFromErdbeerenFeld(int index) {
        erdbeerenFeld.remove(index);
    }



    public void addToPilzefeld(int index,Pilz pilz) {
        pilzeFeld.add(pilz);
    }
    public ArrayList<Pilz> getPilzefeld() {
        return pilzeFeld;
    }
    public void removeFromPilzeFeld(int index) {
        pilzeFeld.remove(index);
    }




    public void addToBlumenFeld(int index,Blume blume) {
        blumenFeld.add(blume);
    }
    public ArrayList<Blume> getBlumenFeld() {
        return blumenFeld;
    }
    public void removeFromBlumenFeld(int index) {
        blumenFeld.remove(index);
    }


    public void addToSchneckenFeld(int index,Schnecke schnecke) {
        schneckenFeld.add(schnecke);
    }
    public ArrayList<Schnecke> getSchneckenFeld() {
        return schneckenFeld;
    }

    public void removeFromSchneckenFeld(int index) {
        schneckenFeld.remove(index);
    }

    public Image getImageFeld() {
        return feld;
    }

    public Schnecke getSchnecke(int index) {
        return schneckenFeld.get(index);
    }

}
