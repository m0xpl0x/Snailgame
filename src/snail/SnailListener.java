package snail;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Interpretiert die Tasten f�r die Richtung der snail.Schnecke
 * @author mark
 *
 */

public class SnailListener extends KeyAdapter {

    Schnecke schnecke1,schnecke2;

    public SnailListener(Schnecke schnecke1, Schnecke schnecke2) {
        this.schnecke1 = schnecke1;
        this.schnecke2 = schnecke2;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key_id = e.getKeyCode();
        this.setFocusable(true);

        if(key_id == KeyEvent.VK_LEFT )
        {
            schnecke1.setDirection(0);
        }
        if(key_id == KeyEvent.VK_RIGHT)
        {
            schnecke1.setDirection(1);
        }
        if(key_id == KeyEvent.VK_UP)
        {
            schnecke1.setDirection(2);
        }
        if(key_id == KeyEvent.VK_DOWN)
        {
            schnecke1.setDirection(3);
        }
        if(key_id == KeyEvent.VK_A)
        {
            schnecke2.setDirection(0);
        }
        if(key_id == KeyEvent.VK_D)
        {
            schnecke2.setDirection(1);

        }
        if(key_id ==  KeyEvent.VK_W)
        {
            schnecke2.setDirection(2);

        }
        if(key_id ==  KeyEvent.VK_S)
        {
            schnecke2.setDirection(3);

        }
    }

    private void setFocusable(boolean b) {
        // TODO Auto-generated method stub

    }

    private void requestFocus() {
        // TODO Auto-generated method stub

    }


}