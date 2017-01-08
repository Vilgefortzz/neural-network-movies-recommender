package main;

import main.hopfield.networks.HopfieldNetwork;

import static main.io.DataSave.saveTime;

public class HopfieldApplication {

    public static void main(String[] args) {

        //dane wejsciowe, wektory będące macierzą(wzorem), każdy wiersz osobnym wzorem
        int[][] data = {{-1,1,1,-1,1,-1,-1,1,1,1,1,1,1,-1,-1,1},
                        {1,1,-1,-1,1,-1,1,-1,1,1,-1,-1,1,-1,1,-1},
                        {-1,1,1,1,-1,-1,1,-1,-1,-1,1,-1,-1,-1,1,-1},
                        {-1,1,1,1,1,-1,-1,-1,1,-1,-1,-1,-1,1,1,1}};

        // Wzory w formie zobrazowanej

        int numberOfPatterns = data.length;

        for (int i=0;i<numberOfPatterns;i++){

            System.out.println();
            System.out.println("Pattern number " + (i+1) + " :");
            printPicture(data[i]);
        }

        HopfieldNetwork hopfieldNetwork = new HopfieldNetwork(data[0].length);

        // Start stoper - time learning for one attempt
        long start_time = System.currentTimeMillis();

        hopfieldNetwork.setWeights(data);

        // Uczenie sieci
        int[] input = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,1,-1}; // wejscie do gotowej sieci, niekompletny wzor do odtworzenia

        int[] output = hopfieldNetwork.returnOutput(input);  // wyjscie, odpowiedz sieci

        long end_time = System.currentTimeMillis();
        long difference = end_time - start_time; // time in miliseconds

        System.out.println(difference + " miliseconds");

        saveTime("wyniki_6", 1, difference);

        System.out.println();
        System.out.println("Input:");
        printPicture(input);

        System.out.println();
        System.out.println("Output:");
        printPicture(output);

    }

    private static void printPicture(int[] picture){

        int width = (int)Math.sqrt((double)picture.length);

        for(int i=0;i<picture.length;i++){
            if(i%width==0){
                System.out.println();
            }
            if(picture[i]==1) System.out.print(" O");
            else System.out.print(" X");
        }
        System.out.println();
    }
}