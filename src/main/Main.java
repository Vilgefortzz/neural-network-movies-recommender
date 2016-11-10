package main;

public class Main {

    public static void main(String[] args) {

        // Uczenie podstawowych bramek logicznych and, or, not

//        double[][] dataAND = {{0, 0, 0}, {0, 1, 0}, {1, 0, 0}, {1, 1, 1}};
//        double[][] dataOR = {{0, 0, 0}, {0, 1, 1}, {1, 0, 1}, {1, 1, 1}};
//        double[][] dataNOT = {{0, 1}, {1, 0}};
//
//        Controller controller = new Controller();
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
        controller.generateLearningData(100,1,2); // ilość filmów, ilość użytkowików, ilość parametrów

        // Uczenie perceptronu
        controller.createPerceptron();
        controller.learningPerceptron(2,50); // ilość parametrów, ilość prób - powtarzanie uczenia

        // Wygenerowanie zestawu danych walidujących z pliku
        controller.generateValidationData(30,1,2);

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
    }
}
