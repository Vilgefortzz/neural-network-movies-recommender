package main.neurons;

import java.util.Random;

public abstract class Neuron {

    // szybkość uczenia

    public static final double LEARNING_RATE = 0.01;

    // sygnały wejściowe - dane wejściowe

    protected double inputData[][];

    // bias

    protected double bias;

    // wagi

    protected double w1,w2;

    // sygnał wyjściowy danego neuronu

    protected double outSignal;


    // Suma sygnałów gotowych do funkcji aktywacji

    protected double sumSignal;


    public Neuron(double[][] inputData) {

        Random rand = new Random();

        w1=((double)rand.nextInt(11))/10.0;
        w2=((double)rand.nextInt(11))/10.0;

        this.bias = 0.0;
        this.inputData = inputData;
    }

    public double getSumSignal() {
        return sumSignal;
    }

    public double getOutSignal(){
        return outSignal;
    }

    // Sumator sygnałów wejściowych

    public void computeSumSignalsTwoParameters(double w1, double w2, double x1, double x2, double bias) {

        double sum = 0;

        sum = ((w1*x1)+(w2*x2)+bias);

        this.sumSignal = sum;
    }

    public void computeSumSignalsOneParametr(double w1, double x1, double bias) {

        double sum = 0;

        sum = ((w1*x1)+bias);

        this.sumSignal = sum;
    }

    public void computeOutSignal() {

        this.outSignal = activationFunction(sumSignal);
    }

    // funkcja aktywacji - różna dla różnych neuronów

    protected abstract double activationFunction(double sumSignal);

    // ustawienie metody uczenia z jednym parametrem

    public abstract void applyLearningRuleOneParametr();

    // ustawienie metody uczenia z dwoma parametrami

    public abstract void applyLearningRuleTwoParameters();
}
