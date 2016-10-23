package main.neurons;

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

            System.out.println("Iiteracja: "+j+" W1: " + w1);

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

                computeSumSignalsTwoParameters(w1, w2, inputData[i][0], inputData[i][1], bias);
                computeOutSignal();

                error=inputData[i][2]-outSignal;

                if(error!=0){
                    isDone=false;
                    countErrors++;
                }

                System.out.println("Oczekiwana: " + inputData[i][2] + " Otrzymana: " + outSignal);

                w1 = updateWeight(w1, LEARNING_RATE, error, inputData[i][0]);
                w2 = updateWeight(w2, LEARNING_RATE, error, inputData[i][1]);
                bias = updateBias(bias, LEARNING_RATE, error);
            }
            System.out.println("Iteracja "+(j-1)+" liczba bledow: "+countErrors);
        } while(!isDone);
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
