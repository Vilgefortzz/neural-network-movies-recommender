package main.neurons;

public class McCullochPittsNeuron extends Neuron{

    public McCullochPittsNeuron(double[][] inputData) {
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

    }

    @Override
    public void applyLearningRuleTwoParameters(int attempts) {

    }
}
