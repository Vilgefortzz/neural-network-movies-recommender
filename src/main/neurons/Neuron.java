package main.neurons;

import main.networks.Layer;

import java.util.ArrayList;
import java.util.Random;

public abstract class Neuron {

    // szybkość uczenia

    public static final double LEARNING_RATE = 0.6;

    // sygnały wejściowe - dane wejściowe

    protected double inputData[][];

    // połączenia z innymi neuronami

    protected ArrayList<Connection> connections;

    // bias

    protected double bias;

    // wagi

    protected double w1, w2;

    // błąd

    protected double error;

    // Suma sygnałów gotowych do funkcji aktywacji

    protected double sumSignal;

    // sygnał wyjściowy danego neuronu

    protected double outSignal;


    public Neuron(double[][] inputData) {

        Random rand = new Random();

        w1=((double)rand.nextInt(11))/10.0;
        w2=((double)rand.nextInt(11))/10.0;

        this.bias = 0.0;
        this.inputData = inputData;
    }

    public Neuron() {

        Random rand = new Random();

        w1=((double)rand.nextInt(11))/10.0;
        w2=((double)rand.nextInt(11))/10.0;

        this.bias = 0.0;
        this.error = 0.0;
        this.connections = new ArrayList<>();
    }

    public double getSumSignal() {
        return sumSignal;
    }

    public double getOutSignal(){
        return outSignal;
    }

    public void setOutSignal(double outSignal) {
        this.outSignal = outSignal;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public void setW1(double w1) {
        this.w1 = w1;
    }

    public void setW2(double w2) {
        this.w2 = w2;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
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

    private double computeSumSignals() {

        double sum = 0;

        for (Connection connection:connections) {

            sum += connection.getWeightedSignal();
        }

        return sum;
    }

    public void computeOutSignal() {

        this.outSignal = activationFunction(sumSignal);
    }

    // ustalanie randomowych wag dla neuronu

    public void clearNeuron(){

        Random rand = new Random();

        w1=((double)rand.nextInt(11))/10.0;
        w2=((double)rand.nextInt(11))/10.0;

        this.bias = 0.0;
        this.error = 0.0;
    }

    // funkcja aktywacji - różna dla różnych neuronów

    protected abstract double activationFunction(double sumSignal);

    // ustawienie metody uczenia z jednym parametrem

    public abstract void applyLearningRuleOneParametr();

    // ustawienie metody uczenia z dwoma parametrami

    public abstract void applyLearningRuleTwoParameters(int attempts);


    // ------------------------------------------ DLA SIECI NEURONOWEJ -------------------------------------------------

    // ustawienie połączeń z innymi neuronami

    public void addConnection(Neuron neuron){

        connections.add(new Connection(neuron));
    }

    public void addLayerConnections(Layer layer){

        for(Neuron neuron : layer.neurons){
            addConnection(neuron);
        }
    }

    // Reguła delta ( Widrowa-Hoffa ) w oparciu o algorytm wstecznej propagacji
    public void changeWeight(){

        for (Connection connection: connections){
            connection.inputWeight += LEARNING_RATE * error * computeDerivative(outSignal) * connection.inputNeuron.outSignal;
        }

        bias += LEARNING_RATE * error * computeDerivative(outSignal) * 1.0;
    }

    public void computeError(double expectedOutSignal){

        this.error = expectedOutSignal - this.outSignal;
    }

    public void computeOutputSignal(){

        // suma sygnałów
        double sum = computeSumSignals();

        sum += bias * 1.0;

        // sygnał wyjściowy
        this.outSignal = activationFunction(sum);
    }

    private double computeDerivative(double x){

        return x * (1.0 - x);
    }

    public void changeErrorsBackwards(){

        for (Connection connection: connections){
            connection.inputNeuron.error += connection.inputWeight * error;
        }
    }
}
