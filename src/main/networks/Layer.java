package main.networks;

import main.neurons.Neuron;

import java.util.ArrayList;

public class Layer {

    // neurony należące do warstwy
    public ArrayList<Neuron> neurons;

    public Layer(int numberOfNeurons) {

        try {

            if (numberOfNeurons < 1) {

                throw(new Exception("Number of neurons cannot be smaller than 1"));
            }
            else{

                neurons = new ArrayList<>();

                for(int i=0; i<numberOfNeurons; i++){

                    neurons.add(new Perceptron());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void addNeuron(Neuron neuron){

        neurons.add(neuron);
    }

    public void connectLayers(Layer layer){

        for (Neuron neuron: neurons) {
            neuron.addLayerConnections(layer);
        }
    }

    public void setOutputs(double[] out){

        int i=0;

        for (Neuron neuron: neurons){

            neuron.setOutSignal(out[i++]);
        }
    }

    public double[] returnOutputs() {

        int size = neurons.size();
        int i=0;

        double[] out = new double[size];

        for (Neuron neuron: neurons){

            out[i++] = neuron.getOutSignal();
        }

        return out;
    }

    public void computeAllNeuronsOutputs(){

        for (Neuron neuron: neurons){

            neuron.computeOutputSignal();
        }
    }

    public void clearErrors(){

        for (Neuron neuron: neurons){

            neuron.setError(0.0);
        }
    }

    public void computeErrors(double[] expectedOutputs){

        int i=0;

        for (Neuron neuron: neurons){

            neuron.computeError(expectedOutputs[i++]);
        }
    }

    public void setWeights(){

        for (Neuron neuron: neurons){

            neuron.changeWeight();
        }
    }

    public void changeErrorsBackwards(){

        for (Neuron neuron: neurons){

            neuron.changeErrorsBackwards();
        }
    }
}
