package main.io;

import java.io.*;
import java.util.ArrayList;

public class DataLoader {

    public static ArrayList<String> readFile(String path) {

        ArrayList<String> records = new ArrayList<>();

        try {
            try (
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(path))
            ) {
                while (bufferedReader.ready()){
                    records.add(bufferedReader.readLine());
                }
                bufferedReader.close();
            }

        } catch (IOException | NullPointerException e) {
            System.err.println("Loading file is failed!!");
            System.exit(0);
        }

        return records;
    }

    private static String read(String path) {

        String text = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();

            while (line != null) {
                text += line;
                text += "\n";
                line = br.readLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Loading file is failed!!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static ArrayList<ArrayList<Double>> datStrToArrayList (String path) {
        String inputStr = read(path);
        ArrayList<ArrayList<Double>> input = new ArrayList<>();

        String[] separateInput = inputStr.split("\n");
        String[] vectors;
        String line;
        String vector;
        int index = 0;

        // Going through vectors
        for (int j = 0; j < separateInput.length; j++) {
            line = separateInput[j];

            input.add(new ArrayList<>());
            vectors = line.split("\t");

            // Going through the values of a vector
            for (int i = 1; i < vectors.length; i++) {
                vector = vectors[i];
                input.get(index).add(Double.parseDouble(vector));
            }
            index++;
        }

        return input;
    }
}