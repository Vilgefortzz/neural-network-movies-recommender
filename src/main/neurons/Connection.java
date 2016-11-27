package main.neurons;

import com.sun.istack.internal.NotNull;
import java.util.Random;

public class Connection {

    public Neuron inputNeuron;
    public double inputWeight;

    public Connection(@NotNull Neuron inputNeuron) {

        this.inputNeuron = inputNeuron;
        this.inputWeight = new Random().nextGaussian();
    }

    public double getWeightedSignal() {

        return inputWeight * inputNeuron.getOutSignal(); // sygnał wejściowy dla neuronu
    }

    public void clearWeights(){
        this.inputWeight = new Random().nextGaussian();
    }

}
