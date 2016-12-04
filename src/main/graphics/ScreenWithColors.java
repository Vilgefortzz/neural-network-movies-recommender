package main.graphics;

import main.networks.KohonenNetwork;
import main.neurons.NeuronKohonen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ScreenWithColors extends JPanel{

    private int width;
    private int height;
    private int squareSize;
    private KohonenNetwork kohonenNetwork;

    // stworzenie sieci Kohonena i wyświetlenie okna z losowymi kolorami
    // nastepnie jest zaimplementowany algorytm WTA czyli sieci samoorganizujacych sie
    // proces re-organizacji jest widoczny - nastepuje przemalowywanie kwadratow
    // w koncowym etapie widac gotowa mape kolorow 2D

    public ScreenWithColors(int w, int h, int size) {

        this.height = h;
        this.width = w;
        this.squareSize = size;

        this.kohonenNetwork = new KohonenNetwork("data_set/learning_set/colors.dat", w / size, h / size, this);
        this.init();

        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setVisible(true);
    }

    public KohonenNetwork getKohonenNetwork() {
        return this.kohonenNetwork;
    }

    // Inicjalizacja z losowymi wartosciami kolorow
    private void init() {
        for (int i = 0; i < this.height / this.squareSize; i++) {
            for (int j = 0; j < this.width / this.squareSize; j++) {
                // Set random colors
                double red = Math.random();
                double green = Math.random();
                double blue = Math.random();
                // Create the neuron and add it to the kohonenNetwork.
                this.kohonenNetwork.addNeuron(new ArrayList<>(Arrays.asList(red, green, blue)), i, j);
            }
        }
    }

    // Rysowanie kwadracikow z kolorami
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < this.height / this.squareSize; i++) {
            for (int j = 0; j < this.width / this.squareSize; j++) {
                NeuronKohonen n = this.kohonenNetwork.getNeuron(i, j);
                ArrayList<Double> colors = n.getWeights();
                g.setColor(new Color((int)(colors.get(0)*255), (int)(colors.get(1)*255), (int)(colors.get(2)*255)));
                // Fill a rectangle with the color
                g.fillRect(j * this.squareSize, i * this.squareSize, this.squareSize, this.squareSize);
            }
        }
    }
}