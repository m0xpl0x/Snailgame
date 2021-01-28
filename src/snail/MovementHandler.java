package snail;

import java.util.ArrayList;

public class MovementHandler {

    private ArrayList<Schnecke> schneckenFeld;

    public MovementHandler() {
    }

    public void canMove(ArrayList<Schnecke> schneckenFeld) {

        for (Schnecke snail : schneckenFeld) {
            Speed speed = snail.getSpeed();
            int steps = snail.getSteps();
            Direction direction = snail.getDirection();


            manageSteps(speed, steps, snail);

        if (hasNoWallCollision(direction, snail)) {

            if ((speed == Speed.NORMAL) && (steps % 2 == 0)) {        //normal
                moveSnail(direction, snail);
            } else if (speed == Speed.FAST) {                         //schnell
                moveSnail(direction, snail);
            } else if ((speed == Speed.SLOW) && (steps % 3 == 0)) {   //langsam
                moveSnail(direction, snail);
            }
        }

    }

    }

    private void manageSteps(Speed speed, int steps,Schnecke snail) {

        /* speed : 0 = langsam; 1 = normal; 2 = schnell */

        if ((speed == Speed.SLOW && steps == 8) ||(speed == Speed.FAST && steps == 14) ) {
            snail.resetSteps();
            speed = Speed.NORMAL;
            System.out.println("normaler Speed");
        }
        if (speed == Speed.NORMAL && steps > 29) {
            snail.resetSteps();
        }
        snail.erhoeheSteps();

    }

    private void moveSnail(Direction direction, Schnecke snail ) {


        /* direction :  0 = left; 1 = right; 2 = up; 3 = down */

        switch(direction)
        {
            case LEFT: //links
                snail.setx(snail.getx() -1);
            case RIGHT: //rechts
                snail.setx(snail.getx() + 1);
            case UP: //unten
                snail.sety(snail.gety()-1);
            case DOWN: //hoch
                snail.sety(snail.gety()+1);

        }

    }

    private boolean hasNoWallCollision(Direction direction, Schnecke snail) {

        switch(direction) {

            case LEFT :
                if (snail.getx() == 0) {
                    return false;
                }

            case RIGHT :

                if (snail.getx() == 17) {
                    return false;
                }

            case UP :
                if (snail.gety() == 0) {
                    return false;
                }

            case DOWN:
                if (snail.gety() == 10) {
                    return false;
                }
                break;

        }
        return true;
    }
    
    

}
