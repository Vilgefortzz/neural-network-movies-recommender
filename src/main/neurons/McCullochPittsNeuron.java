package main.neurons;

import java.util.Random;

public class McCullochPittsNeuron extends Neuron{

    public McCullochPittsNeuron(double[][] inputData) {
        super(inputData);
    }

    @Override
    protected double activationFunction(double sumSignal) {

        // Funkcja aktywacji - liniowa
        return sumSignal;
    }

    @Override
    public void applyLearningRuleOneParametr() {

    }

    @Override
    public void applyLearningRuleTwoParameters() {

        int j=0;
        double error;
        boolean isDone;
        int countErrors;

        do {

            isDone=true;
            error=0.0;
            countErrors=0;
            System.out.println("Iteracja: "+ j +" w1: "+w1+" w2: "+w2);

            j++;

            for (int i = 0; i < inputData.length; i++) {

                computeOutSignal();

                System.out.println("Oczekiwana: " + inputData[i][2] + " Otrzymana: " + outSignal);
                error=inputData[i][2]-outSignal;

                if(error!=0){
                    isDone=false;
                    countErrors++;
                }

                w1 = updateWeight(w1, LEARNING_RATE, error, inputData[i][0]);
                w2 = updateWeight(w2, LEARNING_RATE, error, inputData[i][1]);
                bias = updateBias(bias, LEARNING_RATE, error);
            }
            System.out.println("Iteracja "+(j-1)+" liczba bledow: "+countErrors);
        } while(!isDone);
    }

    private double updateWeight(double weight, double n, double y, double x){
        return new Random().nextGaussian();
    }
    private double updateBias(double b, double n, double y) {return b+(n*(y));}

    public double testTwoParameters(double x1, double x2){

        computeSumSignalsTwoParameters(w1,w2,x1,x2,bias);
        computeOutSignal();
        return outSignal;
    }
}
