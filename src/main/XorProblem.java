package main;

import main.networks.NeuralNetwork;

public class XorProblem {

    public static void main(String[] args) {

        // Sieć neuronowa

//         Schemat: 1-2-1
//
//         1.ilość neuronów na warstwie wejściowej - 2 wejścia -> 2 neurony
//         2.ilość neuronów na warstwie ukrytej,
//         3.ilość neuronów na warstwie wyjściowej - 1 wyjście -> 1 neuron
//         4.liczba warstw ukrytych

        NeuralNetwork neuralNetwork = new NeuralNetwork(2, 50, 1, 2);

        // Sieć neuronowa - różne metody uczenia sieci

        // Uczenie bramki logicznej XOR - sprawdzenie działania sieci wielowarstwowej
        // 1 perceptron nie jest w stanie się nauczyć

        double[][] dataXOR = {{0, 0, 0}, {0, 1, 1}, {1, 0, 1}, {1, 1, 0}};

        double[][] inputData = {{0,0},{0,1},{1,0},{1,1}};
        double[][] expectedData ={{0},{1},{1},{0}};

        neuralNetwork.learning(inputData, expectedData, 50);
        neuralNetwork.testValidation(inputData);

    }
}
