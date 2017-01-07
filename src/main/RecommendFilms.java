package main;

public class RecommendFilms {

    public static void main(String[] args) {

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
    }
}
