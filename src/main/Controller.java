package main;

import main.io.FileReader;
import main.neurons.McCullochPittsNeuron;
import main.neurons.Perceptron;

import java.util.ArrayList;

public class Controller{

    private FileReader file;
    private ArrayList<String> movies;
    private ArrayList<String> ratings;
    private ArrayList<String> users;

    private double[][] inputData;

    // McCullochPitts

    public McCullochPittsNeuron mcCullochPittsNeuron;

    // Perceptron

    public Perceptron perceptron;

    public Controller() {

        this.movies = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void readInputData() {

        file = new FileReader("movies.dat");
        movies = file.getLines();

        file = new FileReader("ratings.dat");
        ratings = file.getLines();

        file = new FileReader("users.dat");
        users = file.getLines();
    }

    public void generateData(int moviesNumber, int usersNumber, int parameters) {

        String[] moviesRatings = new String[moviesNumber];
        String[] usersLikes = new String[usersNumber];
        inputData = new double[moviesNumber][parameters+1]; // o jeden więcej gdyż jeszcze wartość oczekiwana

        for (int i=0;i<moviesNumber;i++){

            String[] info1 = movies.get(i).split("::");
            moviesRatings[i] = info1[3];
        }

        for (int i=0;i<usersNumber;i++){

            String[] info2 = users.get(i).split("::");
            usersLikes[i] = info2[1];
        }

        int k=0;
        int index = 0;

        for (int i=0; i<inputData.length; i++){

            inputData[i][index++] = Double.parseDouble(usersLikes[k]);
            inputData[i][index++] = Double.parseDouble(moviesRatings[i]);

            if (Double.parseDouble(moviesRatings[i]) >= 7.0)
                inputData[i][index] = 1.0;
            else
                inputData[i][index] = 0.0;

            index = 0;
        }
    }

    public void createPerceptron() {
        perceptron = new Perceptron(inputData);
    }

    public void createPerceptron(double[][] inputData){

        perceptron = new Perceptron(inputData);
    }

    public void createMcCullochPittsNeuron() {
        mcCullochPittsNeuron = new McCullochPittsNeuron(inputData);
    }

    public void learningMcCullohPittsNeuron(int parameters){

        if (parameters == 1)
            mcCullochPittsNeuron.applyLearningRuleOneParametr();
        else if (parameters == 2)
            mcCullochPittsNeuron.applyLearningRuleTwoParameters();

    }

    public void learningPerceptron(int parameters){

        if (parameters == 1)
            perceptron.applyLearningRuleOneParametr();
        else if (parameters == 2)
            perceptron.applyLearningRuleTwoParameters();
    }
}
