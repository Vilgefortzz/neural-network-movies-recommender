package main;

public class BasicLogicGates {

    public static void main(String[] args){

        // Uczenie podstawowych bramek logicznych and, or, not - perceptron jest się w stanie nauczyć

        double[][] dataAND = {{0, 0, 0}, {0, 1, 0}, {1, 0, 0}, {1, 1, 1}};
        double[][] dataOR = {{0, 0, 0}, {0, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        double[][] dataNOT = {{0, 1}, {1, 0}};

        Controller controller = new Controller();

//        controller.createMcCullochPittsNeuron(dataAND);
//        controller.learningMcCullohPittsNeuron(2,1);
//
//        System.out.println("AND->>>>>>>>>>>>>>>>>>>>>");
//        System.out.println("TEST: x1: " + 1 + " x2: " + 1 + " AND: " + controller.mcCullochPittsNeuron.testTwoParameters(1, 1));
//        System.out.println("TEST: x1: " + 0 + " x2: " + 1 + " AND: " + controller.mcCullochPittsNeuron.testTwoParameters(0, 1));
//        System.out.println("TEST: x1: " + 1 + " x2: " + 0 + " AND: " + controller.mcCullochPittsNeuron.testTwoParameters(1, 0));
//        System.out.println("TEST: x1: " + 0 + " x2: " + 0 + " AND: " + controller.mcCullochPittsNeuron.testTwoParameters(0, 0));

        controller.createPerceptron(dataAND);
        controller.learningPerceptron(2,1);

        System.out.println("AND->>>>>>>>>>>>>>>>>>>>>");
        System.out.println("TEST: x1: " + 1 + " x2: " + 1 + " AND: " + controller.perceptron.testTwoParameters(1, 1));
        System.out.println("TEST: x1: " + 0 + " x2: " + 1 + " AND: " + controller.perceptron.testTwoParameters(0, 1));
        System.out.println("TEST: x1: " + 1 + " x2: " + 0 + " AND: " + controller.perceptron.testTwoParameters(1, 0));
        System.out.println("TEST: x1: " + 0 + " x2: " + 0 + " AND: " + controller.perceptron.testTwoParameters(0, 0));

        controller.createPerceptron(dataOR);
        controller.learningPerceptron(2,1);

        System.out.println("OR: ");
        System.out.println("TEST: x1: " + 0 + " x2: " + 0 + " OR: " + controller.perceptron.testTwoParameters(0, 0));
        System.out.println("TEST: x1: " + 1 + " x2: " + 0 + " OR: " + controller.perceptron.testTwoParameters(1, 0));
        System.out.println("TEST: x1: " + 0 + " x2: " + 1 + " OR: " + controller.perceptron.testTwoParameters(0, 1));
        System.out.println("TEST: x1: " + 1 + " x2: " + 1 + " OR: " + controller.perceptron.testTwoParameters(1, 1));

        controller.createPerceptron(dataNOT);
        controller.learningPerceptron(1,1);

        System.out.println("NOT->>>>>>>>>>>>>>>>>>>>>");
        System.out.println("TEST: x1: " + 1 + " NOT: " + controller.perceptron.testOneParameter(1));
        System.out.println("TEST: x1: " + 0 + " NOT: " + controller.perceptron.testOneParameter(0));


    }
}
