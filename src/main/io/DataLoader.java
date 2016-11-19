package main.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DataLoader {

    private ArrayList<String> lines;

    public DataLoader(String path) {

        try {
            readFile(path);
        } catch (IOException | NullPointerException e) {
            System.err.println("Loading file is failed!!");
            System.exit(0);
        }
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    private void readFile(String path) throws IOException, NullPointerException {

        lines = new ArrayList<>();

        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        getClass().getResourceAsStream(path)))
        ) {
            while (bufferedReader.ready()){
                lines.add(bufferedReader.readLine());
            }
            bufferedReader.close();
        }
    }
}
