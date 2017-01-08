package main.hopfield.networks;

import main.hopfield.Neuron;

import java.util.ArrayList;

public class Layer {

    public ArrayList<Neuron> neurons;
    private boolean ifChanged = false;

    public Layer(int numberOfNeurons){

        try {
            if (numberOfNeurons < 1) {
                throw(new Exception("Number of neurons cannot be smaller than 1"));
            }else{
                neurons = new ArrayList<>();
                for(int i=0;i<numberOfNeurons;i++){
                    neurons.add(new Neuron(i));
                }

                connectNeurons(this);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void connectNeurons(Layer layer){
        for (Neuron neuron: neurons) {
            neuron.addLayerDataInputs(layer);
        }
    }

    public void setWeights(int[][] data){
        for(Neuron neuron: neurons){
            neuron.changeWeight(data);
        }
    }

    public void printWeights(){
        for(Neuron neuron: neurons){
            neuron.printConnectionWeights();
        }
    }

    public boolean changeAllPerceptronsStates(){ // zmiany stanow do nauki sieci
        ifChanged=false;
        for(Neuron neuron: neurons){
            boolean test = neuron.changeState();
            if(test==true) ifChanged=true;
        }
        return ifChanged;
    }

    public int[] returnOutput(int[] input){

        int[] output = new int[neurons.size()];
        setAllPerceptronsStates(input);

        while(!changeAllPerceptronsStates()){
            System.out.println("Setting states");
        }
        for(Neuron neuron: neurons){
            output[neuron.getId()]=(int)neuron.getState();
        }

        return output;
    }

    public void setAllPerceptronsStates(int[] input){ //uzywana do uzyskiwania outputu, tylko dla ustawionej sieci (stany wejsciowe)
        for(Neuron neuron : neurons){
            neuron.setState(input[neuron.getId()]);
        }
    }
}
