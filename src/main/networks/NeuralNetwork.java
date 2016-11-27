package main.networks;

import main.neurons.Connection;
import main.neurons.Neuron;

import static main.io.DataSave.*;

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

    public void backPropagationLearning(double[] expectedOutput, double[] input){

        updateSignalErrors(expectedOutput, input);
        updateWeights();
    }

    public void learning(double[][] inputData, double[][] expectedData, int attempts){

        for (int i=0;i<attempts;i++){

            int t = 0;
            boolean isDone1 = false;

            double acceptableError = 0.01; // akceptowalny błąd obliczeń

            // błędy mse i mape

            double tmpMse;
            double tmpMape;

            double mse;
            double mape;

            System.out.println("|_Proba: "+ i + "_|");

            // t - ilość iteracji ( można ustalić maksymalną ilość iteracji po której sieć albo się nauczy albo nie )

            // Start stoper - time learning for one attempt
            long start_time = System.currentTimeMillis();

            while (!isDone1 && t<500) {

                tmpMse = 0.0;
                tmpMape = 0.0;
                int n = inputData.length;

                t++;
                isDone1 = true;

                for (int j = 0; j < expectedData.length; j++) {

                    backPropagationLearning(expectedData[j], inputData[j]);

                    // Wyliczenie mse (błędu średniokwadratowego)

                    tmpMse += Math.pow(getLayerOutput().neurons.get(0).getError(),2.);

                    // Wyliczenie mape (błędu procentowego)

                    if(expectedData[j][0] != 0.)
                        tmpMape += Math.abs(getLayerOutput().neurons.get(0).getError()/expectedData[j][0]);
                    else
                        n--;
                }

                if ((Math.abs(expectedData[0][0] - computeOutput(inputData[0])[0]) > acceptableError)||(Math.abs(expectedData[1][0] - computeOutput(inputData[1])[0]) > acceptableError)||(Math.abs(expectedData[2][0] - computeOutput(inputData[2])[0]) > acceptableError)||(Math.abs(expectedData[3][0] - computeOutput(inputData[3])[0]) > acceptableError)) {
                    isDone1 = false;
                }

                mse = tmpMse/(double)inputData.length;
                mape = tmpMape*100./(double)(n!=0?n:1);

                saveMse("wyniki_2",i,t,mse);
                saveMape("wyniki_2",i,t,mape);

                System.out.println("MSE: " + mse);
                System.out.println("MAPE: " + mape + "%");
            }

            long end_time = System.currentTimeMillis();
            long difference = end_time - start_time; // time in miliseconds

            System.out.println(difference + " miliseconds");

            saveTime("wyniki_2", i, difference);
            saveNumberOfEpochs("wyniki_2", i, t);

            // Zostawiam ostatnią próbę aby testować już na zmodyfikowanych wagach
            if (i != attempts-1){
                clearNeuronsInLayers();
                System.out.println("LAST ITERATION: " + t);
            }
        }

        saveAverageTime("wyniki_2",attempts);
        saveAverageNumberOfEpochs("wyniki_2",attempts);
    }

    private void updateSignalErrors(double[] expectedOutput, double[] input) {

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
    }

    private void updateWeights() {

        // Modyfikacja wag - alogrytm wstecznej propagacji z regułą delta
        layerOutput.setWeights();

        for (Layer layerHidden : layersHidden) {

            layerHidden.setWeights();
        }
    }

    public void testValidation(double[][] inputData){

        System.out.println("XOR: " + "x1: " + 0 + " x2: " + 0 + " -> " + computeOutput(inputData[0])[0]
                + " x1: " + 0 + " x2: " + 1 + " -> " + computeOutput(inputData[1])[0]
                + " x1: " + 1 + " x2: " + 0 + " -> " + computeOutput(inputData[2])[0]
                + " x1: " + 1 + " x2: " + 1 + " -> " + computeOutput(inputData[3])[0]);
    }

    public Layer getLayerOutput() {
        return layerOutput;
    }

    private void clearNeuronsInLayers(){

        for (Neuron neuron: layerInput.neurons){
            for (Connection connection: neuron.getConnections()){
                connection.clearWeights();
            }
            neuron.clearNeuron();
        }

        for (int i=0;i<layersHidden.length;i++){
            for (Neuron neuron: layersHidden[i].neurons){
                for (Connection connection: neuron.getConnections()){
                    connection.clearWeights();
                }
                neuron.clearNeuron();
            }
        }

        for (Neuron neuron: layerOutput.neurons){
            for (Connection connection: neuron.getConnections()){
                connection.clearWeights();
            }
            neuron.clearNeuron();
        }
    }
}
