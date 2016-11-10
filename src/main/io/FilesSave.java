package main.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class FilesSave {

    private static long averageTime = 0;
    private static double averageEpochs = 0;

    public static void saveMse(int attempt, int epoch, double value){

        try (
                FileWriter fileWriter = new FileWriter("wyniki_1/MSE_per_epochs.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter out = new PrintWriter(bufferedWriter)
        ) {
            String addToFile = "Attempt number: " + attempt + "\n" + "Epoch number: " + epoch + "\n" + "MSE value: " + value;
            out.println(addToFile);

        } catch (IOException ex) {
            System.err.println("IOException: " + ex.getMessage());
        }
    }

    public static void saveMape(int attempt, int epoch, double value){

        try (
                FileWriter fileWriter = new FileWriter("wyniki_1/MAPE_per_epochs.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter out = new PrintWriter(bufferedWriter)
        ) {
            String addToFile = "Attempt number: " + attempt + "\n" + "Epoch number: " + epoch + "\n" + "MAPE value: " + value + "%";
            out.println(addToFile);

        } catch (IOException ex) {
            System.err.println("IOException: " + ex.getMessage());
        }
    }

    public static void saveTime(int attempt, long time){

            try (
                    FileWriter fileWriter = new FileWriter("wyniki_1/Time_learning.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    PrintWriter out = new PrintWriter(bufferedWriter)
            ) {
                String addToFile = "Attempt number: " + attempt + "\n" + "Time in nanoseconds: " + time;
                averageTime+=time;
                out.println(addToFile);

            } catch (IOException ex) {
                System.err.println("IOException: " + ex.getMessage());
            }
    }

    public static void saveNumberOfEpochs(int attempt, int numberOfEpochs){

        try (
                FileWriter fileWriter = new FileWriter("wyniki_1/Number_of_epochs.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter out = new PrintWriter(bufferedWriter)
        ) {
            String addToFile = "Attempt number: " + attempt + "\n" + "Number of epochs/iterations: " + numberOfEpochs;
            averageEpochs+=numberOfEpochs;
            out.println(addToFile);

        } catch (IOException ex) {
            System.err.println("IOException: " + ex.getMessage());
        }
    }

    public static void saveAverageNumberOfEpochs(int attempts){

        try (
                FileWriter fileWriter = new FileWriter("wyniki_1/Average_number_of_epochs.txt", false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter out = new PrintWriter(bufferedWriter)
        ) {
            String addToFile = "Average number of epochs after all attempts: " + averageEpochs/attempts;
            out.println(addToFile);

        } catch (IOException ex) {
            System.err.println("IOException: " + ex.getMessage());
        }
    }

    public static void saveAverageTime(int attempts){

        try (
                FileWriter fileWriter = new FileWriter("wyniki_1/Average_time_learning.txt", false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter out = new PrintWriter(bufferedWriter)
        ) {
            String addToFile = "Average time learning after all attempts: " + averageTime/attempts;
            out.println(addToFile);

        } catch (IOException ex) {
            System.err.println("IOException: " + ex.getMessage());
        }
    }
}
