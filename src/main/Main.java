package main;

public class Main {

    public static void main(String[] args) {

        // Uczenie podstawowych bramek logicznych and, or, not

        double[][] dataAND = {{0, 0, 0}, {0, 1, 0}, {1, 0, 0}, {1, 1, 1}};
        double[][] dataOR = {{0, 0, 0}, {0, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        double[][] dataNOT = {{0, 1}, {1, 0}};

        Controller controller = new Controller();

        controller.createPerceptron(dataAND);
        controller.learningPerceptron(2);

        System.out.println("AND->>>>>>>>>>>>>>>>>>>>>");
        System.out.println("TEST: x1: " + 1 + " x2: " + 1 + " AND: " + controller.perceptron.testTwoParameters(1, 1));
        System.out.println("TEST: x1: " + 0 + " x2: " + 1 + " AND: " + controller.perceptron.testTwoParameters(0, 1));
        System.out.println("TEST: x1: " + 1 + " x2: " + 0 + " AND: " + controller.perceptron.testTwoParameters(1, 0));
        System.out.println("TEST: x1: " + 0 + " x2: " + 0 + " AND: " + controller.perceptron.testTwoParameters(0, 0));

        controller.createPerceptron(dataOR);
        controller.learningPerceptron(2);

        System.out.println("OR: ");
        System.out.println("TEST: x1: " + 0 + " x2: " + 0 + " OR: " + controller.perceptron.testTwoParameters(0, 0));
        System.out.println("TEST: x1: " + 1 + " x2: " + 0 + " OR: " + controller.perceptron.testTwoParameters(1, 0));
        System.out.println("TEST: x1: " + 0 + " x2: " + 1 + " OR: " + controller.perceptron.testTwoParameters(0, 1));
        System.out.println("TEST: x1: " + 1 + " x2: " + 1 + " OR: " + controller.perceptron.testTwoParameters(1, 1));

        controller.createPerceptron(dataNOT);
        controller.learningPerceptron(1);

        System.out.println("NOT->>>>>>>>>>>>>>>>>>>>>");
        System.out.println("TEST: x1: " + 1 + " NOT: " + controller.perceptron.testOneParameter(1));
        System.out.println("TEST: x1: " + 0 + " NOT: " + controller.perceptron.testOneParameter(0));

        // Rozwiązanie podproblemu

        controller.readInputData();
        controller.generateData(5,1,2);

        controller.createPerceptron();
        controller.learningPerceptron(2);

        System.out.println("FILMS->>>>>>>>>>>>>>>>>>>");
        System.out.println("TEST: x1: " + 7 + " x2: " + 7.6 + ": " + controller.perceptron.testTwoParameters(7, 7.6));
        System.out.println("TEST: x1: " + 7 + " x2: " + 8.2 + ": " + controller.perceptron.testTwoParameters(7, 8.2));
        System.out.println("TEST: x1: " + 7 + " x2: " + 6.4 + ": " + controller.perceptron.testTwoParameters(7, 6.4));
        System.out.println("TEST: x1: " + 7 + " x2: " + 5.8 + ": " + controller.perceptron.testTwoParameters(7, 5.8));
        System.out.println("TEST: x1: " + 7 + " x2: " + 9.2 + ": " + controller.perceptron.testTwoParameters(7, 9.2));
    }
}
