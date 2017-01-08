package main.hopfield;

import main.hopfield.networks.Layer;
import main.hopfield.neurons.Connection;

import java.util.ArrayList;

public class Neuron {

    private int id; //id neuronu
    private double state;
    private double prev_state;

    private ArrayList<Connection> connections; //lista wszystkich polaczen

    public Neuron(int id){
        this.id=id;
        state =-1.0;
        prev_state =-1.0;
        connections = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getState() {
        return state;
    }

    public void setState(double state) {
        this.state = state;
    }

    public double getPrev_state() {
        return prev_state;
    }

    public void setPrev_state(double prev_state) {
        this.prev_state = prev_state;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<Connection> connections) {
        this.connections = connections;
    }

    public void addDataInput(Neuron neuron){
        connections.add(new Connection(neuron));
    }

    public void addLayerDataInputs(Layer layer){
        for(Neuron neuron : layer.neurons){
            addDataInput(neuron);
        }
    }

    public void changeWeight(int[][] data){ // zmiana wag wszystkich połączeń dla tego neuronu

        for(Connection connection: connections){
            if(connection.inputNeuron.id != this.id) {
                double sum = 0;
                for (int i = 0; i < data.length; i++) {
                    sum += data[i][connection.inputNeuron.id] * data[i][this.id];
                }
                connection.inputWeight = (1 / ((double)connections.size())) * sum;
            }
            else{
                connection.inputWeight = 0;
            }
        }
    }

    public void printConnectionWeights(){
        for(Connection connection: connections){
            System.out.print(connection.inputWeight + " ");
        }
        System.out.println();
    }

    public boolean changeState(){ //funkcja zmiany stanu neuronu, uzywana przy nauce jak i odczycie danych, zwraca informacje czy neuron jest stabilny (jego stan sie zmienil)
        double sum=0;

        for(Connection connection: connections) {
            sum += connection.inputWeight*connection.inputNeuron.state;  //ze strony google: agh hopfield galaxy ~vlsi
        }

        if(sum>=0) state =1;
        else if(sum<0) state =-1;

        if(state != prev_state){
            prev_state = state;
            return true;
        }
        else
            return false;
    }
}