package main.neurons;

import java.util.ArrayList;

public class NeuronKohonen {

    // Array with the weights.
    private ArrayList<Double> weights;
    private int x;
    private int y;

    public NeuronKohonen(ArrayList<Double> w) {
        this.weights = w;
    }

    public NeuronKohonen(ArrayList<Double> w, int x, int y) {
        this.weights = w;
        this.x = x;
        this.y = y;
    }

    public ArrayList<Double> getWeights() {
        return weights;
    }

    public Double getWeightI(int i) {
        return this.weights.get(i);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setWeights(ArrayList<Double> weights) {
        this.weights = weights;
    }

    public void setWeightI(int i, double value) {
        this.weights.set(i, value);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSizeWeights() {
        return this.weights.size();
    }

}