package main.hopfield.networks;

public class HopfieldNetwork {

    private Layer mainLayer; // sieć jednowarstwowa

    public HopfieldNetwork(int sizeOfInput){
        mainLayer = new Layer(sizeOfInput);
    }

    public void setWeights(int[][] data){

        mainLayer.setWeights(data);
        mainLayer.printWeights();
        System.out.println();

    }

    public int[] returnOutput(int[] input){
        return mainLayer.returnOutput(input);
    }
}