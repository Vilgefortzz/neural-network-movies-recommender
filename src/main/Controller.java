package main;

import main.io.DataLoader;
import main.neurons.McCullochPittsNeuron;
import main.networks.Perceptron;

import java.util.ArrayList;

public class Controller {

    // Files which have cut ( to learn and validate )
    private ArrayList<String> moviesLearn;
    private ArrayList<String> moviesValidate;
    private ArrayList<String> users;

    private double[][] inputDataLearn;
    private double[][] inputDataValidate;

    // McCullochPitts

    public McCullochPittsNeuron mcCullochPittsNeuron;

    // Perceptron

    public Perceptron perceptron;

    public Controller() {

        this.moviesLearn = new ArrayList<>();
        this.moviesValidate = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public double[][] getInputDataValidate() {
        return inputDataValidate;
    }

    public void readInputData() {

        DataLoader file;

        file = new DataLoader("/data_set/learning_set/movies.dat");
        this.moviesLearn = file.getLines();

        file = new DataLoader("/data_set/validation_set/movies.dat");
        this.moviesValidate = file.getLines();

        file = new DataLoader("/data_set/learning_set/users.dat");
        this.users = file.getLines();

        // Finally moviesLearn + users and their rates = learningRuleWithBackPropagation set
    }

    public void generateLearningData(int numberOfMovies, int numberOfUsers, int parameters) {

        String[] moviesRatings = new String[numberOfMovies];
        String[] usersLikes = new String[numberOfUsers];
        inputDataLearn = new double[numberOfMovies][parameters+1]; // o jeden więcej gdyż jeszcze wartość oczekiwana

        for (int i=0;i<numberOfMovies;i++){

            String[] info1 = moviesLearn.get(i).split(" +");
            moviesRatings[i] = info1[3];
        }

        for (int i=0;i<numberOfUsers;i++){

            String[] info2 = users.get(i).split(" +");
            usersLikes[i] = info2[3];
        }

        int k=0;
        int index = 0;

        // Wartość oczekiwana jaka ma być
        for (int i = 0; i< inputDataLearn.length; i++){

            inputDataLearn[i][index++] = Double.parseDouble(usersLikes[k]);
            inputDataLearn[i][index++] = Double.parseDouble(moviesRatings[i]);

            if (Double.parseDouble(moviesRatings[i]) >= Double.parseDouble(usersLikes[k]))
                inputDataLearn[i][index] = 1;
            else
                inputDataLearn[i][index] = 0;

            index = 0;
        }
    }

    public void generateValidationData(int numberOfMovies, int numberOfUsers, int parameters) {

        String[] moviesRatings = new String[numberOfMovies];
        String[] usersLikes = new String[numberOfUsers];
        inputDataValidate = new double[numberOfMovies][parameters]; // normalnie nie ma oczekiwanej wartości

        for (int i = 0; i < numberOfMovies; i++) {

            String[] info1 = moviesValidate.get(i).split(" +");
            moviesRatings[i] = info1[3];
        }

        for (int i = 0; i < numberOfUsers; i++) {

            String[] info2 = users.get(i).split(" +");
            usersLikes[i] = info2[3];
        }

        int k = 0;
        int index = 0;

        // Wartości do testowania
        for (int i = 0; i < inputDataValidate.length; i++) {

            inputDataValidate[i][index++] = Double.parseDouble(usersLikes[k]);
            inputDataValidate[i][index] = Double.parseDouble(moviesRatings[i]);

            index = 0;
        }
    }

    public void createPerceptron() {
        perceptron = new Perceptron(inputDataLearn);
    }

    public void createPerceptron(double[][] inputData){
        perceptron = new Perceptron(inputData);
    }

    public void createMcCullochPittsNeuron() {
        mcCullochPittsNeuron = new McCullochPittsNeuron(inputDataLearn);
    }

    public void createMcCullochPittsNeuron(double[][] inputData){

        mcCullochPittsNeuron = new McCullochPittsNeuron(inputData);
    }

    public void learningMcCullohPittsNeuron(int parameters, int attempts){

        if (parameters == 1)
            mcCullochPittsNeuron.applyLearningRuleOneParametr();
        else if (parameters == 2)
            mcCullochPittsNeuron.applyLearningRuleTwoParameters(attempts);

    }

    public void learningPerceptron(int parameters, int attempts){

        if (parameters == 1)
            perceptron.applyLearningRuleOneParametr();
        else if (parameters == 2)
            perceptron.applyLearningRuleTwoParameters(attempts);
    }
}
