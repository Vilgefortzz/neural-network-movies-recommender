package main;

import main.io.DataSet;
import main.networks.HebbianNetwork;

import java.util.ArrayList;

public class Main {

    // szybkość uczenia
    public static final double LEARNING_RATE = 0.6;

    public static void main(String[] args) {

        // Uczenie podstawowych bramek logicznych and, or, not - perceptron jest się w stanie nauczyć

//        double[][] dataAND = {{0, 0, 0}, {0, 1, 0}, {1, 0, 0}, {1, 1, 1}};
//        double[][] dataOR = {{0, 0, 0}, {0, 1, 1}, {1, 0, 1}, {1, 1, 1}};
//        double[][] dataNOT = {{0, 1}, {1, 0}};
//
//        Controller controller = new Controller();
//
////        controller.createMcCullochPittsNeuron(dataAND);
////        controller.learningMcCullohPittsNeuron(2,1);
////
////        System.out.println("AND->>>>>>>>>>>>>>>>>>>>>");
////        System.out.println("TEST: x1: " + 1 + " x2: " + 1 + " AND: " + controller.mcCullochPittsNeuron.testTwoParameters(1, 1));
////        System.out.println("TEST: x1: " + 0 + " x2: " + 1 + " AND: " + controller.mcCullochPittsNeuron.testTwoParameters(0, 1));
////        System.out.println("TEST: x1: " + 1 + " x2: " + 0 + " AND: " + controller.mcCullochPittsNeuron.testTwoParameters(1, 0));
////        System.out.println("TEST: x1: " + 0 + " x2: " + 0 + " AND: " + controller.mcCullochPittsNeuron.testTwoParameters(0, 0));
//
//        controller.createPerceptron(dataAND);
//        controller.learningPerceptron(2,1);
//
//        System.out.println("AND->>>>>>>>>>>>>>>>>>>>>");
//        System.out.println("TEST: x1: " + 1 + " x2: " + 1 + " AND: " + controller.perceptron.testTwoParameters(1, 1));
//        System.out.println("TEST: x1: " + 0 + " x2: " + 1 + " AND: " + controller.perceptron.testTwoParameters(0, 1));
//        System.out.println("TEST: x1: " + 1 + " x2: " + 0 + " AND: " + controller.perceptron.testTwoParameters(1, 0));
//        System.out.println("TEST: x1: " + 0 + " x2: " + 0 + " AND: " + controller.perceptron.testTwoParameters(0, 0));
//
//        controller.createPerceptron(dataOR);
//        controller.learningPerceptron(2,1);
//
//        System.out.println("OR: ");
//        System.out.println("TEST: x1: " + 0 + " x2: " + 0 + " OR: " + controller.perceptron.testTwoParameters(0, 0));
//        System.out.println("TEST: x1: " + 1 + " x2: " + 0 + " OR: " + controller.perceptron.testTwoParameters(1, 0));
//        System.out.println("TEST: x1: " + 0 + " x2: " + 1 + " OR: " + controller.perceptron.testTwoParameters(0, 1));
//        System.out.println("TEST: x1: " + 1 + " x2: " + 1 + " OR: " + controller.perceptron.testTwoParameters(1, 1));
//
//        controller.createPerceptron(dataNOT);
//        controller.learningPerceptron(1,1);
//
//        System.out.println("NOT->>>>>>>>>>>>>>>>>>>>>");
//        System.out.println("TEST: x1: " + 1 + " NOT: " + controller.perceptron.testOneParameter(1));
//        System.out.println("TEST: x1: " + 0 + " NOT: " + controller.perceptron.testOneParameter(0));

        // Rozwiązanie podproblemu

        Controller controller = new Controller();

        controller.readInputData();

        // Wygenerowanie zestawu uczącego z pliku
        controller.generateLearningData(175,1,2); // ilość filmów, ilość użytkowników, ilość parametrów

        // Uczenie perceptronu
        controller.createPerceptron();
        controller.learningPerceptron(2,50); // ilość parametrów, ilość prób - powtarzanie uczenia

        // Wygenerowanie zestawu danych walidujących z pliku
        controller.generateValidationData(75,1,2);

        // Walidacja/Testowanie sieci przy użyciu zestawu danych walidujących z pliku

        double[][] validation_data = controller.getInputDataValidate();

        System.out.println("<<<<<<<<<<<<<<<< FILMS VALIDATION - RECOMMENDATION >>>>>>>>>>>>>>>>>");
        int test_number = 1;

        for (int i=0;i<validation_data.length;i++){

            double signal = controller.perceptron.testValidation(validation_data[i][0], validation_data[i][1]);
            String respond = ""; // recommended or not

            if (signal == 1)
                respond = "Recommended";
            else if (signal == 0)
                respond = "Not recommended";

            System.out.println("TEST " + test_number + ": " + "x1: " + validation_data[i][0] + " x2: " + validation_data[i][1] + ": " + respond);
            test_number++;
        }


        // ---------------------------------------------------------------------------------------------------------------------

        // Sieć neuronowa

        // Schemat: 1-2-1

        // 1.ilość neuronów na warstwie wejściowej - 2 wejścia -> 2 neurony
        // 2.ilość neuronów na warstwie ukrytej,
        // 3.ilość neuronów na warstwie wyjściowej - 1 wyjście -> 1 neuron
        // 4.liczba warstw ukrytych

//        NeuralNetwork neuralNetwork = new NeuralNetwork(2, 50, 1, 2);
//
//        // Sieć neuronowa - różne metody uczenia sieci
//
//        // Uczenie bramki logicznej XOR - sprawdzenie działania sieci wielowarstwowej
//        // 1 perceptron nie jest w stanie się nauczyć
//
//        double[][] dataXOR = {{0, 0, 0}, {0, 1, 1}, {1, 0, 1}, {1, 1, 0}};
//
//        double[][] inputData = {{0,0},{0,1},{1,0},{1,1}};
//        double[][] expectedData ={{0},{1},{1},{0}};
//
//
//        neuralNetwork.learning(inputData, expectedData, 50);
//        //neuralNetwork.learningHebb(inputData, 5);
//        neuralNetwork.testValidation(inputData);

        // Uczenie Hebba bez nauczyciela

//        double x,y;
//        ArrayList<DataSet> records = new ArrayList<>();
//
//        // Uczenie
//
//        records.add(new DataSet(0.47,0.9));
//        //records.add(new DataSet(1,-1));
//        records.add(new DataSet(0.8,0.2));
//        //records.add(new DataSet(-1,-1));
//        HebbianNetwork h = new HebbianNetwork();
//        h.train(records, 50);
//
//        // Walidacja
//
//        x = 0.5;
//        y = 0.78;
//
//        System.out.println("OUTPUT:"+h.test(x,y));



        // Sieci Kohonena




    }
}
