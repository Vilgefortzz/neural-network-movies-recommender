package main;

import main.kohonen.graphics.ScreenWithColors;

import javax.swing.*;

public class KohonenApplication {

    public static void main(String[] args) {

        final int height = 500;
        final int width = 500;

        // stworzenie okna i panelu wyswietlajacego

        JFrame frame = new JFrame();
        ScreenWithColors screenWithColors = new ScreenWithColors(width, height, 50);
        frame.add(screenWithColors, "Center");

        frame.setTitle("Color classification by Kohonen Network");
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Algorytm WTA
        // Tutaj nastepuje grupowanie tych kolorow
        //screenWithColors.getKohonenNetwork().WTA();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Algorytm WTM
        // Tutaj nastepuje grupowanie tych kolorow
        screenWithColors.getKohonenNetwork().WTM();
    }
}