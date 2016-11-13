package main.networks;

public class NeuralNetwork {

//    Warstwy wchodzące w skład sieci neuronowej - wielowarstwowa sieć neuronowa
//    3-warstwowa sieć neuronowa : warstwa wejściowa, ukryta oraz wyjściowa

    private Layer layerInput, layerOutput;
    private Layer[] layersHidden;

    public NeuralNetwork(int sizeOfInput, int sizeOfHidden, int sizeOfOutput, int numberOfHidden){

        layerInput = new Layer(sizeOfInput);
        layerOutput = new Layer(sizeOfOutput);
        layersHidden = new Layer[numberOfHidden];

        for (int i=0; i<numberOfHidden; i++) {

            layersHidden[i] = new Layer(sizeOfHidden);
        }

        layerOutput.connectLayers(layersHidden[numberOfHidden-1]);

        for (int i=(numberOfHidden-1); i>0; i--) {

            layersHidden[i].connectLayers(layersHidden[i-1]);
        }

        layersHidden[0].connectLayers(layerInput);
    }

    public double[] computeOutput(double[] input){

        // Warstwa wejściowa - 2 neurony, 2 parametry
        layerInput.setOutputs(input);

        // Warstwy ukryte - tyle neuronów ile damy na warstwę ukrytą
        for (Layer layerHidden : layersHidden) {

            layerHidden.computeAllNeuronsOutputs();
        }

        // Warstwa wyjściowa - 1 neuron posiadający sygnał wyjściowy sieci
        layerOutput.computeAllNeuronsOutputs();

        // Sygnał wyjściowy sieci
        return layerOutput.returnOutputs();
    }

    public void learning(double[] expectedOutput, double[] input){

        layerOutput.clearErrors();

        for (Layer layerHidden : layersHidden) {

            layerHidden.clearErrors();
        }

        // Obliczanie sygnałów na warstwach + komunikacja
        double[] outSignals = computeOutput(input);

        for (double outSignal : outSignals) {

            System.out.println("Out signal from output Layer: " + outSignal);
        }

        // Obliczanie błędu regułą delta - różnicy między oczekiwaną wartością a tym co dostajemy
        layerOutput.computeErrors(expectedOutput);

        // Wsteczna propagacja błędów warstwy ukrytej wykrytych w warstwie odbierającej sygnały - outputu
        layerOutput.changeErrorsBackwards();

        // Wsteczna propagacja błędów warstw ukrytych o jeden wcześniej aż do warstwy inputu
        for (int i = (layersHidden.length-1); i>0; i--){

            layersHidden[i].changeErrorsBackwards();
        }

        // Modyfikacja wag - alogrytm wstecznej propagacji z regułą delta
        layerOutput.setWeights();

        for (Layer layerHidden : layersHidden) {

            layerHidden.setWeights();
        }
    }
}
