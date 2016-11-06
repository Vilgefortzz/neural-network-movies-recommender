package main.neurons;

import java.util.Random;

import static main.io.FilesSave.*;

public class Perceptron extends Neuron{

    public Perceptron(double[][] inputData) {
        super(inputData);
    }

    @Override
    protected double activationFunction(double sumSignal) {

        if (sumSignal>=0)
            return 1;

        return 0;
    }

    @Override
    public void applyLearningRuleOneParametr() {

        boolean isDone;
        int j=0;
        double error;
        int countErrors;

        do {

            error=0.0;
            countErrors=0;
            isDone=true;

            System.out.println("Iteracja: "+j+" W1: " + w1);

            j++;

            for (int i = 0; i < inputData.length; i++) {

                computeSumSignalsOneParametr(w1, inputData[i][0], bias);
                computeOutSignal();

                error = inputData[i][1] - outSignal;

                if(error!=0) {
                    isDone=false;
                    countErrors++;
                }

                System.out.println("Oczekiwana: " + inputData[i][1] + " Otrzymana: " + outSignal);

                w1 = updateWeight(w1, LEARNING_RATE, error, inputData[i][0]);
                bias = updateBias(bias, LEARNING_RATE, error);

            }
            System.out.println("Iteracja "+(j-1)+" liczba bledow: "+countErrors);
        } while(!isDone);

    }

    @Override
    public void applyLearningRuleTwoParameters(int attempts) {

        for (int i=0;i<attempts;i++){

            int j=0;
            double error;
            boolean isDone;
            int countErrors;

            double tmpMse;
            double tmpMape;

            double mse;
            double mape;

            System.out.println("|_Proba: "+ i + "_|");

            // Start stoper - time learning for n attempt
            long tStart = System.nanoTime();

            do {

                isDone=true;
                error=0.0;
                countErrors=0;
                System.out.println("Iteracja: "+ j +" w1: "+w1+" w2: "+w2);

                tmpMse = 0.0;
                tmpMape = 0.0;

                mse = 0.0;
                mape = 0.0;

                j++;

                int n = inputData.length;

                for (int k = 0; k < inputData.length; k++) {

                    computeSumSignalsTwoParameters(w1, w2, inputData[k][0], inputData[k][1], bias);
                    computeOutSignal();

                    double expectedOutSignal = inputData[k][2];

                    error = expectedOutSignal - outSignal;

                    // Wyliczenie mse (błędu średniokwadratowego)

                    tmpMse += Math.pow(error,2.);

                    // Wyliczenie mape (błędu procentowego)

                    if (expectedOutSignal != 0){

                        tmpMape += Math.abs(error/expectedOutSignal);
                    }
                    else
                        n--;


                    if(error!=0){
                        isDone=false;
                        countErrors++;
                    }

                    System.out.println("Oczekiwana: " + inputData[k][2] + " Otrzymana: " + outSignal);

                    w1 = updateWeight(w1, LEARNING_RATE, error, inputData[k][0]);
                    w2 = updateWeight(w2, LEARNING_RATE, error, inputData[k][1]);
                    bias = updateBias(bias, LEARNING_RATE, error);
                }

                mse = tmpMse/(double)inputData.length;
                mape = tmpMape*100./(double)(n!=0?n:1);

                saveMse(i,j,mse);
                saveMape(i,j,mape);

                System.out.println("Iteracja "+(j-1)+" liczba bledow: "+countErrors);
                System.out.println("MSE: " + mse);
                System.out.println("MAPE: " + mape);

            } while(!isDone);

            long tEnd = System.nanoTime();
            long tRes = tEnd - tStart; // time in nanoseconds
            System.out.println(tRes);

            saveTime(i, tRes);
            saveEpochNumbers(i, j);

            if (i != attempts-1){
                this.setW1(((double)new Random().nextInt(11))/10.0);
                this.setW2(((double)new Random().nextInt(11))/10.0);
                this.setBias(0);
            }

        }

        saveAverageTime(attempts);
        saveAverageEpochs(attempts);
    }

    private double updateWeight(double weight, double n, double y, double x){
        return (weight+(n*(y)*x));
}
    private double updateBias(double b, double n, double y) {return b+(n*(y));}

    public double testTwoParameters(double x1, double x2){

        computeSumSignalsTwoParameters(w1,w2,x1,x2,bias);
        computeOutSignal();
        return outSignal;
    }

    public double testOneParameter(double x1){

        computeSumSignalsOneParametr(w1,x1,bias);
        computeOutSignal();
        return outSignal;
    }
}
