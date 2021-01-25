package snail;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GUI Klasse
 * @author zlatiana
 */

public class GUI {

    public GUI() {

        JFrame frame = new JFrame();
        frame.setTitle("S N A I L");
        frame.setSize(720, 440);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		Farbe im Hintergrund, weil die Bilder nicht gross genug sind
        Color hintergrund = new Color(224,227,136);


        /*
          Musik (Code aus dem Internet)
          @author https://stackoverflow.com/questions/11919009/using-javax-sound-sampled-clip-to-play-loop-and-stop-multiple-sounds-in-a-game
         */

        try {
            File file = new File("music.wav");
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                // load the sound into memory (a Clip)
                Clip clip = AudioSystem.getClip();
                clip.open(sound);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else {
                throw new RuntimeException("Sound: file not found: " + file);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }

        JPanel content = new JPanel();
        frame.add(content, BorderLayout.CENTER);
        content.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        CardLayout cl = new CardLayout();
        content.setLayout(cl);


        /*
          Homescreen
          @author zlatiana
         */

        JPanel Home = new JPanel();
        Home.setBackground(hintergrund);
        content.add(Home, "1");
        cl.show(content, "1");

        ImageIcon homescreen = new ImageIcon("homescreen.jpeg");
        JLabel hsLabel = new JLabel(homescreen);
        Home.add(hsLabel);

        JButton start = new JButton();
        start.setIcon(new ImageIcon("start.jpeg"));
        start.setBounds(345, 215, 175, 50);
        start.setOpaque(false);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        start.addActionListener(e -> cl.show(content, "2"));
        hsLabel.add(start);


        /*
          Spielanleitung
          @author zlatiana
         */

        JPanel Spielanleitung = new JPanel();
        Spielanleitung.setBackground(hintergrund);
        content.add(Spielanleitung, "2");
        ImageIcon spanl = new ImageIcon("regeln.jpeg");
        JLabel spanlLabel = new JLabel(spanl);
        Spielanleitung.add(spanlLabel);

        JButton play = new JButton();
        play.setIcon(new ImageIcon("play.jpeg"));
        play.setBounds(490, 325, 155, 50);
        play.setOpaque(false);
        play.setContentAreaFilled(false);
        play.setBorderPainted(false);
        play.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JPanel Spiel = new JPanel();
        Spiel.setBackground(hintergrund);
        content.add(Spiel, "3");

        play.addActionListener(e -> {
            cl.show(content, "3");
            snail.Spiel screen = new Spiel();


            Spiel.add(screen);
            screen.requestFocus(true);
        });
        spanlLabel.add(play);
    }

}

