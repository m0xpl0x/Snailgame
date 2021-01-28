package snail;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class SpielLogik {

    public SpielLogik() {

    }



    public boolean checkifWinner(ArrayList<Schnecke> schneckenFeld) {

        for (Schnecke snail : schneckenFeld) {

            if (snail.getBlumenAnzahl() == 3) {
                 System.out.println(snail.getName() + " hat gewonnen");
                 return false;
            }
        }
        return true;
    }

    public void erzeugeObjekte(ArrayList<Erdbeere> erdbeerenFeld,ArrayList<Pilz> pilzeFeld) {
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
    }







    public void checkObjectCollision(ArrayList<Erdbeere> erdbeerenFeld,ArrayList<Pilz> pilzeFeld,ArrayList<Blume> blumenFeld,ArrayList<Schnecke> schneckenFeld) {

        for (Schnecke snail : schneckenFeld) {
            //Erdbeere

            for (int i = 0; i < erdbeerenFeld.size(); i++) {
                if (snail.getx() == erdbeerenFeld.get(i).getx() && snail.gety() == erdbeerenFeld.get(i).gety()) {
                    erdbeerenFeld.remove(i);
                    System.out.println("schneller Speed");
                    snail.resetSteps();
                    snail.setSpeed(Speed.FAST);

                }
            }

            //Pilze

            for (int i = 0; i < pilzeFeld.size(); i++) {
                if (snail.getx() == pilzeFeld.get(i).getx() && snail.gety() == pilzeFeld.get(i).gety()) {
                    pilzeFeld.remove(i);
                    System.out.println("Langsamer Speed");
                    snail.resetSteps();
                    snail.setSpeed(Speed.SLOW);

                }
            }

            //Blumen

            for (int i = 0; i <= blumenFeld.size() - 1; i++) {
                if (snail.getx() == blumenFeld.get(i).getx() && snail.gety() == blumenFeld.get(i).gety()) {
                    blumenFeld.remove(i);
                    snail.erhoeheBlumen();
                }
            }
        }
    }
}
