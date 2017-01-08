package main.hopfield.neurons;

import com.sun.istack.internal.NotNull;
import main.hopfield.Neuron;

import java.util.Random;

public class Connection {

    public Neuron inputNeuron;
    public double inputWeight;

    public Connection(@NotNull Neuron inputNeuron) {

        this.inputNeuron = inputNeuron;
        this.inputWeight = new Random().nextGaussian();
    }

    public void clearWeights(){
        this.inputWeight = new Random().nextGaussian();
    }
}