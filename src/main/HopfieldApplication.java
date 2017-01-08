package main;

import main.hopfield.networks.HopfieldNetwork;

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

        hopfieldNetwork.setWeights(data);
        int[] input = {-1,1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,1,-1}; // wejscie do gotowej sieci, niekompletny wzor do odtworzenia
        int[] output = hopfieldNetwork.returnOutput(input);  // wyjscie, odpowiedz sieci

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