package main;

import main.networks.NeuralNetwork;

public class Main {

    public static void main(String[] args) {

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

//        controller.createPerceptron(dataAND);
//        controller.learningPerceptron(2,1);

//        System.out.println("AND->>>>>>>>>>>>>>>>>>>>>");
//        System.out.println("TEST: x1: " + 1 + " x2: " + 1 + " AND: " + controller.perceptron.testTwoParameters(1, 1));
//        System.out.println("TEST: x1: " + 0 + " x2: " + 1 + " AND: " + controller.perceptron.testTwoParameters(0, 1));
//        System.out.println("TEST: x1: " + 1 + " x2: " + 0 + " AND: " + controller.perceptron.testTwoParameters(1, 0));
//        System.out.println("TEST: x1: " + 0 + " x2: " + 0 + " AND: " + controller.perceptron.testTwoParameters(0, 0));

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

//        Controller controller = new Controller();
//
//        controller.readInputData();
//
//        // Wygenerowanie zestawu uczącego z pliku
//        controller.generateLearningData(100,1,2); // ilość filmów, ilość użytkowików, ilość parametrów
//
//        // Uczenie perceptronu
//        controller.createPerceptron();
//        controller.learningPerceptron(2,50); // ilość parametrów, ilość prób - powtarzanie uczenia
//
//        // Wygenerowanie zestawu danych walidujących z pliku
//        controller.generateValidationData(30,1,2);
//
//        // Walidacja/Testowanie sieci przy użyciu zestawu danych walidujących z pliku
//
//        double[][] validation_data = controller.getInputDataValidate();
//
//        System.out.println("<<<<<<<<<<<<<<<< FILMS VALIDATION - RECOMMENDATION >>>>>>>>>>>>>>>>>");
//        int test_number = 1;
//
//        for (int i=0;i<validation_data.length;i++){
//
//            double signal = controller.perceptron.testValidation(validation_data[i][0], validation_data[i][1]);
//            String respond = ""; // recommended or not
//
//            if (signal == 1)
//                respond = "Recommended";
//            else if (signal == 0)
//                respond = "Not recommended";
//
//            System.out.println("TEST " + test_number + ": " + "x1: " + validation_data[i][0] + " x2: " + validation_data[i][1] + ": " + respond);
//            test_number++;
//        }


        // Sieć neuronowa

        // 1.ilość wejść,
        // 2.ilość neuronów na warstwie ukrytej,
        // 3.ilość wyjść oczekiwanych,
        // 4.liczba warstw ukrytych

        NeuralNetwork neuralNetwork = new NeuralNetwork(2,50,1,2);

        // Uczenie bramki logicznej XOR - sprawdzenie działania sieci wielowarstwowej
        // 1 perceptron nie jest w stanie się nauczyć

        double[][] dataXOR = {{0, 0, 0}, {0, 1, 1}, {1, 0, 1}, {1, 1, 0}};

        double[][] inputData = {{0,0},{0,1},{1,0},{1,1}};
        double[][] expectedData ={{0},{1},{1},{0}};


        learnAndTestNetwork(neuralNetwork, inputData, expectedData);

    }

    private static void learnAndTestNetwork(NeuralNetwork net, double[][] inputData, double[][] expectedData){

        int i = 0, t = 0;
        boolean isDone1 = false, isDone2 = false;

        double acceptableError = 0.01; // akceptowalny błąd obliczeń

        // t - ilość iteracji ( można ustalić maksymalną ilość iteracji po której sieć albo się nauczy albo nie )

        while (!isDone1 && t<500) {

            t++;
            isDone1 = true;

            for (int j = 0; j < expectedData.length; j++) {

                i = 0;
                isDone2 = false;

                while (!isDone2 && i < 1000) {

                    i++;
                    isDone2 = true;

                    net.learning(expectedData[j], inputData[j]);

                    if ((Math.abs(expectedData[j][0] - net.computeOutput(inputData[j])[0]) > acceptableError)) {
                        isDone2 = false;
                    }
                }
            }

            if ((Math.abs(expectedData[0][0] - net.computeOutput(inputData[0])[0]) > acceptableError)||(Math.abs(expectedData[1][0] - net.computeOutput(inputData[1])[0]) > acceptableError)||(Math.abs(expectedData[2][0] - net.computeOutput(inputData[2])[0]) > acceptableError)||(Math.abs(expectedData[3][0] - net.computeOutput(inputData[3])[0]) > acceptableError)) {
                isDone1 = false;
            }
        }

        System.out.println("LAST ITERATION: " + t);
        System.out.println("XOR: " + "x1: " + 0 + " x2: " + 0 + " -> " + net.computeOutput(inputData[0])[0] + "x1: " + 0 + " x2: " + 1 + " -> " + net.computeOutput(inputData[1])[0] + "x1: " + 1 + " x2: " + 0 + " -> " + net.computeOutput(inputData[2])[0] + "x1: " + 1 + " x2: " + 1 + " -> " + net.computeOutput(inputData[3])[0]);
    }
}
