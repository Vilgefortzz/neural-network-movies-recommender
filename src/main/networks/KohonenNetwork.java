package main.networks;

import main.io.DataLoader;
import main.neurons.NeuronKohonen;

import javax.swing.*;
import java.util.ArrayList;

import static main.io.DataSave.saveNumberOfEpochs;
import static main.io.DataSave.saveTime;

public class KohonenNetwork {

    private int numberofIterations; // liczba iteracji

    private final double learningRate = 0.6;

    private int numberOfIterationsWidth;
    private int numberOfIterationsHeight;

    private NeuronKohonen[][] network;

    private ArrayList<ArrayList<Double>> input;

    private JPanel screen;

    public KohonenNetwork(String path, int w, int h, JPanel screen) {
        this.numberOfIterationsWidth = w;
        this.numberOfIterationsHeight = h;
        this.network = new NeuronKohonen[w][h];
        this.input = DataLoader.datStrToArrayList(path);
        this.numberofIterations = 1000;
        this.screen = screen;
    }

    public void addNeuron(ArrayList<Double> weights, int i, int j) {
        this.network[i][j] = new NeuronKohonen(weights, i, j);
    }

    public NeuronKohonen getNeuron(int i, int j) {
        return this.network[i][j];
    }

    public int getNumberOfIterationsWidth() {
        return numberOfIterationsWidth;
    }

    public int getNumberOfIterationsHeight() {
        return numberOfIterationsHeight;
    }

    public ArrayList<ArrayList<Double>> getInput() {
        return input;
    }

    private double euclideDistance(ArrayList<Double> vectorA, ArrayList<Double> vectorB) {
        double distance = 0.0;
        for (int i = 0; i < vectorA.size(); i++) {
            distance += Math.pow(vectorA.get(i) - vectorB.get(i), 2);
        }
        return Math.sqrt(distance);
    }

    // Neuron, który jest zwyciezca - najlepiej odpowie ( posiada najmniejszą wartość )
    private NeuronKohonen getBMU(ArrayList<Double> input) {
        NeuronKohonen BMU = null;
        double distanceMin = Double.MAX_VALUE;
        for (int i = 0; i < this.numberOfIterationsHeight; i++) {
            for (int j = 0; j < this.numberOfIterationsWidth; j++) {
                double distanceTmp = this.euclideDistance(input, this.network[i][j].getWeights());
                if (distanceTmp < distanceMin) {
                    distanceMin = distanceTmp;
                    BMU = this.network[i][j];
                }
            }
        }
        return BMU;
    }

    // Uczenie sieci Kohonena - SOM-y ( metody WTM oraz WTA )

    public void WTA() {

        ArrayList<Double> oldVector = new ArrayList<>();
        ArrayList<Double> vector;

        // Start stoper - time learning for one attempt
        long start_time = System.currentTimeMillis();

        for (int t = 0; t < this.numberofIterations; t++) {

            do {
                int randomInput = (int) (Math.random() * this.input.size());
                vector = this.input.get(randomInput);
            } while (oldVector.equals(vector));

            oldVector = new ArrayList<>(vector);

            NeuronKohonen BMU = this.getBMU(vector); // znaleziony neuron zwyciezca

            double radiusMap = this.numberOfIterationsWidth / 2;
            double lambda = this.numberofIterations / radiusMap;

            double radius = radiusMap * Math.exp(-t / lambda);

            int iMin = (BMU.getY() - radius) < 0 ? 0 : (int) (BMU.getY() - radius);
            int iMax = (BMU.getY() + radius) > this.numberOfIterationsWidth ? this.numberOfIterationsWidth : (int) (BMU.getY() + radius);
            int jMin = (BMU.getX() - radius) < 0 ? 0 : (int) (BMU.getX() - radius);
            int jMax = (BMU.getX() + radius) > this.numberOfIterationsWidth ? this.numberOfIterationsWidth : (int) (BMU.getX() + radius);

            for (int i = iMin; i < iMax; i++) {
                for (int j = jMin; j < jMax; j++) {
                    NeuronKohonen n = this.network[i][j];
                    // nowa wartosc wspolczynnika uczenia
                    double Lt = this.learningRate * Math.exp(-t / lambda);

                    for (int k = 0; k < BMU.getSizeWeights(); k++) {

                        double value = n.getWeightI(k) + Lt * (vector.get(k) - n.getWeightI(k));

                        n.setWeightI(k, value);
                        if (value > 1.0)
                            System.out.println("error");
                    }
                }
            }

            this.screen.repaint();

            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                System.out.println("Error during sleep : " + e);
            }
        }

        long end_time = System.currentTimeMillis();
        long difference = end_time - start_time; // time in miliseconds

        System.out.println(difference + " miliseconds");

        saveTime("wyniki_4", 1, difference);
        saveNumberOfEpochs("wyniki_4", 1, numberofIterations);
    }
}